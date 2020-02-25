package pro.devapp.newslist.application

import android.app.Application
import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import pro.devapp.newslist.di.appModule
import pro.devapp.newslist.logic.DataController
import pro.devapp.newslist.storage.database.AppDataBase
import pro.devapp.newslist.storage.database.DataRepository
import pro.devapp.newslist.storage.database.RoomDataRepository
import pro.devapp.newslist.storage.serverapi.ServerApi

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Android context
            androidContext(this@App)
            // modules
            modules(appModule)
        }
    }
}