package com.nikolovlazar.goodbyemoney.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nikolovlazar.goodbyemoney.components.TableRow
import com.nikolovlazar.goodbyemoney.components.UnstyledTextField
import com.nikolovlazar.goodbyemoney.ui.theme.BackgroundElevated
import com.nikolovlazar.goodbyemoney.ui.theme.DividerColor
import com.nikolovlazar.goodbyemoney.ui.theme.Shapes
import com.nikolovlazar.goodbyemoney.ui.theme.TopAppBarBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Add(navController: NavController) {
  Scaffold(
    topBar = {
      MediumTopAppBar(title = { Text("Add") }, colors = TopAppBarDefaults.mediumTopAppBarColors(
        containerColor = TopAppBarBackground
      ))
    },
    content = {innerPadding ->
      Column(modifier = Modifier.padding(innerPadding)) {
        Column(modifier = Modifier
          .padding(16.dp)
          .clip(Shapes.large)
          .background(BackgroundElevated)
          .fillMaxWidth()
        ) {
          TableRow("Amount") {
            UnstyledTextField(
              value = "whaaa",
              onValueChange = {},
              modifier = Modifier.fillMaxWidth(),
              textStyle = TextStyle(
                textAlign = TextAlign.End
              ),
              keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
              )
            )
          }
          Divider(modifier = Modifier
            .padding(start = 16.dp), thickness = 1.dp, color = DividerColor
          )
          TableRow("Recurrence")
          Divider(modifier = Modifier
            .padding(start = 16.dp), thickness = 1.dp, color = DividerColor
          )
          TableRow("Date")
          Divider(modifier = Modifier
            .padding(start = 16.dp), thickness = 1.dp, color = DividerColor
          )
          TableRow("Note") {
            UnstyledTextField(
              value = "",
              onValueChange = {},
              placeholder = {Text("Leave some notes")},
              modifier = Modifier.fillMaxWidth().height(44.dp),
              textStyle = TextStyle(
                textAlign = TextAlign.End,
              ),
              keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
              )
            )
          }
          Divider(modifier = Modifier
            .padding(start = 16.dp), thickness = 1.dp, color = DividerColor
          )
          TableRow("Category")
        }
      }
    }
  )
}