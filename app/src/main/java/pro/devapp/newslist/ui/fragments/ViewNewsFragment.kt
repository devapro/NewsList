package pro.devapp.newslist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_news_view.*
import org.koin.android.ext.android.inject
import pro.devapp.newslist.R
import pro.devapp.newslist.logic.presenters.ViewNewsPresenter

class ViewNewsFragment : NavigationFragment() {

    private val presenter : ViewNewsPresenter by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_view, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            presenter.setNewsId(it.getLong("newsId"))
        }

        displayHome()
        webView.settings.javaScriptEnabled = true
        presenter.news.observe(viewLifecycleOwner, Observer {
            if(it != null){
                webView.loadUrl(it.url)
                setTitle(it.title ?: "")
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(newsId: Long) = ViewNewsFragment().apply {
            arguments = Bundle().apply {
                putLong("newsId", newsId)
            }
        }
    }
}
