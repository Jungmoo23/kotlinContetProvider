package com.example.workkotlien3.vote

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workkotlien3.databinding.MenuVoltBinding


class MenuVote : AppCompatActivity() {

    private val TAG = "JM MenuVote.kt"
    private var mBinding : MenuVoltBinding? = null

    private val binding get() = mBinding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = MenuVoltBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG,"메뉴투표 하는 곳으로 이동 완료")

        val adapter = RecyclerAdapter()
        mBinding!!.recyclerview.adapter = adapter
        mBinding!!.recyclerview.layoutManager = LinearLayoutManager(this)
        Log.d(TAG,"리사이클 뷰 생성 완료")

        mBinding!!.voltbtn.setOnClickListener {
            val intent = Intent(this,ResultVote::class.java)
            startActivity(intent)
            Log.d(TAG,"결과 창으로 이동 ")
        }




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



}