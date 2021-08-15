package ge.ttopu18alkhok18.messenger.chats

import ge.ttopu18alkhok18.messenger.models.Chat

class ChatsPresenter(var view: IChatsView): IChatsPresenter {

    var interactor = ChatsInteractor(this)

    override fun fetchChats() {
        interactor.fetchChats()
    }

    override fun chatsFetched(chats: List<Chat>) {
        view.displayChats(chats)
    }

}