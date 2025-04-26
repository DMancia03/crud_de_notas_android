package sv.edu.udb.cruddenotas.components.commons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import sv.edu.udb.cruddenotas.ui.theme.CrudDeNotasTheme

@Composable
fun ButtonInfo(
    text : String,
    action: () -> Unit,
    modifier: Modifier
) {
    Button(
        onClick = {
            action()
        },
        colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.onTertiary,
            contentColor = MaterialTheme.colorScheme.secondaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onTertiary,
            disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        modifier = modifier
    ) {
        Text(
            text = text
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonInfoPreview(){
    CrudDeNotasTheme {
        Surface {
            ButtonInfo(
                text = "Nuevo",
                action = {},
                modifier = Modifier
            )
        }
    }
}