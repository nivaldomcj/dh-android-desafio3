package nivaldo.dh.exercise.webservices.home.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Date(val date: String?,
                val type: String?) : Parcelable