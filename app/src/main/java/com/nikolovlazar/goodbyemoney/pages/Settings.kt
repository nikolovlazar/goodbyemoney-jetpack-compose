package com.nikolovlazar.goodbyemoney.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.nikolovlazar.goodbyemoney.components.TableRow
import com.nikolovlazar.goodbyemoney.ui.theme.BackgroundElevated
import com.nikolovlazar.goodbyemoney.ui.theme.DividerColor
import com.nikolovlazar.goodbyemoney.ui.theme.Shapes
import com.nikolovlazar.goodbyemoney.ui.theme.TopAppBarBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Settings(navController: NavController) {
  Scaffold(
    topBar = {
      MediumTopAppBar(title = { Text("Settings") }, colors = TopAppBarDefaults.mediumTopAppBarColors(
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
          TableRow(label = "Categories", hasArrow = true, modifier = Modifier.clickable {
            navController.navigate("settings/categories")
          })
          Divider(modifier = Modifier
            .padding(start = 16.dp), thickness = 1.dp, color = DividerColor)
          TableRow(label = "Erase all data", isDestructive = true)
        }
      }
    }
  )
}