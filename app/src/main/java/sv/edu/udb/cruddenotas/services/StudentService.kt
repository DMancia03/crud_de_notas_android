package sv.edu.udb.cruddenotas.services

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import sv.edu.udb.cruddenotas.data.HelperDb
import sv.edu.udb.cruddenotas.models.Student

class StudentService(context: Context) {
    private var helper : HelperDb
    private var db : SQLiteDatabase
    private var gradeService: SchoolGradeService

    init {
        helper = HelperDb(context)
        db = helper.writableDatabase
        gradeService = SchoolGradeService(context)
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
            var s : Student = Student(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2)
            )


            var grades = gradeService.getByCarnet(s.Carnet)

            if(grades.count() > 0){
                var units : Double = grades.sumOf { it.Units }
                var notas : Double = grades.sumOf { it.Calification * it.Units }
                s.CUM = if (units > 0) { (notas / units) } else 0.0
            }

            students.add(s)
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

        if(cursor == null || cursor.count == 0){
            return Student("", "", "")
        }

        cursor.moveToFirst()
        return Student(
            cursor.getString(0),
            cursor.getString(1),
            cursor.getString(2)
        )
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

    fun alreadyExist(
        carnet : String
    ) : Boolean{
        return getByCarnet(carnet).Carnet != ""
    }
}