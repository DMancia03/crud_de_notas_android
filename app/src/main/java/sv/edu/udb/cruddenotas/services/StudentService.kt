package sv.edu.udb.cruddenotas.services

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import sv.edu.udb.cruddenotas.data.HelperDb

class StudentService(context: Context) {
    private lateinit var helper : HelperDb
    private lateinit var db : SQLiteDatabase

    init {
        helper = HelperDb(context)
        db = helper.writableDatabase
    }
}