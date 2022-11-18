package com.example.sorixjson.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.sorixjson.R
import com.example.sorixjson.databinding.FragmentSelectedBinding
import com.example.sorixjson.databinding.FragmentStarterBinding
import com.example.sorixjson.model.JsonViewModel

class SelectedFragment : Fragment() {

    private val viewModel: JsonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_selected, container, false)
        val binding = FragmentSelectedBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

}