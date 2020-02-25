package pro.devapp.newslist.logic.presenters

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pro.devapp.newslist.logic.entity.EntityNews
import pro.devapp.newslist.logic.livedata.NewsMapper
import pro.devapp.newslist.storage.database.DataRepository

class ViewNewsPresenter(private val dataRepository: DataRepository) {
    val news = MutableLiveData<EntityNews>()
    private val mapper = NewsMapper()

    fun setNewsId(newsId: Long){
        GlobalScope.launch {
            loadNews(newsId)
        }
    }

    private suspend fun loadNews(newsId: Long){
        withContext(Dispatchers.IO){
            val entityDb = dataRepository.findNewsById(newsId)
            if (entityDb != null){
                news.postValue(mapper.map(entityDb))
            }
            else {
                news.postValue(null)
            }
        }
    }
}