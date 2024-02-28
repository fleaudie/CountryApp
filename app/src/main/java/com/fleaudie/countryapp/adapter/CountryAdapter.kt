package com.fleaudie.countryapp.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.fleaudie.countryapp.R
import com.fleaudie.countryapp.databinding.ItemCountryBinding
import com.fleaudie.countryapp.model.Country

class CountryAdapter(private val countryList: ArrayList<Country>): RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(), CountryClickListener {
    class CountryViewHolder(var view: ItemCountryBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.item_country, parent, false)
        val view = DataBindingUtil.inflate<ItemCountryBinding>(inflater, R.layout.item_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.view.country = countryList[position]
        holder.view.listener = this

        /*holder.view.findViewById<TextView>(R.id.name).text = countryList[position].countryName
        holder.view.findViewById<TextView>(R.id.region).text = countryList[position].countryRegion

        holder.view.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("selectedUuid", countryList[position].uuid)
            it.findNavController().navigate(R.id.action_feedFragment_to_countryFragment, bundle)
        }
        holder.view.findViewById<ImageView>(R.id.imageView).downloadFromUrL(countryList[position].imageURrl, placeholderProgressBar(holder.view.context))*/
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateCountryList(newCountryList: List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

    override fun onCountryClicked(v: View) {
        val uuid = v.findViewById<TextView>(R.id.countryUuidText).text.toString().toInt()
        val bundle = Bundle()
        bundle.putInt("selectedUuid", uuid)
        v.findNavController().navigate(R.id.action_feedFragment_to_countryFragment, bundle)
    }
}