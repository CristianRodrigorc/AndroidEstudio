package com.cristianproyecto.appgym

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.Button


class TransferMethods {

    fun cambiarScreen(view: View, viewProx: Class<*>) {
        view.setOnClickListener {
            val i = Intent(view.context,viewProx)
            view.context.startActivity(i)
        }
    }
}