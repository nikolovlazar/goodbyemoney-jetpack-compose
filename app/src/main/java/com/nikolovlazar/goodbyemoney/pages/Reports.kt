package com.nikolovlazar.goodbyemoney.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.nikolovlazar.goodbyemoney.R
import com.nikolovlazar.goodbyemoney.components.ReportPage
import com.nikolovlazar.goodbyemoney.components.charts.MonthlyChart
import com.nikolovlazar.goodbyemoney.components.charts.WeeklyChart
import com.nikolovlazar.goodbyemoney.components.charts.YearlyChart
import com.nikolovlazar.goodbyemoney.components.expensesList.ExpensesList
import com.nikolovlazar.goodbyemoney.mock.mockExpenses
import com.nikolovlazar.goodbyemoney.models.Recurrence
import com.nikolovlazar.goodbyemoney.ui.theme.LabelSecondary
import com.nikolovlazar.goodbyemoney.ui.theme.TopAppBarBackground
import com.nikolovlazar.goodbyemoney.ui.theme.Typography
import com.nikolovlazar.goodbyemoney.viewmodels.ReportsViewModel
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun Reports(vm: ReportsViewModel = viewModel()) {
  val uiState = vm.uiState.collectAsState().value

  val recurrences = listOf(
    Recurrence.Weekly,
    Recurrence.Monthly,
    Recurrence.Yearly
  )

  Scaffold(
    topBar = {
      MediumTopAppBar(
        title = { Text("Reports") },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
          containerColor = TopAppBarBackground
        ),
        actions = {
          IconButton(onClick = vm::openRecurrenceMenu) {
            Icon(
              painterResource(id = R.drawable.ic_today),
              contentDescription = "Change recurrence"
            )
          }
          DropdownMenu(
            expanded = uiState.recurrenceMenuOpened,
            onDismissRequest = vm::closeRecurrenceMenu
          ) {
            recurrences.forEach { recurrence ->
              DropdownMenuItem(text = { Text(recurrence.name) }, onClick = {
                vm.setRecurrence(recurrence)
                vm.closeRecurrenceMenu()
              })
            }
          }
        }
      )
    },
    content = { innerPadding ->
      val numOfPages = when (uiState.recurrence) {
        Recurrence.Weekly -> 53
        Recurrence.Monthly -> 12
        Recurrence.Yearly -> 1
        else -> 53
      }
      HorizontalPager(count = numOfPages, reverseLayout = true) { page ->
        ReportPage(innerPadding, page, uiState.recurrence)
      }
    }
  )
}