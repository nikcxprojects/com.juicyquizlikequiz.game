package com.juicyquizlikequiz.game

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.juicyquizlikequiz.game.databinding.ActivityGameMenuBinding

class GameMenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lvlInf: SharedPreferences =
            getSharedPreferences("lvl", Context.MODE_PRIVATE)

        val imgStore = intArrayOf(R.drawable.green_botton,
            R.drawable.pink_botton,
            R.drawable.orange_botton)
        var countLevel = 0

        val lvl = lvlInf.getInt("lvl", 1)
        if (lvl>1){
            binding.level.setBackgroundResource(R.drawable.blye_botton)
            binding.lvlRef.setBackgroundResource(imgStore[1])
            binding.lvlRef.text = "FLOWERS"
            countLevel = 2
        }
        var lvlStart = 1
        binding.startGame.setOnClickListener {
            if (lvlInf.getInt("lvl", 1) == 1){
                val i = Intent(this, GameFlowersActivity::class.java)
                startActivity(i)
                finish()
            } else {
                if (lvlStart==0){
//                    Toast.makeText(applicationContext, "TREES", Toast.LENGTH_SHORT)
//                        .show()
                    val i = Intent(this, GameTreesActivity::class.java)
                    startActivity(i)
                    finish()
                }
                if (lvlStart==1){

                    val i = Intent(this, GameFlowersActivity::class.java)
                    startActivity(i)
                    finish()
                }
                if (lvlStart==2){
//                    Toast.makeText(applicationContext, "FRUITS", Toast.LENGTH_SHORT)
//                        .show()
                    val i = Intent(this, GameFruitsActivity::class.java)
                    startActivity(i)
                    finish()
                }
            }

        }

        binding.lvlRef.setOnClickListener {
            if (lvlInf.getInt("lvl", 1) == 1) {
                Toast.makeText(applicationContext, "Проидите первую часть игры", Toast.LENGTH_SHORT)
                    .show()
            } else {
                if (countLevel == 0){
                    binding.lvlRef.setBackgroundResource(imgStore[0])
                    binding.lvlRef.text = "TREES"
                    lvlStart = 0
                }
                if (countLevel == 1){
                    binding.lvlRef.setBackgroundResource(imgStore[1])
                    binding.lvlRef.text = "FLOWERS"
                    lvlStart = 1
                }
                if (countLevel == 2){
                    binding.lvlRef.setBackgroundResource(imgStore[2])
                    binding.lvlRef.text = "FRUITS"
                    countLevel=-1
                    lvlStart = 2
                }
                countLevel++
            }
        }

        binding.options.setOnClickListener {
            val i = Intent(this, GameOptionsActivity::class.java)
            startActivity(i)
        }
    }
}