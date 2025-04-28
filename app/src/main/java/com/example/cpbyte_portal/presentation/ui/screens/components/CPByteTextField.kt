import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CPByteTextField(
    value: String,
    onValueChange: (String) -> Unit, // takes a String input and doesn't return anything
    label: String,
    isError: Boolean,
) {
    Column {

        // label above the text field
        Text(
            text = label,
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(bottom = 8.dp)
        )

        TextField(
            value = value,
            onValueChange = onValueChange, //called when any change in the text field
            isError=isError,
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = if (isError) Color.Red else Color(0xFF1F305A),
                focusedIndicatorColor =Color(0xFF1F305A),
                focusedContainerColor = Color(0xFF1F305A),
                unfocusedContainerColor = Color(0xFF1F305A)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black),
            shape = RoundedCornerShape(10.dp)

        )

    }
}