package com.juicyquizlikequiz.game

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.appcompat.app.AppCompatActivity
import com.juicyquizlikequiz.game.databinding.ActivityGameOptionsBinding

class GameOptionsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameOptionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameOptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fxInf: SharedPreferences =
            getSharedPreferences("effects", Context.MODE_PRIVATE)
        val fxSave: SharedPreferences.Editor = fxInf.edit()

        if (fxInf.getBoolean("effects", false)) {
            binding.fx.setBackgroundResource(R.drawable.on)
        } else {
            binding.fx.setBackgroundResource(R.drawable.off)
        }

        binding.fx.setOnClickListener {
            //val mediaPlayer = MediaPlayer.create(this, R.raw.effects)
            if (fxInf.getBoolean("effects", false)) {
                binding.fx.setBackgroundResource(R.drawable.off)
                fxSave.putBoolean("effects", false)
                fxSave.apply()
            } else {
                binding.fx.setBackgroundResource(R.drawable.on)
                //mediaPlayer.start()
                fxSave.putBoolean("effects", true)
                fxSave.apply()
            }
        }

        val musicInf: SharedPreferences =
            getSharedPreferences("music", Context.MODE_PRIVATE)
        val musicSave: SharedPreferences.Editor = musicInf.edit()

        if (musicInf.getBoolean("music", false)) {
            binding.music.setBackgroundResource(R.drawable.on)
        } else {
            binding.music.setBackgroundResource(R.drawable.off)
        }

        binding.music.setOnClickListener {
            //val mediaPlayer = MediaPlayer.create(this, R.raw.music)
            if (musicInf.getBoolean("music", false)) {
                binding.music.setBackgroundResource(R.drawable.off)
                musicSave.putBoolean("music", false)
                musicSave.apply()
            } else {
                binding.music.setBackgroundResource(R.drawable.on)
                //mediaPlayer.start()
                musicSave.putBoolean("music", true)
                musicSave.apply()
            }
        }

        val vibrationInf: SharedPreferences =
            getSharedPreferences("vibration", Context.MODE_PRIVATE)
        val vibrationSave: SharedPreferences.Editor = vibrationInf.edit()

        if (vibrationInf.getBoolean("vibration", false)) {
            binding.vibration.setBackgroundResource(R.drawable.on)
        } else {
            binding.vibration.setBackgroundResource(R.drawable.off)
        }

        binding.vibration.setOnClickListener {
            if (vibrationInf.getBoolean("vibration", false)) {
                binding.vibration.setBackgroundResource(R.drawable.off)


                vibrationSave.putBoolean("vibration", false)
                vibrationSave.apply()
            } else {
                binding.vibration.setBackgroundResource(R.drawable.on)
                vibrationSave.putBoolean("vibration", true)
                vibrationSave.apply()
                val v = getSystemService(VIBRATOR_SERVICE) as Vibrator
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(300, VibrationEffect.DEFAULT_AMPLITUDE))
                } else {
                    //deprecated in API 26
                    v.vibrate(300)
                }

            }
        }

        binding.backMenu.setOnClickListener {
            finish()
        }
    }
}