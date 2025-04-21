package sv.edu.udb.cruddenotas.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import sv.edu.udb.cruddenotas.components.school_grades.GradeList
import sv.edu.udb.cruddenotas.components.student.StudentList
import sv.edu.udb.cruddenotas.models.SchoolGrade
import sv.edu.udb.cruddenotas.models.Student
import sv.edu.udb.cruddenotas.services.SchoolGradeService
import sv.edu.udb.cruddenotas.utils.StringsKotlin

@Composable
fun ScreenSchoolGrades (
    modifier: Modifier,
    navController : NavHostController
) {
    Column (
        modifier = modifier
    ) {
        val context = LocalContext.current

        val gradeService = SchoolGradeService(context)

        var grades = remember { mutableStateListOf<SchoolGrade>() }

        grades.addAll(gradeService.getAll())

        Button({
            navController.navigate(
                "${StringsKotlin.routeSchoolGradeForm}?action=create",
            )
        }) {
            Text(
                text = "Ingresar nota"
            )
        }

        GradeList(
            grades = grades,
            modifier = modifier,
            navController = navController,
            context = context,
            update = updateGrade,
            delete = deleteGrade
        )
    }
}

val updateGrade : (navHostController : NavHostController, grade : SchoolGrade) -> Unit = {
    navHostController, grade ->
    navHostController.navigate(
        "${StringsKotlin.routeSchoolGradeForm}?action=update&id=${grade.Id}"
    )
}

val deleteGrade : (c : Context, gs : SnapshotStateList<SchoolGrade>, g : SchoolGrade) -> Unit = {
    c, gs, g ->
    val gradeService = SchoolGradeService(c)
    gradeService.delete(g.Id)
    gs.remove(g)
    Toast.makeText(
        c,
        "Nota eliminada!!",
        Toast.LENGTH_SHORT
    ).show()
}