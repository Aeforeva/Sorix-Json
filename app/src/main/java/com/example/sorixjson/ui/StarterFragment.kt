package com.example.sorixjson.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.sorixjson.adapters.ApplicableListAdapter
import com.example.sorixjson.adapters.ApplicableListener
import com.example.sorixjson.databinding.FragmentStarterBinding

class StarterFragment : Fragment() {

//    private var _binding: FragmentSelectedBinding? = null
//    private val binding get() = _binding!!

//    private lateinit var binding: FragmentStarterBinding

    private val viewModel: JsonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentStarterBinding.inflate(inflater)
//        binding = FragmentStarterBinding.inflate(inflater)
        viewModel.getJson()

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.recyclerView.adapter = ApplicableListAdapter(ApplicableListener { applicable ->
            viewModel.onApplicableClicked(applicable)
            val action = StarterFragmentDirections.actionStarterFragmentToSelectedFragment(
                fragmentSelected = applicable.label
            )
            findNavController().navigate(action)
            // no action navigation
//            findNavController().navigate(R.id.action_starterFragment_to_selectedFragment)
        })

        return binding.root
    }
}