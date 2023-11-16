package com.example.flordechocolate.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.flordechocolate.ui.adapters.ProductsAdapter
import com.example.flordechocolate.data.models.ProductsItemModel
import com.example.flordechocolate.R
import com.example.flordechocolate.data.models.ServiceItemModel
import com.example.flordechocolate.data.viewmodels.HomeViewModel
import com.example.flordechocolate.databinding.FragmentProductsBinding
import com.example.flordechocolate.interfaces.OnProductsClickListener
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [ProductsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductsFragment : Fragment() {

    private val args: ProductsFragmentArgs by navArgs()
    private val homeViewModel:HomeViewModel by sharedViewModel()
    private var _binding: FragmentProductsBinding? = null
    private val binding: FragmentProductsBinding get() = _binding!!
    private lateinit var productsAdapter: ProductsAdapter
    private lateinit var categories: MutableList<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProductsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        productsAdapter = ProductsAdapter(listOf())
        productsAdapter.Listener = object : OnProductsClickListener {
            override fun onClick(item: ProductsItemModel) {
                homeViewModel.selected(item)
               findNavController().navigate(R.id.action_productsFragment_to_productsDetailFragment)
            }

        }
        binding.productsFragmentRecycler.apply {
            adapter = productsAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        if(args.search){
            homeViewModel.product(null)
            binding.productsFragmentSearchLayout.visibility = View.VISIBLE
            binding.productsFragmentSubtitle.visibility = View.GONE
            binding.productsFragmentTitle.text = getString(R.string.products_title)

        }else{
            homeViewModel.product(args.name)
            binding.productsFragmentTitle.text = args.name
            binding.productsFragmentSearchLayout.visibility = View.GONE
            binding.productsFragmentSubtitle.visibility = View.VISIBLE
        }
        observeViewModels()
    }

    private fun observeViewModels() {
        homeViewModel.services.observe(viewLifecycleOwner, Observer {
            categories = it.map { it.title }.toMutableList()
            categories.add(0, element = "Todos")
            binding.productsFragmentSearch.setAdapter(ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line,categories))
            binding.productsFragmentSearch.setOnItemClickListener { parent, view, position, id ->
                if (position == 0){
                    homeViewModel.product(null)
                }else{
                    val category = categories[position]
                    homeViewModel.product(category)
                }
            }

        })
        homeViewModel.product.observe(viewLifecycleOwner, Observer {
            productsAdapter.updateDataset(it)
        })
    }

}