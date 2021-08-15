package ge.ttopu18alkhok18.messenger.profile

import android.net.Uri
import android.util.Log
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import ge.ttopu18alkhok18.messenger.util.username


class ProfileInteractor(val presenter: IProfilePresenter) {

    // this is supposed to stay same during single interactor
    val username = Firebase.auth.currentUser!!.username


    fun fetchProfile() {
        val profileRef = Firebase.storage.reference.child("profiles/${username}")
        var job: String? = null
        var imageUri: Uri? = null

        val imageUriTask = profileRef.downloadUrl.addOnSuccessListener {
            Log.v("profile_fragment", it.toString())
            imageUri = it
        }

        val jobTask = Firebase.database.getReference("jobs").child(username).get().addOnSuccessListener {
            job = it.getValue<String>()
        }

        Tasks.whenAllComplete(jobTask, imageUriTask).addOnCompleteListener {
            presenter.profileFetched(UserProfile(
                username,
                imageUri,
                job!! // since jobtask is complete and should have fetched value :)
            ))
        }
    }

    fun updateJob(job: String) {
        Firebase.database.getReference("jobs").child(username).setValue(job)
    }

    fun updateImage(imageUri: Uri) {
        val profileRef = Firebase.storage.reference.child("profiles/${username}")
        val uploadTask = profileRef.putFile(imageUri)
        // Register observers to listen for when the download is done or if it fails
        uploadTask.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.v("profile_fragment", "upload succeeded")
            } else {
                Log.v("profile_fragment", "upload failed")
            }
        }
    }

}