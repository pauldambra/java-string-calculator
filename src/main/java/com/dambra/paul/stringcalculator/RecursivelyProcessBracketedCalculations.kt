package com.dambra.paul.stringcalculator

private fun replaceBracketsWithResult(parts: List<String>, start: Int, end: Int, result: String): List<String> {
    val filteredParts = parts.filterIndexed { i, _ -> i < start || i > end }.toMutableList()
    filteredParts.add(start, result)
    return filteredParts.toList()
}

private fun calculateInsideBrackets(parts: List<String>, start: Int, end: Int) =
        StringCalculator().calculate(getContentsOfBrackets(parts, start, end))

private fun getContentsOfBrackets(parts: List<String>, start: Int, end: Int) =
        parts.subList(start + 1, end).joinToString("")

fun List<String>.recursivelyProcessBracketedCalculations(): List<String> {
    if (!this.contains("(")) {
        return this
    }

    val start = this.indexOf("(")
    val end = this.indexOf(")")

    val result = calculateInsideBrackets(this, start, end)

    val filteredParts = replaceBracketsWithResult(this, start, end, result)

    return filteredParts.recursivelyProcessBracketedCalculations()
}