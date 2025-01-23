package com.cristianproyecto.appgym

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MetodoDataBase(context: Context) :SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION){
    companion object{
        const val DATABASE_NAME = "GymApp.db"
        const val DATABASE_VERSION = 1

        //Datos tabla_usuarios
        const val TABLE_USERS = "tabla_usuarios"
        const val COLUMN_USER_ID = "id_user"
        const val COLUMN_USERNAME = "username"
        const val COLUMN_PASSWORD = "password"
        const val COLUMN_NAME = "name"
        const val COLUMN_LASTNAME = "lastname"
        const val COLUMN_DATE = "date"
        const val COLUMN_EMAIL = "email"


        //Datos tabla_datos_usuarios
        const val TABLE_USER_DATA = "tabla_datos_usuarios"
        const val COLUMN_USER_DATA_ID = "id_user"
        const val COLUMN_SIZE = "size"
        const val COLUMN_WEIGTH = "weigth"
        const val COLUMN_PHYSICAL_ACTIVITY = "actividad_fisica"
        const val COLUMN_DAYS_TRAINING = "dias_entrenar"
        const val COLUMN_HEALTH_PROBLEMS = "problemas_salud"
        const val COLUMN_PREFERENCE_SCHEDULE = "preferencia_horario"
        const val COLUMN_MOTIVATION = "motivacion"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createUsersTableQuery = """
            CREATE TABLE $TABLE_USERS (
                $COLUMN_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_USERNAME TEXT NOT NULL,
                $COLUMN_PASSWORD TEXT NOT NULL,
                $COLUMN_NAME TEXT NOT NULL,
                $COLUMN_LASTNAME TEXT NOT NULL,
                $COLUMN_DATE DATE NOT NULL,
                $COLUMN_EMAIL TEXT NOT NULL
            )
        """
        db.execSQL(createUsersTableQuery)

        val createUserDataTableQuery = """
            CREATE TABLE $TABLE_USER_DATA(
                $COLUMN_USER_DATA_ID INTEGER,
                $COLUMN_SIZE INTEGER,
                $COLUMN_WEIGTH INTEGER,
                $COLUMN_PHYSICAL_ACTIVITY TEXT,
                $COLUMN_DAYS_TRAINING TEXT,
                $COLUMN_HEALTH_PROBLEMS TEXT,
                $COLUMN_PREFERENCE_SCHEDULE TEXT,
                $COLUMN_MOTIVATION TEXT,
                FOREING KEY ($COLUMN_USER_DATA_ID) REFERENCES $TABLE_USERS($COLUMN_USER_ID)
            )
        """
        db.execSQL(createUserDataTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}