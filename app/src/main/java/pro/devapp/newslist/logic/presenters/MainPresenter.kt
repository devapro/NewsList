package pro.devapp.newslist.logic.presenters

import android.os.Handler
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import pro.devapp.newslist.R
import pro.devapp.newslist.ui.fragments.ListNewsFragment
import pro.devapp.newslist.ui.fragments.SplashFragment

class MainPresenter(private val supportFragmentManager: FragmentManager) {
    fun openInitFragment(){
        val currentFragment = supportFragmentManager.findFragmentByTag("main")
        if(currentFragment == null){
            addFragment(SplashFragment.newInstance())
            Handler().postDelayed({
                replaceFragment(ListNewsFragment.newInstance())
            }, 1000)
        }
    }

    fun replaceFragment(fragment: Fragment) {
        try {
            supportFragmentManager.beginTransaction().replace(R.id.content, fragment, "main").commit()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun addFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().addToBackStack("fr").add(R.id.content, fragment).commit()
    }
}