package com.nikolovlazar.goodbyemoney.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikolovlazar.goodbyemoney.mock.mockExpenses
import com.nikolovlazar.goodbyemoney.models.Expense
import com.nikolovlazar.goodbyemoney.models.Recurrence
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.YearMonth

data class State(
  val expenses: List<Expense> = mockExpenses,
  val dateStart: LocalDateTime = LocalDateTime.now(),
  val dateEnd: LocalDateTime = LocalDateTime.now(),
  val avgPerDay: Double = 0.0,
  val totalInRange: Double = 0.0
)

class ReportPageViewModel(private val page: Int, val recurrence: Recurrence) :
  ViewModel() {
  private val _uiState = MutableStateFlow(State())
  val uiState: StateFlow<State> = _uiState.asStateFlow()

  private lateinit var start: LocalDate
  private lateinit var end: LocalDate
  private var daysInRange = 1

  init {
    viewModelScope.launch(Dispatchers.IO) {
      val today = LocalDate.now()
      when (recurrence) {
        Recurrence.Weekly -> {
          start =
            LocalDate.now().minusDays(today.dayOfWeek.value.toLong() - 1)
              .minusDays((page * 7).toLong())
          end = start.plusDays(6)
          daysInRange = 7
        }
        Recurrence.Monthly -> {
          start =
            LocalDate.of(today.year, today.month, 1)
              .minusMonths(page.toLong())
          val numberOfDays =
            YearMonth.of(start.year, start.month).lengthOfMonth()
          end = start.plusDays(numberOfDays.toLong())
          daysInRange = numberOfDays
        }
        Recurrence.Yearly -> {
          start = LocalDate.of(today.year, 1, 1)
          end = LocalDate.of(today.year, 12, 31)
          daysInRange = 365
        }
        else -> Unit
      }

      val filteredExpenses = mockExpenses.filter { expense ->
        (expense.date.toLocalDate().isAfter(start) && expense.date.toLocalDate()
          .isBefore(end)) || expense.date.toLocalDate()
          .isEqual(start) || expense.date.toLocalDate().isEqual(end)
      }

      val totalExpensesAmount = filteredExpenses.sumOf { it.amount }
      val avgPerDay: Double = totalExpensesAmount / daysInRange

      viewModelScope.launch(Dispatchers.Main) {
        _uiState.update { currentState ->
          currentState.copy(
            dateStart = LocalDateTime.of(start, LocalTime.MIN),
            dateEnd = LocalDateTime.of(end, LocalTime.MAX),
            expenses = filteredExpenses,
            avgPerDay = avgPerDay,
            totalInRange = totalExpensesAmount
          )
        }
      }
    }
  }
}