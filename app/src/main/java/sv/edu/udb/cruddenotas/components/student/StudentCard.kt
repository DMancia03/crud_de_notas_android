package sv.edu.udb.cruddenotas.components.student

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import sv.edu.udb.cruddenotas.models.Student

@Composable
fun StudentCard(
    student : Student,
    modifier: Modifier = Modifier
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
    }
}