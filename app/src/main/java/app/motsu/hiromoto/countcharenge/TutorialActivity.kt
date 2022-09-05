package app.motsu.hiromoto.countcharenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import app.motsu.hiromoto.countcharenge.databinding.ActivityTutorialBinding

private const val NUM_PAGES = 3

class TutorialActivity : AppCompatActivity() {

    private lateinit var binding:ActivityTutorialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        binding.viewPager.adapter = ViewPagerAdapter(this)
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val maxItemCount = binding.viewPager.adapter?.itemCount ?: 0
    }

//    override fun onBackPressed() {
//        super.onBackPressed()
//        if(binding.viewPager.currentItem == 0){
//            super.onBackPressed()
//        }else{
//            binding.viewPager.currentItem--
//        }
//    }
}