package ge.ttopu18alkhok18.messenger.auth

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase

class AuthInteractor(var presenter: IAuthPresenter) {

    companion object {
        const val MAIL_SUFFIX = "@mess.com"
        const val TAG = "auth_interactor"
    }

    fun signIn(auth: FirebaseAuth, username: String, password: String) {
        auth.signInWithEmailAndPassword(username+MAIL_SUFFIX, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                    presenter.signInSuccessful(auth.currentUser)
                } else {
                    Log.w(TAG, "signInWithEmail:failure", it.exception)
                    presenter.signInFail(it.exception)
                }
            }
    }

    fun singUp(auth: FirebaseAuth, db: FirebaseDatabase, username: String, password: String, job: String) {
        Log.d(TAG, username+MAIL_SUFFIX)
        auth.createUserWithEmailAndPassword(username+MAIL_SUFFIX, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail:success")
                    presenter.signUpSuccessful(auth.currentUser)
                    db.getReference("jobs").child(username).setValue(job)
                } else {
                    Log.w(TAG, "createUserWithEmail:failure", it.exception)
                    presenter.signUpFail(it.exception)
                }
            }
    }

}