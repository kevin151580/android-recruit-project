package `in`.hahow.android_recruit_project.domain.repository

import `in`.hahow.android_recruit_project.domain.entity.Course
import `in`.hahow.android_recruit_project.domain.entity.HahowResponse

interface CourseRepository {
    suspend fun getCourses(): HahowResponse<List<Course>>
}