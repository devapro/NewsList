package pro.devapp.newslist.logic

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pro.devapp.newslist.storage.database.DataRepository
import pro.devapp.newslist.storage.mapToDbEntity
import pro.devapp.newslist.storage.serverapi.Api
import pro.devapp.newslist.storage.serverapi.entity.EntityApiNews
import java.lang.Exception


class DataController(private val api: Api, private val dataRepository: DataRepository) {

    private val TAG = DataController::class.java.simpleName

    val errorMessage = MutableLiveData<String>()

    fun loadSinceData(time: Long){
        GlobalScope.launch {
            val newsTotalCount = dataRepository.getNewsTotalCount() + 19
            val page = (newsTotalCount/20) + 1
            loadPage(page)
        }
    }

    fun loadInitialData(){
        GlobalScope.launch {
            loadPage(1)
        }
    }

    private suspend fun loadPage(page: Int){
        Log.d(TAG, "page $page")
        withContext(Dispatchers.IO){
            try {
                val items = api.loadNextPage(page)
                Log.d(TAG, items.size.toString())
                for (item in items){
                    val news = dataRepository.findNewsByUrl(item.url)
                    if (news == null){
                        saveItem(item)
                    }
                    else {
                        updateItem(news.id, item)
                    }
                }
            }
            catch (e: Exception){
                errorMessage.postValue(e.message)
                Log.d(TAG, e.message)
                e.printStackTrace()
            }
        }
    }

    private fun updateItem(id: Long, entityApiNews: EntityApiNews){
        val entity = entityApiNews.mapToDbEntity(id)
        dataRepository.saveNews(entity)
    }

    private fun saveItem(entityApiNews: EntityApiNews){
        val entity = entityApiNews.mapToDbEntity(0)
        dataRepository.saveNews(entity)
    }
}