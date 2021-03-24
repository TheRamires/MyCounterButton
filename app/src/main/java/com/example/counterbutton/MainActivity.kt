package com.example.counterbutton
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import java.lang.reflect.Array
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), RecyclerAdapter.OnCounterClickListener {
    lateinit var viewModel:MyViewModel
    lateinit var values:MutableList<MyEntity>
    lateinit var recyclerView:RecyclerView
    lateinit var adapter:RecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MyViewModel::class.java]

        values= ArrayList()

        for (i in 0..5){
            values.add(MyEntity(i, "Element $i", 3))
        }
        for (i in 6..30){
            values.add(MyEntity(i, "Element $i", 50))
        }

        recyclerView=findViewById(R.id.recycler)
        adapter=RecyclerAdapter(values)
        adapter.setCounterClickListener(this)
        recyclerView.adapter=adapter

        var bubbleButton =findViewById<Button>(R.id.bubble_button)
        val tvNotification=findViewById<TextView>(R.id.tvNotification)
    }

    override fun OnCounterClick(view: View, entity: MyEntity) {

        val id=entity.id
        when(view.id){
            R.id.button_price -> {
                entity.visibleCounter = true
                viewModel.getCountDownTimerVisible(entity, recyclerView.adapter as RecyclerAdapter)
                viewModel.countDownTimer?.start()
            }

            R.id.bubble_button -> {
                entity.counter++
                values[id]=entity
                viewModel.countDownTimer?.cancel()
                viewModel.countDownTimer?.start()
            }
        }

        recyclerView.adapter!!.notifyItemChanged(id)
    }
}