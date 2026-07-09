package com.example.wittsmith.ui.glancewidget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun Carousels() {
    data class CarouselItem(
        val album: String,
        val label: String,
    )

    val items = remember {
        listOf(
            CarouselItem("","For you "),
            CarouselItem("","For you "),
            CarouselItem("","For you "),
            CarouselItem("","For you "),
            CarouselItem("","For you "),
            CarouselItem("","For you "),
            CarouselItem("","For you "),
            CarouselItem("","For you "),
            CarouselItem("","For you "),

        )
    }

    Column(
        modifier =  Modifier
            .fillMaxWidth()
            .aspectRatio(1.65f)
            .padding(start = 15.dp, end = 15.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            text = "It's New Music Friday!",
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 2,
            modifier = Modifier.fillMaxWidth()
                .padding(start = 6.dp, end = 5.dp, bottom = 10.dp)
        )
        HorizontalMultiBrowseCarousel(
            state = rememberCarouselState { items.count() },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 16.dp, bottom = 16.dp),
            preferredItemWidth = 186.dp,
            itemSpacing = 15.dp,
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) { i ->
            val item = items[i]
            val info = carouselItemDrawInfo
            val fraction = (info.size - info.minSize) / (info.maxSize - info.minSize)
            CarouselView(
                album = item.album,
                label = item.label,
                modifier = Modifier
                    .height(205.dp)
                    .maskClip(MaterialTheme.shapes.extraLarge),
                progress = fraction
            )
        }
    }
}

@Composable
private fun CarouselView(
    album: String,
    label: String,
    modifier: Modifier,
    progress: Float
) {
    Box(
        modifier = modifier
    ) {
        AsyncImage(
            model = "",
            contentDescription = album,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(30.dp))
                .background(
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                )
        )

        Text(
            text = label,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .alpha(progress+0.0005f)
                .padding(
                    start = 20.dp,
                    end = 12.dp,
                    bottom = 12.dp
                ),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 2,
            textAlign = TextAlign.Start
        )
    }
}

