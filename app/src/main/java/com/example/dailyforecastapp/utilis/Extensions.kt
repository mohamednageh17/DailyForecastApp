package com.example.dailyforecastapp.utilis

import android.R
import android.content.Context
import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.core.view.isVisible
import com.example.domain.model.Kelvin
import com.example.domain.utilis.SpinnerItem
import com.google.android.material.textfield.TextInputLayout

fun <T : SpinnerItem> TextInputLayout.setAsSpinner(
    spinner: Spinner,
    list: List<T>,
    setOnTextChanged: (EditText?, T?) -> Unit = { editText, item ->
        editText?.setText(item?.getSpinnerText())
    },
    onItemSelectedListener: (T?) -> Unit = {},
) {
    editText?.keyListener = null
    editText?.setOnClickListener {
        spinner.performClick()
    }
    editText?.setOnFocusChangeListener { _, hasFocus ->
        if (hasFocus) {
            spinner.performClick()
        }
    }

    val adapter = ArrayAdapter(
        this.context,
        R.layout.simple_spinner_item,
        (listOf(hint) + list.map {
            it.getSpinnerText() ?: ""
        }),
    )
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    spinner.adapter = adapter

    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
            parent: AdapterView<*>?, view: View?, position: Int, id: Long
        ) {
            val isFirstTime = (spinner.tag as? Boolean) == true
            if (isFirstTime) {
                spinner.tag = false
                if (position == 0) {
                    setOnTextChanged(editText, null)
                } else {
                    setOnTextChanged(editText, list[position - 1])
                }
            } else {
                if (position == 0) {
                    onNothingSelected(parent)
                } else {
                    setOnTextChanged(editText, list[position - 1])
                    onItemSelectedListener(list[position - 1])
                }
            }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            setOnTextChanged(editText, null)
            onItemSelectedListener(null)
        }

    }
}

fun View.visible(){
    this.visibility=VISIBLE
}

fun View.invisible(){
    this.visibility= INVISIBLE
}

fun View.gone(){
    this.visibility= GONE
}

fun Double.roundTo(n: Int): Double {
    return "%.${n}f".format(this).toDouble()
}

fun Double.toCelsius() = this.minus(Kelvin).roundTo(2)

