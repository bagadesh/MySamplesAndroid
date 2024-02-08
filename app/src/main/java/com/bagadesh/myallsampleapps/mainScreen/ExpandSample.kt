//package com.bagadesh.myallsampleapps.mainScreen
//
//import androidx.compose.animation.core.animateFloatAsState
//import androidx.compose.animation.core.tween
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.layoutId
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.constraintlayout.compose.ExperimentalMotionApi
//import androidx.constraintlayout.compose.MotionLayout
//
///**
// * Created by bagadesh on 20/11/22.
// */
//
//@OptIn(ExperimentalMotionApi::class)
//@Preview
//@Composable
//fun DynamicIsland4() {
//    var expanded by remember { mutableStateOf(false) }
//    val progress by animateFloatAsState(
//        targetValue = if (expanded) 1f else 0f,
//        animationSpec = tween(1000)
//    )
//    var colorBackgroundState by remember { mutableStateOf(Color.Black) }
//
//    var heightState by remember { mutableStateOf(48.dp) }
//    var widthState by remember { mutableStateOf(196.dp) }
//    var percentCornerState by remember {
//        mutableStateOf(50)
//    }
//
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp), Alignment.Center
//    ) {
//        MotionLayout(
//            modifier = Modifier
//                .width(widthState)
//                .height(heightState)
//                .clip(shape = RoundedCornerShape(percent = percentCornerState))
//                .clickable { expanded = !expanded },
//            motionScene = getMotionSceneForDemo4(),
//            progress = progress
//        ) {
//            val colorBackground = motionProperties(id = "surface").value.color("color")
//            colorBackground.let { colorBackgroundState = it }
//
//            val newHeight = motionProperties(id = "surface").value.float("height")
//            newHeight.let { heightState = it.dp }
//
//            val newWidth = motionProperties(id = "surface").value.float("width")
//            newWidth.let { widthState = it.dp }
//
//            val percentCorner = motionProperties(id = "surface").value.int("corner")
//            percentCorner.let { percentCornerState = it }
//
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .clip(shape = RoundedCornerShape(percent = percentCornerState))
//                    .layoutId("surface")
//                    .background(colorBackgroundState)
//            )
//            Image(
//                painter = painterResource(R.drawable.solana_icon),
//                contentDescription = "solanaLogo",
//                contentScale = ContentScale.Crop,            // crop the image if it's not a square
//                modifier = Modifier
//                    .layoutId("solanaLogo")
//                    .size(34.dp)
//            )
//            Text(
//                text = "+1.69%",
//                fontWeight = FontWeight.Bold,
//                fontSize = 18.sp,
//                color = Color.fromHex("#28E9A3"),
//                modifier = Modifier
//                    .layoutId("labelText")
//            )
//
//            Image(
//                painter = painterResource(R.drawable.chart_solana),
//                contentDescription = "solanaChart",
//                contentScale = ContentScale.Crop,            // crop the image if it's not a square
//                modifier = Modifier
//                    .layoutId("solanaChart")
//            )
//
//
//            // Expand dynamic island
//            Image(
//                painter = painterResource(R.drawable.binance_logo),
//                contentDescription = "binanceLogo",
//                contentScale = ContentScale.Crop,            // crop the image if it's not a square
//                modifier = Modifier
//                    .layoutId("binanceLogo")
//            )
//            RowLabel(
//                modifier = Modifier
//                    .layoutId("rowLabel")
//            )
//            RowPrice(
//                modifier = Modifier
//                    .layoutId("rowPrice")
//            )
//            RowButtons(
//                modifier = Modifier
//                    .layoutId("rowButtons")
//            )
//        }
//
//    }
//}
//
//@Composable
//fun RowLabel(modifier: Modifier) {
//    Row(
//        modifier
//            .fillMaxWidth()
//            .padding(start = 16.dp, end = 16.dp),
//        horizontalArrangement = Arrangement.SpaceBetween
//    ) {
//        Text(
//            text = "Bitcoin",
//            fontSize = 18.sp,
//            color = Color.fromHex("#ffffff")
//        )
//        Text(
//            text = "Ethereum",
//            fontSize = 18.sp,
//            color = Color.fromHex("#ffffff")
//        )
//        Text(
//            text = "Solana",
//            fontWeight = FontWeight.Bold,
//            style = TextStyle(textDecoration = TextDecoration.Underline),
//            fontSize = 18.sp,
//            color = Color.fromHex("#F3BA2F")
//        )
//        Text(
//            text = "Theter",
//            fontSize = 18.sp,
//            color = Color.fromHex("#ffffff")
//        )
//    }
//}
//
//@Composable
//fun RowPrice(modifier: Modifier) {
//    Column(
//        modifier
//            .fillMaxWidth()
//            .padding(start = 16.dp, end = 16.dp)
//    ) {
//        Row(verticalAlignment = Alignment.CenterVertically) {
//            Image(
//                painter = painterResource(R.drawable.solana_icon),
//                contentDescription = "solanaLogo",
//                contentScale = ContentScale.Crop,            // crop the image if it's not a square
//                modifier = Modifier
//                    .size(24.dp)
//            )
//            Text(
//                modifier = Modifier.padding(start = 8.dp),
//                text = "Solana",
//                color = Color.White,
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Bold,
//            )
//        }
//        Row(
//            modifier
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(
//                text = "MX$ 606.43", color = Color.White,
//                fontSize = 30.sp,
//                fontWeight = FontWeight.Bold,
//            )
//            Text(
//                modifier = Modifier
//                    .clip(shape = RoundedCornerShape(percent = 50))
//                    .background(Color.fromHex("#29C57A"))
//                    .padding(8.dp),
//                text = "+1.69%",
//                color = Color.White
//            )
//        }
//    }
//}
//
//@Composable
//fun RowButtons(modifier: Modifier) {
//    Row(
//        modifier
//            .fillMaxWidth()
//            .padding(start = 16.dp, end = 16.dp),
//        horizontalArrangement = Arrangement.SpaceBetween,
//    ) {
//        Text(
//            modifier = Modifier
//                .padding(end = 4.dp)
//                .weight(1f)
//                .clip(shape = RoundedCornerShape(percent = 50))
//                .background(Color.fromHex("#29C57A"))
//                .padding(16.dp),
//            text = "Buy",
//            textAlign = TextAlign.Center,
//            fontWeight = FontWeight.Bold,
//            color = Color.White
//        )
//        Text(
//            modifier = Modifier
//                .padding(start = 4.dp)
//                .weight(1f)
//                .clip(shape = RoundedCornerShape(percent = 50))
//                .background(Color.fromHex("#F55572"))
//                .padding(16.dp),
//            text = "Sell",
//            textAlign = TextAlign.Center,
//            fontWeight = FontWeight.Bold,
//            color = Color.White
//        )
//    }
//}
//
//private fun Color.Companion.fromHex(colorString: String): Color {
//    return Color(android.graphics.Color.parseColor(colorString))
//}
//
//
//@SuppressLint("Range")
//@OptIn(ExperimentalMotionApi::class)
//@Composable
//fun getMotionSceneForDemo4(): MotionScene {
//    return MotionScene {
//        val start1 = constraintSet {
//            val surface = createRefFor("surface")
//            val solanaLogo = createRefFor("solanaLogo")
//            val labelText = createRefFor("labelText")
//            val solanaChart = createRefFor("solanaChart")
//            val binanceLogo = createRefFor("binanceLogo")
//            val rowLabel = createRefFor("rowLabel")
//            val rowPrice = createRefFor("rowPrice")
//            val rowButtons = createRefFor("rowButtons")
//
//            constrain(surface) {
//                customFloat("width", 196f)
//                customFloat("height", 48f)
//                customFloat("corner", 50f)
//                customColor("color", Color.fromHex("#000000"))
//            }
//
//            constrain(solanaLogo) {
//                top.linkTo(parent.top)
//                start.linkTo(parent.start, 8.dp)
//                bottom.linkTo(parent.bottom)
//            }
//
//            constrain(labelText) {
//                top.linkTo(parent.top)
//                start.linkTo(solanaLogo.end)
//                bottom.linkTo(parent.bottom)
//            }
//
//            constrain(solanaChart) {
//                top.linkTo(parent.top)
//                end.linkTo(parent.end, 16.dp)
//                bottom.linkTo(parent.bottom)
//            }
//
//            // Expand dynamic island
//
//            constrain(binanceLogo) {
//                top.linkTo(parent.top, 22.dp)
//                start.linkTo(parent.start, 22.dp)
//                scaleX = 0f
//                scaleY = 0f
//                alpha = 0f
//
//            }
//
//            constrain(rowLabel) {
//                top.linkTo(binanceLogo.bottom, 16.dp)
//                start.linkTo(parent.start, 22.dp)
//                end.linkTo(parent.end, 22.dp)
//                scaleX = 0f
//                scaleY = 0f
//                alpha = 0f
//            }
//
//            constrain(rowPrice) {
//                top.linkTo(rowLabel.bottom, 16.dp)
//                start.linkTo(parent.start, 22.dp)
//                end.linkTo(parent.end, 22.dp)
//                scaleX = 0f
//                scaleY = 0f
//                alpha = 0f
//            }
//
//            constrain(rowButtons) {
//                top.linkTo(rowPrice.bottom, 8.dp)
//                start.linkTo(parent.start, 22.dp)
//                end.linkTo(parent.end, 22.dp)
//                scaleX = 0f
//                scaleY = 0f
//                alpha = 0f
//            }
//        }
//        val end1 = constraintSet {
//            val surface = createRefFor("surface")
//            val solanaLogo = createRefFor("solanaLogo")
//            val labelText = createRefFor("labelText")
//            val solanaChart = createRefFor("solanaChart")
//            val binanceLogo = createRefFor("binanceLogo")
//            val rowLabel = createRefFor("rowLabel")
//            val rowPrice = createRefFor("rowPrice")
//            val rowButtons = createRefFor("rowButtons")
//
//            constrain(surface) {
//                customFloat("width", 340f)
//                customFloat("height", 239f)
//                customFloat("corner", 10f)
//                customColor("color", Color.fromHex("#000000"))
//            }
//            constrain(solanaLogo) {
//                top.linkTo(parent.top)
//                start.linkTo(parent.start, 8.dp)
//                bottom.linkTo(parent.bottom)
//                scaleX = 0f
//                scaleY = 0f
//                alpha = 0f
//            }
//            constrain(labelText) {
//                top.linkTo(parent.top)
//                start.linkTo(solanaLogo.end)
//                bottom.linkTo(parent.bottom)
//                scaleX = 0f
//                scaleY = 0f
//                alpha = 0f
//            }
//            constrain(solanaChart) {
//                top.linkTo(parent.top)
//                end.linkTo(parent.end, 16.dp)
//                bottom.linkTo(parent.bottom)
//                scaleX = 0f
//                scaleY = 0f
//                alpha = 0f
//            }
//
//            // Expand dynamic island
//
//            constrain(binanceLogo) {
//                top.linkTo(parent.top, 22.dp)
//                start.linkTo(parent.start, 22.dp)
//                scaleX = 1f
//                scaleY = 1f
//            }
//
//            constrain(rowLabel) {
//                top.linkTo(binanceLogo.bottom, 16.dp)
//                start.linkTo(parent.start, 22.dp)
//                end.linkTo(parent.end, 22.dp)
//                scaleX = 1f
//                scaleY = 1f
//            }
//
//            constrain(rowPrice) {
//                top.linkTo(rowLabel.bottom, 16.dp)
//                start.linkTo(parent.start, 22.dp)
//                end.linkTo(parent.end, 22.dp)
//                scaleX = 1f
//                scaleY = 1f
//            }
//
//            constrain(rowButtons) {
//                top.linkTo(rowPrice.bottom, 8.dp)
//                start.linkTo(parent.start, 22.dp)
//                end.linkTo(parent.end, 22.dp)
//                scaleX = 1f
//                scaleY = 1f
//            }
//
//        }
//        transition("default", start1, end1) {}
//    }
//}