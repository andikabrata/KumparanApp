package com.example.kumparanapp.common.extension

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun Date.toStringDate(pattern: String = "dd MMM yyyy"): String {
    val format = SimpleDateFormat(pattern, Locale("en", "us"))
    return format.format(this)
}