package com.example.workkotlien3.information

import com.example.workkotlien3.vote.MenuVote
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.workkotlien3.databinding.FirstMenuBinding
import com.example.workkotlien3.makemenu.MakeMenu
import com.example.workkotlien3.prevResult.PrevMenuRecord

class firstMain : AppCompatActivity() {

    // 전역 변수로 바인딩 객체 선언
    private var mBinding: FirstMenuBinding? = null
    // 매번 null 체크를 할 필요 없이 편의성을 위해 바인딩 변수 재 선언
    private val binding get() = mBinding!!
    private val TAG = "JM menu"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = FirstMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG,"메뉴 구성")

        mBinding!!.menurecomendbtn.setOnClickListener {
            val intent = Intent(this,MakeMenu::class.java)
            startActivity(intent)
        }
        mBinding!!.menuvotingbtn.setOnClickListener {
            val intent = Intent(this, MenuVote::class.java)
            startActivity(intent)
        }

        mBinding!!.prevrecode.setOnClickListener {
            Log.d(TAG,"이전 메뉴 기록 창으로 이동")
            val intent = Intent(this, PrevMenuRecord::class.java)
            startActivity(intent)
        }


    }
}