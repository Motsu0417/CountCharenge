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
        // Doggy: this を書いているのが丁寧！
        //  Kotlin は this を省略できるので、省略しても良さそう！
        //  ただ、this はそのスコープを作っているクラス・オブジェクトを示すので、
        //  Activity のつもりで this を使用して、別のオブジェクトを示してしまう場合もあるので注意！

        // Doggy: "motsu_countCha" というキーが複数回出てくるので、
        //  変数に代入しておいて、定数として扱うとバグを防げそう！
        //  このキーに関しては、MainActivity のインスタンスそれぞれで持つ必要がないので、
        //  static なプロパティとして定義するのが良さそう！
        //  static なプロパティは companion object 内で定義できるので、調べてみよ！
        // "motsu_countCha"をキーにsharedPreferencesを取得
        val sharedPref = this.getSharedPreferences("motsu_countCha", Context.MODE_PRIVATE)

        // Doggy: TutorialActivity を開くのはアプリをインストールして1回なので、
        //  まず MainActivity を起動してから、必要に応じて TutorialActivity を起動するという設計が無駄がなくてナイス！

        // Doggy: "isFirst" も定数にして良さそう！
        //  せっかくなので、実装してみよ！！
//        if(sharedPref.getBoolean("isFirst", true)){
        if(true){
            sharedPref.edit().putBoolean("isFirst", false).apply()

            val intent = Intent(this, TutorialActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Doggy: 細かいけど、setContentView は super.onCreate() の後に書くのが通例！
        //  個人開発の場合ではどこに書いても大丈夫だけど、
        //  未来の自分や将来一緒に開発する人が読みやすいコードにするために移動した方が良さそう！
        //  驚き最小の原則！
        binding = ActivityMainBinding.inflate(layoutInflater).apply{setContentView(this.root)}

        // "count"の値を取得
        count = sharedPref.getInt("COUNT", 0)


        // Doggy: val で良い変数は val にしよう！
        //  Kotlin はイミュータブル性を大事にする言語なので、
        //  変更しない値は、その意図を明示的にコードに反映しよう！
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
        // Doggy: with や apply などはスコープ関数という Kotlin の文法なんだけど、
        //  ネストが深くなったり値が追いにくくなったりするので、個人的には可読性を下げると考えています！
        //  使う場面ももちろんあるので、適切に使い分けると良いと思います！
        //  参考：https://qiita.com/ngsw_taro/items/d29e3080d9fc8a38691e
        // "count"の値を保存
        with (sharedPref.edit()) {
            putInt("COUNT", count)
            apply()
        }
        super.onStop()
    }
}