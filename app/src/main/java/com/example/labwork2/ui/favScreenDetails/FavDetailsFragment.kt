package com.example.labwork2.ui.favScreenDetails


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.labwork2.R
import com.example.labwork2.databinding.FragmentFavDetailsBinding
import com.example.labwork2.db.SuperHeroModel
import com.squareup.picasso.Picasso


class FavDetailsFragment : Fragment() {


    private var _binding: FragmentFavDetailsBinding? = null
    private val binding: FragmentFavDetailsBinding
        get() = _binding!!

    private lateinit var hero: SuperHeroModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hero = arguments?.getParcelable("hero")!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavDetailsBinding.inflate(inflater, container, false)
        initUI()
        return binding.root
    }

    private fun initUI() {
        with(binding) {
            tvName.text = requireContext().getString(R.string.hero_name, hero.name)
            tvCreatedBy.text = requireContext().getString(R.string.createdBy, hero.createdby)
            tvPublisher.text = requireContext().getString(R.string.publisher, hero.publisher)
            tvTeam.text = requireContext().getString(R.string.team, hero.team)
            tvRealName.text = requireContext().getString(R.string.realName, hero.realname)
            tvBio.text = requireContext().getString(R.string.bio, hero.bio)
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            Picasso.get().load(hero.imageurl).into(image)

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

    }
}