package com.example.flordechocolate.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.flordechocolate.R
import com.example.flordechocolate.data.viewmodels.LoginViewModel
import com.example.flordechocolate.databinding.FragmentSignUpBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * A simple [Fragment] subclass.
 * Use the [SignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUpFragment : Fragment() {

    private val loginViewModel: LoginViewModel by viewModel()
    private var _binding: FragmentSignUpBinding? = null
    private val binding: FragmentSignUpBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val attachToParent= false
        _binding = FragmentSignUpBinding.inflate(layoutInflater, container, attachToParent)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.fragmentSignupLabel2.setOnClickListener { val view = it
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
        binding.loginFragmentSignupButton.setOnClickListener {
            val id = binding.fragmentSignupRadioGroup.checkedRadioButtonId
            val radioButton = requireActivity().findViewById<RadioButton>(id)
            loginViewModel.signUp(
                binding.signupFragmentEmail.text.toString(),
                binding.signupFragmentPassword.text.toString(),
                binding.signupFragmentName.text.toString(),
                radioButton.text.toString()
            )
        }
        observeViewModels()
    }

    private fun observeViewModels() {
        loginViewModel.error.observe(viewLifecycleOwner, Observer {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        })
        loginViewModel.signUp.observe(viewLifecycleOwner, Observer {
            Snackbar.make(binding.root, "Usuario registrado", Snackbar.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        })
    }
}