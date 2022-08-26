package com.rsstudio.weather.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

class AppHelper {

    companion object {

        @RequiresApi(Build.VERSION_CODES.O)
        fun getWeekDayName(s: String?): String? {
            val dtfInput: DateTimeFormatter = DateTimeFormatter.ofPattern("u-M-d", Locale.ENGLISH)
         var temp =  LocalDate.parse(s, dtfInput).dayOfWeek
                .getDisplayName(TextStyle.FULL, Locale.ENGLISH)

            var answer = ""

            for (i in 0..2) {
                answer += temp[i]
            }
            return answer
        }

        fun getPatternOfDate(date: String): String {
            var format = date.split("-").toTypedArray()
            return format[2] + "/" + format[1]
        }

    }
}