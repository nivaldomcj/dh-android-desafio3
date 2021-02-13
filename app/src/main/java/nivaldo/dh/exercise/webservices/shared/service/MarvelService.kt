package nivaldo.dh.exercise.webservices.shared.service

import nivaldo.dh.exercise.webservices.home.model.Comics
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {

    @GET("comics")
    suspend fun getComics(@Query("series") series: Int,
                          @Query("noVariants") noVariants: Boolean): Response<Comics>

}