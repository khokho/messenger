package ge.ttopu18alkhok18.messenger.chats

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import ge.ttopu18alkhok18.messenger.models.Chat
import ge.ttopu18alkhok18.messenger.util.username

class ChatsInteractor(val presenter: IChatsPresenter){
    val username = Firebase.auth.currentUser!!.username

    fun fetchChats() {
        Firebase.database.getReference("chats").child(username).get()
            .addOnSuccessListener {
                val chats = it.getValue<HashMap<String, Chat>>()!!.values.toList()
                presenter.chatsFetched(chats)
            }
    }

}

