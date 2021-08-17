package ge.ttopu18alkhok18.messenger.chat

import ge.ttopu18alkhok18.messenger.models.Message

interface IChatPresenter {
    fun sendMessage(message: Message)
    fun startListeningToMessages(toUsername: String, chatKey: String)
}