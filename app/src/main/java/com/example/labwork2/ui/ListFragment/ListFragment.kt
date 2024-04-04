package com.example.labwork2.ui.ListFragment

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
import com.example.labwork2.ApiState
import com.example.labwork2.R
import com.example.labwork2.databinding.FragmentSecondBinding
import com.example.labwork2.ui.adapters.ListHeroAdapter

class ListFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    private val vm: ListViewModel by viewModels { ListViewModel.factory }
    private lateinit var heroAdapter : ListHeroAdapter

    private var currentContext: Context? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        currentContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVM()
        initRV()
    }

    private fun initVM() {
        vm.status.observe(viewLifecycleOwner) {
            Log.d("STATUSVM",it.toString())
            when (it) {
                ApiState.LOADING -> {
                    with(binding) {
                        progress.visibility = View.VISIBLE
                        rvAdapter.visibility = View.INVISIBLE
                        tvFav.visibility = View.INVISIBLE
                    }
                }

                ApiState.SUCCESS -> {
                    with(binding) {
                        progress.visibility = View.INVISIBLE
                        rvAdapter.visibility = View.VISIBLE
                        tvFav.visibility = View.INVISIBLE
                    }
                }

                ApiState.FAILURE -> {
                    with(binding) {
                        progress.visibility = View.INVISIBLE
                        rvAdapter.visibility = View.INVISIBLE
                        tvFav.visibility = View.VISIBLE
                    }
                }
            }
        }
        vm.data.observe(viewLifecycleOwner){

            heroAdapter.submitList(it)
        }
    }

    private fun initRV(){
        heroAdapter = ListHeroAdapter(requireContext())
        heroAdapter.onUserClicked = {hero->
            val arg = bundleOf("heroApi" to hero)
            findNavController().navigate(R.id.action_nav_home_to_heroFragment,arg)
        }
        with(binding.rvAdapter){
            adapter = heroAdapter
            layoutManager = LinearLayoutManager(currentContext, LinearLayoutManager.VERTICAL, false)
        }
    }




    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        currentContext = null
    }
}