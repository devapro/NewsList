package pro.devapp.newslist.ui.screens.newslist.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import androidx.paging.PagedList
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pro.devapp.newslist.R
import pro.devapp.newslist.logic.models.ModelItemNews
import pro.devapp.newslist.util.OnItemClickListener
import pro.devapp.newslist.util.addOnItemClickListener

class NewsList(contetxt: Context, attrs: AttributeSet?, defStyle: Int) : RecyclerView(contetxt, attrs, defStyle) {
    constructor(contetxt: Context, attrs: AttributeSet?) : this(contetxt, attrs, 0)
    constructor(contetxt: Context) : this(contetxt, null, 0)

    var listener: ActionListener? = null

    init {
        layoutManager = LinearLayoutManager(context, VERTICAL, false)
        itemAnimator = DefaultItemAnimator()
        setHasFixedSize(true)
        val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(ContextCompat.getDrawable(context, R.drawable.divider)!!)
        addItemDecoration(itemDecorator)
        adapter = NewsListAdapter(object : NewsListAdapter.ActionListener{
            override fun onTryAgainClick() {
                listener?.onTryAgainClick()
            }
        })
        addOnItemClickListener(object: OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                val news = getItemByPosition(position)
                listener?.onItemClick(news)
            }
        })
    }

    fun submitList(list: PagedList<ModelItemNews>){
        (adapter as NewsListAdapter).submitList(list)
    }

    fun getItemByPosition(position: Int): ModelItemNews?{
        return (adapter as NewsListAdapter).getItemByPosition(position)
    }

    fun setError(message: String?){
        (adapter as NewsListAdapter).setError(message)
    }

    interface ActionListener{
        fun onItemClick(item: ModelItemNews?)
        fun onTryAgainClick()
    }
}