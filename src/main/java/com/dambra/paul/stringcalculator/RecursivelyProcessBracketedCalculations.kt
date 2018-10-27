package com.dambra.paul.stringcalculator

internal object RecursivelyProcessBracketedCalculations {
    fun from(parts: MutableList<String>): MutableList<String> {
        if (!parts.contains("(")) {
            return parts
        }

        val start = parts.indexOf("(")
        val end = parts.indexOf(")")

        val result = calculateInsideBrackets(parts, start, end)

        val filteredParts = replaceBracketsWithResult(parts, start, end, result)

        return RecursivelyProcessBracketedCalculations.from(filteredParts)
    }

    private fun replaceBracketsWithResult(parts: MutableList<String>, start: Int, end: Int, result: String): MutableList<String> {
        val filteredParts = parts.filterIndexed { i, _ -> i < start || i > end }.toMutableList()
        filteredParts.add(start, result)
        return filteredParts
    }

    private fun calculateInsideBrackets(parts: MutableList<String>, start: Int, end: Int) =
            StringCalculator().calculate(getContentsOfBrackets(parts, start, end))

    private fun getContentsOfBrackets(parts: MutableList<String>, start: Int, end: Int) =
            parts.subList(start + 1, end).joinToString("")

}
