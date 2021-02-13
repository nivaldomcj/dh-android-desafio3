package nivaldo.dh.exercise.webservices.shared.api

sealed class ApiResponse {

    class Success(val data: Any?) : ApiResponse()
    class Failure(val error: String?) : ApiResponse()

}