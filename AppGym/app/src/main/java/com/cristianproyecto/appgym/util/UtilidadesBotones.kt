package com.cristianproyecto.appgym.util

import android.content.Intent
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


object UtilidadesBotones {

    fun cambiarScreen(btn:Button, currentActivity: AppCompatActivity,targetActivity:Class<out AppCompatActivity>) {
        btn.setOnClickListener {
            val i = Intent(currentActivity,targetActivity)
            currentActivity.startActivity(i)
        }
    }

    /*
    JAVA
        public void cambiarScreen(Button btn, AppCompatActivity currentActivity, Class<? extends AppCompatActivity> targetActivity) {
        btn.setOnClickListener(view -> {
            Intent intent = new Intent(currentActivity, targetActivity);
            currentActivity.startActivity(intent);
        });
    }
     */


}