package com.dambra.paul.stringcalculator

internal object SplitToStringOperations {

    fun from(s: String) =
            splitParts(s.replace("\\s+".toRegex(), ""))

    private fun splitParts(input: String): MutableList<String> =
            input.fold(mutableListOf()) { acc, c ->
                if (Character.isDigit(c)) {
                    accumulateADigit(acc, c)
                } else {
                    foundOperator(acc, c)
                }
                acc
            }

    // ugh, side effects
    private fun accumulateADigit(acc: MutableList<String>, c: Char) {
        if (acc.isEmpty()) {
            acc.add(c.toString())
        } else {
            var last = acc[acc.size - 1]
            if (last.matches("[^0-9]".toRegex())) {
                acc.add("")
                last = acc[acc.size - 1]
            }
            val current = last + c
            acc[acc.size - 1] = current
        }
    }

    //ugh, side effects
    private fun foundOperator(acc: MutableList<String>, c: Char?) = acc.add(c.toString())
}
