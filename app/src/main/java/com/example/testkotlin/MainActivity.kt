package com.example.testkotlin

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var mLeftImageView:ImageView
    lateinit var mRightImageView:ImageView
    lateinit var mLeftImageViewEnemy:ImageView
    lateinit var mRightImageViewEnemy:ImageView
    lateinit var rollDicesButton:Button
    lateinit var rollDicesButtonStart:Button
    lateinit var You:TextView
    lateinit var Computer:TextView
    lateinit var Itog:TextView
    var itogYou = 0
    var itogComputer = 0
    var value1 = 0
    var value2 = 0
    var value3 = 0
    var value4 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rollDicesButton =  findViewById(R.id.button_roll)
        rollDicesButtonStart = findViewById(R.id.button_start)
        mLeftImageView =  findViewById(R.id.imageview_left)
        mRightImageView =  findViewById(R.id.imageview_right)
        mLeftImageViewEnemy =  findViewById(R.id.imageview_left_enemy)
        mRightImageViewEnemy = findViewById(R.id.imageview_right_enemy)
        You = findViewById(R.id.textView)
        Computer = findViewById(R.id.textView_enemy)
        Itog = findViewById(R.id.textView_itog)
        rollDicesButton.setClickable(false)
    }

    fun randomDiceValue():Int {
        val random = Random()
        return random.nextInt(6) + 1
    }

    fun Shot(view: View) {
         value1 = randomDiceValue()
         value2 = randomDiceValue()

        val res1 = getResources().getIdentifier(
            "cube_" + value1,
            "drawable", "com.example.testkotlin"
        )
        val res2 = getResources().getIdentifier(
            "cube_" + value2,
            "drawable", "com.example.testkotlin"
        )

        mLeftImageView.setImageResource(res1)
        mRightImageView.setImageResource(res2)
        You.setText("Вы:" + (value1 + value2).toString())
        Itog()
        rollDicesButton.setClickable(false)

        val handler = Handler()
        handler.postDelayed({
            rollDicesButtonStart.performClick()
            You.setText("Вы:")
            mLeftImageView.setImageResource(R.drawable.cube_0)
            mRightImageView.setImageResource(R.drawable.cube_0)
            rollDicesButton.setClickable(true)
        }, 1500)
    }

    fun Start(view: View) {
        rollDicesButton.setClickable(true)
         value3 = randomDiceValue()
         value4 = randomDiceValue()
        val res1 = getResources().getIdentifier(
            "cube_" + value3,
            "drawable", "com.example.testkotlin"
        )
        val res2 = getResources().getIdentifier(
            "cube_" + value4,
            "drawable", "com.example.testkotlin"
        )

        mLeftImageViewEnemy.setImageResource(res1)
        mRightImageViewEnemy.setImageResource(res2)
        Computer.setText("Компьютер:" + (value3 + value4).toString())
       // rollDicesButtonStart.setVisibility(View.GONE)
        rollDicesButtonStart.setClickable(false)
    }
    
    fun Itog() {
     if((value1+value2)>(value3+value4)){
         itogYou++
         Itog.setText("Счет:" + itogYou.toString() + "-" + itogComputer.toString())
     }
      else if((value1+value2)<(value3+value4)){
         itogComputer++
         Itog.setText("Счет:" + itogYou.toString() + "-" + itogComputer.toString())
     }
     else if((value1+value2)==(value3+value4)){
     }
        if((itogYou)==5){
            ShowWin()
        }
        else if ((itogComputer)==5){
            ShowOver()
        }
    }

    fun ShowWin() {
        val alertbox = AlertDialog.Builder(this)
        alertbox.setCancelable(false)
        alertbox.setTitle("Победа")
        val TextToast = "Поздравляю, вы спасли человечество, Скайнет проиграл!"
        alertbox.setMessage(TextToast)
        alertbox.setNeutralButton(
            "Рестарт"
        ) { arg0, arg1 ->
            recreate()
        }
        alertbox.show()
    }

    fun ShowOver() {
        val alertbox = AlertDialog.Builder(this)
        alertbox.setCancelable(false)
        alertbox.setTitle("Поражение")
        val TextToast = "Ты запустил(а) восстание машин! Спасайся кто может!!"
        alertbox.setMessage(TextToast)
        alertbox.setNeutralButton(
            "Рестарт"
        ) { arg0, arg1 ->
            recreate()
        }
        alertbox.show()
    }
}
