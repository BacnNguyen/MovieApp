package gst.trainingcourse.movie.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gst.trainingcourse.movie.data.repo.*
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindSharedPref(impl: SharedPrefRepoImpl): SharedPrefRepo

    @Binds
    @Singleton
    abstract fun bindLocalRepo(impl: LocalRepoImpl): LocalRepo

    @Binds
    @Singleton
    abstract fun bindRemoteRepo(impl: RemoteRepoImpl): RemoteRepo
}
