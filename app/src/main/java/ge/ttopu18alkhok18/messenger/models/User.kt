package ge.ttopu18alkhok18.messenger.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    val username: String? = null,
    val job: String? = null,
    val profilePicURL: String? = null
)