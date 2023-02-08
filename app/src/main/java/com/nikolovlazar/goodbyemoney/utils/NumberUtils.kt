package com.nikolovlazar.goodbyemoney.utils

import java.text.DecimalFormat

fun simplifyNumber(value: Float): String {
  return when {
    value >= 1000 && value < 1_000_000 -> DecimalFormat("0.#K").format(value / 1000)
    value >= 1_000_000 -> DecimalFormat("0.#M").format(value / 1_000_000)
    else -> DecimalFormat("0.#").format(value)
  }
}