package com.example.workkotlien3.prevResult

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workkotlien3.data.PrevMenuRecode
import com.example.workkotlien3.databinding.PrevmainActivityBinding
import com.example.workkotlien3.databinding.PrevmenuItemBinding
import com.google.android.material.tabs.TabLayout.TabGravity

class PrevMenuRecord : AppCompatActivity() {


    private var TAG = "JM PrevMenuRecord"
    private var prevMenuList :MutableList<PrevMenuRecode> ? = null
    private var mBinding : PrevmainActivityBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = PrevmainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        val adapter = PrevMenuRecyclerAdapter()
        adapter.setList(prevMenuList!!)
        mBinding!!.prevlistview.adapter = adapter
        mBinding!!.prevlistview.layoutManager = LinearLayoutManager(this)
        Log.d(TAG,"리사이클 뷰 생성 완료")



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

    private fun init(){
        prevMenuList = mutableListOf()
        prevMenuList!!.add(PrevMenuRecode("2024-01-01","돈까스",6))
        prevMenuList!!.add(PrevMenuRecode("2024-01-02","피자",4))
        prevMenuList!!.add(PrevMenuRecode("2024-01-03","햄버거",8))
        prevMenuList!!.add(PrevMenuRecode("2024-01-04","분식",7))
        prevMenuList!!.add(PrevMenuRecode("2024-01-05","중국집",5))

    }



}