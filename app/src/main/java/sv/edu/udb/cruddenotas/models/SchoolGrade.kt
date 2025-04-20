package sv.edu.udb.cruddenotas.models

class SchoolGrade {
    var Id : Int
    //var Subject : String
    var Calification : Double = 0.0
    var CarnetStudent : String

    constructor(_id : Int, _nota : Double, _carnet : String){
        Id = _id
        //Subject = _materia
        Calification = _nota
        CarnetStudent = _carnet
    }

    companion object{
        val NOMBRE_TABLE = "notas"

        val COL_ID = "id"
        //val COL_SUBJECT = "materia"
        val COL_CALIFICATION = "nota"
        val COL_CARNET = "carnet_estudiante"

        val CREATE_TABLE = "CREATE TABLE ${NOMBRE_TABLE} (" +
                "${COL_ID} integer primary key autoincrement," +
                //"${COL_SUBJECT} varchar(50) not null," +
                "${COL_CALIFICATION} double not null," +
                "${COL_CARNET} varchar(8) not null," +
                "FOREIGN KEY (${COL_CARNET}) REFERENCES ${Student.TABLE_NAME}(${Student.COL_CARNET})" +
                ")"

        val DROP_TABLE = "DROP TABLE ${NOMBRE_TABLE}"
    }
}