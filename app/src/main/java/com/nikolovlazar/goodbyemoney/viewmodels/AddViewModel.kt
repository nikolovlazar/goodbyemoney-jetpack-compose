package com.nikolovlazar.goodbyemoney.viewmodels

import androidx.lifecycle.ViewModel
import com.nikolovlazar.goodbyemoney.models.Recurrence
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate

data class AddScreenState(
  val amount: String = "",
  val recurrence: Recurrence? = null,
  val date: LocalDate = LocalDate.now(),
  val note: String = "",
  val category: String? = null, // TODO: Replace when you build the Category model
)

class AddViewModel: ViewModel() {
  private val _uiState = MutableStateFlow(AddScreenState())
  val uiState: StateFlow<AddScreenState> = _uiState.asStateFlow()

  fun setAmount(amount: String) {
    _uiState.update {currentState ->
      currentState.copy(
        amount = amount.trim(),
      )
    }
  }

  fun setRecurrence(recurrence: Recurrence) {
    _uiState.update {currentState ->
      currentState.copy(
        recurrence = recurrence,
      )
    }
  }

  fun setDate(date: LocalDate) {
    _uiState.update {currentState ->
      currentState.copy(
        date = date,
      )
    }
  }

  fun setNote(note: String) {
    _uiState.update {currentState ->
      currentState.copy(
        note = note,
      )
    }
  }

  fun setCategory(category: String) { // TODO: replace String with actual Category class
    _uiState.update {currentState ->
      currentState.copy(
        category = category,
      )
    }
  }

  fun submitExpense() {
    // TODO: Save to local db
  }
}