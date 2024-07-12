package `in`.hahow.android_recruit_project.data.datasource.course

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import `in`.hahow.android_recruit_project.data.datasource.dataloader.DataLoader
import `in`.hahow.android_recruit_project.data.datasource.util.toHahowResponse
import `in`.hahow.android_recruit_project.domain.entity.Course
import `in`.hahow.android_recruit_project.domain.entity.HahowResponse
import javax.inject.Inject

class JsonCourseDataSource @Inject constructor(
    private val assetDataLoader: DataLoader,
    private val gson: Gson
) : CourseDataSource {
    override suspend fun getCourses(): HahowResponse<List<Course>> =
        runCatching {
            gson.fromJson<List<CourseResponse>>(
                assetDataLoader.readFileAsString(COURSE_FILE_NAME),
                object : TypeToken<List<CourseResponse>>() {}.type
            )
        }.toHahowResponse {
            map(CourseResponse::convertToCourse)
        }

    companion object {
        private const val COURSE_FILE_NAME = "courses.json"
    }
}