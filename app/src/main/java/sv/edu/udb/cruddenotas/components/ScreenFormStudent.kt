package sv.edu.udb.cruddenotas.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import sv.edu.udb.cruddenotas.models.Student
import sv.edu.udb.cruddenotas.services.StudentService
import sv.edu.udb.cruddenotas.ui.theme.CrudDeNotasTheme

@Composable
fun ScreenFormStudent(
    modifier: Modifier,
    navController : NavHostController
) {
    Column (
        modifier = Modifier
    ) {
        // Parametros por route
        var action : String? = ""
        var carnetToUpdate : String? = ""

        if(navController.currentBackStackEntry != null && navController.currentBackStackEntry?.arguments != null){
            action = navController.currentBackStackEntry!!.arguments!!.getString("action")
            carnetToUpdate = navController.currentBackStackEntry!!.arguments!!.getString("carnet")
        }

        // Contexto
        val context = LocalContext.current

        // Servicio para acceder a bd
        val studentService = StudentService(context = context)

        // Estudiante default
        var studentDefault : Student = Student("", "", "")

        // Cuando sea actualizacion de un estudiante
        if(action == "update" && carnetToUpdate != null && carnetToUpdate.isNotEmpty() && carnetToUpdate.isNotBlank()){
            studentDefault = studentService.getByCarnet(carnetToUpdate ?: "")
        }

        // Variables de estado en formulario
        val (carnet, setCarnet) = remember { mutableStateOf(studentDefault.Carnet) }
        val (name, setName) = remember { mutableStateOf(studentDefault.Name) }
        val (lastname, setLastname) = remember { mutableStateOf(studentDefault.Lastname) }

        // Titulo del formulario
        Text(
            text = if (action == "create") "Crear estudiante" else "Editar esudiante"
        )

        OutlinedTextField(
            value = carnet,
            onValueChange = { setCarnet(it) },
            label = {
                Text("Carnet del estudiante")
            },
            readOnly = action == "update"
        )

        OutlinedTextField(
            value = name,
            onValueChange = { setName(it) },
            label = {
                Text("Nombre del estudiante")
            }
        )

        OutlinedTextField(
            value = lastname,
            onValueChange = { setLastname(it) },
            label = {
                Text("Apellido del estudiante")
            }
        )

        Row {
            Button({
                // Cuando sea creacion
                if(action == "create"){
                    studentService.add(
                        Student(carnet, name, lastname)
                    )
                    Toast.makeText(
                        context,
                        "Estudiante creado!!",
                        Toast.LENGTH_SHORT
                    ).show()
                }else{ // Cuando se edicion
                    studentService.update(
                        Student(carnet, name, lastname)
                    )
                    Toast.makeText(
                        context,
                        "Estudiante actualizado!!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                navController.navigateUp()
            }) {
                Text(
                    text = "Guardar"
                )
            }

            Button({
                navController.navigateUp()
            }) {
                Text(
                    text = "Cancelar"
                )
            }
        }
    }
}

@Composable
fun textInput(){
    //var text by remember { mutableStateOf(TextFieldValue("")) }

    TextField(
        value = "",
        onValueChange = { }
    )
}

@Preview(showBackground = true)
@Composable
fun ScreenFormStudentPreview(){
    CrudDeNotasTheme {
        Surface {
            ScreenFormStudent(
                modifier = Modifier,
                navController = rememberNavController()
            )
        }
    }
}