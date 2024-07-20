package `in`.hahow.android_recruit_project.domain.usecase

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import `in`.hahow.android_recruit_project.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import kotlin.time.Duration.Companion.days

class CourseListFormatTimeUseCase @Inject constructor(
    @ApplicationContext private val context: Context
) : FormatTimeUseCase {
    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.TAIWAN)

    override fun formatLastViewedAt(lastViewedAt: Date?): String? {
        if (lastViewedAt == null) {
            return null
        }
        val lastViewedAtTimeMillis = lastViewedAt.time
        val elapsedTimeMillis = System.currentTimeMillis() - lastViewedAtTimeMillis
        return if (elapsedTimeMillis > 0) {
            val days = elapsedTimeMillis / 1.days.inWholeMilliseconds
            "$days ${context.getString(R.string.watched_days_before)}"
        } else {
            null
        }
    }

    override fun formatStudiedAt(studiedAt: Date?): String? {
        if (studiedAt == null) {
            return null
        }
        return "${simpleDateFormat.format(studiedAt)} ${context.getString(R.string.passed)}"
    }

    override fun formatTimeLeft(dueDate: Date?): String? {
        if (dueDate == null) {
            return null
        }
        val dueTimeMillis = dueDate.time
        val leftTimeMillis = dueTimeMillis - System.currentTimeMillis()
        return when {
            leftTimeMillis >= 8.days.inWholeMilliseconds -> {
                val date = simpleDateFormat.format(dueDate)
                "$date ${context.getString(R.string.due)}"
            }

            leftTimeMillis >= 1.days.inWholeMilliseconds -> {
                val days = leftTimeMillis / 1.days.inWholeMilliseconds
                "${context.getString(R.string.due_in)} $days ${context.getString(R.string.day)}"
            }

            leftTimeMillis >= 0L -> {
                context.getString(R.string.due_today)
            }

            else -> {
                context.getString(R.string.overdue)
            }
        }
    }
}