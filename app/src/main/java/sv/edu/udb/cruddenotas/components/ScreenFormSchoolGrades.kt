package sv.edu.udb.cruddenotas.components

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavHostController
import sv.edu.udb.cruddenotas.models.SchoolGrade
import sv.edu.udb.cruddenotas.services.SchoolGradeService
import sv.edu.udb.cruddenotas.services.StudentService
import sv.edu.udb.cruddenotas.utils.StringsKotlin

@Composable
fun ScreenFormSchoolGrades (
    modifier: Modifier,
    navController: NavHostController
) {
    Column {
        // Parametros por route
        var action : String? = ""
        var idToUpdate : Int? = 0

        if(navController.currentBackStackEntry != null && navController.currentBackStackEntry?.arguments != null){
            action = navController.currentBackStackEntry!!.arguments!!.getString("action")
            idToUpdate = navController.currentBackStackEntry!!.arguments!!.getInt("id")
        }

        // Contexto
        val context = LocalContext.current

        // Servicios para acceder a la bd
        val studentService = StudentService(context)
        val gradeService = SchoolGradeService(context)

        // Lista de carnets
        val students = studentService.getAll()

        // Nota default
        var gradeDefault = SchoolGrade(0, 0.0, "")

        // Cuando sea actualizacion de nota
        if(action == "update" && idToUpdate != null){
            gradeDefault = gradeService.getById(idToUpdate)
        }

        // Variables de estado en formulario
        var (calification, setCalification) = remember { mutableStateOf(gradeDefault.Calification.toString()) }
        var (carnet, setCarnet) = remember { mutableStateOf(gradeDefault.CarnetStudent) }
        var (select, setSelect) = remember { mutableStateOf(false) }

        // Titulo del formulario
        Text(
            text = if (action == "create") "Ingresar nota" else "Editar nota"
        )

        OutlinedTextField(
            value = calification,
            onValueChange = { setCalification(it) },
            label = {
                Text("Nota:")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            singleLine = true
        )

        Text(
            text = "Seleccionar un carnet:"
        )

        Row {
            Button({
                setSelect(!select)
            }) {
                Text(
                    text = carnet
                )
            }

            DropdownMenu (
                expanded = select,
                onDismissRequest = { setSelect(false) }
            ) {
                students.forEach({
                    DropdownMenuItem(
                        text = { Text(it.Carnet) },
                        onClick = {
                            setCarnet(it.Carnet)
                            setSelect(!select)
                        }
                    )
                })
            }
        }

        Row {
            Button({
                if(action == "create"){
                    gradeService.add(SchoolGrade(0, calification.toDouble(), carnet))

                    Toast.makeText(
                        context,
                        "Nota ingresada!!",
                        Toast.LENGTH_SHORT
                    ).show()
                }else{
                    gradeService.update(SchoolGrade(idToUpdate ?: 0, calification.toDouble(), carnet))
                    Toast.makeText(
                        context,
                        "Nota actualizada!!",
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