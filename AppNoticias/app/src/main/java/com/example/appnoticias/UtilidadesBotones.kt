package com.example.appnoticias

import android.content.Intent
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

object UtilidadesBotones {

    fun cambiarScreen(
        btn: Button,
        currentActivity: AppCompatActivity,
        targetActivity: Class<out AppCompatActivity>
    ) {
        btn.setOnClickListener({
            val i = Intent(currentActivity, targetActivity)
            currentActivity.startActivity(i)
        })
    }
}