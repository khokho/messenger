package ge.ttopu18alkhok18.messenger.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Chat(
    val chatKey: String? = null,
    val from: String? = null,
    val to: String? = null,
    val lastMessage: Message? = null

)