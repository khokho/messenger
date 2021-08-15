package ge.ttopu18alkhok18.messenger.startchat

import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import ge.ttopu18alkhok18.messenger.models.Chat
import ge.ttopu18alkhok18.messenger.models.User
import ge.ttopu18alkhok18.messenger.util.username


class StartChatInteractor(val presenter: IStartChatPresenter) {

    fun fetchAllUsersList() {
        Firebase.database.getReference("users")
            .get().addOnSuccessListener {
                val users = it.getValue<HashMap<String, User>>()
                if(users == null) {
                    presenter.usersFetched(listOf())
                } else {
                    presenter.usersFetched(users.values.toList())
                }
            }.addOnFailureListener {
                presenter.fetchFailed()
            }
    }

    fun createChat(to: String) {
        val from = Firebase.auth.currentUser!!.username
        Firebase.database.getReference("messages")
            .push().get().addOnSuccessListener { dataSnapshot ->
                val key = dataSnapshot.key
                val myChat = Chat(key, from, to)
                val hisChat = Chat(key, to, from)
                val chats = Firebase.database.getReference("chats")

                val task1 = chats.child(from).child(to)
                    .setValue(myChat)

                val task2 = chats.child(to).child(from)
                    .setValue(hisChat)

                Tasks.whenAllComplete(task1, task2).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        presenter.chatCreated(myChat)
                    } else {
                        presenter.fetchFailed()
                    }
                }
            }
    }

}