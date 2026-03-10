package com.catyun.findyou.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.cos
import kotlin.math.sin

data class PlayerTarget(
    val name: String,
    val distance: String,
    val color: Color,
    val angle: Float, // 0-360 degrees for compass bearing
    val isPrimaryTarget: Boolean = false,
)

@Suppress("ktlint:standard:function-naming")
@Composable
fun GameScreen(modifier: Modifier = Modifier) {
    GameScreenContent(modifier = modifier)
}


@Suppress("ktlint:standard:function-naming")
@Composable
private fun RadarCenterSelf(modifier: Modifier = Modifier) {
    // 使用同心圓加上十字光標的設計來精緻地表示「自己 (正中央)」
    Box(
        modifier =
            modifier
                .size(60.dp)
                .background(Color.White.copy(alpha = 0.1f), CircleShape)
                .padding(8.dp)
                .background(Color(0xFF42E8E0).copy(alpha = 0.2f), CircleShape),
        contentAlignment = Alignment.Center,
    ) {
        // 十字準星中心點
        Canvas(modifier = Modifier.fillMaxSize()) {
            val center = Offset(size.width / 2, size.height / 2)
            // 發光的中心點
            drawCircle(
                color = Color(0xFF42E8E0),
                radius = 6.dp.toPx(),
                center = center,
            )
            // 外圍發光環
            drawCircle(
                color = Color(0xFF42E8E0),
                radius = 16.dp.toPx(),
                center = center,
                style = Stroke(width = 3f),
            )
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
private fun CompassGrid(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        // Draw Radar Circles and Crosshairs
        Canvas(modifier = Modifier.fillMaxSize()) {
            val center = Offset(size.width / 2, size.height / 2)
            val maxRadius = size.width / 2.5f

            // 十字準心 (Crosshairs) - N, S, E, W
            drawLine(
                color = Color.White.copy(alpha = 0.1f),
                start = Offset(center.x, 0f),
                end = Offset(center.x, size.height),
                strokeWidth = 2f,
            )
            drawLine(
                color = Color.White.copy(alpha = 0.1f),
                start = Offset(0f, center.y),
                end = Offset(size.width, center.y),
                strokeWidth = 2f,
            )

            // 雷達同心圓 (Radar Circles)
            drawCircle(
                color = Color.White.copy(alpha = 0.1f),
                radius = maxRadius,
                center = center,
                style = Stroke(width = 2f),
            )
            drawCircle(
                color = Color.White.copy(alpha = 0.15f),
                radius = maxRadius * 0.6f,
                center = center,
                style = Stroke(width = 2f),
            )
            drawCircle(
                color = Color.White.copy(alpha = 0.2f),
                radius = maxRadius * 0.3f,
                center = center,
                style = Stroke(width = 2f),
            )
        }

        // NESW 標示
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                "N",
                color = Color.White.copy(alpha = 0.3f),
                modifier = Modifier.align(Alignment.TopCenter).padding(top = 16.dp),
            )
            Text(
                "S",
                color = Color.White.copy(alpha = 0.3f),
                modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 16.dp),
            )
            Text(
                "E",
                color = Color.White.copy(alpha = 0.3f),
                modifier = Modifier.align(Alignment.CenterEnd).padding(end = 16.dp),
            )
            Text(
                "W",
                color = Color.White.copy(alpha = 0.3f),
                modifier = Modifier.align(Alignment.CenterStart).padding(start = 16.dp),
            )
        }

        // 放置內容物 (Arrows 和其他雷達上的使用者)
        content()
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
private fun TargetButton3D(
    target: PlayerTarget,
    modifier: Modifier = Modifier,
) {
    // Determine shadow color based on main color to keep the 3D effect
    val shadowColor =
        when (target.color) {
            Color(0xFFFF5252) -> Color(0xFFC62828) // Red shadow
            Color(0xFF4CAF50) -> Color(0xFF2E7D32) // Green shadow
            Color(0xFFFFB300) -> Color(0xFFFF8F00) // Orange shadow
            else -> target.color.copy(alpha = 0.7f)
        }

    Box(
        modifier =
            modifier
                .background(shadowColor, RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.TopCenter,
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
                    .background(target.color, RoundedCornerShape(16.dp))
                    .padding(vertical = 12.dp),
            contentAlignment = Alignment.Center,
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = target.name,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier =
                        Modifier
                            .background(Color.Black.copy(alpha = 0.2f), RoundedCornerShape(50))
                            .padding(horizontal = 8.dp, vertical = 2.dp),
                ) {
                    Text(
                        text = target.distance,
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                    )
                }
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun GameScreenContent(modifier: Modifier = Modifier) {
    val targets =
        listOf(
            PlayerTarget("小華", "2.5km", Color(0xFFFF5252), angle = 45f, isPrimaryTarget = true),
            PlayerTarget("小白", "0.8km", Color(0xFF4CAF50), angle = 135f),
            PlayerTarget("小強", "4.1km", Color(0xFFFFB300), angle = 300f),
        )

    Column(
        modifier =
            modifier
                .fillMaxSize()
                .background(Color(0xFF161F2C)) // Dark radar theme background
                .padding(24.dp),
    ) {
        // Top Header Section
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "我是: ",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "小明",
                    color = Color(0xFFFFD54F),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier =
                        Modifier
                            .size(48.dp)
                            .background(Color(0xFFFF5252), CircleShape),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Leave Room",
                        tint = Color.White,
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "離開房間",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                )
            }
        }

        // Radar / Compass Section 佔據中間的彈性空間
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(vertical = 32.dp),
            contentAlignment = Alignment.Center,
        ) {
            CompassGrid {
                // 繪製目標 (Targets) 在雷達圓盤上
                targets.forEach { target ->
                    // 根據角度和給定的距離計算出在畫面上的 X/Y 偏移量 (offset)
                    val radiusStr = target.distance.replace("km", "").toFloatOrNull() ?: 1.0f
                    // 將公里轉換為畫面上的距離，這裡做一個簡單的倍數對應，最大不超過 140dp
                    val visualRadiusFactor = minOf(radiusStr / 5f, 1f) * 140.dp.value

                    // 把角度轉換為弧度 (減去 90 度讓 0 度朝向正上方)
                    val angleRad = Math.toRadians((target.angle - 90).toDouble())
                    val xOffset = cos(angleRad).toFloat() * visualRadiusFactor
                    val yOffset = sin(angleRad).toFloat() * visualRadiusFactor

                    // 改用精緻的純色圓點和距離標籤來表示其他玩家，不再顯示人名
                    Box(
                        modifier =
                            Modifier
                                .align(Alignment.Center)
                                .offset(x = xOffset.dp, y = yOffset.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            // 顏色點位
                            Box(
                                modifier =
                                    Modifier
                                        .size(16.dp)
                                        .background(target.color, CircleShape)
                                        // 簡單的外發光效果
                                        .padding(2.dp)
                                        .background(target.color, CircleShape),
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = target.distance,
                                color = target.color.copy(alpha = 0.9f),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    }

                    // 移除了 NavigationArrow 方向箭頭

                }

                // 中心點自己 (Self) 的精緻雷達標示 (不再有頭像與文字)
                RadarCenterSelf(modifier = Modifier.align(Alignment.Center))
            }
        }

        // Bottom Target Selection Panel 底部卡片按鈕
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            targets.forEach { target ->
                TargetButton3D(
                    target = target,
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    MaterialTheme {
        GameScreenContent()
    }
}
