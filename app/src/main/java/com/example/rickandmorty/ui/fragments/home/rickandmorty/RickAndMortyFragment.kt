package com.example.rickandmorty.ui.fragments.home.rickandmorty

import android.content.ContentValues.TAG
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.databinding.FragmentRickAndMortyBinding
import com.example.rickandmorty.ui.adapters.RickAndMortyAdapter
import com.example.rickandmorty.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RickAndMortyFragment :
    BaseFragment<FragmentRickAndMortyBinding, RickAndMortyViewModel>(R.layout.fragment_rick_and_morty) {

    override val binding by viewBinding(FragmentRickAndMortyBinding::bind)
    override val viewModel: RickAndMortyViewModel by viewModels()
    private val rickAndMortyAdapter = RickAndMortyAdapter()

    override fun initialize() {
        binding.rvRickAndMorty.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = rickAndMortyAdapter
        }
    }

    override fun setupSubscribes() {
        viewModel.noteLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> Log.e(TAG, "Error")
                is Resource.Loading -> Log.e(TAG, "Loading")
                is Resource.Success -> rickAndMortyAdapter.submitList(it.data)
            }
        }

    }
}