package com.cold0.crier.social.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.cold0.crier.social.composant.DraggableImage
import com.cold0.crier.social.model.ImageHolder
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

// ------------------------------------------------
// Public Layouts
// ------------------------------------------------
@Composable
fun ImageLayout1(image: ImageHolder, painter: Painter) {
    SingleImage(
        image = image, painter = painter, Modifier
            .fillMaxWidth()
            .aspectRatio(image.width / image.height.toFloat())
    )
}

@Composable
fun ImageLayout2(imageList: List<ImageHolder>, painterList: List<Painter>) {
    Row(horizontalArrangement = Arrangement.Center) {
        SingleImage(
            imageList[0], painterList[0],
            Modifier
                .fillMaxWidth(0.5f)
                .height(200.dp)
                .padding(end = 2.dp)
        )
        SingleImage(
            imageList[1], painterList[1],
            Modifier
                .fillMaxWidth(1.0f)
                .height(200.dp)
                .padding(start = 2.dp)
        )
    }
}

@Composable
fun ImageLayout3(imageList: List<ImageHolder>, painterList: List<Painter>) {
    Row(horizontalArrangement = Arrangement.Center) {
        SingleImage(
            imageList[0], painterList[0],
            Modifier
                .fillMaxWidth(0.5f)
                .height(200.dp)
                .padding(end = 2.dp)
        )
        DoubleImageColumn(painterList[1], painterList[2], imageList[1], imageList[2], PaddingValues(start = 2.dp), 1.0f)
    }
}

@Composable
fun ImageLayout4(imageList: List<ImageHolder>, painterList: List<Painter>) {
    Row(horizontalArrangement = Arrangement.Center) {
        DoubleImageColumn(painterList[0], painterList[1], imageList[0], imageList[1], PaddingValues(end = 2.dp), 0.5f)
        DoubleImageColumn(painterList[2], painterList[3], imageList[2], imageList[3], PaddingValues(start = 2.dp), 1.0f)
    }
}

@Composable
fun ImageLayoutN(imageList: List<ImageHolder>, painterList: List<Painter>) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0.5f, 0.5f, 0.5f, .1f))
    ) {
        val pagerState = rememberPagerState()
        HorizontalPager(
            count = imageList.size,
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
        ) { page ->
            SingleImage(
                imageList[page], painterList[page],
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

// ------------------------------------------------
// Private Build Block
// ------------------------------------------------

@Composable
private fun SingleImage(image: ImageHolder, painter: Painter, modifier: Modifier) {
    var openFull by remember { mutableStateOf(false) }
    Image(
        painter = painter,
        "",
        modifier = modifier.then(
            Modifier
                .background(image.colorAverage)
                .clickable(onClick = {
                    openFull = true
                })
        ),
        contentScale = ContentScale.Crop
    )

    if (openFull) {
        Dialog(onDismissRequest = { openFull = false }, properties = DialogProperties(usePlatformDefaultWidth = false)) {
            Surface(
                color = lerp(MaterialTheme.colors.surface, image.colorAverage, 0.2f),
                modifier = Modifier
                    .fillMaxSize()
            ) {
                DraggableImage(image, painter)
            }
        }
    }
}

@Composable
private fun DoubleImageColumn(painter: Painter, painter2: Painter, image: ImageHolder, image2: ImageHolder, paddingValues: PaddingValues, fraction: Float) {
    Column(
        modifier = Modifier
            .fillMaxWidth(fraction)
            .height(200.dp)
            .padding(paddingValues)
    ) {
        SingleImage(
            image, painter,
            Modifier
                .fillMaxWidth(1.0f)
                .fillMaxHeight(0.5f)
                .padding(bottom = 2.dp)
        )
        SingleImage(
            image2, painter2,
            Modifier
                .fillMaxWidth(1.0f)
                .fillMaxHeight(1.0f)
                .padding(top = 2.dp)
        )
    }
}