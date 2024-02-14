package com.nikolovlazar.goodbyemoney.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.nikolovlazar.goodbyemoney.ui.theme.Primary
import com.nikolovlazar.goodbyemoney.ui.theme.TextPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnstyledTextField(
  value: String,
  onValueChange: (String) -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  readOnly: Boolean = false,
  textStyle: TextStyle = LocalTextStyle.current,
  label: @Composable() (() -> Unit)? = null,
  placeholder: @Composable() (() -> Unit)? = null,
  leadingIcon: @Composable() (() -> Unit)? = null,
  trailingIcon: @Composable() (() -> Unit)? = null,
  prefix: @Composable() (() -> Unit)? = null,
  suffix: @Composable() (() -> Unit)? = null,
  supportingText: @Composable() (() -> Unit)? = null,
  isError: Boolean = false,
  visualTransformation: VisualTransformation = VisualTransformation.None,
  keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
  keyboardActions: KeyboardActions = KeyboardActions.Default,
  singleLine: Boolean = false,
  maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
  minLines: Int = 1,
  interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
  shape: Shape = TextFieldDefaults.shape,
  arrangement: Arrangement.Horizontal = Arrangement.Start

) {
  val textColor = TextPrimary
  val mergedTextStyle = textStyle.merge(TextStyle(color = textColor))

  BasicTextField(
    value = value,
    modifier = modifier,
    onValueChange = onValueChange,
    enabled = enabled,
    readOnly = readOnly,
    textStyle = mergedTextStyle,
    cursorBrush = SolidColor(Primary),
    visualTransformation = visualTransformation,
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions,
    interactionSource = interactionSource,
    singleLine = singleLine,
    maxLines = maxLines,
    minLines = minLines,
    decorationBox = @Composable { innerTextField ->
      TextFieldDefaults.DecorationBox(
        value = value,
        innerTextField = innerTextField,
        enabled = enabled,
        singleLine = singleLine,
        visualTransformation = visualTransformation,
        interactionSource = interactionSource,
        isError = isError,
        label = label,
        placeholder = {
          Row( modifier = Modifier.fillMaxWidth(), horizontalArrangement = arrangement){
            placeholder?.invoke()
          }
        },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        prefix = prefix,
        suffix = suffix,
        supportingText = supportingText,
        shape = shape,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp),
        colors = TextFieldDefaults.colors(
          unfocusedIndicatorColor = Color.Transparent,
          focusedIndicatorColor = Color.Transparent,
          disabledIndicatorColor = Color.Transparent,
          cursorColor = Primary,
          focusedContainerColor = Color.Transparent,
          unfocusedContainerColor = Color.Transparent
        )
      )
    }


  )
}