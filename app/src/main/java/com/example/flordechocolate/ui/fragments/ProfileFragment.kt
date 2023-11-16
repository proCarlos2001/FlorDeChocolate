package com.example.flordechocolate.ui.fragments

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.flordechocolate.CAMERA_PERMISSION_CODE
import com.example.flordechocolate.REQUEST_IMAGE_CODE
import com.example.flordechocolate.checkPermission
import com.example.flordechocolate.data.viewmodels.LoginViewModel
import com.example.flordechocolate.databinding.FragmentProfileBinding
import com.example.flordechocolate.ui.activities.LoginActivity
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding get() = _binding!!
    private val loginViewModel: LoginViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.profileFragmentImage.setOnClickListener{
            if(checkPermission(Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE)) {
                openCamera()
            }
        }
        binding.profileFragmentLogOut.setOnClickListener {
            loginViewModel.logOut()
        }
        observeViewModels()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openCamera()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_IMAGE_CODE){
            val extras = data!!.extras!!
            val image = extras["data"] as Bitmap?
            if(image != null)
                loginViewModel.uploadImage(image)

        }
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try{
            startActivityForResult(intent, REQUEST_IMAGE_CODE)
        }catch (e: ActivityNotFoundException){
            Log.d("HOLA", e.message.toString())
        }
    }

    private fun observeViewModels() {
        loginViewModel.user.observe(viewLifecycleOwner, Observer {
            if(it != null) {
                if(it.icon_profile != null) {
                    Glide.with(binding.root).load(it.icon_profile).into(binding.profileFragmentImage)
                }
                binding.profileFragmentEmail.text = it.email
                binding.profileFragmentName.text = it.name
                binding.profileFragmentGender.text = it.gender
            }else {
                val intent = Intent(requireContext(), LoginActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
        })
    }

}