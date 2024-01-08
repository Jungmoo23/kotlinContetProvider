package com.example.workkotlien3.vote

import android.content.Context
import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.workkotlien3.databinding.CustommarketViewBinding
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF

class CustomMarketView(context: Context?, layoutResource: Int) :
    MarkerView(context, layoutResource) {

    private var mBinding : CustommarketViewBinding? = null
    private val binding get() = mBinding!!
    private var TAG = "JM CustomMarketView"

    init {
        Log.d(TAG, "CustomMarketView imit Block")
    }

    override fun refreshContent(e: Entry?, highlight: Highlight?) {

        if(e is PieEntry){
            val pe : PieEntry = e
            mBinding!!.tv.text = "${pe.value} 표"
        }else {
            mBinding!!.tv.text = "${e!!.y} 표"
        }


        super.refreshContent(e, highlight)
    }

    override fun getOffset(): MPPointF {
        return MPPointF(-(width/2).toFloat(), (-(height/2)).toFloat())
    }






}