package nivaldo.dh.exercise.webservices.home.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Price(val price: Double?,
                 val type: String) : Parcelable