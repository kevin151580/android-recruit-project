package `in`.hahow.android_recruit_project.data.repository

import `in`.hahow.android_recruit_project.data.datasource.course.CourseDataSource
import `in`.hahow.android_recruit_project.domain.entity.Course
import `in`.hahow.android_recruit_project.domain.entity.HahowResponse
import `in`.hahow.android_recruit_project.domain.repository.CourseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CourseRepositoryImpl @Inject constructor(
    private val jsonCourseDataSource: CourseDataSource
) : CourseRepository {
    override suspend fun getCourses(): HahowResponse<List<Course>> =
        withContext(Dispatchers.IO) {
            jsonCourseDataSource.getCourses()
        }
}