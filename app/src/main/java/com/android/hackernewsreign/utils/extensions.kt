package com.android.hackernewsreign.utils

import android.content.Context
import android.widget.Toast
import com.android.hackernewsreign.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


fun Context.toast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Context.covertTimeToText(dataDate: String?): String {
    val dateFormat = "MM/dd/yyyy"
    var convTime: String? = null
    val pasTime: Date? = getDatefromString(dataDate)
    val mDateTime: String = getDateFromTimeStamp(pasTime?.time, dateFormat)
    if (pasTime == null) {
        return ""
    }
    val nowTime = Date()
    val dateDiff = nowTime.time - pasTime.time
    val second = TimeUnit.MILLISECONDS.toSeconds(dateDiff)
    val minute = TimeUnit.MILLISECONDS.toMinutes(dateDiff)
    val hour = TimeUnit.MILLISECONDS.toHours(dateDiff)
    val day = TimeUnit.MILLISECONDS.toDays(dateDiff)
    convTime = when {
        second in 1..59 -> {
            getString(R.string.seconds, second)
        }
        minute in 1..59 -> {
            getString(R.string.minutes, minute)
        }
        hour in 1..23 -> {
            getString(R.string.hours, hour)
        }
        else -> {
            if (day == 1L){
                getString(R.string.yesterday)
            } else {
                mDateTime
            }
        }
    }
    return convTime
}

fun getDateFromTimeStamp(time: Long?, mDateFormat: String?): String {
    val dateFormat = SimpleDateFormat(mDateFormat)
    dateFormat.timeZone = TimeZone.getTimeZone("UTC")
    val dateTime = Date(time!!)
    return dateFormat.format(dateTime)
}


fun getDatefromString(date: String?): Date? {
    return getDatefromString(date, "yyyy-MM-dd'T'HH:mm:ss")
}

fun getDatefromString(date: String?, format: String?): Date? {
    val formatter = SimpleDateFormat(format)
    formatter.timeZone = TimeZone.getTimeZone("UTC")
    var dateResult: Date? = null
    try {
        dateResult = formatter.parse(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return dateResult
}
