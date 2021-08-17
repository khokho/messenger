package ge.ttopu18alkhok18.messenger.chats

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ge.ttopu18alkhok18.messenger.R
import ge.ttopu18alkhok18.messenger.databinding.ChatItemBinding
import ge.ttopu18alkhok18.messenger.databinding.UserItemBinding
import ge.ttopu18alkhok18.messenger.models.Chat
import ge.ttopu18alkhok18.messenger.models.User
import ge.ttopu18alkhok18.messenger.util.formatTime

interface ChatsRecyclerViewListener{
    fun chatItemClicked(to: Chat);
}

class ChatsAdapter(private val chats: List<Chat>, val listener: ChatsRecyclerViewListener):
    RecyclerView.Adapter<ChatsAdapter.ChatViewHolder>() {

    inner class ChatViewHolder(val binding: ChatItemBinding): RecyclerView.ViewHolder(binding.root) {

        lateinit var boundData: Chat

        fun bind(data: Chat) {
            boundData = data
            binding.usernameTextView.text = data.to
            binding.msgTextView.text = data.lastMessage?.text
            binding.timestampTextView.text =
                data.lastMessage?.timestamp?.formatTime(System.currentTimeMillis()) ?: ""
            Glide.with(binding.root)
                .load("https://firebasestorage.googleapis.com/v0/b/messenger-f2e2b.appspot.com/o/profiles%2F${data.to}?alt=media&token=d2765253-6741-4955-96d2-c2e5c728d631")
                .circleCrop()
                .placeholder(R.drawable.avatar_image_placeholder)
                .into(binding.profilePictureImageView )
        }

        init {
            binding.root.setOnClickListener {
                listener.chatItemClicked(boundData)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val itemBinding = ChatItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(chats[position])
    }

    override fun getItemCount(): Int {
        return chats.size
    }


}