package sv.edu.udb.cruddenotas.components.student

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import sv.edu.udb.cruddenotas.models.Student

@Composable
fun StudentCard(
    student : Student,
    students : SnapshotStateList<Student>,
    modifier: Modifier = Modifier,
    navController : NavHostController,
    context: Context,
    update : (n : NavHostController, s : Student) -> Unit,
    delete : (context: Context, students : SnapshotStateList<Student>, s : Student) -> Unit
) {
    Column {
        Row {
            Text(
                text = "${student.Name}, ${student.Lastname}",
                color = MaterialTheme.colorScheme.primary,
                modifier = modifier
            )
        }

        Row {
            Text(
                text = "Carnet: ${student.Carnet}",
                modifier = modifier
            )
        }

        Row {
            Button({
                update(navController, student)
            }) {
                Text(
                    text = "Editar"
                )
            }

            Button({
                delete(context, students, student)
            }) {
                Text(
                    text = "Eliminar"
                )
            }
        }
    }
}