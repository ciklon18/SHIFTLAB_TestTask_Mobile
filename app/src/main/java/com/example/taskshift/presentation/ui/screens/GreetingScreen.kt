package com.example.taskshift.presentation.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskshift.R
import com.example.taskshift.presentation.viewmodel.RegistrationViewModel

@Composable
fun GreetingPage(
    viewModel: RegistrationViewModel,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,

    ) {
    var showDialog by remember { mutableStateOf(false) }
    Box(
        modifier = modifier
            .padding(20.dp)
            .fillMaxSize()
            .clickable {  },
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = { showDialog = true },
            modifier = Modifier
                .align(Alignment.Center),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 4.dp,
                pressedElevation = 8.dp
            )
        ) {
            Text(
                text = stringResource(R.string.greeting_page),
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 30.sp,
                lineHeight = 35.sp,
            )
        }
    }
    if (showDialog) {
        showGreetingToast(context, viewModel.getUserName())
        showDialog = false
    }
}

fun showGreetingToast(context: Context, userName: String) {
    Toast.makeText(
        context, context.getString(R.string.greeting_message, userName), Toast.LENGTH_SHORT
    ).show()
}