package app.motsu.hiromoto.countcharenge

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity):
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        Log.d("debug", "position = $position")
        
        return when(position){
            0 ->{
                FirstFragment()
            }
            1 ->{
                SecondFragment()
            }
            2 ->{
                ThirdFragment()
            }
            else -> throw IllegalArgumentException()
        }
    }
//    override fun createFragment(position: Int): Fragment = ScreenSlidePageFragment()
}