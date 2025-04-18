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
    }
}