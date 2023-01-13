package com.example.sorixjson.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.sorixjson.adapters.InputAdapter
import com.example.sorixjson.databinding.FragmentSelectedBinding
import com.example.sorixjson.model.InputElement
import com.google.android.material.textfield.TextInputEditText

class SelectedFragment : Fragment() {

    private val viewModel: JsonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_selected, container, false)
        val binding = FragmentSelectedBinding.inflate(inflater)

        /**
         * No need to observe data change here, [myDataset] is static.
         * But it might be needed if we try to get input values somehow different
         * */
//        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        Log.i("Applicable = ", viewModel.applicable.value.toString())

        val myDataset = viewModel.applicable.value?.inputElements
        binding.recyclerView2.adapter = InputAdapter(myDataset)
        binding.recyclerView2.setHasFixedSize(true)
        binding.button.setOnClickListener {
            getInputValues(myDataset)
            sendRespond()
        }

        return binding.root
    }

    private fun getInputValues(myDataset: List<InputElement>?) {
        if (myDataset != null) {
            var id = 0
            var editText: TextInputEditText?
            viewModel.outPutString.clear()
            while (id < myDataset.size) {
                editText = view?.findViewById(id)
                viewModel.outPutString.add("{${editText?.hint.toString()} : \"${editText?.text.toString()}\"}")
                id++
            }
            Log.d("outPutString", viewModel.outPutString.toString())
        }
    }

    private fun sendRespond() {
        val intent = Intent(Intent.ACTION_SEND)
            .setType("text/plain")
            .putExtra(Intent.EXTRA_SUBJECT, viewModel.applicable.value?.label.toString())
            .putExtra(Intent.EXTRA_TEXT, viewModel.outPutString.toString())
        if (activity?.packageManager?.resolveActivity(intent, 0) != null) {
            startActivity(intent)
        }
    }
}