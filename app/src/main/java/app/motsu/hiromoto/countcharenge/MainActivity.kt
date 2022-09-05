package app.motsu.hiromoto.countcharenge

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.media.AudioAttributes
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.motsu.hiromoto.countcharenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var soundPool: SoundPool
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // "motsu_countCha"をキーにsharedPreferencesを取得
        val sharedPref = this.getSharedPreferences("motsu_countCha", Context.MODE_PRIVATE)

//        if(sharedPref.getBoolean("isFirst", true)){
        if(true){
            sharedPref.edit().putBoolean("isFirst", false).apply()

            val intent = Intent(this, TutorialActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding = ActivityMainBinding.inflate(layoutInflater).apply{setContentView(this.root)}

        // "count"の値を取得
        count = sharedPref.getInt("COUNT", 0)


        // 効果音の設定
        var audioAttributes = AudioAttributes.Builder().apply {
            setUsage(AudioAttributes.USAGE_GAME)
            setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)

        }.build()

        soundPool = SoundPool.Builder().apply{
            setAudioAttributes(audioAttributes)
            setMaxStreams(10)
        }.build()

        var sound = soundPool.load(this, R.raw.maou_se_system39, 1)


        binding.countText.text = count.toString()

        binding.plusButton.setOnClickListener{
            count++
            binding.countText.text = count.toString()
            when(count % 2){
                1 ->{
                    // 奇数
                    binding.countText.setTextColor(Color.RED)
                    soundPool.play(sound,1.0f, 1.0f, 1, 0, 1.0f)
                }
                0 ->{
                    // 偶数
                    binding.countText.setTextColor(Color.BLUE)

                }
            }
        }
    }

    override fun onStop() {

        // "motsu_countCha"をキーにsharedPreferencesを取得
        val sharedPref = this.getSharedPreferences("motsu_countCha", Context.MODE_PRIVATE)
        // "count"の値を保存
        with (sharedPref.edit()) {
            putInt("COUNT", count)
            apply()
        }
        super.onStop()
    }
}