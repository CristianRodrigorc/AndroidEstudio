package com.cristianproyecto.appgym

import android.content.Intent
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class NavigationMethods {

    fun cambiarScreen(btn:Button, currentActivity: AppCompatActivity,targetActivity:Class<out AppCompatActivity>) {
        btn.setOnClickListener {
            val i = Intent(currentActivity,targetActivity)
            currentActivity.startActivity(i)
        }
    }


}