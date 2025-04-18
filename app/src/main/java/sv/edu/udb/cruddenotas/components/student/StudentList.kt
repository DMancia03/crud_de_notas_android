package sv.edu.udb.cruddenotas.components.student

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import sv.edu.udb.cruddenotas.models.Student

@Composable
fun StudentList(
    students : List<Student>,
    modifier: Modifier = Modifier
){
    LazyColumn {
        items(students) { student ->
            StudentCard(
                student,
                modifier = Modifier
            )
        }
    }
}