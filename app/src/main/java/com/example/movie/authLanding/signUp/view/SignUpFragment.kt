package com.example.movie.authLanding.signUp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.movie.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class SignUpFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        firestore = FirebaseFirestore.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val signUpButton = view.findViewById<Button>(R.id.btn_signup)
        val emailEditText = view.findViewById<EditText>(R.id.edt_email)
        val usernameEditText = view.findViewById<EditText>(R.id.edt_username)
        val passwordEditText = view.findViewById<EditText>(R.id.edt_password)
        val confirmPasswordEditText = view.findViewById<EditText>(R.id.edt_password_confirm)

        signUpButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val confirmPassword = confirmPasswordEditText.text.toString().trim()

            if (email.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                // TODO: CR NOTES -> move the hardcoded hint to a string resource -->
                showToast("Please fill in all fields")
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                // TODO: CR NOTES -> move the hardcoded hint to a string resource -->
                showToast("Passwords do not match")
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        val userId = auth.currentUser?.uid
                        if (userId != null) {
                            // TODO: CR NOTES -> move the hardcoded hint to a string resource -->
                            val user = hashMapOf(
                                "username" to username
                            )
                            firestore.collection("users").document(userId)
                                .set(user)
                                .addOnSuccessListener {
                                    // TODO: CR NOTES -> move the hardcoded hint to a string resource -->
                                    showToast("Username stored successfully!")
                                }
                                .addOnFailureListener { e ->
                                    // TODO: CR NOTES -> move the hardcoded hint to a string resource -->
                                    showToast("Error storing username: $e")
                                }
                        }
                    } else {
                        // TODO: CR NOTES -> move the hardcoded hint to a string resource -->
                        showToast("Sign up failed: ${task.exception?.message}")
                    }
                }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
