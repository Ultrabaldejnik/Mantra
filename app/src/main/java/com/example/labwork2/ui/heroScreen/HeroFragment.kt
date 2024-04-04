package com.example.labwork2.ui.heroScreen

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.labwork2.ui.api.SuperHeroModelApi
import com.example.labwork2.R
import com.example.labwork2.databinding.FragmentHeroBinding
import com.squareup.picasso.Picasso

class HeroFragment : Fragment() {
    private lateinit var hero: SuperHeroModelApi

    private var _binding: FragmentHeroBinding? = null
    private val binding: FragmentHeroBinding
        get() = _binding!!

    private val vm: HeroViewModel by viewModels { HeroViewModel.factory }


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hero = arguments?.getParcelable("heroApi")!!
        initVM()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHeroBinding.inflate(inflater, container, false)
        initUI()
        return binding.root
    }

    private fun initVM() {
        vm.toastMessage.observe(this) {
            if (it == "") return@observe
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }


    private fun initUI() {
        with(binding) {
            tvName.text = requireContext().getString(R.string.hero_name, hero.name)
            tvCreatedBy.text = requireContext().getString(R.string.createdBy, hero.createdby)
            tvPublisher.text = requireContext().getString(R.string.publisher, hero.publisher)
            tvTeam.text = requireContext().getString(R.string.team, hero.team)
            tvRealName.text = requireContext().getString(R.string.realName, hero.realname)
            tvBio.text = requireContext().getString(R.string.bio, hero.bio)
            Picasso.get().load(hero.imageurl).into(image)
            btnAddFav.setOnClickListener {
                vm.addFavHero(hero)
            }
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    companion object {

    }
}