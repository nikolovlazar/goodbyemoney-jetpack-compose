package com.nikolovlazar.goodbyemoney.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nikolovlazar.goodbyemoney.components.TableRow
import com.nikolovlazar.goodbyemoney.ui.theme.BackgroundElevated
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
          .clip(Shapes.medium)
          .background(BackgroundElevated)
          .fillMaxWidth()
        ) {
          TableRow("Categories", hasArrow = true)
          TableRow("Erase all data", isDestructive = true)
        }
      }
    }
  )
}