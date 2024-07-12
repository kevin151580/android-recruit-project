package `in`.hahow.android_recruit_project.data.datasource.util

import `in`.hahow.android_recruit_project.domain.entity.HahowResponse

fun <T, R> Result<T>.toHahowResponse(
    convert: T.() -> R
): HahowResponse<R> =
    fold(
        onSuccess = {
            HahowResponse.Success(
                data = it.convert()
            )
        },
        onFailure = {
            HahowResponse.Fail(exception = it)
        }
    )