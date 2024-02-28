package com.fleaudie.countryapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.fleaudie.countryapp.R
import com.fleaudie.countryapp.model.Country
import com.fleaudie.countryapp.util.downloadFromUrL
import com.fleaudie.countryapp.util.placeholderProgressBar
import com.fleaudie.countryapp.view.FeedFragment

class CountryAdapter(val countryList: ArrayList<Country>): RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    class CountryViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.name).text = countryList[position].countryName
        holder.view.findViewById<TextView>(R.id.region).text = countryList[position].countryRegion

        holder.view.setOnClickListener {
            it.findNavController().navigate(R.id.action_feedFragment_to_countryFragment)
        }
        holder.view.findViewById<ImageView>(R.id.imageView).downloadFromUrL(countryList[position].imageURrl, placeholderProgressBar(holder.view.context))
    }

    fun updateCountryList(newCountryList: List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }
}