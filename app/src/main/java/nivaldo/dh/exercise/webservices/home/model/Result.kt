package nivaldo.dh.exercise.webservices.home.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Result(val id: Int?,
                  val description: String?,
                  val title: String?,
                  val pageCount: Int?,
                  val thumbnail: Thumbnail?,
                  val issueNumber: Double?,
                  val images: List<Image>?,
                  val dates: List<Date>?,
                  val prices: List<Price>?,

                  var mThumbnailPath: String?,
                  var mThumbnailTitle: String?,
                  var mImagePath: String?,
                  var mPrice: String?,
                  var mPublishedDate: String?) : Parcelable
