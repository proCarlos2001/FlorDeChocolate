package com.example.flordechocolate.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.flordechocolate.R
import com.example.flordechocolate.data.viewmodels.HomeViewModel
import com.example.flordechocolate.databinding.FragmentUbicationBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [UbicationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UbicationFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentUbicationBinding? = null
    private val homeViewModel: HomeViewModel by sharedViewModel()
    private val binding: FragmentUbicationBinding get() = _binding!!
    private lateinit var nMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUbicationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        homeViewModel.company()
        val mapFragment: SupportMapFragment = childFragmentManager.findFragmentById(R.id.fragment_ubication_map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        nMap = map
        nMap.uiSettings.isZoomControlsEnabled = true
        nMap.uiSettings.isZoomControlsEnabled = true
        nMap.uiSettings.isRotateGesturesEnabled = true
        nMap.uiSettings.isZoomControlsEnabled = true
        nMap.uiSettings.isCompassEnabled = true
        observeViewModels()
    }

    private fun observeViewModels() {
        homeViewModel.company.observe(viewLifecycleOwner, Observer {
            val latLng = LatLng(it.lat, it.lng)
            nMap.addMarker(MarkerOptions().title(it.name).position(latLng))
            nMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12.0f))
        })
    }

}