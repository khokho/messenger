package ge.ttopu18alkhok18.messenger.util

import com.google.firebase.auth.FirebaseUser

val FirebaseUser.username: String
    get() {
        return this.email!!.split("@")[0]
    }
