package pro.devapp.newslist.logic.livedata

import androidx.paging.PagedList
import pro.devapp.newslist.logic.DataController
import pro.devapp.newslist.logic.entity.EntityNews

class NewsBoundaryCallback(private val dataController: DataController): PagedList.BoundaryCallback<EntityNews>()  {
    override fun onZeroItemsLoaded() {
        dataController.loadInitialData()
    }

    override fun onItemAtEndLoaded(itemAtEnd: EntityNews) {
       dataController.loadSinceData(itemAtEnd.createdAt)
    }
}