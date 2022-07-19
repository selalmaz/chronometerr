package com.ammo.chronometer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.ammo.chronometer.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var sn=0
        var dk=0
        var runnable:Runnable= Runnable {  }
        var handler=Handler()

        binding.baslatButton.setOnClickListener {
            binding.baslatButton.setVisibility(View.INVISIBLE)
            binding.durdurButton.setVisibility(View.VISIBLE)

            runnable=object :Runnable{
                override fun run() {
                    sn += 1

                    if(sn==60){
                        dk += 1
                        sn=0
                    }

                    if(sn>=10){
                        if(dk<10){
                          binding.sayacText.text="0${dk}:${sn}"
                        }
                        else{
                            binding.sayacText.text="$dk:$sn"
                        }
                    }
                        if(sn<10){

                            if(dk<10){
                                binding.sayacText.text="0${dk}:0$sn"
                            }
                            else{
                                binding.sayacText.text="$dk:0$sn"
                            }
                        }
                    handler.postDelayed(runnable,1000)
                }
            }
            handler.post(runnable)
        }

        binding.durdurButton.setOnClickListener {

            binding.durdurButton.setVisibility(View.INVISIBLE)
            binding.baslatButton.setVisibility(View.VISIBLE)


            if(sn>=10){
                if(dk<10){
                    binding.sayacText.text="0${dk}:${sn}"
                }
                else{
                    binding.sayacText.text="$dk:$sn"
                }
            }
            if(sn<10){

                if(dk<10){
                    binding.sayacText.text="0${dk}:0${sn}"
                }
                else{
                    binding.sayacText.text="$dk:0$sn"
                }
            }



            handler.removeCallbacks(runnable)
        }

        binding.sifirlaButton.setOnClickListener {
            binding.baslatButton.setVisibility(View.VISIBLE)
            binding.durdurButton.setVisibility(View.INVISIBLE)
            sn=0
            dk=0
            binding.sayacText.text="00:00"
            handler.removeCallbacks(runnable)
        }





    }
}