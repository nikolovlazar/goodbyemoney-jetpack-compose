package com.nikolovlazar.goodbyemoney.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun LocalDate.formatDay(): String {
  val today = LocalDate.now()
  val yesterday = today.minusDays(1)

  return when {
    this.isEqual(today) -> "Today"
    this.isEqual(yesterday) -> "Yesterday"
    this.year != today.year -> this.format(DateTimeFormatter.ofPattern("ddd, dd MMM yyyy"))
    else -> this.format(DateTimeFormatter.ofPattern("E, dd MMM"))
  }
}