package pro.devapp.newslist.di

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.room.Room
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import pro.devapp.newslist.application.DATA_BASE_NAME
import pro.devapp.newslist.application.TIMEOUT
import pro.devapp.newslist.logic.DataController
import pro.devapp.newslist.logic.presenters.ListPresenter
import pro.devapp.newslist.logic.presenters.MainPresenter
import pro.devapp.newslist.logic.presenters.ViewNewsPresenter
import pro.devapp.newslist.storage.database.AppDataBase
import pro.devapp.newslist.storage.database.DataRepository
import pro.devapp.newslist.storage.database.RoomDataRepository
import pro.devapp.newslist.storage.serverapi.Api
import pro.devapp.newslist.storage.serverapi.ServerApi
import java.util.concurrent.TimeUnit

fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .build()
}

fun createDataBase(context: Context): AppDataBase {
    return Room.databaseBuilder(
        context,
        AppDataBase::class.java, DATA_BASE_NAME
    )
        .build()
}

fun createGson(): Gson {
    return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
}

val appModule = module {

    single<Api> { ServerApi(get(), createGson()) }
    single<DataRepository> { RoomDataRepository(get()) }
    single { DataController(get(), get()) }
    single { createOkHttpClient() }
    single { createDataBase(get()) }

    factory { ListPresenter(get(), get()) }
    factory { ViewNewsPresenter(get()) }
    factory { (supportFragmentManager: FragmentManager) -> MainPresenter(supportFragmentManager) }
}