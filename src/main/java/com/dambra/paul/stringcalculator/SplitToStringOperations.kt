package com.dambra.paul.stringcalculator


fun String.splitToStringOperations(): List<String> =
        this.fold(listOf()) { acc, c ->
            when {
                c.isWhitespace() -> acc
                acc.isEmpty() -> listOf(c.toString())
                c.isDigit() && isNumeric(acc.last()) -> accumulateADigit(acc, acc.last(), c)
                else -> acc + c.toString()
            }
        }

private fun isNumeric(s: String) = s.toLongOrNull() != null

private fun accumulateADigit(acc: List<String>, current: String, next: Char) =
        acc.dropLast(1) + (current + next)