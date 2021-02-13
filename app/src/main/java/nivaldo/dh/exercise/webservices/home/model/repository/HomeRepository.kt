package nivaldo.dh.exercise.webservices.home.model.repository

import nivaldo.dh.exercise.webservices.shared.api.ApiClient
import nivaldo.dh.exercise.webservices.shared.api.ApiResponse

class HomeRepository {

    suspend fun getComics(series: Int, noVariants: Boolean): ApiResponse {
        return try {
            val response = ApiClient.marvelService.getComics(series, noVariants)

            if (response.isSuccessful)
                ApiResponse.Success(response.body())
            else
                ApiResponse.Failure(response.code().toString())
        } catch (e: Exception) {
            ApiResponse.Failure(e.localizedMessage)
        }
    }

}