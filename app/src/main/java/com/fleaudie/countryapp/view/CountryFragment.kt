package com.fleaudie.countryapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.fleaudie.countryapp.databinding.FragmentCountryBinding
import com.fleaudie.countryapp.util.downloadFromUrL
import com.fleaudie.countryapp.util.placeholderProgressBar
import com.fleaudie.countryapp.viewmodel.CountryViewModel

class CountryFragment : Fragment() {
    private var countryUuid = 0
    private var binding: FragmentCountryBinding? = null
    private lateinit var viewModel : CountryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        countryUuid = arguments?.getInt("selectedUuid") ?: 0

        viewModel = ViewModelProvider(this)[CountryViewModel::class.java]
        viewModel.getDataFromRoom(countryUuid)

        observeLiveData()
    }

    private fun observeLiveData(){
        val countryName = binding?.countryName
        val countryCapital = binding?.countryCapital
        val countryRegion = binding?.countryRegion
        val countryCurrency = binding?.countryCurrency
        val countryLanguage = binding?.countryLanguage
        val countryFlag = binding?.countryImage
        viewModel.countryLiveData.observe(viewLifecycleOwner) { country ->
            country?.let {
                countryName?.text = country.countryName
                countryCapital?.text = country.countryCapital
                countryRegion?.text = country.countryRegion
                countryCurrency?.text = country.countryCurrency
                countryLanguage?.text = country.countryLanguage
                context?.let {
                    countryFlag?.downloadFromUrL(country.imageURrl, placeholderProgressBar(it))
                }
            }
        }
    }
}