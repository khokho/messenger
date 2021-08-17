package ge.ttopu18alkhok18.messenger.chat

import ge.ttopu18alkhok18.messenger.models.Message
import ge.ttopu18alkhok18.messenger.models.User

class ChatPresenter(var view: IChatView?): IChatPresenter {

    private val messages: MutableList<Message> = mutableListOf()
    val presenter = ChatInteractor(this)
    lateinit var chatKey: String
    lateinit var toUsername: String

    override fun sendMessage(message: Message) {
        presenter.sendMessage(chatKey, message, toUsername)
    }

    override fun start(toUsername: String, chatKey: String) {
        this.chatKey = chatKey
        this.toUsername = toUsername
        presenter.fetchUser(toUsername)
        presenter.setUpMessageListener(chatKey)
        view?.adapterList(messages)
    }

    override fun newMessageArrived(message: Message) {
        messages.add(message)
        view?.newMessageArrived()
    }

    override fun userFetched(user: User) {
        view?.profileFetched(user)
    }

    override fun detachView() {
        view = null
    }
}