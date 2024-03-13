package com.example.labwork2.ui.third

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.navigation.fragment.findNavController
import com.example.labwork2.R
import com.example.labwork2.databinding.FragmentThirdBinding
import com.example.labwork2.ui.innerThird.InnerThirdFragment

class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!

    private var text = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        setup()
        return binding.root
    }

    private fun setup() {
        binding.btnFirstScreen.setOnClickListener {
            launchSecondScreen()
        }
        setupEditText()
    }

    private fun setupEditText() {
        with(binding) {
            editText.addTextChangedListener(
                object : TextWatcher {
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    }

                    override fun onTextChanged(
                        changeText: CharSequence?,
                        p1: Int,
                        p2: Int,
                        p3: Int
                    ) {
                        text = changeText.toString()
                    }

                    override fun afterTextChanged(p0: Editable?) {

                    }
                },
            )
            editText.setOnKeyListener(
                object : View.OnKeyListener {
                    override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {

                        if (event.action === KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {

                            launchSecondScreen()
                            return true
                        }
                        return false
                    }
                },
            )
        }
    }

    private fun launchSecondScreen() {

        findNavController().navigate(
            R.id.action_nav_slideshow_to_innerThirdFragment,
            Bundle().apply {
                putString(SAVED_TEXT, text)
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "third_fragment"
        private const val SAVED_TEXT = "text"
    }


}