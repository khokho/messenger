package ge.ttopu18alkhok18.messenger.startchat

import ge.ttopu18alkhok18.messenger.models.Chat
import ge.ttopu18alkhok18.messenger.models.User

interface IStartChatPresenter {
    // for view
    fun search(s: String)
    fun getModelList(): List<User>
    fun createChat(to: String)
    fun requestFailed()
    fun detachView()


    // for interactor
    fun usersFetched(users: List<User>)
    fun chatCreated(chat: Chat)
    fun fetchFailed()
}