package com.assesment.shared.extension

import java.text.SimpleDateFormat
import java.util.Locale

fun String.toStandardDate(): String {
    try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val date = inputFormat.parse(this)
        if (date != null) {
            return outputFormat.format(date)
        }

        return this
    } catch (e: Exception) {
        return this
    }
}