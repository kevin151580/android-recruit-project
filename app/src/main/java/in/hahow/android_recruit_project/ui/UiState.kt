package `in`.hahow.android_recruit_project.ui

import androidx.annotation.StringRes

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()

    data class Ready<T>(
        val data: T
    ) : UiState<T>()

    data class Fail(
        @StringRes
        val message: Int
    ) : UiState<Nothing>()
}