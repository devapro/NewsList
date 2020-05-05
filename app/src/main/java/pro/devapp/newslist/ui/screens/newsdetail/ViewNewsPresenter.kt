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

    suspend fun loadNews(newsId: Long){
        withContext(Dispatchers.IO){
            val entityDb = dataRepository.findNewsById(newsId)
            news.postValue(entityDb?.mapToModel())
        }
    }
}