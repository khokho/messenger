package ge.ttopu18alkhok18.messenger.chats

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import ge.ttopu18alkhok18.messenger.R
import ge.ttopu18alkhok18.messenger.databinding.HomeChatsPageFragmentBinding
import ge.ttopu18alkhok18.messenger.databinding.HomeProfilePageFragmentBinding
import ge.ttopu18alkhok18.messenger.models.Chat
import ge.ttopu18alkhok18.messenger.startchat.StartChatPresenter
import ge.ttopu18alkhok18.messenger.startchat.UsersAdapter

class ChatsFragment: Fragment(), IChatsView, ChatsRecyclerViewListener {

    private var _binding: HomeChatsPageFragmentBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var presenter = ChatsPresenter(this)
    private lateinit var adapter: ChatsAdapter
    private var chatsList: MutableList<Chat> = mutableListOf<Chat>();

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeChatsPageFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ChatsAdapter(chatsList, this)
        binding.chatsRv.adapter = adapter

        presenter.fetchChats()

        binding.searchEt.doOnTextChanged { text, _, _, _ ->
            val s = text.toString()
            presenter.search(s)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun displayChats(chats: List<Chat>) {
        chatsList.clear()
        chatsList.addAll(chats)
        adapter.notifyDataSetChanged()
    }

    override fun chatItemClicked(to: Chat) {
        TODO("Not yet implemented")
    }

}