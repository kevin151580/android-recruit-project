package `in`.hahow.android_recruit_project.data.datasource.course

import `in`.hahow.android_recruit_project.data.datasource.util.DateHelper.parseDate
import `in`.hahow.android_recruit_project.domain.entity.Course
import `in`.hahow.android_recruit_project.domain.entity.Course.Assignment.Assigner

data class CourseResponse(
    val __typename: String?,
    val id: String?,
    val title: String?,
    val coverImageUrl: String?,
    val totalSeconds: Int?,
    val enrollmentsCount: Int?,
    val averageRating: Double?,
    val level: String?,
    val completionStatus: String?,
    val completionPercentage: Double?,
    val source: String?,
    val studiedAt: String?,
    val enrolled: Boolean?,
    val teacher: Teacher?,
    val recentStartedAssignment: Assignment?,
    val lastViewedAt: String?,
    val accessExpiredReason: String?
) {
    data class Teacher(
        val __typename: String?,
        val name: String?
    )

    data class Assignment(
        val __typename: String?,
        val id: String?,
        val title: String?,
        val assigners: List<Assigner>?,
        val rule: String?,
        val completedAt: String?,
        val timeline: Timeline?
    )

    data class Assigner(
        val __typename: String?,
        val id: String?,
        val name: String?
    )

    data class Timeline(
        val __typename: String?,
        val startAt: String?,
        val dueAt: String?
    )

    enum class Level {
        BEGINNER, INTERMEDIATE, ADVANCED
    }

    enum class CompletionStatus {
        STUDYING, COMPLETED
    }

    enum class Source {
        UNLIMITED_PRODUCT, TENANT_COURSE
    }

    enum class Rule {
        ELECTIVE, COMPULSORY
    }

    fun convertToCourse() =
        Course(
            id = id.orEmpty(),
            title = title.orEmpty(),
            coverImageUrl = coverImageUrl.orEmpty(),
            totalSeconds = totalSeconds,
            enrollmentsCount = enrollmentsCount,
            averageRating = averageRating,
            level = when (level) {
                Level.BEGINNER.name -> {
                    Course.Level.BEGINNER
                }

                Level.INTERMEDIATE.name -> {
                    Course.Level.INTERMEDIATE
                }

                Level.ADVANCED.name -> {
                    Course.Level.ADVANCED
                }

                else -> {
                    null
                }
            },
            completionStatus = when (completionStatus) {
                CompletionStatus.STUDYING.name -> {
                    Course.CompletionStatus.STUDYING
                }

                CompletionStatus.COMPLETED.name -> {
                    Course.CompletionStatus.COMPLETED
                }

                else -> {
                    null
                }
            },
            completionPercentage = completionPercentage,
            type = when (source) {
                Source.UNLIMITED_PRODUCT.name -> {
                    Course.Type.SUBSCRIBED_COURSE
                }

                Source.TENANT_COURSE.name -> {
                    Course.Type.ENTERPRISE_ASSIGNED_COURSE
                }

                else -> {
                    null
                }
            },
            studiedAt = studiedAt?.parseDate(),
            enrolled = enrolled,
            teacher = teacher?.run {
                Course.Teacher(
                    name = name.orEmpty()
                )
            },
            assignment = recentStartedAssignment?.run {
                Course.Assignment(
                    id = id.orEmpty(),
                    title = title.orEmpty(),
                    assigners = assigners?.map {
                        Assigner(
                            id = it.id.orEmpty(),
                            name = it.name.orEmpty(),
                        )
                    },
                    rule = when (rule) {
                        Rule.ELECTIVE.name -> {
                            Course.Assignment.Rule.ELECTIVE
                        }

                        Rule.COMPULSORY.name -> {
                            Course.Assignment.Rule.COMPULSORY
                        }

                        else -> {
                            null
                        }
                    },
                    completedAt = completedAt?.parseDate(),
                    timeline = timeline?.run {
                        Course.Assignment.Timeline(
                            startAt = startAt?.parseDate(),
                            dueAt = dueAt?.parseDate()
                        )
                    }
                )
            },
            lastViewedAt = lastViewedAt?.parseDate(),
            accessExpiredReason = accessExpiredReason.orEmpty()
        )
}