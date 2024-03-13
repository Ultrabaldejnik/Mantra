package com.example.labwork2.ui.innerThird

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.labwork2.R
import com.example.labwork2.databinding.FragmentInnerThirdBinding


class InnerThirdFragment : Fragment() {

    private val binding: FragmentInnerThirdBinding
        get() = _binding!!
    private var _binding: FragmentInnerThirdBinding? = null
    private var text = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        text = arguments?.getString(SAVED_TEXT) ?: "Пусто"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentInnerThirdBinding.inflate(inflater, container, false)
        setup()
        return binding.root
    }

    private fun setup() {
        with(binding) {

            tvMessage.isSelected = true
            if (text != "") {
                tvMessage.text = text
            } else {
                tvMessage.text = "Пусто"
            }
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    companion object {

        private const val SAVED_TEXT = "text"
    }
}