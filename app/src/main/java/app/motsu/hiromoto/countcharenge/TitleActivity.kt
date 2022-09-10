package app.motsu.hiromoto.countcharenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.motsu.hiromoto.countcharenge.databinding.ActivityTitleBinding

class TitleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTitleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTitleBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        binding.toMainButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            // Doggy: Fragment へのチャレンジナイス！！
//            val fragmentManager = supportFragmentManager
//            val fragmentTransaction = fragmentManager.beginTransaction()
//            val fragment = ThirdFragment()
////            fragmentTransaction.add(, fragment)
        }
    }
}