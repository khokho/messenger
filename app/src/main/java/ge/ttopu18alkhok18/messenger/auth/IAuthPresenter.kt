package ge.ttopu18alkhok18.messenger.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import java.lang.Exception

interface IAuthPresenter {
    // for view
    fun signIn(auth: FirebaseAuth, username: String, password: String)
    fun singUp(auth: FirebaseAuth, db: FirebaseDatabase, username: String, password: String, job: String)

    // for interactor
    fun signInSuccessful(currentUser: FirebaseUser?)
    fun signInFail(exception: Exception?)

    fun signUpSuccessful(currentUser: FirebaseUser?)
    fun signUpFail(exception: Exception?)
}