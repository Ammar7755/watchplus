import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.movie.homePage.view.HomePageFragment
import com.example.movie.R
import com.example.movie.authLanding.signUp.view.SignUpFragment
import com.example.movie.utils.SharedPrefConstants
import com.example.movie.utils.SharedPrefsManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LogInFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_log_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loginButton = view.findViewById<Button>(R.id.btn_login_)
        val emailEditText = view.findViewById<EditText>(R.id.edt_email)
        val passwordEditText = view.findViewById<EditText>(R.id.edt_password)
        val forgetPasswordTextView = view.findViewById<TextView>(R.id.txv_forget_password)
        val noAccountTextView = view.findViewById<TextView>(R.id.txv_no_account)



        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                showToast(getString(R.string.please_enter_both_email_and_password))
                return@setOnClickListener
            }
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        showToast(getString(R.string.login_successful))
                        SharedPrefsManager.setBoolean(SharedPrefConstants.IS_LOGGED_IN, true)
                        emailEditText.text.clear()
                        passwordEditText.text.clear()
                        openFragment(R.id.fl_container, HomePageFragment())
                    } else {
                        showToast(getString(R.string.login_failed, task.exception?.message))
                    }
                }
        }

        forgetPasswordTextView.setOnClickListener {
            val email = emailEditText.text.toString()
            if (email.isNotEmpty()) {
                auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            showToast(getString(R.string.password_reset_email_sent_successfully))
                        } else {
                            showToast(
                                getString(
                                    R.string.failed_to_send_password_reset_email,
                                    task.exception?.message
                                )
                            )
                        }
                    }
            } else {
                showToast(getString(R.string.please_enter_your_email_address))
            }
        }

        noAccountTextView.setOnClickListener {
            val signUpFragment = SignUpFragment()
            openFragment(R.id.fl_container, signUpFragment)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    /**
     *  openFragment will start/begin a transaction using FragmentManager
     */
    private fun openFragment(containerId: Int, fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(containerId, fragment)
            .addToBackStack(HomePageFragment.TAG)
            .commit()
    }
}
