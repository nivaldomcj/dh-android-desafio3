package nivaldo.dh.exercise.webservices.home.model

data class Result(
        // API attributes
        val id: Int?,
        val description: String?,
        val title: String?,
        val pageCount: Int?,
        val thumbnail: Thumbnail?,
        val issueNumber: Double?,
        val images: List<Image>?,
        val dates: List<Date>?,
        val prices: List<Price>?,

        // APP attributes
        var mThumbnailPath: String?,
        var mThumbnailTitle: String?,
        var mImagePath: String?,
        var mPrice: String?
)
