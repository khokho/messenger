package ge.ttopu18alkhok18.messenger.chat

import ge.ttopu18alkhok18.messenger.models.Message
import ge.ttopu18alkhok18.messenger.models.User

interface IChatView {
    fun adapterList(messages: List<Message>)
    fun newMessageArrived()
    fun profileFetched(user: User)
}