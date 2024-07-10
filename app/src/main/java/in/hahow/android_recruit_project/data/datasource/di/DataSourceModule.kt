package `in`.hahow.android_recruit_project.data.datasource.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
}