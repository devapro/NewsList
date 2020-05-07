package pro.devapp.newslist.ui.screens.newslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_news_list.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import pro.devapp.newslist.R
import pro.devapp.newslist.logic.models.ModelItemNews
import pro.devapp.newslist.logic.controllers.NavigationController
import pro.devapp.newslist.ui.screens.newslist.customviews.NewsList
import pro.devapp.newslist.ui.main.NavigationFragment
import pro.devapp.newslist.ui.screens.newsdetail.ViewNewsFragment
import pro.devapp.newslist.util.observeNonNull

class ListNewsFragment : NavigationFragment() {

    private val listPresenter : ListPresenter by inject()
    private val navigationController : NavigationController by inject{ parametersOf( (activity as AppCompatActivity).supportFragmentManager) }

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

        listPresenter.pagedList.observeNonNull(viewLifecycleOwner){
            newsList.submitList(it)
        }

        listPresenter.getErrorMessage().observeNonNull(viewLifecycleOwner){
            newsList.setError(it)
        }

        newsList.listener = object : NewsList.ActionListener{
            override fun onItemClick(item: ModelItemNews?) {
                item?.let {
                    navigationController.addFragment(
                        ViewNewsFragment.newInstance(
                            it.id
                        )
                    )
                }
            }

            override fun onTryAgainClick() {
                listPresenter.tryAgain()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ListNewsFragment()
    }
}
