import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType


@Composable
fun CPByteTextField(
    value: String,
    onValueChange: (String) -> Unit, // takes a String input and doesn't return anything
    label: String,
    isPassword: Boolean = false // true only if the input text is a password
) {
    var passwordVisibility by rememberSaveable { mutableStateOf(false) }
    val visualTransformation = if (isPassword && !passwordVisibility)
        PasswordVisualTransformation() //hides password
    else
        VisualTransformation.None //shows the text as it is
    Column {

        // label above the text field
        Text(
            text = label,
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(bottom = 8.dp)
        )

        // OutlineTextField forms a border around the selected field
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange, //called when any change in the text field
            visualTransformation = visualTransformation,
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color(0xFFFFFFFF),
                unfocusedTextColor = Color(0xFFFFFFFF),
                focusedContainerColor = Color(0xFF1F305A),
                unfocusedContainerColor = Color(0xFF1F305A)
            ),
            trailingIcon = {
                if (isPassword) {
                    val image = if (passwordVisibility)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff
                    val description = if (passwordVisibility)
                        "Hide password"
                    else "Show password"
                    IconButton( onClick = {passwordVisibility = !passwordVisibility} ) {
                        Icon(
                            imageVector = image,
                            contentDescription = description,
                            tint = Color.LightGray
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text, // Standard alphanumeric keyboard
                imeAction = ImeAction.Next // What happens when the user presses the action button
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black),
            shape = RoundedCornerShape(10.dp)

        )

    }
}