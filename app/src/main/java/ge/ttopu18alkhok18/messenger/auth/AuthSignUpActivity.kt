package ge.ttopu18alkhok18.messenger.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import ge.ttopu18alkhok18.messenger.main.MainActivity
import ge.ttopu18alkhok18.messenger.databinding.ActivityAuthSignupBinding
import java.lang.Exception

class AuthSignUpActivity: AppCompatActivity(), IAuthView {

    private lateinit var binding: ActivityAuthSignupBinding
    private lateinit var presenter: IAuthPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthSignupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Initialize Firebase Auth
        val auth = Firebase.auth


        if(auth.currentUser != null) {
            openMainActivity()
            finish()
        }
        binding.progressBar.isVisible = false

        presenter = AuthPresenter(this)
    }

    private fun openMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun signUpSuccessful(currentUser: FirebaseUser?) {
        Toast.makeText(this, currentUser.toString(), Toast.LENGTH_SHORT).show()
        openMainActivity()
    }

    override fun signUpFail(exception: Exception?) {
        binding.passwordEdittext.setText("")
        Toast.makeText(this, "Sign up failed", Toast.LENGTH_SHORT).show()
        binding.progressBar.isVisible = false
        binding.singUpButton.isEnabled = true
    }

    fun singUpPressed(view: View) {
        presenter.singUp(Firebase.auth, Firebase.database, binding.nicknameEdittext.text.toString(), binding.passwordEdittext.text.toString(), binding.jobEdittext.text.toString())
        binding.progressBar.isVisible = true
        binding.singUpButton.isEnabled = false
    }

}