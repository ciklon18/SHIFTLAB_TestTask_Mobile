package com.example.taskshift.presentation.ui.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.taskshift.R
import com.example.taskshift.presentation.ui.theme.LightMainGray
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    navController: NavController,
    startDestination: String
) {
    val alpha = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        alpha.animateTo(
            1f,
            animationSpec = tween(
                durationMillis = 1500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(2000)
        navController.navigate(startDestination)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightMainGray)
            .alpha(alpha.value),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(R.drawable.laboratory_icon), contentDescription = null
        )
    }
}
