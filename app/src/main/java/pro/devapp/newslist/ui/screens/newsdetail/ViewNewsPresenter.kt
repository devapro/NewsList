package pro.devapp.newslist.ui.screens.newsdetail

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pro.devapp.newslist.logic.mapToModel
import pro.devapp.newslist.logic.models.ModelItemNews
import pro.devapp.newslist.storage.database.DataRepository

class ViewNewsPresenter(private val dataRepository: DataRepository) {
    val news = MutableLiveData<ModelItemNews>()

    fun setNewsId(newsId: Long){
        GlobalScope.launch {
            loadNews(newsId)
        }
    }

    private suspend fun loadNews(newsId: Long){
        withContext(Dispatchers.IO){
            val entityDb = dataRepository.findNewsById(newsId)
            if (entityDb != null){
                news.postValue(entityDb.mapToModel())
            }
            else {
                news.postValue(null)
            }
        }
    }
}