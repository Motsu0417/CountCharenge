package app.motsu.hiromoto.countcharenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.motsu.hiromoto.countcharenge.databinding.FragmentThirdBinding

class ThirdFragment : Fragment() {

    // Doggy: 実は Fragment では、メモリリークを防ぐために、View Binding を使用するときに Activity とは異なる書き方をします…！
    //  公式ドキュメントを参照しつつ、書き換えてみよう！
    //  https://developer.android.com/topic/libraries/view-binding?hl=ja#fragments
    lateinit var binding:FragmentThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding.

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentThirdBinding.inflate(layoutInflater)
        return binding.root
    }

    // Doggy: 本来はここで、 カウント画面に飛ぶ感じかな？？
    //  Fragment における View の操作は onViewCreated で実装します！ぜひ実装してみよう！！

}