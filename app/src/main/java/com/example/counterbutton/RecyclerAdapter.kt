package com.example.counterbutton

import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.counterbutton.databinding.CounterBinding
import com.example.counterbutton.databinding.ItemBinding

class RecyclerAdapter(values: List<MyEntity>): RecyclerView.Adapter<RecyclerAdapter.Item>() {
    var values:List<MyEntity> =values
    lateinit var counterListener: OnCounterClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Item {
        val inflater=LayoutInflater.from(parent.context)
        val binding=ItemBinding.inflate(inflater, parent,false)
        return Item(binding.root)
    }

    override fun onBindViewHolder(holder: Item, position: Int) {
        holder.binding!!.entity=values.get(position)
    }

    override fun getItemCount(): Int {
        return values.size
    }

    fun setCounterClickListener(listener: OnCounterClickListener){
        counterListener=listener
    }

    inner class Item(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var binding: ItemBinding?=null

        init {
            binding= DataBindingUtil.bind(itemView)
            binding!!.include.buttonPrice.setOnClickListener(this)
            binding!!.include.bubbleButton.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val entity=values.get(position)
                counterListener.OnCounterClick(v!!, entity!!)
        }
    }

    interface OnCounterClickListener{
        fun OnCounterClick(view: View, entity: MyEntity)
    }
}