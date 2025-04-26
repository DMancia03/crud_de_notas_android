package sv.edu.udb.cruddenotas.components.student

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import sv.edu.udb.cruddenotas.components.commons.ButtonCancel
import sv.edu.udb.cruddenotas.components.commons.ButtonConfirm
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
    Column(
        modifier = Modifier.padding(
            top = 10.dp,
            bottom = 10.dp
        )
    ) {
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

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ButtonConfirm(
                text = "Editar",
                action = {
                    update(navController, student)
                }
            )

            ButtonCancel(
                text = "Eliminar",
                action = {
                    delete(context, students, student)
                }
            )
        }
    }
}