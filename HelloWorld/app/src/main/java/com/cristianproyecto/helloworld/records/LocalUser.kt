package com.cristianproyecto.helloworld.records

class LocalUser {
    private val localTask: MutableList<Tasks> = mutableListOf()

    fun addTask(task: Tasks){
        localTask.add(task)
    }

    
}