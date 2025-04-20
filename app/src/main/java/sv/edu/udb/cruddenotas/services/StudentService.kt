package sv.edu.udb.cruddenotas.services

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import sv.edu.udb.cruddenotas.data.HelperDb
import sv.edu.udb.cruddenotas.models.Student

class StudentService(context: Context) {
    private lateinit var helper : HelperDb
    private lateinit var db : SQLiteDatabase

    init {
        helper = HelperDb(context)
        db = helper.writableDatabase
    }

    fun generarContentValue(
        student : Student
    ) : ContentValues {
        val contentValues = ContentValues()
        contentValues.put(Student.COL_CARNET, student.Carnet)
        contentValues.put(Student.COL_NAME, student.Name)
        contentValues.put(Student.COL_LASTNAME, student.Lastname)
        return contentValues
    }

    fun generarList(
        cursor : Cursor?
    ) : List<Student> {
        if(cursor == null || cursor.count == 0){
            return listOf()
        }

        var students : MutableList<Student> = mutableListOf()
        cursor.moveToFirst()

        do{
            students.add(Student(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2)
            ))
        }while (cursor.moveToNext())

        return students
    }

    fun getAll() : List<Student>{
        val cursor : Cursor? = db.query(
            Student.TABLE_NAME,
            Student.COLUMNS,
            null,
            null,
            null,
            null,
            null
        )

        return generarList(cursor)
    }

    fun getByCarnet(
        carnet : String
    ) : Student{
        val cursor : Cursor? = db.query(
            Student.TABLE_NAME,
            Student.COLUMNS,
            "${Student.COL_CARNET}=?",
            arrayOf(carnet),
            null,
            null,
            null
        )

        return generarList(cursor).first()
    }

    fun add(
        student: Student
    ){
        db.insert(
            Student.TABLE_NAME,
            null,
            generarContentValue(student)
        )
    }

    fun update(
        student: Student
    ){
        db.update(
            Student.TABLE_NAME,
            generarContentValue(student),
            "${Student.COL_CARNET}=?",
            arrayOf(student.Carnet),
        )
    }

    fun delete(
        carnet : String
    ) {
        db.delete(
            Student.TABLE_NAME,
            "${Student.COL_CARNET}=?",
            arrayOf(carnet),
        )
    }
}