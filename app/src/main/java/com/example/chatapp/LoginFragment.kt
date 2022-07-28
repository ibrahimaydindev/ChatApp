package com.example.chatapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.SurfaceControl
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.chatapp.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()



        val currentUser = auth.currentUser
        currentUser?.let {
            val action = LoginFragmentDirections.actionLoginFragmentToListFragment()
            findNavController().navigate(action)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signUpButton.setOnClickListener {
            auth.signInWithEmailAndPassword(
                binding.emailText.text.toString(),
                binding.passwordText.text.toString()
            ).addOnSuccessListener {
                val action = LoginFragmentDirections.actionLoginFragmentToListFragment()
                findNavController().navigate(action)

            }.addOnFailureListener {
                Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }

        binding.registerButton.setOnClickListener {
            auth.createUserWithEmailAndPassword(
                binding.emailText.text.toString(),
                binding.passwordText.text.toString()
            ).addOnSuccessListener {
                val action = LoginFragmentDirections.actionLoginFragmentToListFragment()
                findNavController().navigate(action)
            }.addOnFailureListener {
                Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_LONG).show()
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}