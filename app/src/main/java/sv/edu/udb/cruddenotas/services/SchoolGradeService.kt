package sv.edu.udb.cruddenotas.services

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import sv.edu.udb.cruddenotas.data.HelperDb
import sv.edu.udb.cruddenotas.models.SchoolGrade
import sv.edu.udb.cruddenotas.models.Student

class SchoolGradeService(context: Context) {
    private lateinit var helper : HelperDb
    private lateinit var db : SQLiteDatabase

    init {
        helper = HelperDb(context)
        db = helper.writableDatabase
    }

    fun generarContentValue(
        schoolGrade: SchoolGrade
    ) : ContentValues {
        val contentValues = ContentValues()
        contentValues.put(SchoolGrade.COL_ID, schoolGrade.Id)
        contentValues.put(SchoolGrade.COL_CALIFICATION, schoolGrade.Calification)
        contentValues.put(SchoolGrade.COL_CARNET, schoolGrade.CarnetStudent)
        return contentValues
    }

    fun generarList(
        cursor : Cursor?
    ) : List<SchoolGrade> {
        if(cursor == null || cursor.count == 0){
            return listOf()
        }

        var grades : MutableList<SchoolGrade> = mutableListOf()
        cursor.moveToFirst()
        do{
            grades.add(
                SchoolGrade(
                cursor.getInt(0),
                cursor.getDouble(1),
                cursor.getString(2)
            )
            )
        }while (cursor.moveToNext())

        return grades
    }

    fun getAll() : List<SchoolGrade>{
        val cursor : Cursor? = db.query(
            SchoolGrade.TABLE_NAME,
            SchoolGrade.COLUMNS,
            null,
            null,
            null,
            null,
            null
        )

        return generarList(cursor)
    }

    fun getById(
        id : Int
    ) : SchoolGrade {
        val cursor : Cursor? = db.query(
            SchoolGrade.TABLE_NAME,
            SchoolGrade.COLUMNS,
            "${SchoolGrade.COL_ID}=?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )

        if(cursor == null || cursor.count == 0){
            return SchoolGrade(0, 0.0, "")
        }

        cursor.moveToFirst()
        return SchoolGrade(
            cursor.getInt(0),
            cursor.getDouble(1),
            cursor.getString(3)
        )
    }

    fun getByCarnet(
        carnet : String
    ) : List<SchoolGrade> {
        val cursor : Cursor? = db.query(
            SchoolGrade.TABLE_NAME,
            SchoolGrade.COLUMNS,
            "${SchoolGrade.COL_CARNET}=?",
            arrayOf(carnet),
            null,
            null,
            null
        )

        return generarList(cursor)
    }

    fun add(
        schoolGrade: SchoolGrade
    ) {
        db.insert(
            SchoolGrade.TABLE_NAME,
            null,
            generarContentValue(schoolGrade)
        )
    }

    fun update(
        schoolGrade: SchoolGrade
    ) {
        db.update(
            SchoolGrade.TABLE_NAME,
            generarContentValue(schoolGrade),
            "${SchoolGrade.COL_ID}=?",
            arrayOf(schoolGrade.Id.toString())
        )
    }

    fun delete(
        schoolGrade: SchoolGrade
    ) {
        db.delete(
            SchoolGrade.TABLE_NAME,
            "${SchoolGrade.COL_ID}=?",
            arrayOf(schoolGrade.Id.toString())
        )
    }
}