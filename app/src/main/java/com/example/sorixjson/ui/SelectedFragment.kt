package com.example.sorixjson.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.sorixjson.adapters.InputAdapter
import com.example.sorixjson.databinding.FragmentSelectedBinding
import com.example.sorixjson.model.InputElement
import com.example.sorixjson.model.InputData
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
//        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        Log.i("Applicable = ", viewModel.applicable.value.toString())

        val myDataset = viewModel.applicable.value?.inputElements
        binding.recyclerView2.adapter = InputAdapter(myDataset)
        binding.recyclerView2.setHasFixedSize(true)

        binding.button.setOnClickListener {
            getInputAndLog(myDataset)
            sendIntent()
        }

        binding.buttonPost.setOnClickListener {
            getInputAndLog(myDataset)
            viewModel.postResponse()
        }

        binding.buttonPut.setOnClickListener {
            getInputAndLog(myDataset)
            viewModel.putResponse()
        }

        viewModel.sendStatus.observe(viewLifecycleOwner) {
            when (it) {
                SorixApiStatus.LOADING -> Toast.makeText(
                    context,
                    "Sending...",
                    Toast.LENGTH_SHORT
                ).show()
                SorixApiStatus.DONE -> Toast.makeText(
                    context,
                    "Done\nStatus Code: ${viewModel.statusCode.value}",
                    Toast.LENGTH_LONG
                ).show()
                SorixApiStatus.ERROR -> Toast.makeText(
                    context,
                    viewModel.errorMsg.value.toString(),
                    Toast.LENGTH_LONG
                ).show()
                else -> return@observe
            }
        }

        return binding.root
    }

    private fun getInputAndLog(myDataset: List<InputElement>?) {
        getInputValuesAsString(myDataset)
        getInputValuesAsInputData(myDataset)
        Log.d("outPutString = ", viewModel.outPutString.toString())
        Log.d("response = ", viewModel.inputData.toString())
    }

    private fun getInputValuesAsString(myDataset: List<InputElement>?) {
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
    }

    private fun getInputValuesAsInputData(myDataset: List<InputElement>?) {
        if (myDataset != null) {
            var id = 0
            val resp = InputData()
            while (id < myDataset.size) {
                val editText: TextInputEditText? = view?.findViewById(id)
                val string = editText?.text.toString()
                when (editText?.hint.toString()) {
                    "number" -> resp.number = string.toIntOrNull()
                    "expiryMonth" -> resp.expiryMonth = string.toIntOrNull()
                    "expiryYear" -> resp.expiryYear = string.toIntOrNull()
                    "verificationCode" -> resp.verificationCode = string.toIntOrNull()
                    "holderName" -> resp.holderName = noEmptyString(string)
                    "iban" -> resp.iban = noEmptyString(string)
                    "bic" -> resp.bic = noEmptyString(string)
                }
                id++
            }
            viewModel.inputData = resp
        }
    }

    private fun sendIntent() {
        val intent = Intent(Intent.ACTION_SEND)
            .setType("text/plain")
            .putExtra(Intent.EXTRA_SUBJECT, viewModel.applicable.value?.label.toString())
            .putExtra(Intent.EXTRA_TEXT, viewModel.outPutString.toString())
        if (activity?.packageManager?.resolveActivity(intent, 0) != null) { // TODO
            startActivity(intent)
        }
    }

    private fun noEmptyString(string: String): String? {
        return if (string == "") null else string
    }
}