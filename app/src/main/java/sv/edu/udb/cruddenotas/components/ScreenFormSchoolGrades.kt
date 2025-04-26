package sv.edu.udb.cruddenotas.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import sv.edu.udb.cruddenotas.components.commons.ButtonCancel
import sv.edu.udb.cruddenotas.components.commons.ButtonConfirm
import sv.edu.udb.cruddenotas.components.commons.ButtonInfo
import sv.edu.udb.cruddenotas.models.SchoolGrade
import sv.edu.udb.cruddenotas.services.SchoolGradeService
import sv.edu.udb.cruddenotas.services.StudentService
import sv.edu.udb.cruddenotas.utils.StringsKotlin

@Composable
fun ScreenFormSchoolGrades (
    modifier: Modifier,
    navController: NavHostController
) {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
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
        var gradeDefault = SchoolGrade(0, 0.0, "", 0.0)

        // Cuando sea actualizacion de nota
        if(action == "update" && idToUpdate != null){
            gradeDefault = gradeService.getById(idToUpdate)
        }

        // Variables de estado en formulario
        var (calification, setCalification) = remember { mutableStateOf(gradeDefault.Calification.toString()) }
        var (carnet, setCarnet) = remember { mutableStateOf(gradeDefault.CarnetStudent) }
        var (units, setUnits) = remember { mutableStateOf(gradeDefault.Units.toString()) }
        var (select, setSelect) = remember { mutableStateOf(false) }

        // Titulo del formulario
        Text(
            text = if (action == "create") "Ingresar nota" else "Editar nota",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
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

        OutlinedTextField(
            value = units,
            onValueChange = { setUnits(it) },
            label = {
                Text("Unidades valorativas:")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            singleLine = true
        )

        Text(
            text = "Seleccionar un carnet:"
        )

        Row {
            ButtonInfo(
                text = carnet,
                action = {
                    setSelect(!select)
                },
                modifier = Modifier
            )

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

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ButtonConfirm(
                text = "Guardar",
                action = {
                    if(calification.toDoubleOrNull() == null){
                        Toast.makeText(
                            context,
                            "Debe ingresar una calificación...",
                            Toast.LENGTH_SHORT
                        ).show()

                        return@ButtonConfirm
                    }

                    if(calification.toDouble() < 0 || calification.toDouble() > 10){
                        Toast.makeText(
                            context,
                            "La calificación debe estar entre 0 y 10...",
                            Toast.LENGTH_SHORT
                        ).show()

                        return@ButtonConfirm
                    }

                    if(units.toDoubleOrNull() == null){
                        Toast.makeText(
                            context,
                            "Debe ingresar unidades valorativas...",
                            Toast.LENGTH_SHORT
                        ).show()

                        return@ButtonConfirm
                    }

                    if(units.toDouble() < 0){
                        Toast.makeText(
                            context,
                            "Las unidades valorativas deben ser mayor o igual a 0...",
                            Toast.LENGTH_SHORT
                        ).show()

                        return@ButtonConfirm
                    }

                    if(carnet.isNullOrBlank() || carnet.isNullOrEmpty()){
                        Toast.makeText(
                            context,
                            "Debe ingresar el carnet del estudiante...",
                            Toast.LENGTH_SHORT
                        ).show()

                        return@ButtonConfirm
                    }

                    if(action == "create"){
                        gradeService.add(SchoolGrade(0, calification.toDouble(), carnet, units.toDouble()))

                        Toast.makeText(
                            context,
                            "Nota ingresada!!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }else{
                        gradeService.update(SchoolGrade(idToUpdate ?: 0, calification.toDouble(), carnet, units.toDouble()))
                        Toast.makeText(
                            context,
                            "Nota actualizada!!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    navController.navigateUp()
                }
            )

            ButtonCancel(
                text = "Cancelar",
                action = {
                    navController.navigateUp()
                }
            )
        }
    }
}