package com.example.putchaselist.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.putchaselist.data.local.Pref
import com.example.putchaselist.data.model.PurModel
import com.example.putchaselist.databinding.FragmentListBinding
import com.example.putchaselist.presentation.list.adapter.PurAdapter


class ListFragment : Fragment() {
    private val binding: FragmentListBinding by lazy {
        FragmentListBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initManager()
        binding.apply {
            val list = ArrayList<PurModel>()
            val adapter = PurAdapter(requireContext(), list)
            Pref.loadData(requireContext(), list)
            rvPurchases.adapter = adapter
            btnSave.setOnClickListener {
                val name = etName.text.toString()
                if (name.isNotEmpty()){
                    val model = PurModel(name)
                    adapter.addPlace(model)
                    Pref.saveData(requireContext(), list)
                }else{
                    Pref.saveData(requireContext(), list)
                }
                etName.text = null
            }
        }
    }



    private fun initManager() {
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvPurchases.layoutManager = layoutManager
    }
}
