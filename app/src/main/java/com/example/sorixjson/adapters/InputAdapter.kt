package com.example.sorixjson.adapters

import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sorixjson.R
import com.example.sorixjson.model.InputElement
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class InputAdapter(

    private val dataset: List<InputElement>?
) : RecyclerView.Adapter<InputAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextInputLayout = view.findViewById(R.id.input_layout)
        val editText: TextInputEditText = view.findViewById(R.id.input_edit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_view_input, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset?.get(position)

        when (item?.name) {
//            "number" -> holder.textView.hint = "card number"
//            "expiryMonth" -> holder.textView.hint = "month"
//            "expiryYear" -> holder.textView.hint = "year"
//            "verificationCode" -> holder.textView.hint = "CVV"
//            "holderName" -> holder.textView.hint = "name"
//            "iban" -> holder.textView.hint = "iban"
//            "bic" -> holder.textView.hint = "bic"
            else -> holder.textView.hint = item?.name
        }

        when (item?.type) {
            "numeric" -> holder.editText.inputType = InputType.TYPE_CLASS_NUMBER
            "integer" -> holder.editText.inputType = InputType.TYPE_CLASS_NUMBER
            else -> holder.editText.inputType = InputType.TYPE_CLASS_TEXT
        }

//        holder.editText.id = ViewCompat.generateViewId()
        holder.editText.id = position
        Log.d("ID", "position: $position - ${holder.editText.hint}")
    }

    override fun getItemCount() = dataset?.size ?: 0
}
