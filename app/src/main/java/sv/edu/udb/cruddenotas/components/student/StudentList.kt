package sv.edu.udb.cruddenotas.components.student

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import sv.edu.udb.cruddenotas.models.Student

@Composable
fun StudentList(
    students : SnapshotStateList<Student>,
    modifier: Modifier = Modifier,
    navController : NavHostController,
    context : Context,
    update : (n : NavHostController, s : Student) -> Unit,
    delete : (context: Context, students : SnapshotStateList<Student>, s : Student) -> Unit
){
    if (students.count() > 0){
        LazyColumn {
            items(students) { student ->
                StudentCard(
                    student,
                    students,
                    modifier = Modifier,
                    navController,
                    context,
                    update,
                    delete
                )
            }
        }
    }else{
        Text(
            text = "No hay estudiantes..."
        )
    }
}