package sv.edu.udb.cruddenotas.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import sv.edu.udb.cruddenotas.utils.StringsKotlin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar (
    drawerState: DrawerState,
    scope: CoroutineScope,
    currentRoute : String
) {
    val item : ItemMenu? = ItemMenu.ItemsMenu.find { it.Route == currentRoute }

    TopAppBar(
        title = {
            if(item != null){
                Text(
                    text = item.Nombre
                )
            }else{
                Text(
                    text = StringsKotlin.NombreApp
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    if (drawerState.isClosed) {
                        drawerState.open()
                    } else {
                        drawerState.close()
                    }
                }
            })
            {
                Icon(Icons.Filled.Menu, contentDescription = "Menu")
            }
        }
    )
}