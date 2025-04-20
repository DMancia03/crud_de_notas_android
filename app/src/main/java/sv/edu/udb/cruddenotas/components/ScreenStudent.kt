package sv.edu.udb.cruddenotas.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import sv.edu.udb.cruddenotas.components.student.StudentList
import sv.edu.udb.cruddenotas.models.Student
import sv.edu.udb.cruddenotas.services.StudentService

@Composable
fun ScreenStudent (
    modifier: Modifier
) {


    Column (
        modifier = modifier
    ) {
        val studentService = StudentService(context = LocalContext.current)
        Log.i("SQLITE", "HOLA")
        Text("Pantalla 1")
        StudentList(Student.DATA_PREVIEW, Modifier)
    }
}