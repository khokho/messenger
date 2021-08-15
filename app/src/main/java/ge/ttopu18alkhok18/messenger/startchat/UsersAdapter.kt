package ge.ttopu18alkhok18.messenger.startchat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ge.ttopu18alkhok18.messenger.R
import ge.ttopu18alkhok18.messenger.models.User
import ge.ttopu18alkhok18.messenger.databinding.UserItemBinding

interface UsersRecyclerViewListener {
    fun userItemClicked(to: User)
}

class UsersAdapter(val users: List<User>, val listener: UsersRecyclerViewListener):
    RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {


    inner class UserViewHolder(val binding: UserItemBinding): RecyclerView.ViewHolder(binding.root) {

        lateinit var boundData: User

        fun bind(data: User) {
            boundData = data
            binding.usernameTextView.setText(data.username)
            binding.jobTextView.setText(data.job)
            Glide.with(binding.root)
                .load(data.profilePicURL)
                .circleCrop()
                .placeholder(R.drawable.avatar_image_placeholder)
                .into(binding.profilePictureImageView)
        }

        init {
            binding.root.setOnClickListener {
                listener.userItemClicked(boundData)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemBinding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }


}