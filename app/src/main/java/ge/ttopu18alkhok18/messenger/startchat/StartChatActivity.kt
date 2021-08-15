package ge.ttopu18alkhok18.messenger.startchat

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import ge.ttopu18alkhok18.messenger.models.Chat
import ge.ttopu18alkhok18.messenger.models.User
import ge.ttopu18alkhok18.messenger.databinding.ActivityStartchatBinding


interface IStartChatPresenter {
    // for view
    fun search(s: String)
    fun getModelList(): List<User>
    fun createChat(to: String)
    fun requestFailed()
    fun detachView()


    // for interactor
    fun usersFetched(users: List<User>)
    fun chatCreated(chat: Chat)
    fun fetchFailed()
}

interface IStartChatView {
    fun seachResultsUpdated()
    fun chatCreated(chat: Chat)
    fun requestFailed()
}



class StartChatActivity: AppCompatActivity(), IStartChatView, UsersRecyclerViewListener {

    private lateinit var binding: ActivityStartchatBinding
    private var presenter = StartChatPresenter(this)
    private lateinit var adapter: UsersAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartchatBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        adapter = UsersAdapter(presenter.getModelList(), this)
        binding.usersRv.adapter = adapter
        binding.backButton.setOnClickListener {
            onBackPressed()
        }

        binding.searchEditText.doOnTextChanged { text, _, _, _ ->
            val s = text.toString()
            presenter.search(s)
        }

    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun seachResultsUpdated() {
        adapter.notifyDataSetChanged()
    }

    override fun chatCreated(chat: Chat) {
//        val intent = Intent(this, ChatActivity::class.java)
//            .putExtra("to", chat.to)
//            .putExtra("chatKey", chat.chatKey)
//        startActivity(intent)
//        finish()
    }

    override fun requestFailed() {
        Toast.makeText(this, "Could not fetch user list", Toast.LENGTH_SHORT).show()
    }

    override fun userItemClicked(to: User) {
        presenter.createChat(to.username!!)
        binding.searchEditText.isEnabled = false
    }

}