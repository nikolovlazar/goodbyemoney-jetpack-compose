package com.nikolovlazar.goodbyemoney.models

import java.time.LocalDate
import java.time.LocalDateTime

data class Expense(
  val id: Int,
  val amount: Double,
  val recurrence: Recurrence,
  val date: LocalDateTime,
  val note: String?,
  val category: Category,
)

data class DayExpenses(
  val expenses: MutableList<Expense>,
  var total: Double,
)

fun List<Expense>.groupedByDay(): Map<LocalDate, DayExpenses> {
  val dataMap: MutableMap<LocalDate, DayExpenses> = mutableMapOf()

  this.forEach { expense ->
    val date = expense.date.toLocalDate()

    if (dataMap[date] == null) {
      dataMap[date] = DayExpenses(
        expenses = mutableListOf(),
        total = 0.0
      )
    }

    dataMap[date]!!.expenses.add(expense)
    dataMap[date]!!.total = dataMap[date]!!.total.plus(expense.amount)
  }

  dataMap.values.forEach { dayExpenses ->
    dayExpenses.expenses.sortBy { expense -> expense.date }
  }

  return dataMap.toSortedMap(compareByDescending { it })
}

fun List<Expense>.groupedByDayOfWeek(): Map<String, DayExpenses> {
  val dataMap: MutableMap<String, DayExpenses> = mutableMapOf()

  this.forEach { expense ->
    val dayOfWeek = expense.date.toLocalDate().dayOfWeek

    if (dataMap[dayOfWeek.name] == null) {
      dataMap[dayOfWeek.name] = DayExpenses(
        expenses = mutableListOf(),
        total = 0.0
      )
    }

    dataMap[dayOfWeek.name]!!.expenses.add(expense)
    dataMap[dayOfWeek.name]!!.total = dataMap[dayOfWeek.name]!!.total.plus(expense.amount)
  }

  return dataMap.toSortedMap(compareByDescending { it })
}

fun List<Expense>.groupedByDayOfMonth(): Map<Int, DayExpenses> {
  val dataMap: MutableMap<Int, DayExpenses> = mutableMapOf()

  this.forEach { expense ->
    val dayOfMonth = expense.date.toLocalDate().dayOfMonth

    if (dataMap[dayOfMonth] == null) {
      dataMap[dayOfMonth] = DayExpenses(
        expenses = mutableListOf(),
        total = 0.0
      )
    }

    dataMap[dayOfMonth]!!.expenses.add(expense)
    dataMap[dayOfMonth]!!.total = dataMap[dayOfMonth]!!.total.plus(expense.amount)
  }

  return dataMap.toSortedMap(compareByDescending { it })
}

fun List<Expense>.groupedByMonth(): Map<String, DayExpenses> {
  val dataMap: MutableMap<String, DayExpenses> = mutableMapOf()

  this.forEach { expense ->
    val month = expense.date.toLocalDate().month

    if (dataMap[month.name] == null) {
      dataMap[month.name] = DayExpenses(
        expenses = mutableListOf(),
        total = 0.0
      )
    }

    dataMap[month.name]!!.expenses.add(expense)
    dataMap[month.name]!!.total = dataMap[month.name]!!.total.plus(expense.amount)
  }

  return dataMap.toSortedMap(compareByDescending { it })
}