package com.juicyquizlikequiz.game

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.*
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.juicyquizlikequiz.game.databinding.ActivityGameBinding

class GameFruitsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding

    private lateinit var mediaPlayer: MediaPlayer

    private var quizNumber: Int = 0
    private var setTxt1 = false
    private var setTxt2 = false
    private var setTxt3 = false
    private var setTxt4 = false
    private var setTxt5 = false
    private var setTxt6 = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.image.setBackgroundResource(R.drawable.lvl_3_img_1)

        binding.setText6.visibility = View.GONE
        binding.next.isEnabled = false
        quizNumber = 1
        gameLvlManager()
        var hintC = 3
        //1) apple - 3 7 10 5 4
        //2) grape - 3 5 6 9 11
        //3) peach - 3 5 7 6 10
        //4) banan - 3 5 7 6 12
        //5) plum - 5 10 2 8

        binding.hint.setOnClickListener {
            if (hintC != 0) {
                hintC--
                if (hintC == 0) {
                    binding.hint.setBackgroundResource(R.drawable.gray_botton)
                }
                binding.hintCount.text = "$hintC"
                if (quizNumber == 1) {
                    hintTap(3, 4, 5, 7, 10)
                }
                if (quizNumber == 2) {
                    hintTap(3, 5, 6, 9, 11)
                }
                if (quizNumber == 3) {
                    hintTap(3, 5, 7, 6, 10)
                }
                if (quizNumber == 4) {
                    hintTap(3, 5, 7, 6, 12)
                }
                if (quizNumber == 5) {
                    hintTap(2, 5, 8, 10, 0)
                }
            } else {
                Toast.makeText(applicationContext,
                    "Вы израсходовали все подсказки",
                    Toast.LENGTH_SHORT).show()
            }
        }

        binding.next.setOnClickListener {
            if (quizNumber == 5) {
                val musicInf: SharedPreferences =
                    getSharedPreferences("music", Context.MODE_PRIVATE)
                val msc = musicInf.getBoolean("music", false)
                if (msc) {
                    mediaPlayer.stop()
                }
                val i = Intent(this, GameMenuActivity::class.java)
                startActivity(i)
                finish()
            } else {
                nextLvl()
                binding.next.isEnabled = false
                binding.next.setBackgroundResource(R.drawable.gray_botton)
            }
        }
        binding.text1.setOnClickListener {
            if (quizNumber == 1) {
                checkTap(false, 1, "W", 0)
            }
            if (quizNumber == 2) {
                checkTap(false, 1, "D", 0)
            }
            if (quizNumber == 3) {
                checkTap(false, 1, "S", 0)
            }
            if (quizNumber == 4) {
                checkTap(false, 1, "F", 0)
            }
            if (quizNumber == 5) {
                checkTap(false, 1, "S", 0)
            }
        }
        binding.text2.setOnClickListener {
            if (quizNumber == 1) {
                checkTap(false, 2, "T", 0)
            }
            if (quizNumber == 2) {
                checkTap(false, 2, "S", 0)
            }
            if (quizNumber == 3) {
                checkTap(false, 2, "G", 0)
            }
            if (quizNumber == 4) {
                checkTap(false, 2, "G", 0)
            }
            if (quizNumber == 5) {
                setTxt3 = true
                checkTap(true, 2, "U", 3)
            }
        }
        binding.text3.setOnClickListener {
            if (quizNumber == 1) {
                setTxt1 = true
                checkTap(true, 3, "A", 1)
            }
            if (quizNumber == 2) {
                setTxt1 = true
                checkTap(true, 3, "G", 1)
            }
            if (quizNumber == 3) {
                setTxt1 = true
                checkTap(true, 3, "P", 1)
            }
            if (quizNumber == 4) {
                setTxt1 = true
                checkTap(true, 3, "B", 1)
            }
            if (quizNumber == 5) {
                checkTap(false, 3, "D", 0)
            }
        }
        binding.text4.setOnClickListener {
            if (quizNumber == 1) {
                setTxt5 = true
                checkTap(true, 4, "E", 5)
            }
            if (quizNumber == 2) {
                checkTap(false, 4, "Q", 0)
            }
            if (quizNumber == 3) {
                checkTap(false, 4, "K", 0)
            }
            if (quizNumber == 4) {
                checkTap(false, 4, "H", 0)
            }
            if (quizNumber == 5) {
                checkTap(false, 4, "Y", 0)
            }
        }
        binding.text5.setOnClickListener {
            if (quizNumber == 1) {
                setTxt4 = true
                checkTap(true, 5, "L", 4)
            }
            if (quizNumber == 2) {
                setTxt2 = true
                checkTap(true, 5, "R", 2)
            }
            if (quizNumber == 3) {
                setTxt2 = true
                checkTap(true, 5, "E", 2)
            }
            if (quizNumber == 4) {
                setTxt2 = true
                checkTap(true, 5, "A", 2)
            }
            if (quizNumber == 5) {
                setTxt1 = true
                checkTap(true, 5, "P", 1)
            }
        }
        binding.text6.setOnClickListener {
            if (quizNumber == 1) {
                checkTap(false, 6, "V", 0)
            }
            if (quizNumber == 2) {
                setTxt3 = true
                checkTap(true, 6, "A", 3)
            }
            if (quizNumber == 3) {
                setTxt4 = true
                checkTap(true, 6, "C", 4)
            }
            if (quizNumber == 4) {
                setTxt4 = true
                checkTap(true, 6, "A", 4)
            }
            if (quizNumber == 5) {
                checkTap(false, 6, "W", 0)
            }
        }
        binding.text7.setOnClickListener {
            if (quizNumber == 1) {
                setTxt2 = true
                checkTap(true, 7, "P", 2)
            }
            if (quizNumber == 2) {
                checkTap(false, 7, "H", 0)
            }
            if (quizNumber == 3) {
                setTxt3 = true
                checkTap(true, 7, "A", 3)
            }
            if (quizNumber == 4) {
                setTxt3 = true
                checkTap(true, 7, "N", 3)
            }
            if (quizNumber == 5) {
                checkTap(false, 7, "H", 0)
            }
        }
        binding.text8.setOnClickListener {
            if (quizNumber == 1) {
                checkTap(false, 8, "H", 0)
            }
            if (quizNumber == 2) {
                checkTap(false, 8, "B", 0)
            }
            if (quizNumber == 3) {
                checkTap(false, 8, "O", 0)
            }
            if (quizNumber == 4) {
                checkTap(false, 8, "J", 0)
            }
            if (quizNumber == 5) {
                setTxt4 = true
                checkTap(true, 8, "M", 4)
            }
        }
        binding.text9.setOnClickListener {
            if (quizNumber == 1) {
                checkTap(false, 9, "K", 0)
            }
            if (quizNumber == 2) {
                setTxt4 = true
                checkTap(true, 9, "P", 4)
            }
            if (quizNumber == 3) {
                checkTap(false, 9, "I", 0)
            }
            if (quizNumber == 4) {
                checkTap(false, 9, "M", 0)
            }
            if (quizNumber == 5) {
                checkTap(false, 9, "X", 0)
            }
        }
        binding.text10.setOnClickListener {
            if (quizNumber == 1) {
                setTxt3 = true
                checkTap(true, 10, "P", 3)
            }
            if (quizNumber == 2) {
                checkTap(false, 10, "X", 0)
            }
            if (quizNumber == 3) {
                setTxt5 = true
                checkTap(true, 10, "H", 5)
            }
            if (quizNumber == 4) {
                checkTap(false, 10, "Q", 0)
            }
            if (quizNumber == 5) {
                setTxt2 = true
                checkTap(true, 10, "L", 2)
            }
        }
        binding.text11.setOnClickListener {
            if (quizNumber == 1) {
                checkTap(false, 11, "F", 0)
            }
            if (quizNumber == 2) {
                setTxt5 = true
                checkTap(true, 11, "E", 5)
            }
            if (quizNumber == 3) {
                checkTap(false, 11, "V", 0)
            }
            if (quizNumber == 4) {
                checkTap(false, 11, "X", 0)
            }
            if (quizNumber == 5) {
                checkTap(false, 11, "C", 0)
            }
        }
        //1) apple - 3 7 10 5 4
        //2) grape - 3 5 6 9 11
        //3) peach - 3 5 7 6 10
        //4) banan - 3 5 7 6 12
        //5) plum - 5 10 2 8
        binding.text12.setOnClickListener {
            if (quizNumber == 1) {
                checkTap(false, 12, "V", 0)
            }
            if (quizNumber == 2) {
                checkTap(false, 12, "L", 0)
            }
            if (quizNumber == 3) {
                checkTap(false, 12, "Z", 0)
            }
            if (quizNumber == 4) {
                setTxt5 = true
                checkTap(true, 12, "N", 5)
            }
            if (quizNumber == 5) {
                checkTap(false, 12, "N", 0)
            }
        }
        binding.back.setOnClickListener {
            mediaPlayer.stop()
            val i = Intent(this, GameMenuActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    private fun gameLvlManager() {
        if (quizNumber == 1) {
            binding.text1.text = "W"
            binding.text2.text = "T"
            binding.text3.text = "A"
            binding.text4.text = "E"
            binding.text5.text = "L"
            binding.text6.text = "V"
            binding.text7.text = "P"
            binding.text8.text = "H"
            binding.text9.text = "K"
            binding.text10.text = "P"
            binding.text11.text = "F"
            binding.text12.text = "V"
        }
        if (quizNumber == 2) {
            binding.text1.text = "D"
            binding.text2.text = "S"
            binding.text3.text = "G"
            binding.text4.text = "Q"
            binding.text5.text = "R"
            binding.text6.text = "A"
            binding.text7.text = "H"
            binding.text8.text = "B"
            binding.text9.text = "P"
            binding.text10.text = "X"
            binding.text11.text = "E"
            binding.text12.text = "L"
        }
        if (quizNumber == 3) {
            binding.text1.text = "S"
            binding.text2.text = "G"
            binding.text3.text = "P"
            binding.text4.text = "K"
            binding.text5.text = "E"
            binding.text6.text = "C"
            binding.text7.text = "A"
            binding.text8.text = "O"
            binding.text9.text = "I"
            binding.text10.text = "H"
            binding.text11.text = "V"
            binding.text12.text = "Z"
        }
        if (quizNumber == 4) {
            binding.text1.text = "F"
            binding.text2.text = "G"
            binding.text3.text = "B"
            binding.text4.text = "H"
            binding.text5.text = "A"
            binding.text6.text = "A"
            binding.text7.text = "N"
            binding.text8.text = "J"
            binding.text9.text = "M"
            binding.text10.text = "Q"
            binding.text11.text = "X"
            binding.text12.text = "N"
        }
        if (quizNumber == 5) {
            binding.text1.text = "S"
            binding.text2.text = "U"
            binding.text3.text = "D"
            binding.text4.text = "Y"
            binding.text5.text = "P"
            binding.text6.text = "W"
            binding.text7.text = "H"
            binding.text8.text = "M"
            binding.text9.text = "X"
            binding.text10.text = "L"
            binding.text11.text = "C"
            binding.text12.text = "N"
        }
    }

    private fun checkTap(flag: Boolean, buttonCount: Byte, letter: String, outText: Int) {

        val outputText = arrayListOf(setTxt1, setTxt2, setTxt3, setTxt4, setTxt5, setTxt6)

        val inputText = arrayListOf(binding.text1,
            binding.text2,
            binding.text3,
            binding.text4,
            binding.text5,
            binding.text6,
            binding.text7,
            binding.text8,
            binding.text9,
            binding.text10,
            binding.text11,
            binding.text12)

        if (flag) {
            setTextCheck(letter, outText)
            inputText[buttonCount - 1].text = ""
            inputText[buttonCount - 1].setBackgroundResource(R.drawable.gray_frame)
            fx()
            Handler(Looper.getMainLooper()).postDelayed({
                inputText[buttonCount - 1].text = letter
                inputText[buttonCount - 1].isEnabled = false
                outputText[outText - 1] = true
                lvlCheck()
            }, 1000)
        } else {
            inputText[buttonCount - 1].text = ""
            inputText[buttonCount - 1].setBackgroundResource(R.drawable.gray_frame)
            vibrate()
            Handler(Looper.getMainLooper()).postDelayed({
                inputText[buttonCount - 1].text = letter
                inputText[buttonCount - 1].setBackgroundResource(R.drawable.red_frame)
                inputText[buttonCount - 1].isEnabled = false
            }, 1000)
        }
    }

    private fun hintTap(a: Int, b: Int, c: Int, d: Int, e: Int) {
        val inputText = arrayListOf(binding.text1,
            binding.text2,
            binding.text3,
            binding.text4,
            binding.text5,
            binding.text6,
            binding.text7,
            binding.text8,
            binding.text9,
            binding.text10,
            binding.text11,
            binding.text12)

        if (inputText[a - 1].isEnabled) {
            inputText[a - 1].setBackgroundResource(R.drawable.green_frame)
        }
        if (inputText[b - 1].isEnabled) {
            inputText[b - 1].setBackgroundResource(R.drawable.green_frame)
        }
        if (inputText[c - 1].isEnabled) {
            inputText[c - 1].setBackgroundResource(R.drawable.green_frame)
        }
        if (inputText[d - 1].isEnabled) {
            inputText[d - 1].setBackgroundResource(R.drawable.green_frame)
        }
        if (e>0)  {
            if (inputText[e - 1].isEnabled) {
                inputText[e - 1].setBackgroundResource(R.drawable.green_frame)
            }
        }
        Handler(Looper.getMainLooper()).postDelayed({
            if (inputText[a - 1].isEnabled) {
                inputText[a - 1].setBackgroundResource(R.drawable.blue_frame)
            }
            if (inputText[b - 1].isEnabled) {
                inputText[b - 1].setBackgroundResource(R.drawable.blue_frame)
            }
            if (inputText[c - 1].isEnabled) {
                inputText[c - 1].setBackgroundResource(R.drawable.blue_frame)
            }
            if (inputText[d - 1].isEnabled) {
                inputText[d - 1].setBackgroundResource(R.drawable.blue_frame)
            }
            if (e>0)  {
                if (inputText[e - 1].isEnabled) {
                    inputText[e - 1].setBackgroundResource(R.drawable.blue_frame)
                }
            }

        }, 200)

    }

    private fun setTextCheck(s: String, m: Int) {

        val outputText = arrayListOf(binding.setText1,
            binding.setText2,
            binding.setText3,
            binding.setText4,
            binding.setText5,
            binding.setText6)

        outputText[m - 1].setBackgroundResource(R.drawable.gray_frame)
        outputText[m - 1].text = s
        Handler(Looper.getMainLooper()).postDelayed({
            outputText[m - 1].setBackgroundResource(R.drawable.green_frame)
        }, 1000)

    }

    private fun lvlCheck() {
        val lvlInf = setTxt5 and setTxt4 and setTxt3 and setTxt2 and setTxt1
        if (lvlInf) {
            binding.next.setBackgroundResource(R.drawable.green_botton)
            binding.next.isEnabled = true
        }
    }

    private fun nextLvl() {
        val imgStore = intArrayOf(R.drawable.lvl_3_img_1,
            R.drawable.lvl_3_img_2,
            R.drawable.lvl_3_img_3,
            R.drawable.lvl_3_img_5,
            R.drawable.lvl_3_img_4)

        val outputText = arrayListOf(binding.setText1,
            binding.setText2,
            binding.setText3,
            binding.setText4,
            binding.setText5,
            binding.setText6)

        for (i in outputText) {
            i.setBackgroundResource(R.drawable.gray_frame)
            i.text = ""
        }

        //setTxt6 = quizNumber == 4
        setTxt5 = quizNumber == 4
        setTxt4 = false
        setTxt3 = false
        setTxt2 = false
        setTxt1 = false

        val inputText = arrayListOf(binding.text1,
            binding.text2,
            binding.text3,
            binding.text4,
            binding.text5,
            binding.text6,
            binding.text7,
            binding.text8,
            binding.text9,
            binding.text10,
            binding.text11,
            binding.text12)

        for (i in inputText) {
            i.setBackgroundResource(R.drawable.blue_frame)
            i.isEnabled = true
        }

        if (quizNumber == 4) {
            binding.setText5.visibility = View.GONE
            binding.next.text = "Back"
        }
        if (quizNumber < 5) {
            binding.image.setBackgroundResource(imgStore[quizNumber])
        }
        quizNumber++
        binding.quizInf.text = "Question $quizNumber/5"
        gameLvlManager()
    }

    private fun fx() {
        val fxInf: SharedPreferences =
            getSharedPreferences("effects", Context.MODE_PRIVATE)
        mediaPlayer = MediaPlayer.create(this, R.raw.fx1)
        if (fxInf.getBoolean("effects", false)) {
            mediaPlayer.start()
        }
    }

    private fun vibrate() {
        val vibrationInf: SharedPreferences =
            getSharedPreferences("vibration", Context.MODE_PRIVATE)
        val v = getSystemService(VIBRATOR_SERVICE) as Vibrator
        if (vibrationInf.getBoolean("vibration", false)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(300, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                //deprecated in API 26
                v.vibrate(300)
            }
        }
    }

    override fun onStart() {
        val musicInf: SharedPreferences =
            getSharedPreferences("music", Context.MODE_PRIVATE)
        mediaPlayer = MediaPlayer.create(this, R.raw.lvl3)
        if (musicInf.getBoolean("music", false)) {
            mediaPlayer.isLooping = true
            mediaPlayer.start()
        }
        super.onStart()
    }

    override fun onStop() {
        val musicInf: SharedPreferences =
            getSharedPreferences("music", Context.MODE_PRIVATE)
        val msc = musicInf.getBoolean("music", false)
        if (msc) {
            mediaPlayer.stop()
        }
        super.onStop()
    }

    override fun onRestart() {
        val musicInf: SharedPreferences =
            getSharedPreferences("music", Context.MODE_PRIVATE)
        val msc = musicInf.getBoolean("music", false)
        if (msc) {
            mediaPlayer.isLooping = true
            mediaPlayer.start()
        }
        super.onRestart()
    }

    override fun onDestroy() {
        val musicInf: SharedPreferences =
            getSharedPreferences("music", Context.MODE_PRIVATE)
        val msc = musicInf.getBoolean("music", false)
        if (msc) {
            mediaPlayer.stop()
        }
        super.onDestroy()
    }

    override fun onBackPressed() {
        val musicInf: SharedPreferences =
            getSharedPreferences("music", Context.MODE_PRIVATE)
        val msc = musicInf.getBoolean("music", false)
        if (msc) {
            mediaPlayer.stop()
        }
        val i = Intent(this, GameMenuActivity::class.java)
        startActivity(i)
        finish()
        super.onBackPressed()
    }
}