package nivaldo.dh.exercise.webservices.home.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nivaldo.dh.exercise.webservices.home.model.Comics
import nivaldo.dh.exercise.webservices.home.model.business.HomeBusiness
import nivaldo.dh.exercise.webservices.shared.api.ApiResponse

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    val onGetComicsResultSuccess: MutableLiveData<Comics> = MutableLiveData()
    val onGetComicsResultFailure: MutableLiveData<String> = MutableLiveData()

    private val business by lazy {
        HomeBusiness()
    }

    fun getComics() {
        viewModelScope.launch {
            when (val response = business.getComics()) {
                is ApiResponse.Success -> onGetComicsResultSuccess.postValue(response.data as Comics)
                is ApiResponse.Failure -> onGetComicsResultFailure.postValue(response.error)
            }
        }
    }

}