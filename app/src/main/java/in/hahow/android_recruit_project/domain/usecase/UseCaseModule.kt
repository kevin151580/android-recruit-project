package `in`.hahow.android_recruit_project.domain.usecase

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    @Singleton
    abstract fun bindFormatTimeUseCase(
        courseListFormatTimeUseCase: CourseListFormatTimeUseCase
    ): FormatTimeUseCase
}