import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CPByteButton(
    value: String,
    onClick: () -> Unit // no input, returns nothing
) {
    val linearGradientBrush = Brush.linearGradient(
        colors = listOf(
            Color(0xFF0344B4),
            Color(0xFF023181),
            Color(0xFF011D4E),
        )
    )
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = Color(0xFF0344B4)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .background(brush = linearGradientBrush) //button with gradient background
            .height(50.dp)
            .width(280.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = value,
            fontSize = 20.sp
        )
    }

}