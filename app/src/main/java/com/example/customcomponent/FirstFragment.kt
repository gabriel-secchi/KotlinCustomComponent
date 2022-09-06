package com.example.customcomponent

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.customcomponent.databinding.FragmentFirstBinding
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPlay.setOnClickListener { view ->

            binding.compRadioGroup.getSelectedRadioButton()?.let { radio ->
                Snackbar.make(
                    view,
                    "ID: ${radio.key} - VAL: ${radio.text}",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        binding.compRadioGroup.addItems(buildRadioItems())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun buildRadioItems(): List<RadioItem> = mutableListOf(
        RadioItem(
            key = "00001",
            text = "Radio 001",
        ),
        RadioItem(
            key = "AG0020",
            text = "Radio 002",
            checked = true
        ),
        RadioItem(
            key = "12345",
            text = "Radio 003",
        ),
        RadioItem(
            key = "QWEASD",
            text = "Radio 004",
        )
    )
}