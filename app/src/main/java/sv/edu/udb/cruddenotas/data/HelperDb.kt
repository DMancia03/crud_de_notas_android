package sv.edu.udb.cruddenotas.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import sv.edu.udb.cruddenotas.models.SchoolGrade
import sv.edu.udb.cruddenotas.models.Student

class HelperDb (
    context: Context
) : SQLiteOpenHelper(
    context,
    DB_NAME,
    null,
    DB_VERSION
) {
    companion object{
        val DB_NAME = "crud_notas"
        val DB_VERSION = 2
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(Student.CREATE_TABLE)
        db.execSQL(SchoolGrade.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL(SchoolGrade.DROP_TABLE)
        db.execSQL(Student.DROP_TABLE)

        db.execSQL(Student.CREATE_TABLE)
        db.execSQL(SchoolGrade.CREATE_TABLE)
    }
}