package nivaldo.dh.exercise.webservices.home.model.business

import nivaldo.dh.exercise.webservices.home.model.Comics
import nivaldo.dh.exercise.webservices.home.model.repository.HomeRepository
import nivaldo.dh.exercise.webservices.shared.api.ApiResponse
import nivaldo.dh.exercise.webservices.shared.constant.MarvelConstants

class HomeBusiness {

    private val repository by lazy {
        HomeRepository()
    }

    suspend fun getComics(): ApiResponse {
        return when (val response = repository.getComics(MarvelConstants.SERIES_AMAZING_SPIDER_MAN, true)) {
            is ApiResponse.Failure -> response
            is ApiResponse.Success -> {
                val comics = response.data as Comics

                comics.data?.results = comics.data?.results?.map {
                    // format thumbnail path
                    it.mThumbnailPath = "${it.thumbnail?.path}.${it.thumbnail?.extension}"

                    // format image path
                    it.mImagePath = "${it.images?.first()?.path}.${it.images?.first()?.extension}"

                    // format price
                    it.mPrice = "$ ${it.prices?.first()?.price}"

                    // format thumbnail title
                    it.mThumbnailTitle = "#${it.issueNumber}"

                    // fixes url path (avoid insecure internet errors)
                    it.mThumbnailPath = it.mThumbnailPath?.replace("http", "https")
                    it.mImagePath = it.mImagePath?.replace("http", "https")

                    it
                }

                return ApiResponse.Success(comics)
            }
        }
    }

}