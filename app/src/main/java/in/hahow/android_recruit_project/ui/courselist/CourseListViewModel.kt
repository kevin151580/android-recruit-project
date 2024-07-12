package `in`.hahow.android_recruit_project.ui.courselist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.hahow.android_recruit_project.R
import `in`.hahow.android_recruit_project.domain.entity.Course
import `in`.hahow.android_recruit_project.domain.entity.HahowResponse
import `in`.hahow.android_recruit_project.domain.repository.CourseRepository
import `in`.hahow.android_recruit_project.domain.usecase.FormatTimeUseCase
import `in`.hahow.android_recruit_project.ui.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseListViewModel @Inject constructor(
    private val courseRepository: CourseRepository,
    private val formatTimeUseCase: FormatTimeUseCase
) : ViewModel() {
    val courseListUiState = MutableLiveData<UiState<List<CourseItem>>>()

    init {
        fetchCourses()
    }

    fun fetchCourses() {
        viewModelScope.launch {
            courseListUiState.value = UiState.Loading
            courseRepository.getCourses().let {
                when (it) {
                    is HahowResponse.Success -> {
                        courseListUiState.value = UiState.Ready(
                            it.data.map(::convertToCourseItem)
                        )
                    }

                    is HahowResponse.Fail -> {
                        courseListUiState.value = UiState.Fail(R.string.cannot_get_course_data)
                    }
                }
            }
        }
    }

    private fun convertToCourseItem(course: Course): CourseItem =
        CourseItem(
            id = course.id,
            title = course.title,
            coverImageUrl = course.coverImageUrl,
            completionPercentage = course.completionPercentage,
            type = when (course.type) {
                Course.Type.SUBSCRIBED_COURSE -> {
                    CourseItem.Type.SUBSCRIBED_COURSE
                }

                Course.Type.ENTERPRISE_ASSIGNED_COURSE -> {
                    CourseItem.Type.ENTERPRISE_ASSIGNED_COURSE
                }

                else -> {
                    null
                }
            },
            passedAt = formatTimeUseCase.formatStudiedAt(course.studiedAt),
            assignment = course.assignment?.let {
                CourseItem.Assignment(
                    assignerName = with(it.assigners) {
                        if (isNullOrEmpty()) {
                            ""
                        } else {
                            get(0).name
                        }
                    },
                    rule = when (it.rule) {
                        Course.Assignment.Rule.ELECTIVE -> {
                            CourseItem.Assignment.Rule.ELECTIVE
                        }

                        Course.Assignment.Rule.COMPULSORY -> {
                            CourseItem.Assignment.Rule.COMPULSORY
                        }

                        else -> {
                            null
                        }
                    },
                    dueAt = formatTimeUseCase.formatTimeLeft(it.timeline?.dueAt)
                )
            },
            lastViewedAt = formatTimeUseCase.formatLastViewedAt(course.lastViewedAt)
        )
}