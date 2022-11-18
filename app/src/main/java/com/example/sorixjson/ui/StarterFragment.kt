package com.example.sorixjson.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.sorixjson.R
import com.example.sorixjson.databinding.FragmentSelectedBinding
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

        binding.button2.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_starterFragment_to_selectedFragment)
        }
        return binding.root
    }
}