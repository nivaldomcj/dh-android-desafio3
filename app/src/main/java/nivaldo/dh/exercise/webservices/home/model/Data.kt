package nivaldo.dh.exercise.webservices.home.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(var results: List<Result>?) : Parcelable