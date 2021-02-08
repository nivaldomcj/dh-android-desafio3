package nivaldo.dh.exercise.webservices.auth.model.business

import nivaldo.dh.exercise.webservices.auth.model.Splash
import nivaldo.dh.exercise.webservices.auth.model.repository.SplashRepository

class SplashBusiness {

    private val repository by lazy {
        SplashRepository()
    }

    fun getSplashResult(): Splash {
        return repository.getSplashResult()
    }

}