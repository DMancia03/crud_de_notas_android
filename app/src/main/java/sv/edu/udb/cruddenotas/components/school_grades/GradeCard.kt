package sv.edu.udb.cruddenotas.components.school_grades

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import sv.edu.udb.cruddenotas.components.commons.ButtonCancel
import sv.edu.udb.cruddenotas.components.commons.ButtonConfirm
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
    Column(
        modifier = Modifier.padding(
            top = 10.dp,
            bottom = 10.dp
        )
    ) {
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
            Text(
                text = "Unidades valorativas: ${schoolGrade.Units}"
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ButtonConfirm(
                text = "Editar",
                action = {
                    update(navHostController, schoolGrade)
                }
            )

            ButtonCancel(
                text = "Eliminar",
                action = {
                    delete(context, grades, schoolGrade)
                }
            )
        }
    }
}