package com.example.sorixjson.ui

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
    private val context: SelectedFragment,
    private val dataset: List<InputElement>?
) : RecyclerView.Adapter<InputAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextInputLayout = view.findViewById(R.id.input_layout)
        val editText: TextInputEditText = view.findViewById(R.id.input_edit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_view_item2, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset?.get(position)

        when (item?.name) {
//            "number" -> holder.textView.hint = "Номер карты"
//            "expiryMonth" -> holder.textView.hint = "месяц"
//            "expiryYear" -> holder.textView.hint = "год"
//            "verificationCode" -> holder.textView.hint = "CVV"
//            "holderName" -> holder.textView.hint = "Имя держателя карты"
//            "iban" -> holder.textView.hint = "ИБАН"
//            "bic" -> holder.textView.hint = "БИК"
            else -> holder.textView.hint = item?.name
        }

        when (item?.type) {
            "numeric" -> holder.editText.inputType = InputType.TYPE_CLASS_NUMBER
            "integer" -> holder.editText.inputType = InputType.TYPE_CLASS_NUMBER
            else -> holder.editText.inputType = InputType.TYPE_CLASS_TEXT
        }

//        holder.editText.id = ViewCompat.generateViewId()
        holder.editText.id = position
        Log.d("ID", holder.editText.id.toString() + holder.editText.hint.toString())
    }

    override fun getItemCount() = dataset?.size ?: 0
}
