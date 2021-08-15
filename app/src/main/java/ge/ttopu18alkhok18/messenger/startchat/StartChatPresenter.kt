package ge.ttopu18alkhok18.messenger.startchat

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ge.ttopu18alkhok18.messenger.models.Chat
import ge.ttopu18alkhok18.messenger.models.User
import ge.ttopu18alkhok18.messenger.util.username

class StartChatPresenter(var view: IStartChatView?) : IStartChatPresenter {

    val users = mutableListOf<User>()
    val searchRes = mutableListOf<User>()

    var didBeginFetching = false
    var lastSearch = ""

    val interactor = StartChatInteractor(this)

    override fun search(s: String) {
        lastSearch = s
        if(users.size==0 && !didBeginFetching && s.length >= 3){
            interactor.fetchAllUsersList()
            didBeginFetching = true
        }
        searchRes.clear()
        searchRes.addAll(users.filter {
            it.username!!.contains(s)
        })
        view?.seachResultsUpdated()
    }

    override fun getModelList(): List<User> {
        return searchRes
    }

    override fun createChat(to: String) {
        interactor.createChat(to)
    }

    override fun requestFailed() {
        view?.requestFailed()
    }

    override fun detachView() {
        view = null
    }

    override fun usersFetched(users: List<User>) {
        this.users.addAll(users)
        this.users.removeIf { it.username!! == Firebase.auth.currentUser!!.username }
        search(lastSearch)
        didBeginFetching = false
    }

    override fun chatCreated(chat: Chat) {
        view?.chatCreated(chat)
    }

    override fun fetchFailed() {
        didBeginFetching = false
    }
}