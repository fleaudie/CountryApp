package com.fleaudie.countryapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fleaudie.countryapp.adapter.CountryAdapter
import com.fleaudie.countryapp.databinding.FragmentFeedBinding
import com.fleaudie.countryapp.viewmodel.FeedViewModel

class FeedFragment : Fragment() {

    private var binding : FragmentFeedBinding? = null
    private lateinit var viewModel : FeedViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[FeedViewModel::class.java]
        viewModel.refreshData()

        val countryList = binding?.countryList
        countryList?.layoutManager = LinearLayoutManager(context)
        countryList?.adapter = countryAdapter

        /*val btn = binding?.buttonFragment
        btn?.setOnClickListener {
            findNavController().navigate(R.id.action_feedFragment_to_countryFragment)
        }*/

        binding?.swipeRefreshLayout?.setOnRefreshListener {
            binding?.countryList?.visibility = View.GONE
            binding?.countryError?.visibility = View.GONE
            binding?.countryLoading?.visibility = View.VISIBLE
            viewModel.refreshFromAPI()
            binding?.swipeRefreshLayout?.isRefreshing = false
        }

        observeLiveData()
    }

    private fun observeLiveData(){
        val countryList = binding?.countryList
        val countryLoading = binding?.countryLoading
        val countryError = binding?.countryError
        viewModel.countries.observe(viewLifecycleOwner) { countries ->
            countries?.let {
                countryList?.visibility = View.VISIBLE
                countryAdapter.updateCountryList(countries)
            }
        }
        viewModel.countryError.observe(viewLifecycleOwner) { error ->
            error?.let {
                if (it) {
                    countryError?.visibility = View.VISIBLE
                } else {
                    countryError?.visibility = View.GONE
                }
            }
        }
        viewModel.countryLoading.observe(viewLifecycleOwner) { loading ->
            loading?.let {
                if (it) {
                    countryLoading?.visibility = View.VISIBLE
                    countryList?.visibility = View.GONE
                    countryError?.visibility = View.GONE
                } else {
                    countryLoading?.visibility = View.GONE
                }
            }
        }
    }
}