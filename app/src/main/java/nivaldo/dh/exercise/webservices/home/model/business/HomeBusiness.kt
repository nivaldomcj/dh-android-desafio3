package nivaldo.dh.exercise.webservices.home.model.business

import nivaldo.dh.exercise.webservices.home.model.Comics
import nivaldo.dh.exercise.webservices.home.model.repository.HomeRepository
import nivaldo.dh.exercise.webservices.shared.api.ApiResponse
import nivaldo.dh.exercise.webservices.shared.constant.MarvelServiceConstants
import java.text.SimpleDateFormat
import java.util.*

class HomeBusiness {

    private val repository by lazy {
        HomeRepository()
    }

    suspend fun getComics(): ApiResponse {
        return when (val response = repository.getComics(MarvelServiceConstants.SERIES_ID_AMAZING_SPIDER_MAN, false)) {
            is ApiResponse.Failure -> response
            is ApiResponse.Success -> {
                val comics = response.data as Comics

                comics.data?.results = comics.data?.results?.map { result ->
                    // format thumbnail path
                    result.mThumbnailPath = "${result.thumbnail?.path}.${result.thumbnail?.extension}"
                    result.mThumbnailPath = result.mThumbnailPath?.replace("http", "https")

                    // format image path
                    result.mImagePath = "${result.images?.first()?.path}.${result.images?.first()?.extension}"
                    result.mImagePath = result.mImagePath?.replace("http", "https")

                    // format price
                    result.mPrice = "$ ${result.prices?.first()?.price}"

                    // format published date
                    result.dates?.first()?.date?.let { date ->
                        val isoDateFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ", Locale.ENGLISH)
                        val fullDateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH)

                        isoDateFormat.parse(date)?.let { parsedDate ->
                            result.mPublishedDate = fullDateFormat.format(parsedDate)
                        }
                    }

                    // format thumbnail title
                    result.mThumbnailTitle = "#${result.issueNumber}"

                    return@map result
                }

                return ApiResponse.Success(comics)
            }
        }
    }

}