package com.fleaudie.countryapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fleaudie.countryapp.databinding.FragmentCountryBinding
import com.fleaudie.countryapp.viewmodel.CountryViewModel
import com.fleaudie.countryapp.viewmodel.FeedViewModel

class CountryFragment : Fragment() {
    private var countryUuid = 0
    private var binding: FragmentCountryBinding? = null
    private lateinit var viewModel : CountryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom()

        countryUuid = arguments?.getInt("countryUid") ?: 0

        observeLiveData()
    }

    private fun observeLiveData(){
        val countryName = binding?.countryName
        val countryCapital = binding?.countryCapital
        val countryRegion = binding?.countryRegion
        val countryCurrency = binding?.countryCurrency
        val countryLanguage = binding?.countryLanguage
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->
            country?.let {
                countryName?.text = country.countryName
                countryCapital?.text = country.countryCapital
                countryRegion?.text = country.countryRegion
                countryCurrency?.text = country.countryCurrency
                countryLanguage?.text = country.countryLanguage
            }
        })
    }
}