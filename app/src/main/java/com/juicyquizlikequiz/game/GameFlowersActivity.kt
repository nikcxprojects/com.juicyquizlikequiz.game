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

class GameFlowersActivity : AppCompatActivity() {

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
        //1) Balsam - 2 6 9 4 11 1
        //2) Orchid - 6 9 1 7 11 8
        //3) Dahlia - 3 2 10 12 8 5
        //4) Rohira - 12 5 3 8 6 2
        //5) Lotus - 3 6 4 8 10
        binding.next.isEnabled = false
        quizNumber = 1
        gameLvlManager()
        var hintC = 3
        binding.hint.setOnClickListener {
            if (hintC !=0){
                hintC--
                if (hintC ==0){
                    binding.hint.setBackgroundResource(R.drawable.gray_botton)
                }
                binding.hintCount.text = "$hintC"
                if (quizNumber == 1) {
                    hintTap(1,2,4,6,9,11)
                }
                if (quizNumber == 2){
                    hintTap(1,6,7,8,9,11)
                }
                if (quizNumber == 3){
                    hintTap(2,3,5,8,10,12)
                }
                if (quizNumber == 4){
                    hintTap(2,3,5,6,8,12)
                }
                if (quizNumber == 5){
                    hintTap(3,4,6,8,10,0)
                }
            } else {
                Toast.makeText(applicationContext,"Вы израсходовали все подсказки",Toast.LENGTH_SHORT).show()
            }

        }

        binding.next.setOnClickListener {
            if (quizNumber == 5) {
                val lvlInf: SharedPreferences =
                    getSharedPreferences("lvl", Context.MODE_PRIVATE)
                val lvlSave: SharedPreferences.Editor = lvlInf.edit()
                if (lvlInf.getInt("lvl", 0) != 2){
                    Toast.makeText(applicationContext, "save", Toast.LENGTH_SHORT).show()
                    lvlSave.putInt("lvl", 2)
                    lvlSave.apply()
                    finish()
                }
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
                setTxt6 = true
                checkTap(true, 1, "M", 6)
            }
            if (quizNumber == 2) {
                setTxt3 = true
                checkTap(true, 1, "C", 3)
            }
            if (quizNumber == 3) {
                checkTap(false, 1, "J", 0)
            }
            if (quizNumber == 4) {
                checkTap(false, 1, "G", 0)
            }
            if (quizNumber == 5) {
                checkTap(false, 1, "X", 0)
            }
        }

        binding.text2.setOnClickListener {
            if (quizNumber == 1) {
                setTxt1 = true
                checkTap(true, 2, "B", 1)
            }
            if (quizNumber == 2) {
                checkTap(false, 2, "W", 0)
            }
            if (quizNumber == 3) {
                setTxt2 = true
                checkTap(true, 2, "A", 2)
            }
            if (quizNumber == 4) {
                setTxt6 = true
                checkTap(true, 2, "A", 6)
            }
            if (quizNumber == 5) {
                checkTap(false, 2, "F", 0)
            }
        }

        binding.text3.setOnClickListener {
            if (quizNumber == 1) {
                checkTap(false, 3, "G", 0)
            }
            if (quizNumber == 2) {
                checkTap(false, 3, "T", 0)
            }
            if (quizNumber == 3) {
                setTxt1 = true
                checkTap(true, 3, "D", 1)
            }
            if (quizNumber == 4) {
                setTxt3 = true
                checkTap(true, 3, "H", 3)
            }
            if (quizNumber == 5) {
                setTxt1 = true
                checkTap(true, 3, "L", 1)
            }
        }

        binding.text4.setOnClickListener {
            if (quizNumber == 1) {
                setTxt4 = true
                checkTap(true, 4, "S", 4)
            }
            if (quizNumber == 2) {
                checkTap(false, 4, "B", 0)
            }
            if (quizNumber == 3) {
                checkTap(false, 4, "F", 0)
            }
            if (quizNumber == 4) {
                checkTap(false, 4, "Q", 0)
            }
            if (quizNumber == 5) {
                setTxt3 = true
                checkTap(true, 4, "T", 3)
            }
        }
        binding.text5.setOnClickListener {
            if (quizNumber == 1) {
                checkTap(false, 5, "D", 0)
            }
            if (quizNumber == 2) {
                checkTap(false, 5, "T", 0)
            }
            if (quizNumber == 3) {
                setTxt6 = true
                checkTap(true, 5, "A", 6)
            }
            if (quizNumber == 4) {
                setTxt2 = true
                checkTap(true, 5, "O", 2)
            }
            if (quizNumber == 5) {
                checkTap(false, 5, "R", 0)
            }
        }
        binding.text6.setOnClickListener {
            if (quizNumber == 1) {
                setTxt2 = true
                checkTap(true, 6, "A", 2)
            }
            if (quizNumber == 2) {
                setTxt1 = true
                checkTap(true, 6, "O", 1)
            }
            if (quizNumber == 3) {
                checkTap(false, 6, "Z", 0)
            }
            if (quizNumber == 4) {
                setTxt5 = true
                checkTap(true, 6, "R", 5)
            }
            if (quizNumber == 5) {
                setTxt2 = true
                checkTap(true, 6, "O", 2)
            }
        }
        binding.text7.setOnClickListener {
            if (quizNumber == 1) {
                checkTap(false, 7, "Q", 0)
            }
            if (quizNumber == 2) {
                setTxt4 = true
                checkTap(true, 7, "H", 4)
            }
            if (quizNumber == 3) {
                checkTap(false, 7, "O", 0)
            }
            if (quizNumber == 4) {
                checkTap(false, 7, "X", 0)
            }
            if (quizNumber == 5) {
                checkTap(false, 7, "K", 0)
            }
        }
        binding.text8.setOnClickListener {
            if (quizNumber == 1) {
                checkTap(false, 8, "R", 0)
            }
            if (quizNumber == 2) {
                setTxt6 = true
                checkTap(true, 8, "D", 6)
            }
            if (quizNumber == 3) {
                setTxt5 = true
                checkTap(true, 8, "I", 5)
            }
            if (quizNumber == 4) {
                setTxt4 = true
                checkTap(true, 8, "I", 4)
            }
            if (quizNumber == 5) {
                setTxt4 = true
                checkTap(true, 8, "U", 4)
            }
        }
        binding.text9.setOnClickListener {
            if (quizNumber == 1) {
                setTxt3 = true
                checkTap(true, 9, "L", 3)
            }
            if (quizNumber == 2) {
                setTxt2 = true
                checkTap(true, 9, "R", 2)
            }
            if (quizNumber == 3) {
                checkTap(false, 9, "X", 0)
            }
            if (quizNumber == 4) {
                checkTap(false, 9, "C", 0)
            }
            if (quizNumber == 5) {
                checkTap(false, 9, "P", 0)
            }
        }
        binding.text10.setOnClickListener {
            if (quizNumber == 1) {
                checkTap(false, 10, "K", 0)
            }
            if (quizNumber == 2) {
                checkTap(false, 10, "L", 0)
            }
            if (quizNumber == 3) {
                setTxt3 = true
                checkTap(true, 10, "H", 3)
            }
            if (quizNumber == 4) {
                checkTap(false, 10, "M", 0)
            }
            if (quizNumber == 5) {
                setTxt5 = true
                checkTap(true, 10, "S", 5)
            }
        }
        binding.text11.setOnClickListener {
            if (quizNumber == 1) {
                setTxt5 = true
                checkTap(true, 11, "A", 5)
            }
            if (quizNumber == 2) {
                setTxt5 = true
                checkTap(true, 11, "I", 5)
            }
            if (quizNumber == 3) {
                checkTap(false, 11, "R", 0)
            }
            if (quizNumber == 4) {
                checkTap(false, 11, "J", 0)
            }
            if (quizNumber == 5) {
                checkTap(false, 11, "G", 0)
            }
        }
        binding.text12.setOnClickListener {
            if (quizNumber == 1) {
                checkTap(false, 12, "J", 0)
            }
            if (quizNumber == 2) {
                checkTap(false, 12, "Q", 0)
            }
            if (quizNumber == 3) {
                setTxt4 = true
                checkTap(true, 12, "L", 4)
            }
            if (quizNumber == 4) {
                setTxt1 = true
                checkTap(true, 12, "R", 1)
            }
            if (quizNumber == 5) {
                checkTap(false, 12, "Q", 0)
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
            binding.text1.text = "M"
            binding.text2.text = "B"
            binding.text3.text = "G"
            binding.text4.text = "S"
            binding.text5.text = "D"
            binding.text6.text = "A"
            binding.text7.text = "Q"
            binding.text8.text = "R"
            binding.text9.text = "L"
            binding.text10.text = "K"
            binding.text11.text = "A"
            binding.text12.text = "J"
        }
        if (quizNumber == 2) {
            binding.text1.text = "C"
            binding.text2.text = "W"
            binding.text3.text = "T"
            binding.text4.text = "B"
            binding.text5.text = "T"
            binding.text6.text = "O"
            binding.text7.text = "H"
            binding.text8.text = "D"
            binding.text9.text = "R"
            binding.text10.text = "L"
            binding.text11.text = "I"
            binding.text12.text = "Q"
        }
        if (quizNumber == 3) {
            binding.text1.text = "J"
            binding.text2.text = "A"
            binding.text3.text = "D"
            binding.text4.text = "F"
            binding.text5.text = "A"
            binding.text6.text = "Z"
            binding.text7.text = "O"
            binding.text8.text = "I"
            binding.text9.text = "X"
            binding.text10.text = "H"
            binding.text11.text = "R"
            binding.text12.text = "L"
        }
        if (quizNumber == 4) {
            binding.text1.text = "G"
            binding.text2.text = "A"
            binding.text3.text = "H"
            binding.text4.text = "Q"
            binding.text5.text = "O"
            binding.text6.text = "R"
            binding.text7.text = "X"
            binding.text8.text = "I"
            binding.text9.text = "C"
            binding.text10.text = "M"
            binding.text11.text = "J"
            binding.text12.text = "R"
        }
        if (quizNumber == 5) {
            binding.text1.text = "X"
            binding.text2.text = "F"
            binding.text3.text = "L"
            binding.text4.text = "T"
            binding.text5.text = "R"
            binding.text6.text = "O"
            binding.text7.text = "K"
            binding.text8.text = "U"
            binding.text9.text = "P"
            binding.text10.text = "S"
            binding.text11.text = "G"
            binding.text12.text = "Q"
        }
    }

    private fun setTextCheck(s: String, m: Int) {

        val outputText = arrayListOf(binding.setText1,
            binding.setText2,
            binding.setText3,
            binding.setText4,
            binding.setText5,
            binding.setText6)

        outputText[m-1].setBackgroundResource(R.drawable.gray_frame)
        outputText[m-1].text = s
        Handler(Looper.getMainLooper()).postDelayed({
            outputText[m-1].setBackgroundResource(R.drawable.green_frame)
        }, 1000)

    }

    private fun lvlCheck() {
        val lvlInf = setTxt6 and setTxt5 and setTxt4 and setTxt3 and setTxt2 and setTxt1
        if (lvlInf) {
            binding.next.setBackgroundResource(R.drawable.green_botton)
            binding.next.isEnabled = true
        }
    }

    private fun nextLvl() {
        val imgStore = intArrayOf(R.drawable.lvl_1_img_1,
            R.drawable.lvl_1_img_2,
            R.drawable.lvl_1_img_3,
            R.drawable.lvl_1_img_4,
            R.drawable.lvl_1_img_5)

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

        setTxt6 = quizNumber == 4
        setTxt5 = false
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
            binding.setText6.visibility = View.GONE
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

    private fun hintTap(a:Int,b:Int,c:Int,d:Int,e:Int,f:Int){
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

        if (inputText[a-1].isEnabled) {
            inputText[a-1].setBackgroundResource(R.drawable.green_frame)
        }
        if (inputText[b-1].isEnabled) {
            inputText[b-1].setBackgroundResource(R.drawable.green_frame)
        }
        if (inputText[c-1].isEnabled) {
            inputText[c-1].setBackgroundResource(R.drawable.green_frame)
        }
        if (inputText[d-1].isEnabled) {
            inputText[d-1].setBackgroundResource(R.drawable.green_frame)
        }
        if (inputText[e-1].isEnabled) {
            inputText[e-1].setBackgroundResource(R.drawable.green_frame)
        }
        if (quizNumber != 5) {
            inputText[f-1].setBackgroundResource(R.drawable.green_frame)
        }
        Handler(Looper.getMainLooper()).postDelayed({
            if (inputText[a-1].isEnabled) {
                inputText[a-1].setBackgroundResource(R.drawable.blue_frame)
            }
            if (inputText[b-1].isEnabled) {
                inputText[b-1].setBackgroundResource(R.drawable.blue_frame)
            }
            if (inputText[c-1].isEnabled) {
                inputText[c-1].setBackgroundResource(R.drawable.blue_frame)
            }
            if (inputText[d-1].isEnabled) {
                inputText[d-1].setBackgroundResource(R.drawable.blue_frame)
            }
            if (inputText[e-1].isEnabled) {
                inputText[e-1].setBackgroundResource(R.drawable.blue_frame)
            }
            if (quizNumber != 5) {
                inputText[f-1].setBackgroundResource(R.drawable.blue_frame)
            }

        }, 200)

    }
    override fun onStart() {
        val musicInf: SharedPreferences =
            getSharedPreferences("music", Context.MODE_PRIVATE)
        mediaPlayer = MediaPlayer.create(this, R.raw.lvl1)
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