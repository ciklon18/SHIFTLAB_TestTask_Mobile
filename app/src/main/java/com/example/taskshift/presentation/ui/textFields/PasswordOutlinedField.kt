package com.example.taskshift.presentation.ui.textFields

import androidx.annotation.StringRes
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.example.taskshift.R
import com.example.taskshift.presentation.ui.enums.FocusManagerAction


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordOutlinedField(
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
    @StringRes stringRes: Int,
    @StringRes errorStringRes: Int?,
    imageVector: ImageVector,
    isPasswordVisible: Boolean,
    isPasswordVisibilityToggle: () -> Unit,
    focusManager: FocusManager,
    focusManagerAction: FocusManagerAction,
    modifier: Modifier = Modifier
) {
    val visibilityIcon = if (isPasswordVisible) {
        R.drawable.hide_password_icon
    } else {
        R.drawable.show_password_icon
    }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { getLabelText(stringRes) },
        isError = isError,
        singleLine = true,
        keyboardOptions = getKeyboardOptions(focusManagerAction),
        leadingIcon = { Icon(imageVector = imageVector, contentDescription = null) },
        trailingIcon = {
            val description = getToggleDescription(isPasswordVisible)
            IconButton(onClick = {
                handleToggleClick(
                    isPasswordVisibilityToggle, focusManagerAction, focusManager
                )
            }) {
                getButtonIcon(visibilityIcon, description)
            }
        },
        visualTransformation = getVisualTransformation(isPasswordVisible),
        supportingText = { errorStringRes?.let { Text(stringResource(errorStringRes)) } },
        modifier = modifier
    )
}

@Composable
private fun getLabelText(@StringRes stringRes: Int) {
    Text(
        text = stringResource(stringRes),
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp
    )
}

@Composable
private fun getButtonIcon(visibilityIcon: Int, description: String) {
    Icon(
        painter = painterResource(visibilityIcon),
        contentDescription = description,
        tint = Color.Black
    )
}

@Composable
private fun getToggleDescription(isPasswordVisible: Boolean): String {
    return if (isPasswordVisible) stringResource(R.string.hide_password_description) else stringResource(
        R.string.show_password_description
    )
}

private fun handleToggleClick(
    isPasswordVisibilityToggle: () -> Unit,
    focusManagerAction: FocusManagerAction,
    focusManager: FocusManager
) {
    isPasswordVisibilityToggle()
    when (focusManagerAction) {
        FocusManagerAction.Next -> focusManager.moveFocus(FocusDirection.Next)
        FocusManagerAction.Exit -> focusManager.clearFocus(true)
    }
}

private fun getKeyboardOptions(
    focusManagerAction: FocusManagerAction
): KeyboardOptions {
    return KeyboardOptions(
        keyboardType = KeyboardType.Password, imeAction = when (focusManagerAction) {
            FocusManagerAction.Next -> ImeAction.Next
            FocusManagerAction.Exit -> ImeAction.Done
        }
    )
}

private fun getVisualTransformation(isPasswordVisible: Boolean): VisualTransformation {
    return if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
}