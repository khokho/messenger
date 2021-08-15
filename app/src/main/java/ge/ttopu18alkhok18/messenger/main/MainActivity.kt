package ge.ttopu18alkhok18.messenger.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ge.ttopu18alkhok18.messenger.R
import ge.ttopu18alkhok18.messenger.databinding.ActivityMainBinding
import ge.ttopu18alkhok18.messenger.chats.ChatsFragment
import ge.ttopu18alkhok18.messenger.profile.ProfileFragment
import ge.ttopu18alkhok18.messenger.startchat.StartChatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        Log.v("main_activity", Firebase.auth.currentUser.toString())
//        Firebase.auth.signOut()


        binding.fabButton.setOnClickListener {
            val intent = Intent(this, StartChatActivity::class.java)
            startActivity(intent)
        }

        val pagerAdapter = ScreenSlidePagerAdapter(this)
        binding.pager.adapter = pagerAdapter

        binding.navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home_action -> {
//                    binding.appBarLayout.isVisible = true
                    binding.pager.currentItem = 0
                }
                R.id.settings_action -> {
//                    binding.appBarLayout.isVisible = false
                    binding.pager.currentItem = 1
                }
            }
            true
        }
    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        val chats = ChatsFragment()
        val profile = ProfileFragment()


        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            return when(position) {
                0 -> chats
                else -> profile
            }
        }
    }

}