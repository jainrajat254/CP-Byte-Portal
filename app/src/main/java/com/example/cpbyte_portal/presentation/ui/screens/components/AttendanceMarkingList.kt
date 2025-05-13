package com.example.cpbyte_portal.presentation.ui.screens.components

import Member
import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MemberAttendanceMarkingList(members: SnapshotStateList<Member>) {
    LazyColumn(
        modifier = Modifier.clip(
            RoundedCornerShape(5.dp),
        )
    ) {
        itemsIndexed(members, key = { index, _ -> index }) { index, member ->
            //State for swipeToDismiss
            val dismissState = rememberSwipeToDismissBoxState(
                confirmValueChange = { dismissValue ->
                    when (dismissValue) {
                        SwipeToDismissBoxValue.StartToEnd -> {
                            members[index] = members[index].copy(status = "PRESENT")
                            false
                        }

                        SwipeToDismissBoxValue.EndToStart -> {
                            members[index] =
                                members[index].copy(status = "ABSENT_WITHOUT_REASON")
                            false
                        }

                        else -> false

                    }
                }
            )

            //swipe to dismiss box for attendance marking by swiping
            SwipeToDismissBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Transparent)
                    .padding(0.5.dp)
                    .clip((RoundedCornerShape(5.dp))),
                state = dismissState,
                backgroundContent = {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                when (dismissState.dismissDirection) {
                                    SwipeToDismissBoxValue.StartToEnd -> Color(0xFF1DE9B6)
                                    SwipeToDismissBoxValue.EndToStart -> Color(0xDDFD2C51)
                                    else -> Color.Transparent
                                }
                            ), verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = when (dismissState.dismissDirection) {
                            SwipeToDismissBoxValue.StartToEnd -> Arrangement.Start
                            SwipeToDismissBoxValue.EndToStart -> Arrangement.End
                            else -> Arrangement.Start
                        }
                    )
                    {
                        Spacer(Modifier.padding(5.dp))
                        Icon(
                            imageVector = when(dismissState.dismissDirection){
                                SwipeToDismissBoxValue.StartToEnd->Icons.Default.CheckCircle
                                SwipeToDismissBoxValue.EndToStart->Icons.Default.Close
                                else->Icons.Default.CheckCircle
                            },
                            contentDescription = "",
                            tint = Color.White
                        )
                        Spacer(Modifier.padding(5.dp))
                    }
                }) {

                //member detail card
                MemberDetail(
                    index + 1,
                    member,
                    { newState ->
                        members[index] = members[index].copy(status = newState)
                    }
                )

            }
        }
    }
}