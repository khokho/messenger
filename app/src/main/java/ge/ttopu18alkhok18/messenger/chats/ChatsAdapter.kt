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
//            binding.timestampTextView.text = data.lastMessage?.timestamp
//            Glide.with(binding.root)
//                .load(data.profilePicURL)
//                .circleCrop()
//                .placeholder(R.drawable.avatar_image_placeholder)
//                .into(binding.profilePictureImageView)
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