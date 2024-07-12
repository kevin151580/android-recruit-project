package `in`.hahow.android_recruit_project.domain.entity

sealed class HahowResponse<T> {
    class Success<T>(
        val data: T
    ) : HahowResponse<T>()

    class Fail<T>(
        val exception: Throwable
    ) : HahowResponse<T>()
}