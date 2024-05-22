package com.example.movie.authLanding.logIn.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.movie.R
import com.example.movie.authLanding.signUp.view.SignUpFragment
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

        val loginButton = view.findViewById<Button>(R.id.btn_login)
        val emailEditText = view.findViewById<EditText>(R.id.edt_email)
        val passwordEditText = view.findViewById<EditText>(R.id.edt_password)
        val forgetPasswordTextView = view.findViewById<TextView>(R.id.txv_forget_password)
        val noAccountTextView = view.findViewById<TextView>(R.id.txv_no_account)

        auth = Firebase.auth

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                showToast("Please enter both email and password.")
                return@setOnClickListener
            }
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        showToast("Login successful!")
                    } else {
                        showToast("Login failed: ${task.exception?.message}")
                    }
                }
        }

        forgetPasswordTextView.setOnClickListener {
            val email = emailEditText.text.toString()
            if (email.isNotEmpty()) {
                auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            showToast("Password reset email sent successfully!")
                        } else {
                            showToast("Failed to send password reset email: ${task.exception?.message}")
                        }
                    }
            } else {
                showToast("Please enter your email address.")
            }
        }

        noAccountTextView.setOnClickListener {
            val signUpFragment = SignUpFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fl_container, signUpFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
