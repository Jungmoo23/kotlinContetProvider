package com.example.workkotlien3.vote

import Utils.MenuUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workkotlien3.databinding.ListviewItemBinding

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.Holder>()  {

    private val TAG = "JM RecyclerAdapter.kt"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ListviewItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        Log.d(TAG, "onCreateViewHolder")

        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val menuVote = MenuUtils.MenuEdit.get(position)
        Log.d(TAG, "onBindViewHolder $menuVote")
        holder.isCheck(menuVote)
    }

    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount ${MenuUtils.MenuEdit.size}")

        return MenuUtils.MenuEdit.size
    }

    inner class Holder(val binding: ListviewItemBinding) : RecyclerView.ViewHolder(binding.root){
        var menuName:String? = null
        init {
            Log.d(TAG, "Holder init")
            binding.itemlistlayout.setOnClickListener{
                isCheck()
                notifyDataSetChanged()
            }
        }
        fun isCheck(){
            Log.d(TAG, "Holder isCheck")

            if(binding!!.checkbox.isChecked){
                binding!!.checkbox.isChecked = false
            }else {
                binding!!.checkbox.isChecked = true
            }

        }
        fun isCheck(menuName :String){
            Log.d(TAG, "Holder isCheck menuName")
            binding!!.itemlist.text = menuName
            this.menuName = menuName
        }


    }
}