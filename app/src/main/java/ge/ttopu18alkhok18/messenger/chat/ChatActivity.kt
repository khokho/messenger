package ge.ttopu18alkhok18.messenger.chat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ge.ttopu18alkhok18.messenger.R
import ge.ttopu18alkhok18.messenger.databinding.ActivityChatBinding
import ge.ttopu18alkhok18.messenger.models.Message
import ge.ttopu18alkhok18.messenger.models.User
import ge.ttopu18alkhok18.messenger.util.username


class ChatActivity: AppCompatActivity(), IChatView {

    private lateinit var adapter: ChatAdapter
    private lateinit var binding: ActivityChatBinding

    var presenter: IChatPresenter = ChatPresenter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val chatKey = intent.getStringExtra("chatKey")!!
        val toUsername = intent.getStringExtra("to")!!


        presenter.start(toUsername, chatKey)

        binding.sendBtn.setOnClickListener {
            val message = Message(
                Firebase.auth.currentUser!!.username,
                binding.messageET.text.toString(),
                System.currentTimeMillis()
            )
            presenter.sendMessage(message)
        }

        binding.animToolbar.setNavigationOnClickListener {
            onBackPressed()
        }





    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun adapterList(messages: List<Message>) {
        adapter = ChatAdapter(messages)
        binding.messagesRv.adapter = adapter
    }

    override fun newMessageArrived() {
        adapter.notifyDataSetChanged()
        binding.messagesRv.scrollToPosition(adapter.itemCount-1)
    }

    override fun profileFetched(user: User) {
        binding.animToolbar.title = user.username
        binding.collapsingToolbar.title = user.username
        binding.collapsingSubtitle.setText(user.job)
        Glide
            .with(this)
            .load(user.profilePicURL)
            .circleCrop()
            .placeholder(R.drawable.avatar_image_placeholder)
            .into(binding.collapsingProfileImage)
    }


}