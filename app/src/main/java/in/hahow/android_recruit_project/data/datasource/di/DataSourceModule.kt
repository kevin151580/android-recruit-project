package `in`.hahow.android_recruit_project.data.datasource.di

import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import `in`.hahow.android_recruit_project.data.datasource.course.CourseDataSource
import `in`.hahow.android_recruit_project.data.datasource.course.JsonCourseDataSource
import `in`.hahow.android_recruit_project.data.datasource.dataloader.AssetDataLoader
import `in`.hahow.android_recruit_project.data.datasource.dataloader.DataLoader
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindDataLoader(
        assetDataLoader: AssetDataLoader
    ): DataLoader

    @Binds
    @Singleton
    abstract fun bindCourseDataLoader(
        jsonCourseDataSource: JsonCourseDataSource
    ): CourseDataSource
}

@Module
@InstallIn(SingletonComponent::class)
object DataSourceProvideModule {
    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()
}