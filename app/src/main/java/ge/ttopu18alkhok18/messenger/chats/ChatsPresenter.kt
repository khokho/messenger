package ge.ttopu18alkhok18.messenger.chats

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ge.ttopu18alkhok18.messenger.models.Chat
import ge.ttopu18alkhok18.messenger.util.username

class ChatsPresenter(var view: IChatsView): IChatsPresenter {

    val chats = mutableListOf<Chat>()
    val searchRes = mutableListOf<Chat>()

    var lastSearch = ""

    var interactor = ChatsInteractor(this)

    override fun fetchChats() {
        interactor.fetchChats()
    }

    override fun search(s: String) {
        lastSearch = s
        view.displayChats(chats)
        searchRes.clear()
        searchRes.addAll(chats.filter {
            it.to!!.contains(s)
        })
        view.displayChats(searchRes)
    }

    override fun chatsFetched(chats: List<Chat>) {
        this.chats.clear()
        this.chats.addAll(chats)
        search(lastSearch)
    }

}