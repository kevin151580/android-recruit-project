package `in`.hahow.android_recruit_project.data.datasource.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateHelper {
    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.TAIWAN)

    fun String.parseDate(): Date? =
        runCatching {
            simpleDateFormat.parse(this)
        }.getOrNull()
}