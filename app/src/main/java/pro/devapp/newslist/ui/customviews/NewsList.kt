package pro.devapp.newslist.ui.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import androidx.paging.PagedList
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_news_list.*
import pro.devapp.newslist.R
import pro.devapp.newslist.logic.entity.EntityNews
import pro.devapp.newslist.ui.MainActivity
import pro.devapp.newslist.ui.fragments.ViewNewsFragment
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

    fun submitList(list: PagedList<EntityNews>){
        (adapter as NewsListAdapter).submitList(list)
    }

    fun getItemByPosition(position: Int): EntityNews?{
        return (adapter as NewsListAdapter).getItemByPosition(position)
    }

    fun setError(message: String?){
        (adapter as NewsListAdapter).setError(message)
    }

    interface ActionListener{
        fun onItemClick(item: EntityNews?)
        fun onTryAgainClick()
    }
}