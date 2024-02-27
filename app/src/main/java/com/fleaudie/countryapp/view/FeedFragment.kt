package com.fleaudie.countryapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.fleaudie.countryapp.R
import com.fleaudie.countryapp.databinding.FragmentFeedBinding

class FeedFragment : Fragment() {

    private var binding : FragmentFeedBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*val btn = binding?.buttonFragment
        btn?.setOnClickListener {
            findNavController().navigate(R.id.action_feedFragment_to_countryFragment)
        }*/
    }
}