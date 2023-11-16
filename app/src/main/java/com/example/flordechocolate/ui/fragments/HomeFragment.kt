package com.example.flordechocolate.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flordechocolate.*
import com.example.flordechocolate.data.models.ServiceItemModel
import com.example.flordechocolate.data.models.TwoServiceItemModel
import com.example.flordechocolate.data.viewmodels.HomeViewModel
import com.example.flordechocolate.databinding.FragmentHomeBinding
import com.example.flordechocolate.interfaces.OnServiceClickListener
import com.example.flordechocolate.ui.adapters.ServiceAdapter
import com.example.flordechocolate.ui.adapters.TwoServiceAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private lateinit var TwoServiceAdapter: TwoServiceAdapter

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!
    private lateinit var serviceAdapter: ServiceAdapter
    private val homeViewModel: HomeViewModel by sharedViewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        homeViewModel.services()
        serviceAdapter = ServiceAdapter(listOf())
        serviceAdapter.Listener = object : OnServiceClickListener {
            override fun onClick(item: ServiceItemModel) {
                val direction =
                    HomeFragmentDirections.actionHomeFragmentToProductsFragment()
                direction.name = item.title
                direction.search = false
                findNavController().navigate(direction)
            }

        }
        binding.homeFragmentRecyclerRefresh.setOnRefreshListener {
            homeViewModel.services()
        }
        binding.homeFragmentRecycler.apply {
            adapter = serviceAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        TwoServiceAdapter = TwoServiceAdapter(listOf(
            TwoServiceItemModel(
                id = "1", title_two = "Cheesecake de frutos rojos",
                amount = "$8.500", subtitle = "Ingredientes", description = "Delicioso cheesecake a base de queso y galleta, acompañado con salsa de frutos rojos.",
                R.drawable.ch_frutos_rojos.toString()
            ),
            TwoServiceItemModel(
                id = "2", title_two = " Café irlandés",
                amount = "$7.900", subtitle = "Ingredientes", description = "Bebida a base de café, whisky y leche cremada.",
                R.drawable.irlandes.toString()
            ),
            TwoServiceItemModel(
                id = "3", title_two = "Sandwich de cerdo BBQ",
                amount = "$13.800", subtitle = "Ingredientes", description = "Sándwich en pan artellano con vegetales, carne de cerdo y salsa de la casa.",
                R.drawable.sandich_bbq.toString()
            ),
            TwoServiceItemModel(
                id = "4", title_two = "Postre de milo",
                amount = "$7.000", subtitle = "Ingredientes", description = "Delicioso postre a base de crema de leche y milo.",
                R.drawable.postre_de_milo.toString()
            ),
            TwoServiceItemModel(
                id = "5", title_two = "Frappé de café",
                amount = "$7.500", subtitle = "Ingredientes", description = "Bebida a base de hielo, cafe y leche en polvo.",
                R.drawable.frappe_de_cafe.toString()
            ),
            TwoServiceItemModel(
                id = "6", title_two = "Jugo de mango",
                amount = "$6.000", subtitle = "Ingredientes", description = "Delicioso jugo natural de mango.",
                R.drawable.jugo_de_naranja.toString()
            )
        ))
        binding.homeFragmentRecyclerTwo.apply { this
            adapter = TwoServiceAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        observeViewModels()

    }
    private fun observeViewModels() {
        homeViewModel.services.observe(viewLifecycleOwner, Observer {
            binding.homeFragmentRecyclerRefresh.isRefreshing = false
            serviceAdapter.updateDataset(it)
        })
    }




}