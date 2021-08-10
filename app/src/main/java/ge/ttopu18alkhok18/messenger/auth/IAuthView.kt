package ge.ttopu18alkhok18.messenger.auth

import com.google.firebase.auth.FirebaseUser
import java.lang.Exception

interface IAuthView {
    fun signInSuccessful(currentUser: FirebaseUser?) {}
    fun signInFail(exception: Exception?) {}

    fun signUpSuccessful(currentUser: FirebaseUser?) {}
    fun signUpFail(exception: Exception?) {}
}
