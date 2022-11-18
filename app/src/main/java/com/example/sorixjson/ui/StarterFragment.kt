package com.example.sorixjson.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.sorixjson.databinding.FragmentStarterBinding
import com.example.sorixjson.model.JsonViewModel

class StarterFragment : Fragment() {

//    private var _binding: FragmentSelectedBinding? = null
//    private val binding get() = _binding!!

//    private lateinit var binding: FragmentStarterBinding

    private val viewModel: JsonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentStarterBinding.inflate(inflater)
//        binding = FragmentStarterBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }
}