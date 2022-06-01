package com.alexpetrov.tinder.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexpetrov.tinder.presentation.utils.AppState
import com.alexpetrov.tinder.presentation.adapter.CatAdapter
import com.alexpetrov.tinder.presentation.viewmodel.CatViewModel
import com.alexpetrov.tinder.databinding.FragmentCatBinding

class CatFragment : Fragment() {

    private var binding: FragmentCatBinding? = null
    private val bind get() = binding!!
    private val adapter: CatAdapter by lazy { CatAdapter() }
    private val viewModel: CatViewModel by lazy {
        ViewModelProvider(this)[CatViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCatBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initModel()
        initAdapter()
    }

    private fun initAdapter() {
        bind.recyclerView.layoutManager = LinearLayoutManager(context)
        bind.recyclerView.adapter = adapter
    }

    private fun initModel() {
        viewModel.liveData.observe(viewLifecycleOwner) { renderData(it) }
        viewModel.getData()
    }

    private fun renderData(appState: AppState) {
        if (appState is AppState.SuccessFavorite) {
            if (appState.favoriteData.isNotEmpty())
                adapter.setData(appState.favoriteData)
            else
                Toast.makeText(context, "No data available", Toast.LENGTH_SHORT).show()
        }
        else if (appState is AppState.Error) {
            Toast.makeText(context, appState.e.message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}