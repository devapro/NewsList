package pro.devapp.newslist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_news_list.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import pro.devapp.newslist.R
import pro.devapp.newslist.logic.entity.EntityNews
import pro.devapp.newslist.logic.presenters.ListPresenter
import pro.devapp.newslist.logic.presenters.MainPresenter
import pro.devapp.newslist.ui.customviews.NewsList

class ListNewsFragment : NavigationFragment() {

    private val listPresenter : ListPresenter by inject()
    private val mainPresenter : MainPresenter by inject{ parametersOf( (activity as AppCompatActivity).supportFragmentManager) }

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

        listPresenter.pagedList.observe(viewLifecycleOwner, Observer {
            newsList.submitList(it)
        })

        listPresenter.getErrorMessage().observe(viewLifecycleOwner, Observer {
            newsList.setError(it)
        })

        newsList.listener = object : NewsList.ActionListener{
            override fun onItemClick(item: EntityNews?) {
                item?.let {
                    mainPresenter.addFragment(ViewNewsFragment.newInstance(it.id))
                }
            }

            override fun onTryAgainClick() {
                listPresenter.tryAgain()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListNewsFragment()
    }
}
