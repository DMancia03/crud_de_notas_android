package sv.edu.udb.cruddenotas.components.school_grades

import android.content.Context
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import sv.edu.udb.cruddenotas.models.SchoolGrade

@Composable
fun GradeList(
    grades : SnapshotStateList<SchoolGrade>,
    modifier: Modifier,
    navController : NavHostController,
    context: Context,
    update : (n : NavHostController, g : SchoolGrade) -> Unit,
    delete : (c : Context, gs : SnapshotStateList<SchoolGrade>, g : SchoolGrade) -> Unit
) {
    if(grades.count() == 0){
        Text(
            text = "No hay notas..."
        )
    }else{
        LazyColumn {
            items(grades){
                grade ->
                GradeCard(
                    grades = grades,
                    schoolGrade = grade,
                    modifier = modifier,
                    navHostController = navController,
                    context = context,
                    update = update,
                    delete = delete
                )
            }
        }
    }
}