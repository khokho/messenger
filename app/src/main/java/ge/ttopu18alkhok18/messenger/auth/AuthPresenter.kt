package ge.ttopu18alkhok18.messenger.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import java.lang.Exception

class AuthPresenter(var view: IAuthView?): IAuthPresenter {

    var interactor: AuthInteractor = AuthInteractor(this)

    override fun signIn(auth: FirebaseAuth, username: String, password: String) {
        interactor.signIn(auth, username, password)
    }

    override fun singUp(auth: FirebaseAuth, db: FirebaseDatabase, username: String, password: String, job: String) {
        interactor.singUp(auth, db, username, password, job)
    }

    override fun signInSuccessful(currentUser: FirebaseUser?) {
        view?.signInSuccessful(currentUser)
    }

    override fun signInFail(exception: Exception?) {
        view?.signInFail(exception)
    }

    override fun signUpSuccessful(currentUser: FirebaseUser?) {
        view?.signUpSuccessful(currentUser)
    }

    override fun signUpFail(exception: Exception?) {
        view?.signUpFail(exception)
    }
}