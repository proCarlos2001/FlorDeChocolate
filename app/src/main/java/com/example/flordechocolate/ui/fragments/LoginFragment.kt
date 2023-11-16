package com.example.flordechocolate.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.flordechocolate.R
import com.example.flordechocolate.data.viewmodels.LoginViewModel
import com.example.flordechocolate.databinding.FragmentLoginBinding
import com.example.flordechocolate.isValidEmail
import com.example.flordechocolate.isValidPassword
import com.example.flordechocolate.ui.activities.HomeActivity
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!
    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.loginFragmentForgotButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotFragment)
        }
        binding.fragmentLoginLabel2.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        binding.loginFragmentLoginButton.setOnClickListener {
            if(!binding.loginFragmentEmail.text.toString().isValidEmail()){
                binding.loginFragmentEmailLayout.error = getString(R.string.email_error)
            }else {
                binding.loginFragmentEmailLayout.error = null
            }

            if(!binding.loginFragmentPassword.text.toString().isValidPassword()){
                binding.loginFragmentPasswordLayout.error = getString(R.string.password_error)
            }else {
                binding.loginFragmentPasswordLayout.error = null
            }

            if(binding.loginFragmentEmail.text.toString().isValidEmail() && binding.loginFragmentPassword.text.toString().isValidPassword()){
                binding.loginFragmentLoginButton.isEnabled = false
                loginViewModel.login(binding.loginFragmentEmail.text.toString(),binding.loginFragmentPassword.text.toString())

            }
        }

        observeViewModels()

    }

    private fun observeViewModels() {
        loginViewModel.login.observe(viewLifecycleOwner, Observer {
            binding.loginFragmentLoginButton.isEnabled = true
            if(it) {
                val intent = Intent(requireContext(), HomeActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
        })
        loginViewModel.error.observe(viewLifecycleOwner, Observer {
            binding.loginFragmentLoginButton.isEnabled = true
            Snackbar.make(binding.root,it, Snackbar.LENGTH_LONG).show()
        })
    }

}