package ge.ttopu18alkhok18.messenger.startchat

import ge.ttopu18alkhok18.messenger.models.Chat

interface IStartChatView {
    fun seachResultsUpdated()
    fun chatCreated(chat: Chat)
    fun requestFailed()
}