package com.catyun.findyou.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.catyun.findyou.viewmodel.HomeViewModel

@Suppress("ktlint:standard:function-naming")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    // viewModel會使用HomeViewModel建立instance然後放入ViewModelStore
    viewModel: HomeViewModel = viewModel(),
    onJoinRoom: (String) -> Unit = {},
) {
    // 將狀態從 ViewModel 提取出來並傳遞給 UI 層，遵循單向資料流 (UDF)
    // 這裡暫時 hardcode 傳入 "888666" 作為測試
    val roomCode = viewModel.roomCode

    HomeScreenContent(
        modifier = modifier,
        onJoinRoomClick = { onJoinRoom("888666") },
    )
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun Button3D(
    text: String,
    buttonColor: Color,
    shadowColor: Color,
    textColor: Color = Color.White,
    fontSize: TextUnit = 18.sp,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Box(
        modifier =
            modifier
                .background(shadowColor, RoundedCornerShape(50))
                .clickable { onClick() },
        contentAlignment = Alignment.TopCenter,
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(bottom = 6.dp)
                    .background(buttonColor, RoundedCornerShape(50)),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = text,
                color = textColor,
                fontSize = fontSize,
                fontWeight = FontWeight.ExtraBold,
            )
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    onJoinRoomClick: () -> Unit = {},
) {
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .background(Color(0xFF7A4DF1))
                .padding(bottom = 32.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Logo Section
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Find",
                color = Color(0xFFFFD54F),
                fontSize = 56.sp,
                fontWeight = FontWeight.Black,
            )
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "You!",
                    color = Color.White,
                    fontSize = 56.sp,
                    fontWeight = FontWeight.Black,
                )
                Box(
                    modifier =
                        Modifier
                            .padding(bottom = 12.dp, start = 4.dp)
                            .background(Color(0xFFFF4081), RoundedCornerShape(50))
                            .padding(horizontal = 10.dp, vertical = 2.dp),
                ) {
                    Text(
                        text = "APP",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Come join the game!",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
        }

        // Dark Card Section
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .background(Color(0xFF2E0954), RoundedCornerShape(32.dp))
                    .padding(24.dp),
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                // Room Code Input
                Button3D(
                    text = "Room Code",
                    buttonColor = Color.White,
                    shadowColor = Color(0xFFD6D6D6),
                    textColor = Color(0xFFAAAAAA),
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(64.dp),
                    onClick = {},
                )

                // Join Room Button
                Button3D(
                    text = "JOIN ROOM →]",
                    buttonColor = Color(0xFF27D051),
                    shadowColor = Color(0xFF169038),
                    textColor = Color.White,
                    onClick = onJoinRoomClick,
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(64.dp),
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Footer Section
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Button3D(
                text = "Create Room",
                buttonColor = Color(0xFF2C84F3),
                shadowColor = Color(0xFF1456A8),
                textColor = Color.White,
                fontSize = 16.sp,
                modifier =
                    Modifier
                        .width(140.dp)
                        .height(48.dp),
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp),
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Settings",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreenContent()
    }
}
