package com.example.testfluttermodel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testfluttermodel.databinding.FragmentSecondBinding
import io.flutter.embedding.android.FlutterFragment

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

private var _binding: FragmentSecondBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

      _binding = FragmentSecondBinding.inflate(inflater, container, false)
      return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(
                    R.id.activity_root,
                    SecondFragment()
                )
                .addToBackStack("BB")
                .commit()
        }
        binding.buttonToFlutter.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(
                    R.id.activity_root,
                    FlutterFragment.createDefault()
                )
                .addToBackStack("CC")
                .commit()
        }
    }
override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}