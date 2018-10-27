package com.dambra.paul.stringcalculator

internal object RemoveTrailingPointZero {
    fun from(result: Double) =
            result.toString().replaceFirst("\\.0$".toRegex(), "")
}
