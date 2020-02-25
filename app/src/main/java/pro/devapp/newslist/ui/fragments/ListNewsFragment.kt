package pro.devapp.newslist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_news_list.*
import org.koin.android.ext.android.inject
import pro.devapp.newslist.R
import pro.devapp.newslist.logic.entity.EntityNews
import pro.devapp.newslist.logic.presenters.MainListPresenter
import pro.devapp.newslist.ui.MainActivity
import pro.devapp.newslist.ui.customviews.NewsList
import pro.devapp.newslist.util.OnItemClickListener
import pro.devapp.newslist.util.addOnItemClickListener

class ListNewsFragment : NavigationFragment() {

    private val mainListPresenter : MainListPresenter by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setTitle(getString(R.string.app_name))

        mainListPresenter.pagedList.observe(viewLifecycleOwner, Observer {
            newsList.submitList(it)
        })

        mainListPresenter.getErrorMessage().observe(viewLifecycleOwner, Observer {
            newsList.setError(it)
        })

        newsList.listener = object : NewsList.ActionListener{
            override fun onItemClick(item: EntityNews?) {
                item?.let {
                    (activity as MainActivity).addFragment(ViewNewsFragment.newInstance(it.id))
                }
            }

            override fun onTryAgainClick() {
                mainListPresenter.tryAgain()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListNewsFragment()
    }
}
