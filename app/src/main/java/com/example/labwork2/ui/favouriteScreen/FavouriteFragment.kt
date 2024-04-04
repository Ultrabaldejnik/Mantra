package com.example.labwork2.ui.favouriteScreen

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.labwork2.R
import com.example.labwork2.databinding.FragmentFirstBinding
import com.example.labwork2.ui.adapters.FavAdapter


class FavouriteFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null


    private val binding get() = _binding!!

    private lateinit var heroAdapter: FavAdapter

    private var currentContext: Context? = null
    private val vm: FavouriteViewModel by viewModels { FavouriteViewModel.factory }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.changeMessage()
        initVM()
        initRV()
    }

    private fun initVM() {
        vm.listHero.observe(viewLifecycleOwner) {
            heroAdapter.submitList(it)
        }
        vm.message.observe(viewLifecycleOwner) {
            when (it) {
                true -> {
                    with(binding) {
                        rvAdapter.visibility = View.INVISIBLE
                        tvFav.visibility = View.VISIBLE
                    }
                }

                false -> {
                    with(binding) {
                        rvAdapter.visibility = View.VISIBLE
                        tvFav.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }



    private fun initRV() {
        heroAdapter = FavAdapter(requireContext())
        heroAdapter.onUserClicked = { hero ->
            val arg = bundleOf("hero" to hero)
            findNavController().navigate(R.id.action_nav_gallery_to_favDetailsFragment, arg)
        }
        with(binding.rvAdapter) {
            adapter = heroAdapter
            layoutManager = LinearLayoutManager(currentContext, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}