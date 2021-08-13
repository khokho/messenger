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

class ProfileFragment: Fragment() {

    private lateinit var getContent: ActivityResultLauncher<String>

    private var _binding: HomeProfilePageFragmentBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            Log.v("profile_fragment", uri.toString())
            uri?.let {
                val storageRef = Firebase.storage.reference
                val username = Util.mailToUserName(Firebase.auth.currentUser?.email!!)
                val profileRef = storageRef.child("profiles/${username}")
                val uploadTask = profileRef.putFile(it)
                // Register observers to listen for when the download is done or if it fails
                uploadTask.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        loadProfilePicture()
                    } else {
                        Log.v("profile_fragment", "upload failed")
                    }
                }
            }
        }
        _binding = HomeProfilePageFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun signOut(context: Context) {
        Firebase.auth.signOut()
        val intent = Intent(context, AuthSignInActivity::class.java)
        startActivity(intent)
    }

    private fun loadProfilePicture() {
        val username = Util.mailToUserName(Firebase.auth.currentUser?.email!!)
        val storageRef = Firebase.storage.reference
        val profileRef = storageRef.child("profiles/${username}")
        profileRef.downloadUrl.addOnSuccessListener {
            Log.v("profile_fragment", it.toString())
            Glide
                .with(this)
                .load(it)
                .circleCrop()
                .placeholder(R.drawable.avatar_image_placeholder)
                .into(binding.profilePictureImageView);
        }
    }

    private fun updateJob(username: String, job: String) {
        Log.v("profile_fragment", "${username}'s job updated to $job")
        Firebase.database.getReference("jobs").child(username).setValue(job)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username = Util.mailToUserName(Firebase.auth.currentUser?.email!!)
        binding.usernameEdittext.setText(username)

        loadProfilePicture()

        Firebase.database.getReference("jobs").child(username).get().addOnSuccessListener {
            binding.jobEdittext.setText(it.getValue<String>())
        }

        binding.signOutButton.setOnClickListener {
            signOut(view.context)
        }

        binding.updateButton.setOnClickListener {
            updateJob(username, binding.jobEdittext.text.toString())
        }

        binding.profilePictureImageView.setOnClickListener {
            getContent.launch("image/*")
        }
    }

}