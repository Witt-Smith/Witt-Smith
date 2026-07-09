package com.example.wittsmithh.ui.compoents

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SecondaryScrollableTabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun TabRowPreview() {
    MaterialTheme {
        TabRow()
    }
}

@Composable
fun TabRow() {
    val type = listOf("R&B", "Hip-Hop", "Pop", "Rock", "Jazz", "Electronic", "Soul", "K-Pop", "Lofi", "Trap", "Metal", "Classical", "Reggae")
    var selectedIndex by remember { mutableIntStateOf(0) }

    SecondaryScrollableTabRow(
        selectedTabIndex = selectedIndex,
        modifier = Modifier.padding(vertical = 8.dp),
        contentColor = MaterialTheme.colorScheme.primary,
        containerColor = Color.Transparent,
        edgePadding = 16.dp,
        indicator = {
            Box(
                Modifier
                    .tabIndicatorOffset(selectedIndex)
                    .fillMaxHeight()
                    .padding(vertical = 4.dp)
                    .background(
                        color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.45f),
                        shape = CircleShape
                    )
            )
        },
        divider = {}
    ) {
        type.forEachIndexed { index, string ->
            TabsItem(
                text = string,
                isSelected = selectedIndex == index,
                onClick = { selectedIndex = index }
            )
        }
    }
}

@Composable
fun TabsItem(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val textMorph = lerp(start = if (isSelected) 20.sp else 14.sp, stop = if (isSelected) 20.sp else 14.sp, fraction = 0.5f)
    val heightAnimate by animateDpAsState(
        targetValue = if (isSelected) 65.dp else 48.dp,
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        ),
        label = "HeightAnimate"
    )
    Box(
        modifier = Modifier
            .height(heightAnimate)
            .clip(CircleShape)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick
            )
            .padding(horizontal = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (isSelected) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.labelLarge,
            fontSize = textMorph,
            fontWeight = FontWeight.Bold ,
            textAlign = TextAlign.Center
        )
    }
}