package com.mulatya.lib.internal.pattern

object CountryPattern : Pattern{

    private const val PATTERN_DIGIT_GROUP = "(\\d+)"
    private const val PATTERN_DIGIT = "\\d"

    override fun create(number: String): String{

        return number
            .replace(PATTERN_DIGIT_GROUP.toRegex(), "[\$0]")
            .replace(PATTERN_DIGIT.toRegex(), "0")

    }
}