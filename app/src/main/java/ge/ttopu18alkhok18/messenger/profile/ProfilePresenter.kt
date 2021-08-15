package ge.ttopu18alkhok18.messenger.profile

import android.net.Uri
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ge.ttopu18alkhok18.messenger.models.User

class ProfilePresenter(var view: IProfileView?): IProfilePresenter {

    var interactor = ProfileInteractor(this)

    override fun fetchProfile() {
        interactor.fetchProfile()
    }

    override fun signOut() {
        Firebase.auth.signOut()
    }

    override fun updateJob(job: String) {
        interactor.updateJob(job)
    }

    override fun updateImage(imageUri: Uri) {
        interactor.updateImage(imageUri)
    }

    override fun profileFetched(profile: User) {
        view?.displayProfile(profile)
    }

    override fun uploadSucceeded() {
        view?.uploadSucceeded()
    }

    override fun uploadFailed() {
        view?.uploadFailed()
    }

    override fun detachView() {
        view = null
    }
}