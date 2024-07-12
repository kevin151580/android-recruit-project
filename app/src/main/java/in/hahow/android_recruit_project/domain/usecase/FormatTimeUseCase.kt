package `in`.hahow.android_recruit_project.domain.usecase

import java.util.Date

interface FormatTimeUseCase {
    fun formatLastViewedAt(lastViewedAt: Date?): String?
    fun formatStudiedAt(studiedAt: Date?): String?
    fun formatTimeLeft(dueDate: Date?): String?
}