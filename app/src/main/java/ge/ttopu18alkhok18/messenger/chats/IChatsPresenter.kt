package ge.ttopu18alkhok18.messenger.chats

import ge.ttopu18alkhok18.messenger.models.Chat


interface IChatsPresenter {
    //for view
    fun fetchChats()
    fun search(s: String)


    //for interactor
    fun chatsFetched(chats: List<Chat>)

}