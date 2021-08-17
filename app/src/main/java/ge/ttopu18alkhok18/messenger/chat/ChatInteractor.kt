package ge.ttopu18alkhok18.messenger.chat

import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import ge.ttopu18alkhok18.messenger.models.Message
import ge.ttopu18alkhok18.messenger.models.User

class ChatInteractor(val presenter: IChatPresenter) {


    fun setUpMessageListener(chatKey: String) {

        val childEventListener = object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {

                // A new comment has been added, add it to the displayed list
                val message = dataSnapshot.getValue<Message>()!!

                presenter.newMessageArrived(message)
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, previousChildName: String?) {}

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}

            override fun onChildMoved(dataSnapshot: DataSnapshot, previousChildName: String?) {}

            override fun onCancelled(databaseError: DatabaseError) {}
        }

        Firebase.database.getReference("messages").child(chatKey)
            .addChildEventListener(childEventListener)
    }

    fun fetchUser(username: String) {
        Firebase.database.getReference("users").child(username)
            .get().addOnSuccessListener {
                val user = it.getValue<User>()!!
                presenter.userFetched(user)
            }
    }





}