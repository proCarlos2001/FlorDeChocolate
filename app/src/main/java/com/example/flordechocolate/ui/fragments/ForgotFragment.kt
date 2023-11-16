package com.example.flordechocolate.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.flordechocolate.R
import com.example.flordechocolate.databinding.FragmentForgotBinding


/**
 * A simple [Fragment] subclass.
 * Use the [ForgotFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ForgotFragment : Fragment() {

    private var _binding: FragmentForgotBinding? = null
    private val binding: FragmentForgotBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val attachToParent = false
        _binding = FragmentForgotBinding.inflate(layoutInflater, container, attachToParent)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.fragmentForgotLabel2.setOnClickListener { val view = it
            findNavController().navigate(R.id.action_forgotFragment_to_signUpFragment)
        }
    }

}