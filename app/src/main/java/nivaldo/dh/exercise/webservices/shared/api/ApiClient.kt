package nivaldo.dh.exercise.webservices.shared.api

import nivaldo.dh.exercise.webservices.shared.constant.ApiConstants
import nivaldo.dh.exercise.webservices.shared.constant.ApiTokenConstants
import nivaldo.dh.exercise.webservices.shared.service.MarvelService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.security.MessageDigest
import java.util.concurrent.TimeUnit

object ApiClient {

    val marvelService: MarvelService = getMarvelApiClient().create(MarvelService::class.java)

    private fun getMarvelApiClient(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .client(getMarvelInterceptorClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private fun getMarvelInterceptorClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor { chain ->
                    val timestamp = System.currentTimeMillis().toString()

                    val url = chain.request().url.newBuilder()
                            .addQueryParameter(ApiConstants.PARAM_NAME_TIMESTAMP, timestamp)
                            .addQueryParameter(ApiConstants.PARAM_NAME_PUBLIC_KEY, ApiTokenConstants.PUBLIC_KEY)
                            .addQueryParameter(ApiConstants.PARAM_NAME_HASH, getHashParameter(timestamp))
                            .build()

                    val request = chain.request()
                            .newBuilder()
                            .url(url)
                            .build()

                    chain.proceed(request)
                }
                .build()
    }

    private fun getHashParameter(timestamp: String): String {
        val md5 = MessageDigest.getInstance("MD5")
        val msg = timestamp + ApiTokenConstants.PRIVATE_KEY + ApiTokenConstants.PUBLIC_KEY

        return BigInteger(1, md5.digest(msg.toByteArray())).toString(16).padStart(32, '0')
    }

}