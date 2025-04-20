package sv.edu.udb.cruddenotas.models

class Student {
    var Carnet : String = ""
    var Name : String = ""
    var Lastname : String = ""

    constructor(_carnet : String, _name : String, _lastname : String){
        Carnet = _carnet
        Name = _name
        Lastname = _lastname
    }

    companion object{
        val DATA_PREVIEW : List<Student> = listOf(
            Student("AA000000", "Diego", "Mancía"),
            Student("AA000001", "Yensy", "Cruz")
        )
        val DATA_PREVIEW_2 : List<Student> = listOf(
            Student("AA000002", "Juan", "López"),
            Student("AA000003", "Angela", "Mejía")
        )

        val TABLE_NAME = "estudiantes"

        val COL_CARNET = "carnet"
        val COL_NAME = "name"
        val COL_LASTNAME = "lastname"

        val CREATE_TABLE = "CREATE TABLE ${TABLE_NAME} (" +
                "${COL_CARNET} varchar(8) primary key," +
                "${COL_NAME} varchar(50) not null," +
                "${COL_LASTNAME} varchar(50) not null" +
                ")"

        val DROP_TABLE = "DROP TABLE ${TABLE_NAME}"
    }
}