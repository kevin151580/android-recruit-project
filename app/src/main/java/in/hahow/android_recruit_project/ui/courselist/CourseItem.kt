package `in`.hahow.android_recruit_project.ui.courselist

data class CourseItem(
    val id: String,
    val title: String,
    val coverImageUrl: String,
    val completionPercentage: Double?,
    val type: Type?,
    val passedAt: String?,
    val assignment: Assignment?,
    val lastViewedAt: String?
) {
    enum class Type {
        SUBSCRIBED_COURSE, ENTERPRISE_ASSIGNED_COURSE
    }

    data class Assignment(
        val assignerName: String,
        val rule: Rule?,
        val dueAt: String?
    ) {
        enum class Rule {
            ELECTIVE, COMPULSORY
        }
    }
}