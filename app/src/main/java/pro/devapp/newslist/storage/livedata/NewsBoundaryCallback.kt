package pro.devapp.newslist.storage.livedata

import androidx.paging.PagedList
import pro.devapp.newslist.logic.DataController
import pro.devapp.newslist.logic.models.ModelItemNews

class NewsBoundaryCallback(private val dataController: DataController): PagedList.BoundaryCallback<ModelItemNews>()  {
    override fun onZeroItemsLoaded() {
        dataController.loadInitialData()
    }

    override fun onItemAtEndLoaded(itemAtEnd: ModelItemNews) {
       dataController.loadSinceData(itemAtEnd.createdAt)
    }
}