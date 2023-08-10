package com.example.catchthegoldensnitch

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.example.catchthegoldensnitch.databinding.ActivityMainBinding
import java.util.Random
import kotlin.random.Random as Random1

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var score=0
    var imageArray=ArrayList<ImageView>()
    var runnable =Runnable{}
    var handler=Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //imagearray
        imageArray.add(binding.imageView)
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)
        hideImages()
        object : CountDownTimer(15400,1000){
            override fun onTick(p0: Long) {
                binding.timeText.text = "Time: ${p0/1000}"
            }

            override fun onFinish() {
                binding.timeText.text = "Time: 0"
                handler.removeCallbacks(runnable)

                for (image in imageArray){
                    image.visibility = View.INVISIBLE
                }
                //alert dialog
                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("GAME OVER")
                alert.setMessage("RESTART THE GAME?")
                alert.setPositiveButton("YES"){ dialogInterface, i ->
                    //restart
                    val intentFromMain = intent
                    finish()
                    startActivity(intentFromMain)
                }
                alert.setNegativeButton("NO"){dialogInterface, i->
                    Toast.makeText(this@MainActivity,"GAME OVER!",Toast.LENGTH_LONG).show()
                }
                alert.show()
            }

        }.start()

    }
    fun hideImages(){
        runnable = object :Runnable{
            override fun run() {
                for(image in imageArray){
                    image.visibility=View.INVISIBLE
                }
                val random= Random()
                val randomindex=random.nextInt(9)
                imageArray[randomindex].visibility=View.VISIBLE
                handler.postDelayed(runnable,500)
            }
        }
        handler.post(runnable)


    }
    fun increaseScore(view:View){
        score=score+1
        binding.scoreText.text="Score :${score}"
    }
}