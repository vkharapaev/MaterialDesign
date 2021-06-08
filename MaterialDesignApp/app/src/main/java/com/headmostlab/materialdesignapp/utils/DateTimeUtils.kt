package com.headmostlab.materialdesignapp.utils

import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils {
    private val simpleFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    fun format(date: Date): String {
        return simpleFormatter.format(date)
    }
}