package pe.com.peruapps.ui.post.tabs

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class PostsPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                NetworkPostsFragment()
            }
            1 -> LocalPostsFragment()
            else -> {
                return NetworkPostsFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Fresh Posts"
            1 -> "Bookmark"
            else -> {
                return "Fresh Posts"
            }
        }
    }
}