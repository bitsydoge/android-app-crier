package com.cold0.crier.social.screens.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.cold0.crier.social.model.ImageHolder
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@Composable
fun ImageLayout1(image: ImageHolder, painter: Painter) {
    Image(
        painter = painter,
        "",
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(image.width / image.height.toFloat())
            .background(image.colorAverage),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ImageLayout2(imageList: List<ImageHolder>, painterList: List<Painter>) {
    Row(horizontalArrangement = Arrangement.Center) {
        Image(
            painter = painterList[0],
            "",
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(200.dp)
                .padding(end = 2.dp)
                .background(imageList[0].colorAverage),
            contentScale = ContentScale.Crop
        )
        Image(
            painter = painterList[1],
            "",
            modifier = Modifier
                .fillMaxWidth(1.0f)
                .height(200.dp)
                .padding(start = 2.dp)
                .background(imageList[1].colorAverage),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun ImageLayout3(imageList: List<ImageHolder>, painterList: List<Painter>) {
    Row(horizontalArrangement = Arrangement.Center) {
        Image(
            painter = painterList[0],
            "",
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(200.dp)
                .padding(end = 2.dp)
                .background(imageList[0].colorAverage),
            contentScale = ContentScale.Crop
        )
        TwoImageColumn(painterList[1], painterList[2], imageList[1], imageList[2], PaddingValues(start = 2.dp), 1.0f)
    }
}

@Composable
fun ImageLayout4(imageList: List<ImageHolder>, painterList: List<Painter>) {
    Row(horizontalArrangement = Arrangement.Center) {
        TwoImageColumn(painterList[0], painterList[1], imageList[0], imageList[1], PaddingValues(end = 2.dp), 0.5f)
        TwoImageColumn(painterList[2], painterList[3], imageList[2], imageList[3], PaddingValues(start = 2.dp), 1.0f)
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
            Image(
                painter = painterList[page],
                "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(imageList[page].colorAverage),
                contentScale = ContentScale.Crop
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

@Composable
private fun TwoImageColumn(painter: Painter, painter2: Painter, image: ImageHolder, image2: ImageHolder, paddingValues: PaddingValues, fraction: Float) {
    Column(
        modifier = Modifier
            .fillMaxWidth(fraction)
            .height(200.dp)
            .padding(paddingValues)
    ) {
        Image(
            painter = painter,
            "",
            modifier = Modifier
                .fillMaxWidth(1.0f)
                .fillMaxHeight(0.5f)
                .padding(bottom = 2.dp)
                .background(image.colorAverage),
            contentScale = ContentScale.Crop
        )
        Image(
            painter = painter2,
            "",
            modifier = Modifier
                .fillMaxWidth(1.0f)
                .fillMaxHeight(1.0f)
                .padding(top = 2.dp)
                .background(image2.colorAverage),
            contentScale = ContentScale.Crop
        )
    }
}