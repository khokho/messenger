package ge.ttopu18alkhok18.messenger.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Message(
    val from: String? =  null,
    val text: String? = null,
    val timestamp: Long? = null
)