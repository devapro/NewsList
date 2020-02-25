package pro.devapp.newslist.ui.customviews

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import androidx.paging.PagedList
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pro.devapp.newslist.R
import pro.devapp.newslist.logic.entity.EntityNews

class NewsList(contetxt: Context, attrs: AttributeSet?, defStyle: Int) : RecyclerView(contetxt, attrs, defStyle) {
    constructor(contetxt: Context, attrs: AttributeSet?) : this(contetxt, attrs, 0)
    constructor(contetxt: Context) : this(contetxt, null, 0)

    init {
        layoutManager = LinearLayoutManager(context, VERTICAL, false)
        itemAnimator = DefaultItemAnimator()
        setHasFixedSize(true)
        val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(ContextCompat.getDrawable(context, R.drawable.divider)!!)
        addItemDecoration(itemDecorator)
        adapter = NewsListAdapter()
    }

    fun submitList(list: PagedList<EntityNews>){
        (adapter as NewsListAdapter).submitList(list)
    }

    fun getItemByPosition(position: Int): EntityNews?{
        return (adapter as NewsListAdapter).getItemByPosition(position)
    }
}