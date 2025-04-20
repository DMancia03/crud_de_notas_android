package sv.edu.udb.cruddenotas.components

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.os.bundleOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import sv.edu.udb.cruddenotas.components.student.StudentList
import sv.edu.udb.cruddenotas.models.Student
import sv.edu.udb.cruddenotas.services.StudentService
import sv.edu.udb.cruddenotas.ui.theme.CrudDeNotasTheme
import sv.edu.udb.cruddenotas.utils.StringsKotlin
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList

@Composable
fun ScreenStudent (
    modifier: Modifier,
    navController : NavHostController
) {


    Column (
        modifier = modifier
    ) {
        val context = LocalContext.current

        val studentService = StudentService(context = context)

        var students = remember { mutableStateListOf<Student>() }

        students.addAll(studentService.getAll())

        Button({
            navController.navigate(
                "${StringsKotlin.routeFormStudents}?action=create",
            )
        }) {
            Text(
                text = "Nuevo estudiante"
            )
        }

        StudentList(
            students,
            Modifier,
            navController,
            context,
            goToUpdateStudent,
            deleteStudent
        )
    }
}

val goToUpdateStudent : (
    navController: NavHostController,
    student: Student
) -> Unit = {
    navController: NavHostController, student: Student ->
    navController.navigate(
        "${StringsKotlin.routeFormStudents}?action=update&carnet=${student.Carnet}",
    )
}

val deleteStudent : (
    context: Context,
    students: SnapshotStateList<Student>,
    student : Student
        ) -> Unit = {
            context, students, student ->
            students.remove(student)
            val studentService = StudentService(context)
    studentService.delete(student.Carnet)
    Toast.makeText(
        context,
        "Estudiante eliminado!!",
        Toast.LENGTH_SHORT
    ).show()
}

@Preview(showBackground = true)
@Composable
fun ScreenStudentPreview(){
    CrudDeNotasTheme {
        Surface {
            ScreenStudent(
                modifier = Modifier,
                navController = rememberNavController()
            )
        }
    }
}