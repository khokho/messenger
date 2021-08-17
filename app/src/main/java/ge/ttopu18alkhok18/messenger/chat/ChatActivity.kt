package ge.ttopu18alkhok18.messenger.chat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ge.ttopu18alkhok18.messenger.databinding.ActivityChatBinding
import ge.ttopu18alkhok18.messenger.models.Message
import ge.ttopu18alkhok18.messenger.models.User


class ChatActivity: AppCompatActivity(), IChatView {

    private lateinit var adapter: ChatAdapter
    private lateinit var binding: ActivityChatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val chatKey = intent.getStringExtra("chatKey")
        val toUsername = intent.getStringExtra("to")


        binding.sendButton.setOnClickListener {
            //
        }


    }

    override fun adapterList(messages: List<Message>) {
        adapter = ChatAdapter(messages)
        binding.messagesRecyclerView.adapter = adapter
    }

    override fun newMessageArrived() {
        adapter.notifyDataSetChanged()
    }

    override fun profileFetched(user: User) {
        // TODO set title and subtitle
    }


}