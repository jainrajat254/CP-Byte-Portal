package com.example.cpbyte_portal.presentation.ui.screens.components


import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CatchingPokemon
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.R
import com.example.cpbyte_portal.presentation.ui.theme.CPByteTheme
import com.example.cpbyte_portal.presentation.ui.theme.WarningRed


@Composable
fun ChangePasswordCard(
    userPassword: String
) {
    val context = LocalContext.current //local context for using Toast
    var oldPassword by rememberSaveable { mutableStateOf("") } // Var for OldPass TextField input
    var newPassword by rememberSaveable { mutableStateOf("") } // Var for NewPass TextField input
    var confirmPassword by rememberSaveable { mutableStateOf("") } // Var for ConfirmPass TextField input



    AccountScreenCard(
        colors = CardDefaults.cardColors(
            containerColor = CPByteTheme.cardBackground
        ),
        modifier = Modifier
            .width(400.dp)
            .padding(35.dp, 14.dp)
            .border(
                width = 1.5.dp,
                color = CPByteTheme.cardBorder,
                shape = RoundedCornerShape(18.dp)
            ),
        shape = RoundedCornerShape(18.dp),
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .wrapContentSize()
                        .fillMaxWidth()
                        .padding(21.dp, 20.dp, 0.dp, 0.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.CatchingPokemon,
                        contentDescription = stringResource(R.string.change_password),
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.rotate(180f)
                    )
                    Text(
                        text = stringResource(R.string.change_password),
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 18.sp,
                        fontFamily = FontFamily.Serif,
                        modifier = Modifier.padding(3.dp, 0.dp, 0.dp, 0.dp)
                    )
                }

                Spacer(Modifier.padding(20.dp))

                /* TextField for entering the old Password  */
                AccountScreenCustomTextField(
                    value = oldPassword,
                    label = stringResource(R.string.old_password),
                    onValueChange = { oldPassword = it },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    imeAction = ImeAction.Done
                )
                val isOldPasswordTrue = oldPassword == userPassword
                if (oldPassword.isNotEmpty() && oldPassword.length >= 8 && !isOldPasswordTrue) {
                    Spacer(Modifier.padding(5.dp))
                    Text(
                        text = "Password Mismatch",
                        fontSize = 15.sp,
                        style = MaterialTheme.typography.bodyMedium,
                        color = WarningRed,
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(end = 30.dp)
                            .shadow(12.dp)
                    )
                    newPassword = ""
                    confirmPassword = ""
                    Spacer(Modifier.padding(bottom = 5.dp))
                } else {
                    Spacer(Modifier.padding(10.dp))
                }
                /* TextField for entering the new Password  */
                AccountScreenCustomTextField(
                    value = newPassword,
                    label = stringResource(R.string.new_password),
                    onValueChange = { newPassword = it },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    imeAction = ImeAction.Done,
                    enabled = isOldPasswordTrue
                )
                Spacer(Modifier.height(20.dp))
                AccountScreenCustomTextField(
                    value = confirmPassword,
                    label = "Confirm Password",
                    onValueChange = { confirmPassword = it },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    imeAction = ImeAction.Done,
                    enabled = isOldPasswordTrue
                )
                Spacer(Modifier.height(20.dp))
                Box(
                    Modifier
                        .width(165.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    //Save Changes Button
                    SaveChangesButton(
                        value = stringResource(R.string.save_changes),
                        onClick = {
                            if (newPassword != oldPassword) {
                                if (confirmPassword == newPassword) {

                                    /*Logic for Changing Password Will be Written here
                                    *
                                    *
                                    * Changing Old Password will occur in this onClick()*/

                                    //Prompt For the User That Password is Changed Successfully
                                    Toast.makeText(
                                        context,
                                        "Password Changed Successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    // All the TextFields Become Empty again
                                    oldPassword = ""
                                    newPassword = ""
                                    confirmPassword = ""
                                } else {
                                    // if the New Password and Confirm Password
                                    // Do not Match then this Block Executed
                                    Toast.makeText( //Prompt which says Passwords Don't match
                                        context,
                                        "Passwords Do Not Match.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            } else {
                                // This Else block is Executed when the Old
                                // password and the new Password are Same and the SaveChanges Button is Pressed
                                confirmPassword = ""
                                Toast.makeText(
                                    context,
                                    "Old and New Password Cannot Be Same!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    )
                }
                Spacer(Modifier.padding(10.dp))
            }
        }
    )
}