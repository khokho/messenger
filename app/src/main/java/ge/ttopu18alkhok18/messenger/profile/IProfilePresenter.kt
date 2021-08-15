package ge.ttopu18alkhok18.messenger.profile

import android.net.Uri
import ge.ttopu18alkhok18.messenger.models.User

interface IProfilePresenter {

    // for view
    fun fetchProfile()
    fun signOut()
    fun updateJob(job: String)
    fun updateImage(imageUri: Uri)
    fun detachView()

    // for interactor
    fun profileFetched(profile: User)
    fun uploadSucceeded()
    fun uploadFailed()


}