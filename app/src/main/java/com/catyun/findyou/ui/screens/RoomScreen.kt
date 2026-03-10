package com.catyun.findyou.ui.screens

import androidx.compose.foundation.Canvas
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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class MockPlayer(
    val name: String,
    val emoji: String,
    val backgroundColor: Color,
    val isCaptain: Boolean = false,
)

@Suppress("ktlint:standard:function-naming")
@Composable
fun RoomScreen(
    roomCode: String = "888666",
    modifier: Modifier = Modifier,
    onStartGame: () -> Unit = {},
    onLeaveRoom: () -> Unit = {},
) {
    RoomScreenContent(
        roomCode = roomCode,
        modifier = modifier,
        onStartClick = onStartGame,
        onLeaveClick = onLeaveRoom,
    )
}

@Suppress("ktlint:standard:function-naming")
@Composable
private fun ActionButton3D(
    buttonColor: Color,
    shadowColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit,
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
            content()
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
private fun PlayerCard(
    player: MockPlayer?,
    modifier: Modifier = Modifier,
) {
    if (player != null) {
        Box(
            modifier =
                modifier
                    .fillMaxSize()
                    .background(player.backgroundColor, RoundedCornerShape(24.dp))
                    .padding(16.dp),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                // Avatar representation
                Box(
                    modifier =
                        Modifier
                            .size(64.dp)
                            .background(Color.Black.copy(alpha = 0.2f), RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(text = player.emoji, fontSize = 40.sp)
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = player.name,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )

                if (player.isCaptain) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Box(
                        modifier =
                            Modifier
                                .background(Color(0xFFFFD54F), RoundedCornerShape(50))
                                .padding(horizontal = 8.dp, vertical = 2.dp),
                    ) {
                        Text(
                            text = "隊長",
                            color = Color(0xFFD84315),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Black,
                        )
                    }
                }
            }
        }
    } else {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            val dashPathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 20f), 0f)
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawRoundRect(
                    color = Color.White.copy(alpha = 0.3f),
                    style = Stroke(width = 6f, pathEffect = dashPathEffect),
                    cornerRadius = CornerRadius(24.dp.toPx()),
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "?",
                    color = Color.White.copy(alpha = 0.3f),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Black,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "等待中...",
                    color = Color.White.copy(alpha = 0.5f),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun RoomScreenContent(
    roomCode: String = "888666",
    modifier: Modifier = Modifier,
    onStartClick: () -> Unit = {},
    onLeaveClick: () -> Unit = {},
) {
    val players =
        listOf(
            MockPlayer("小明", "🦊", Color(0xFFFFB74D), true),
            MockPlayer("小華", "🐶", Color(0xFF4FC3F7), false),
            MockPlayer("小白", "🐼", Color(0xFF81C784), false),
        )

    Column(
        modifier =
            modifier
                .fillMaxSize()
                .background(Color(0xFF7A4DF1))
                .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Header
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column {
                Text(
                    text = "ROOM CODE",
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                )
                Text(
                    text = roomCode,
                    color = Color(0xFF42E8E0),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Black,
                )
            }

            // 3 / 5 Pill
            Box(
                modifier =
                    Modifier
                        .background(Color(0xFF4C279E), RoundedCornerShape(50))
                        .padding(horizontal = 16.dp, vertical = 8.dp),
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Players",
                        tint = Color(0xFFFFD54F),
                        modifier = Modifier.size(16.dp),
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "3 / 5",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }

        // Cards Container
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(Color(0xFF4C279E), RoundedCornerShape(32.dp))
                    .padding(16.dp),
        ) {
            // 2x2 Grid
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    PlayerCard(player = players.getOrNull(0), modifier = Modifier.weight(1f))
                    PlayerCard(player = players.getOrNull(1), modifier = Modifier.weight(1f))
                }
                Row(
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    PlayerCard(player = players.getOrNull(2), modifier = Modifier.weight(1f))
                    PlayerCard(player = players.getOrNull(3), modifier = Modifier.weight(1f))
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Start Tracking Button
        ActionButton3D(
            buttonColor = Color(0xFFFF5252),
            shadowColor = Color(0xFFC62828),
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(64.dp),
            onClick = onStartClick,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "🚀",
                    fontSize = 24.sp,
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "開始追蹤",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 2.sp,
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Wait for all players to join",
            color = Color.White.copy(alpha = 0.5f),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true)
@Composable
fun RoomScreenPreview() {
    MaterialTheme {
        RoomScreenContent()
    }
}
