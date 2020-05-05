package pro.devapp.newslist.ui.screens.newsdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_news_view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import pro.devapp.newslist.R
import pro.devapp.newslist.ui.main.NavigationFragment
import pro.devapp.newslist.util.observe

class ViewNewsFragment : NavigationFragment() {

    private val presenter : ViewNewsPresenter by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_view, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        displayHome()
        webView.settings.javaScriptEnabled = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            GlobalScope.launch {
                presenter.loadNews(it.getLong("newsId"))
            }
        }

        presenter.news.observe(viewLifecycleOwner){
            val news = it ?: return@observe
            webView.loadUrl(news.url)
            setTitle(news.title ?: "")
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(newsId: Long) = ViewNewsFragment()
            .apply {
            arguments = Bundle().apply {
                putLong("newsId", newsId)
            }
        }
    }
}
