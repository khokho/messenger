package ge.ttopu18alkhok18.messenger.chat

import ge.ttopu18alkhok18.messenger.models.Message
import ge.ttopu18alkhok18.messenger.models.User

interface IChatPresenter {
    fun sendMessage(message: Message)
    fun start(toUsername: String, chatKey: String)

    fun newMessageArrived(message: Message) // could be from me
    fun userFetched(user: User)

    fun detachView()
}