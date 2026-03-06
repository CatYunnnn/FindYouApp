package com.catyun.findyou.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview // 補上這個 Import
import androidx.compose.ui.unit.dp
import com.catyun.findyou.ui.theme.FindYouAppTheme // 補上 Theme 的 Import
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextFieldDefaults

@Suppress("ktlint:standard:function-naming")
@Composable
fun HomeScreen(
    onJoinRoom: () -> Unit,
    onCreateRoom: () -> Unit,
) {
    var roomCode by remember { mutableStateOf("") }

    // 整個可以堆疊的背景
    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(
                    brush =
                        Brush.verticalGradient(
                            colors =
                                listOf(
                                    Color(0xFF7B5CFF),
                                    Color(0xFF5F8CFF),
                                ),
                        ),
                ).padding(24.dp),
    ) {
        // 由上往下的排列
        Column(
            modifier = Modifier.fillMaxSize().padding(2.dp).border(1.dp, Color.Blue),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Spacing
            Spacer(modifier = Modifier.weight(1f))

            // Logo Area
            Text(text = "Find You!", style = MaterialTheme.typography.headlineLarge,color = Color.White)

            // Spacing
            Spacer(modifier = Modifier.height(8.dp))

            // description
            Text(text = "Come join the came!",color = Color.White)

            // Spacing
            Spacer(modifier = Modifier.weight(1f))

            // Join Room Area
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                OutlinedTextField(
                    value = roomCode,
                    onValueChange = { roomCode = it },
                    placeholder = { Text("Room Code") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(30.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onJoinRoom,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text("JOIN ROOM")
                }
            }
            // Spacing
            Spacer(modifier = Modifier.height(16.dp))

            // Footer Area
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                // Create Room
                Button(onClick = onCreateRoom) {
                    Text("Create Room")
                }

                // Settings
                TextButton(onClick = {}) {
                    Text("Settings")
                }
            }

            // Spacing
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

// --- 修正報錯：新增一個專屬的 Preview 函數 ---
@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    FindYouAppTheme {
        HomeScreen(
            onJoinRoom = {},
            onCreateRoom = {},
        )
    }
}
