package ge.ttopu18alkhok18.messenger.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ge.ttopu18alkhok18.messenger.main.MainActivity
import ge.ttopu18alkhok18.messenger.databinding.ActivityAuthSigninBinding
import java.lang.Exception


class AuthSignInActivity : AppCompatActivity(), IAuthView {

    private lateinit var binding: ActivityAuthSigninBinding
    private lateinit var presenter: IAuthPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthSigninBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Initialize Firebase Auth
        val auth = Firebase.auth


        if(auth.currentUser != null) {
            openMainActivity()
        }

        presenter = AuthPresenter(this)
    }

    private fun openMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun signInPressed(view: View) {
        presenter.signIn(Firebase.auth, binding.nicknameEdittext.text.toString(), binding.passwordEdittext.text.toString())
    }


    fun wantToSignUpPressed(view: View) {
        val intent = Intent(this, AuthSignUpActivity::class.java)
        startActivity(intent)
    }

    override fun signInSuccessful(currentUser: FirebaseUser?) {
        Toast.makeText(this, currentUser.toString(), Toast.LENGTH_SHORT).show()
        openMainActivity()
    }

    override fun signInFail(exception: Exception?) {
        binding.passwordEdittext.setText("")
        Toast.makeText(this, "Sign in failed", Toast.LENGTH_SHORT).show()
    }



}