package com.example.counterbutton

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {
    var countDownTimer: CountDownTimer?=null

    fun getCountDownTimerVisible (entity: MyEntity, adapter: RecyclerAdapter){
        countDownTimer=object: CountDownTimer(2000, 1000){
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                entity.visibleCounter=false
                adapter.notifyItemChanged(entity.id)
                countDownTimer=null
            }
        }
    }
}