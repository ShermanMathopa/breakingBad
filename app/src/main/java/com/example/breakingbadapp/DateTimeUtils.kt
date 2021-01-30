package com.example.breakingbadapp

import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

class DateTimeUtils {
    companion object {
        val defaultZoneId: ZoneId by lazy { ZoneId.of("UTC") }

        fun getCurrentDateTime(): ZonedDateTime = ZonedDateTime.now(defaultZoneId)

        fun getCurrentLocalDate(): LocalDate = getCurrentDateTime().toLocalDate()

        fun getYearsSince(date: LocalDate): Int =
            ChronoUnit.YEARS.between(date, getCurrentLocalDate()).toInt()


    }
}