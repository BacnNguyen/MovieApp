package gst.trainingcourse.movie.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import gst.trainingcourse.movie.data.room.AppDatabase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(appContext, AppDatabase::class.java, "main.db").build()
    }

    @Provides
    @Singleton
    fun provideAppDao(appDatabase: AppDatabase) = appDatabase.appDao()

    @Provides
    @Singleton
    fun provideGson() = Gson()
}
