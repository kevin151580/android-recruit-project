package `in`.hahow.android_recruit_project.data.datasource.course

import `in`.hahow.android_recruit_project.domain.entity.Course
import `in`.hahow.android_recruit_project.domain.entity.HahowResponse

interface CourseDataSource {
    suspend fun getCourses(): HahowResponse<List<Course>>
}