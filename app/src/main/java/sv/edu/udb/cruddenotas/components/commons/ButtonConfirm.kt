package sv.edu.udb.cruddenotas.components.commons

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import sv.edu.udb.cruddenotas.ui.theme.CrudDeNotasTheme

@Composable
fun ButtonConfirm(
    text : String,
    action : () -> Unit
) {
    Button(
        onClick = {
            action()
        },
        colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            contentColor = MaterialTheme.colorScheme.secondaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Text(
            text = text
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonConfirmPreview(){
    CrudDeNotasTheme {
        Surface {
            ButtonConfirm(
                "Aceptar",
                {}
            )
        }
    }
}