package com.nikolovlazar.goodbyemoney.pages

import android.app.DatePickerDialog
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nikolovlazar.goodbyemoney.components.TableRow
import com.nikolovlazar.goodbyemoney.components.UnstyledTextField
import com.nikolovlazar.goodbyemoney.ui.theme.*
import java.time.LocalDate
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Add(navController: NavController) {
//  TODO: refactor this into a ViewModel because we're losing the values when
//   changing orientation
  val recurrences = listOf(
    "None",
    "Daily",
    "Weekly",
    "Monthly",
    "Yearly"
  )
  var selectedRecurrence by remember {
    mutableStateOf(recurrences[0])
  }
  val categories = listOf("Groceries", "Bills", "Hobbies", "Take out")
  var selectedCategory by remember {
    mutableStateOf(categories[0])
  }

  val mContext = LocalContext.current

  val mYear: Int
  val mMonth: Int
  val mDay: Int

  val mCalendar = Calendar.getInstance()

  mYear = mCalendar.get(Calendar.YEAR)
  mMonth = mCalendar.get(Calendar.MONTH) + 1
  mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

  var mDate by remember {
    mutableStateOf("${mCalendar.get(Calendar.DAY_OF_MONTH)}-${mCalendar.get(Calendar.MONTH) + 1}-${mCalendar.get(Calendar.YEAR)}")
  }

  val mDatePicker = DatePickerDialog(
    mContext,
    { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
      mDate = "${selectedDay}-${selectedMonth + 1}-${selectedYear}"
    },
    mYear,
    mMonth,
    mDay
  )
  mDatePicker.datePicker.maxDate = mCalendar.timeInMillis

  Scaffold(
    topBar = {
      MediumTopAppBar(
        title = { Text("Add") },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
          containerColor = TopAppBarBackground
        )
      )
    },
    content = { innerPadding ->
      Column(
        modifier = Modifier.padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Column(
          modifier = Modifier
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
                textAlign = TextAlign.Right,
              ),
              keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
              )
            )
          }
          Divider(
            modifier = Modifier
              .padding(start = 16.dp), thickness = 1.dp, color = DividerColor
          )
          TableRow("Recurrence") {
            var recurrenceMenuOpened by remember {
              mutableStateOf(false)
            }
            TextButton(
              onClick = { recurrenceMenuOpened = true },
              shape = Shapes.large
            ) {
              Text(selectedRecurrence)
              DropdownMenu(
                expanded = recurrenceMenuOpened,
                onDismissRequest = { recurrenceMenuOpened = false }) {
                recurrences.forEach { recurrence ->
                  DropdownMenuItem(
                    text = { Text(recurrence) },
                    onClick = {
                      selectedRecurrence = recurrence
                      recurrenceMenuOpened = false
                    }
                  )
                }
              }
            }
          }
          Divider(
            modifier = Modifier
              .padding(start = 16.dp), thickness = 1.dp, color = DividerColor
          )
          TableRow("Date") {
            TextButton(onClick = { mDatePicker.show() }) {
              Text(mDate)
            }
          }
          Divider(
            modifier = Modifier
              .padding(start = 16.dp), thickness = 1.dp, color = DividerColor
          )
          TableRow("Note") {
            UnstyledTextField(
              value = "",
              onValueChange = {},
              modifier = Modifier.fillMaxWidth(),
              textStyle = TextStyle(
                textAlign = TextAlign.Right,
              ),
            )
          }
          Divider(
            modifier = Modifier
              .padding(start = 16.dp), thickness = 1.dp, color = DividerColor
          )
          TableRow("Category") {
            var categoriesMenuOpened by remember {
              mutableStateOf(false)
            }
            TextButton(
              onClick = { categoriesMenuOpened = true },
              shape = Shapes.large
            ) {
              Text(selectedCategory)
              DropdownMenu(
                expanded = categoriesMenuOpened,
                onDismissRequest = { categoriesMenuOpened = false }) {
                categories.forEach { category ->
                  DropdownMenuItem(
                    text = {
                      Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(modifier = Modifier.size(10.dp), shape = CircleShape, color = Primary) {}
                        Text(category, modifier = Modifier.padding(start = 8.dp))
                      }
                    },
                    onClick = {
                      selectedCategory = category
                      categoriesMenuOpened = false
                    }
                  )
                }
              }
            }
          }
        }
        Button(
          onClick = { /*TODO*/ },
          modifier = Modifier.padding(16.dp),
          shape = Shapes.large
        ) {
          Text("Submit expense")
        }
      }
    }
  )
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewAdd() {
  GoodbyeMoneyTheme {
    val navController = rememberNavController()
    Add(navController = navController)
  }
}