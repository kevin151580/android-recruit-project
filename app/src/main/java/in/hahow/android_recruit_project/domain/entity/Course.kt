package `in`.hahow.android_recruit_project.domain.entity

import java.util.Date

data class Course(
    val id: String,
    val title: String,
    val coverImageUrl: String,
    val totalSeconds: Int?,
    val enrollmentsCount: Int?,
    val averageRating: Double?,
    val level: Level?,
    val completionStatus: CompletionStatus?,
    val completionPercentage: Double?,
    val type: Type?,
    val studiedAt: Date?,
    val enrolled: Boolean?,
    val teacher: Teacher?,
    val assignment: Assignment?,
    val lastViewedAt: Date?,
    val accessExpiredReason: String
) {
    enum class Level {
        BEGINNER, INTERMEDIATE, ADVANCED
    }

    enum class CompletionStatus {
        STUDYING, COMPLETED
    }

    enum class Type {
        SUBSCRIBED_COURSE, ENTERPRISE_ASSIGNED_COURSE
    }

    data class Teacher(
        val name: String
    )

    data class Assignment(
        val id: String,
        val title: String,
        val assigners: List<Assigner>?,
        val rule: Rule?,
        val completedAt: Date?,
        val timeline: Timeline?
    ) {
        data class Assigner(
            val id: String,
            val name: String
        )

        enum class Rule {
            ELECTIVE, COMPULSORY
        }

        data class Timeline(
            val startAt: Date?,
            val dueAt: Date?
        )
    }
}