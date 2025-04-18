package sv.edu.udb.cruddenotas.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import sv.edu.udb.cruddenotas.components.student.StudentList
import sv.edu.udb.cruddenotas.models.Student

@Composable
fun ScreenSchoolGrades (
    modifier: Modifier
) {
    Column (
        modifier = modifier
    ) {
        Text("Pantalla 2")
        StudentList(Student.DATA_PREVIEW, Modifier)
    }
}