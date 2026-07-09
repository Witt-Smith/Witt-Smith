package com.example.wittsmith.ui.screens

//noinspection SuspiciousImport
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.QueueMusic
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Lyrics
import androidx.compose.material.icons.outlined.Pause
import androidx.compose.material.icons.outlined.SkipNext
import androidx.compose.material.icons.outlined.SkipPrevious
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.wittsmith.ui.themes.WittSmithTheme
import kotlin.math.sin

@Composable
fun PlayerScreen() {
    val colorScheme = MaterialTheme.colorScheme
    val bgColor = colorScheme.surface
    val cardColor = colorScheme.surfaceVariant
    val accentColor = colorScheme.primary
    val secondaryTextColor = colorScheme.onSurface.copy(alpha = 0.7f)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(colorScheme.onSurface.copy(alpha = 0.2f))
                    .weight(0.15f),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Back",
                    tint = colorScheme.onSurface
                )
            }

            Text(
                modifier = Modifier
                    .weight(0.4f)
                    .padding(start = 10.dp),
                text = "Now Playing",
                color = colorScheme.onSurface,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            )

            Row(
                modifier = Modifier
                    .weight(0.2f),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(
                            topStart = 50.dp,
                            topEnd = 12.dp,
                            bottomStart = 50.dp,
                            bottomEnd = 12.dp
                        ))
                        .background(colorScheme.onSurface.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Lyrics,
                        contentDescription = "Lyrics",
                        tint = colorScheme.onSurface
                    )
                }
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(
                            topStart = 12.dp,
                            topEnd = 50.dp,
                            bottomStart = 12.dp,
                            bottomEnd = 50.dp
                        ))
                        .background(colorScheme.onSurface.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.QueueMusic,
                        contentDescription = "Queue",
                        tint = colorScheme.onSurface
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

        // Album Art
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(32.dp)),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = "https://images.unsplash.com/photo-1614613535308-eb5fbd3d2c17?q=80&w=2070&auto=format&fit=crop",
                contentDescription = "Album Art",
                modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(32.dp)).background(colorScheme.surfaceVariant),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(48.dp))

        // Song Info
        Text(
            text = "The Emptiness Machine",
            color = colorScheme.onSurface,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Linkin Park",
            color = secondaryTextColor,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        // Wavy Seek Bar
        Column(modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            WavySlider(
                progress = 0.9f,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp),
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "01:21", color = secondaryTextColor, fontSize = 12.sp)
                Text(text = "03:10", color = secondaryTextColor, fontSize = 12.sp)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Playback Controls
        Row(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(0.4f).padding(5.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.25f)
                    .clip(CircleShape)
                    .background(colorScheme.primary.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.SkipPrevious,
                    contentDescription = "Previous",
                    tint = colorScheme.onSurface,
                    modifier = Modifier.size(32.dp)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.25f)
                    .clip(RoundedCornerShape(35.dp))
                    .background(accentColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Pause,
                    contentDescription = "Pause",
                    tint = colorScheme.onPrimary,
                    modifier = Modifier.size(48.dp)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.25f)
                    .clip(CircleShape)
                    .background(colorScheme.primary.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.SkipNext,
                    contentDescription = "Next",
                    tint = colorScheme.onSurface,
                    modifier = Modifier.size(32.dp)
                )
            }

        }

        Spacer(modifier = Modifier.height(48.dp))

        // Bottom Controls
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            shape = RoundedCornerShape(40.dp),
            color = cardColor

        ) {
            Row(
                modifier = Modifier.fillMaxSize().padding(10.dp).background(Color.Transparent),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    modifier = Modifier
                        .weight(0.3f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(
                            topStart = 50.dp,
                            topEnd = 14.dp,
                            bottomStart = 50.dp,
                            bottomEnd = 14.dp
                        )).background(colorScheme.onSurface.copy(alpha = 0.2f)),
                    onClick = { }
                ) {
                    Icon(imageVector = Icons.Default.Shuffle, contentDescription = "Shuffle", tint = colorScheme.onSurface)
                }

                IconButton(
                    modifier = Modifier
                        .weight(0.3f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(
                            topStart = 16.dp,
                            topEnd = 16.dp,
                            bottomStart = 16.dp,
                            bottomEnd = 16.dp
                        )).background(colorScheme.onSurface.copy(alpha = 0.2f)),
                    onClick = { }
                ) {
                    Icon(imageVector = Icons.Default.Repeat, contentDescription = "Repeat", tint = colorScheme.onSurface)
                }

                IconButton(
                    modifier = Modifier
                        .weight(0.3f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(
                            topStart = 14.dp,
                            topEnd = 50.dp,
                            bottomStart = 14.dp,
                            bottomEnd = 50.dp
                        ))
                        .background(colorScheme.onSurface.copy(alpha = 0.2f)),
                    onClick = { }
                ) {
                    Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = "Like", tint = colorScheme.onSurface)
                }
            }
        }
    }
}

@Composable
fun WavySlider(progress: Float, modifier: Modifier = Modifier) {
    val colorScheme = MaterialTheme.colorScheme
    val trackColor = colorScheme.onSurface.copy(alpha = 0.2f)
    Canvas(
        modifier = modifier
    ) {
        val height = size.height
        val width = size.width
        val centerY = height / 2
        val waveWidth = width * progress
        val waveColor = colorScheme.primary
        val path = Path()
        val amplitude = 4.dp.toPx()
        var x = 0f
        drawLine(
            color = trackColor,
            start = Offset(x, centerY),
            end = Offset(width, centerY),
            strokeWidth = 4.dp.toPx(),
            cap = StrokeCap.Round,
            alpha = 1f,
        )
        path.moveTo(0f, centerY)
        while (x < waveWidth) {
            val y = centerY + sin(x * 0.05.toFloat()) * amplitude
            path.lineTo(x, y)
            x += 1f
        }

        drawPath(
            path = path,
            color = waveColor,
            style = Stroke(width = 7.dp.toPx(), cap = StrokeCap.Round)
        )

        // Draw thumb
        drawCircle(
            color = waveColor,
            radius = 7.dp.toPx(),
            center = Offset(waveWidth, centerY + sin(x * 0.05.toFloat()) * amplitude)
        )
    }
}

@Preview(showBackground = true, device = "id:pixel_10")
@Composable
fun Preview() {
    WittSmithTheme{
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            PlayerScreen()
        }
    }
}