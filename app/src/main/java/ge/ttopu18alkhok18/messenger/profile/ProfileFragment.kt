package ge.ttopu18alkhok18.messenger.profile

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import ge.ttopu18alkhok18.messenger.R
import ge.ttopu18alkhok18.messenger.auth.AuthSignInActivity
import ge.ttopu18alkhok18.messenger.databinding.HomeProfilePageFragmentBinding
import ge.ttopu18alkhok18.messenger.util.Util

class ProfileFragment: Fragment(), IProfileView {

    private lateinit var getContent: ActivityResultLauncher<String>

    private var _binding: HomeProfilePageFragmentBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var presenter = ProfilePresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            Log.v("profile_fragment", uri.toString())
            uri?.let {
                presenter.updateImage(it)
                Glide
                    .with(this)
                    .load(it)
                    .circleCrop()
                    .placeholder(R.drawable.avatar_image_placeholder)
                    .into(binding.profilePictureImageView)
            }
        }
        _binding = HomeProfilePageFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
        _binding = null
    }

    private fun signOut(context: Context) {
        presenter.signOut()
        val intent = Intent(context, AuthSignInActivity::class.java)
        startActivity(intent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.fetchProfile()

        binding.signOutButton.setOnClickListener {
            signOut(view.context)
        }

        binding.updateButton.setOnClickListener {
            presenter.updateJob(binding.jobEdittext.text.toString())
        }

        binding.profilePictureImageView.setOnClickListener {
            getContent.launch("image/*")
        }

    }

    override fun displayProfile(profile: UserProfile) {
        binding.usernameEdittext.setText(profile.username)
        Glide
            .with(this)
            .load(profile.profilePicURL)
            .circleCrop()
            .placeholder(R.drawable.avatar_image_placeholder)
            .into(binding.profilePictureImageView)
        binding.jobEdittext.setText(profile.job)
    }

}