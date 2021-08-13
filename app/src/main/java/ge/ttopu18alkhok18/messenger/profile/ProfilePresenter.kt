package ge.ttopu18alkhok18.messenger.profile

import android.net.Uri
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

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

    override fun profileFetched(profile: UserProfile) {
        view?.displayProfile(profile)
    }

    override fun detachView() {
        view = null
    }
}