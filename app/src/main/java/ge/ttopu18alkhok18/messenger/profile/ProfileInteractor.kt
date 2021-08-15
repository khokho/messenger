package ge.ttopu18alkhok18.messenger.profile

import android.net.Uri
import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import ge.ttopu18alkhok18.messenger.models.User
import ge.ttopu18alkhok18.messenger.util.username


class ProfileInteractor(val presenter: IProfilePresenter) {

    // this is supposed to stay same during single interactor
    val username = Firebase.auth.currentUser!!.username


    fun fetchProfile() {
        Firebase.database.getReference("users").child(username).get()
            .addOnSuccessListener {
                val user = it.getValue<User>()!!
                presenter.profileFetched(user)
            }
    }

    fun updateJob(job: String) {
        Firebase.database.getReference("jobs").child(username).setValue(job)
            .addOnSuccessListener {
                presenter.uploadSucceeded()
            }
            .addOnFailureListener {
                presenter.uploadFailed()
            }
    }

    fun updateImage(imageUri: Uri) {
        val profileRef = Firebase.storage.reference.child("profiles/${username}")
        val uploadTask = profileRef.putFile(imageUri)
        // Register observers to listen for when the download is done or if it fails
        uploadTask.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                profileRef.downloadUrl.addOnSuccessListener {
                    Firebase.database.getReference("users").child(username)
                        .child("profilePicURL").setValue(it.toString())
                        .addOnSuccessListener {
                            presenter.uploadSucceeded()
                        }.addOnFailureListener {
                            presenter.uploadFailed()
                        }
                }.addOnFailureListener {
                    presenter.uploadFailed()
                }
                Log.v("profile_fragment", "upload succeeded")
            } else {
                Log.v("profile_fragment", "upload failed")
                presenter.uploadFailed()
            }
        }

    }

}