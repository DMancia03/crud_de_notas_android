package sv.edu.udb.cruddenotas.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Create
import androidx.compose.ui.graphics.vector.ImageVector
import sv.edu.udb.cruddenotas.utils.StringsKotlin

class ItemMenu {
    lateinit var Nombre : String
    lateinit var Icon : ImageVector
    lateinit var IconDescription : String
    lateinit var Route : String

    constructor(_nombre : String, _icon : ImageVector, _iconDescription : String, _route : String){
        Nombre = _nombre
        Icon = _icon
        IconDescription = _iconDescription
        Route = _route
    }

    companion object{
        val ItemsMenu : List<ItemMenu> = listOf(
            ItemMenu(StringsKotlin.ItemMenuStudents, Icons.Filled.AccountBox, "Estudiantes", StringsKotlin.routeStudents),
            ItemMenu(StringsKotlin.ItemMenuSchoolGrades, Icons.Filled.Create, "Estudiantes", StringsKotlin.routeSchoolGrades)
        )
    }
}