package com.example.workkotlien3.prevResult

import Utils.MenuUtils
import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workkotlien3.data.PrevMenuRecode
import com.example.workkotlien3.databinding.PrevmenuItemBinding

class PrevMenuRecyclerAdapter : RecyclerView.Adapter<PrevMenuRecyclerAdapter.Holder>()  {

    private val TAG = "JM PrevMenuRecyclerAdapter.kt"
    private var list : MutableList<PrevMenuRecode> = mutableListOf()
    private var cnt :Int = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = PrevmenuItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        Log.d(TAG, "onCreateViewHolder")
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val prevMenu = list.get(position)
        Log.d(TAG, "onBindViewHolder $prevMenu")
        holder.setadd(prevMenu)
    }
    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount ${list.size}")

        return list.size
    }

    public fun setList( putlist:MutableList<PrevMenuRecode> ){
        list.addAll(putlist)
    }

    inner class Holder(val binding: PrevmenuItemBinding) : RecyclerView.ViewHolder(binding.root){
        var menuName:String? = null
        init {
            Log.d(TAG, "Holder init")

        }
        fun setadd(prevMenu:PrevMenuRecode){
            cnt ++
            binding.itemnum.text = cnt.toString()
            binding.prevmenudate.text = prevMenu.date
            binding.prevmenu.text = prevMenu.menuName
        }

    }


}