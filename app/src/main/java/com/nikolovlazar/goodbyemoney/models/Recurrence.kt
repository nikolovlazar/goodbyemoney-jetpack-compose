package com.nikolovlazar.goodbyemoney.models

sealed class Recurrence(val name: String, val target: String) {
  object None : Recurrence("None", "None")
  object Daily : Recurrence("Daily", "Today")
  object Weekly : Recurrence("Weekly", "This week")
  object Monthly : Recurrence("Monthly", "This month")
  object Yearly : Recurrence("Yearly", "This year")
}