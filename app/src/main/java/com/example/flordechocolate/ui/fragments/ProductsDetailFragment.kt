package com.example.flordechocolate.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.flordechocolate.data.viewmodels.HomeViewModel
import com.example.flordechocolate.databinding.FragmentProductsDetailBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [ProductsDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductsDetailFragment : Fragment() {

    private var _binding: FragmentProductsDetailBinding? = null
    private val binding: FragmentProductsDetailBinding get() = _binding!!
    private val homeViewModel: HomeViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProductsDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        observeViewModels()
    }

    private fun observeViewModels() {
        homeViewModel.productt.observe(viewLifecycleOwner, Observer {
            homeViewModel.details(it.id)
  //          binding.productsDetailFragmentImage.setImageResource(it.image.toInt())
            Glide.with(binding.root).load(it.image).into(binding.productsDetailFragmentImage)
            binding.productsDetailFragmentName.text = it.name
            binding.productsDetailFragmentCategory.text = it.products_category
            binding.productsDetailFragmentRating.rating = it.star.toFloat()
            binding.productsDetailFragmentDescription.text = it.description_two
        })
        homeViewModel.detail.observe(viewLifecycleOwner, Observer {
            if(it != null) {
                binding.productsDetailCardGroup.visibility = View.VISIBLE
                binding.productsDetailFragmentCardPatientsCal.text = it.calification
                binding.productsDetailFragmentCardGradeName.text = it.star.toString()
                binding.productsDetailFragmentCardGradeRating.rating = (it.star / 5.0).toFloat()
                binding.productDetailFragmentCardExpName.text = it.exp
            }else {
                binding.productsDetailCardGroup.visibility = View.GONE
            }
        })
    }

}