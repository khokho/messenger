package ge.ttopu18alkhok18.messenger.profile

import android.net.Uri

interface IProfilePresenter {

    // for view
    fun fetchProfile()
    fun signOut()
    fun updateJob(job: String)
    fun updateImage(imageUri: Uri)
    fun detachView()

    // for interactor
    fun profileFetched(profile: UserProfile)

}