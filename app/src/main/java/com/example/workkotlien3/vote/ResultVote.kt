package com.example.workkotlien3.vote


import Utils.MenuUtils
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.workkotlien3.databinding.CustommarketViewBinding
import com.example.workkotlien3.databinding.PieChartBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate

class ResultVote : AppCompatActivity()  {

    /*
예제는 지출 목록
PieChart 구현 순서
1. PieEntry - 차트에서 사용할 데이터 값
2. PieDataSet - 데이터 값의 집합
3. PieData - 데이터 한 덩어리
4. Pie CHart

PicCHartDataSetting(setdata)
1.PieEntry(value,Label)를 pieEntryList에 추가
2.PieEntty List를 PieDataSet에 바운딩
3.PieDataSet을 PieData에 바운딩

PieChartSetting(setUpChart)
1.PieChart에 PieData를 바운딩
* */

    private val TAG = "JM ResultVote"
    private var mBinding : PieChartBinding? = null
    private var mCustomMarketView : CustommarketViewBinding? = null

    private val binding get() = mBinding!!
    private val bindingCustomMarketView get() = mCustomMarketView!!

    private var set : PieDataSet? = null
    private var data : PieData? = null
    private var entries : MutableList<PieEntry>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = PieChartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setData()
        setUpdate()

    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun setData(){
        entries = mutableListOf<PieEntry>()

        entries!!.add(PieEntry(5.0f, MenuUtils.MenuEdit.get(0).toString()))
        entries!!.add(PieEntry(0.0f, MenuUtils.MenuEdit.get(1).toString()))
        entries!!.add(PieEntry(2.0f, MenuUtils.MenuEdit.get(2).toString()))
        entries!!.add(PieEntry(7.0f, MenuUtils.MenuEdit.get(3).toString()))
        entries!!.add(PieEntry(3.0f, MenuUtils.MenuEdit.get(4).toString()))

        set = PieDataSet(entries,"투표")

        //색상 선택
        set!!.setColors(ColorTemplate.JOYFUL_COLORS.toMutableList())
        //엔트리를 클릭 시 얼마나 확대 하는지
        set!!.selectionShift = 3.0f
        set!!.xValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE
        set!!.yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE

        data = PieData(set)
        if(data == null) Log.d(TAG,"data value null")
        else Log.d(TAG,"data value exsise")

        data!!.setValueTextSize(12.0f)

    }

    private fun setUpdate(){
        //pie.data = data
        mBinding!!.pie.data = data

        mBinding!!.pie.setEntryLabelColor(Color.BLACK)
        mBinding!!.pie.description.isEnabled = false
        mBinding!!.pie.setUsePercentValues(true)
        mBinding!!.pie.animateY(2000,Easing.EaseInOutQuad)

        var legend :Legend = mBinding!!.pie.legend
        legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER

        mBinding!!.pie.centerText = "총 투표 수 ${getSum()}"

        val itemClick = ItemClick()
        mBinding!!.pie.setOnChartValueSelectedListener(itemClick)

        mBinding!!.pie.invalidate()
    }

    private fun getSum() : Int{
        var sum = 0 ;
        for (i in 0.. entries!!.size-1){
            sum += entries!!.get(i).value.toInt()
        }
        return sum

    }
    inner class ItemClick : OnChartValueSelectedListener{
        override fun onValueSelected(e: Entry?, h: Highlight?) {
            var itemVoteVaue : Int = 0
            try {
                itemVoteVaue = mBinding!!.pie.data.getDataSetForEntry(e).getEntryIndex(e as PieEntry?)
            } catch (e: Throwable){
                Log.d(TAG,"onValueSelected Error")
            }
            Toast.makeText(baseContext,"${entries!!.get(itemVoteVaue).value} :" +
                    " ${entries!!.get(itemVoteVaue).label}",Toast.LENGTH_LONG ).show()


        }

        override fun onNothingSelected() {
            TODO("Not yet implemented")
        }


    }


}

