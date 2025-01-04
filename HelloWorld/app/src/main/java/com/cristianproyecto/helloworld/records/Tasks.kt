package com.cristianproyecto.helloworld.records

import java.util.Date

data class Tasks (
    val nameTas: String,
    val descriptionTask:String,
    val dateTask: Date,
    val programAlarmTask: Date
)
