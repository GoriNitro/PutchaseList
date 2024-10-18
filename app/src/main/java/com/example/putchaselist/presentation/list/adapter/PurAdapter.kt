package com.example.putchaselist.presentation.list.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.putchaselist.R
import com.example.putchaselist.data.model.PurModel
import com.example.putchaselist.databinding.ItemPurchaseBinding
import com.google.gson.Gson

class PurAdapter(private val context: Context, private val list: ArrayList<PurModel>) :
    RecyclerView.Adapter<PurAdapter.PurViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_purchase, parent, false)
        return PurViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: PurViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            val obj = list[position]
            list.remove(obj)
            saveDataToSharedPreferences()
            notifyDataSetChanged()
        }

    }

    class PurViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemPurchaseBinding.bind(item)
        fun bind(model: PurModel) = with(binding) {
            tvName.text = model.name
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addPlace(model: PurModel) {
        list.add(model)
        notifyDataSetChanged()
    }
    private fun saveDataToSharedPreferences() {
        val sharedPreferences =
            context.getSharedPreferences("shared preferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(list)
        editor.putString("courses", json)
        editor.apply()
    }
}