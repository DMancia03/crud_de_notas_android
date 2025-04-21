package sv.edu.udb.cruddenotas.components.school_grades

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import sv.edu.udb.cruddenotas.models.SchoolGrade

@Composable
fun GradeCard (
    schoolGrade: SchoolGrade,
    grades : SnapshotStateList<SchoolGrade>,
    modifier: Modifier,
    navHostController: NavHostController,
    context: Context,
    update : (n : NavHostController, g : SchoolGrade) -> Unit,
    delete : (c : Context, gs : SnapshotStateList<SchoolGrade>, g : SchoolGrade) -> Unit
) {
    Column {
        Row {
            Text(
                text = "Carnet del estudiante: ${schoolGrade.CarnetStudent}"
            )
        }
        Row {
            Text(
                text = "Nota: ${schoolGrade.Calification}"
            )
        }

        Row {
            Button({
                update(navHostController, schoolGrade)
            }) {
                Text(
                    text = "Editar"
                )
            }

            Button({
                delete(context, grades, schoolGrade)
            }) {
                Text(
                    text = "Eliminar"
                )
            }
        }
    }
}