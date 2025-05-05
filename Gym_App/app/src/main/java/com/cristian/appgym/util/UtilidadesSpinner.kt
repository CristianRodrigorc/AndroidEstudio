package com.cristian.appgym.util

import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment

object UtilidadesSpinner {
    private val DEFAULT_SPINNER_HEIGHT = 48

    fun cargarValoresSpinner(fragment: Fragment, spinner: Spinner?, arrayResourceId: Int){
        val adapter = ArrayAdapter.createFromResource(
            fragment.requireContext(), arrayResourceId, android.R.layout.simple_spinner_item
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        spinner?.adapter = adapter
    }

    fun setSpinnerHeight(spinner: Spinner) {
        val layoutParams = spinner.layoutParams
        layoutParams.height = DEFAULT_SPINNER_HEIGHT
        spinner.layoutParams = layoutParams
    }

    fun getSpinnerSelect(spinner: Spinner): String{
        return spinner.selectedItem.toString().trim();
    }
}