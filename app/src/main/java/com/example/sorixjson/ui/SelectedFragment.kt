package com.example.sorixjson.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.sorixjson.R
import com.example.sorixjson.databinding.FragmentSelectedBinding
import com.example.sorixjson.model.JsonViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.Internal

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

        val myDataset = viewModel.applicable.value?.inputElements
        binding.recyclerView2.adapter = InputAdapter(this, myDataset)
        binding.recyclerView2.setHasFixedSize(true)



        binding.button.setOnClickListener {

            if (myDataset != null) {
                var id = 0
                var editText: TextInputEditText?
                viewModel.outPutString.clear()
                while (id < myDataset.size) {
                    editText = view?.findViewById(id)
                    viewModel.outPutString.add("{${editText?.hint.toString()} : \"${editText?.text.toString()}\"}")
                    id++
                }
            }
            Log.d("outPutString", viewModel.outPutString.toString())

            val intent = Intent(Intent.ACTION_SEND)
                .setType("text/plain")
                .putExtra(
                    Intent.EXTRA_TEXT,
                    viewModel.applicable.value?.label.toString() + ": " + viewModel.outPutString.toString()
                )

            // Check if there's an app that can handle this intent before launching it
            if (activity?.packageManager?.resolveActivity(intent, 0) != null) {
                // Start a new activity with the given intent (this may open the share dialog on a
                // device if multiple apps can handle this intent)
                startActivity(intent)
            }
        }

        return binding.root
    }

}