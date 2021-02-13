package nivaldo.dh.exercise.webservices.home.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(val extension: String?,
                 val path: String?) : Parcelable