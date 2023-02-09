package com.nikolovlazar.goodbyemoney.models

sealed class Recurrence(val name: String, val target: String) {
  object None : Recurrence("None", "None")
  object Daily : Recurrence("Daily", "Today")
  object Weekly : Recurrence("Weekly", "This week")
  object Monthly : Recurrence("Monthly", "This month")
  object Yearly : Recurrence("Yearly", "This year")
}

fun String.toRecurrence(): Recurrence {
  return when(this) {
    "None" -> Recurrence.None
    "Daily" -> Recurrence.Daily
    "Weekly" -> Recurrence.Weekly
    "Monthly" -> Recurrence.Monthly
    "Yearly" -> Recurrence.Yearly
    else -> Recurrence.None
  }
}