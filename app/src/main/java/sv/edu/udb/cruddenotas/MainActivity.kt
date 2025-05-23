package sv.edu.udb.cruddenotas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import sv.edu.udb.cruddenotas.ui.theme.CrudDeNotasTheme
import sv.edu.udb.cruddenotas.utils.StringsKotlin
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import kotlinx.coroutines.CoroutineScope
import sv.edu.udb.cruddenotas.components.ScreenFormSchoolGrades
import sv.edu.udb.cruddenotas.components.ScreenFormStudent
import sv.edu.udb.cruddenotas.components.ScreenSchoolGrades
import sv.edu.udb.cruddenotas.components.ScreenStudent
import sv.edu.udb.cruddenotas.components.student.StudentList
import sv.edu.udb.cruddenotas.models.Student
import sv.edu.udb.cruddenotas.navigation.ModalDrawer
import sv.edu.udb.cruddenotas.navigation.TopBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CrudDeNotasTheme {
                Surface {
                    MainComponent()
                }
            }
        }
    }
}

@Composable
fun MainComponent(
    modifier: Modifier = Modifier
){
    val navController : NavHostController = rememberNavController()

    val drawerState : DrawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )
    val scope : CoroutineScope = rememberCoroutineScope()

    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute : String = currentNavBackStackEntry?.destination?.route ?: StringsKotlin.routeStudents

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawer(
                navController,
                drawerState,
                scope,
                currentRoute
            )
        }
    ) {
        Scaffold (
            modifier = Modifier.fillMaxSize(),//.padding(all = 10.dp),
            topBar = {
                TopBar(
                    drawerState,
                    scope,
                    currentRoute
                )
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = StringsKotlin.routeStudents,
                modifier = Modifier.padding(innerPadding).padding(all = 10.dp)
            ){
                composable(StringsKotlin.routeStudents){
                    ScreenStudent(
                        modifier = modifier,
                        navController = navController
                    )
                }

                composable(
                    "${StringsKotlin.routeFormStudents}?action={action}&carnet={carnet}",
                    arguments = listOf(
                        navArgument("action"){
                            type = NavType.StringType
                            defaultValue = "create"
                        },
                        navArgument("carnet"){
                            type = NavType.StringType
                            defaultValue = ""
                        }
                    )
                ){
                    ScreenFormStudent(
                        modifier = modifier,
                        navController = navController
                    )
                }
                
                composable(StringsKotlin.routeSchoolGrades){
                    ScreenSchoolGrades(
                        modifier = modifier,
                        navController= navController
                    )
                }

                composable(
                    "${StringsKotlin.routeSchoolGradeForm}?action={action}&id={id}",
                    arguments = listOf(
                        navArgument("action"){
                            type = NavType.StringType
                            defaultValue = "create"
                        },
                        navArgument("id"){
                            type = NavType.IntType
                            defaultValue = 0
                        }
                    )
                ){
                    ScreenFormSchoolGrades(
                        modifier = modifier,
                        navController = navController
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainComponentPreview() {
    CrudDeNotasTheme {
        Surface {
            MainComponent()
        }
    }
}