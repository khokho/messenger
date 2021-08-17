package ge.ttopu18alkhok18.messenger.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ge.ttopu18alkhok18.messenger.R
import ge.ttopu18alkhok18.messenger.databinding.ItemMsgSentFromMeBinding
import ge.ttopu18alkhok18.messenger.databinding.ItemMsgSentToMeBinding
import ge.ttopu18alkhok18.messenger.databinding.UserItemBinding
import ge.ttopu18alkhok18.messenger.models.Message
import ge.ttopu18alkhok18.messenger.models.User
import ge.ttopu18alkhok18.messenger.startchat.UsersAdapter
import ge.ttopu18alkhok18.messenger.util.username

class ChatAdapter(val messages: List<Message>):
    RecyclerView.Adapter<ChatAdapter.MessageViewHolder>() {
    val username = Firebase.auth.currentUser!!.username

    override fun getItemViewType(position: Int): Int {
        if(messages[position].from == username) {
            return 0 // message sent from me
        } else {
            return 1 // message sent to me
        }
    }

    inner class MessageViewHolder(val view: View): RecyclerView.ViewHolder(view) {

        lateinit var boundData: Message

        fun bind(data: Message) {
            boundData = data


        }

        init {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatAdapter.MessageViewHolder {
        val view = if(viewType == 0) {
            LayoutInflater.from(parent.context).inflate(R.layout.item_msg_sent_from_me, parent, false)
        } else {
            LayoutInflater.from(parent.context).inflate(R.layout.item_msg_sent_to_me, parent, false)
        }
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatAdapter.MessageViewHolder, position: Int) {
        return holder.bind(messages[position])
    }

    override fun getItemCount(): Int {
        return messages.size
    }


}