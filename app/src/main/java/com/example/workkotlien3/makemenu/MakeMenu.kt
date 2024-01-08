package com.example.workkotlien3.makemenu

import Utils.MenuUtils
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.workkotlien3.databinding.ActivityMainBinding
import com.example.workkotlien3.databinding.FirstMenuBinding
import com.example.workkotlien3.databinding.MenuMakeBinding

class MakeMenu : AppCompatActivity() {

    // 전역 변수로 바인딩 객체 선언
    private var mBinding: MenuMakeBinding? = null
    // 매번 null 체크를 할 필요 없이 편의성을 위해 바인딩 변수 재 선언
    private val binding get() = mBinding!!
    private val TAG = "JM menu 구성2"
    private val editList = ArrayList<EditText>()

    //Alter Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = MenuMakeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        Log.d(TAG,"메뉴 구성")

    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        editSetTest()
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
        editList.add(mBinding!!.menuedit1)
        editList.add(mBinding!!.menuedit2)
        editList.add(mBinding!!.menuedit3)
        editList.add(mBinding!!.menuedit4)
        editList.add(mBinding!!.menuedit5)

        Log.d(TAG,"${editList.size}")
        mBinding!!.addmenubtn.setOnClickListener {
            for (i in 0..editList.size - 1) {
                //todo
                if (!editList.get(i).text.toString().isNullOrEmpty()) {
                    MenuUtils.MenuEdit.add(editList.get(i).text.toString())
                    Log.d(TAG, " ${i} value: ${editList.get(i).text.toString()} ")
                } else {
                    Log.d(TAG, " ${i} value: null value  ")
                }
            }
            val builder = AlertDialog.Builder(this)

            builder.setTitle("")
                .setMessage("메뉴 등록이 되었습니다. 메뉴 투표를 해주세요")
                .setOnDismissListener(DialogInterface.OnDismissListener {
                    finish()
                })
                .setPositiveButton("확인", DialogInterface.OnClickListener { dialogInterface, i ->
                    finish()
                }).show()

        }
    }

    private fun editSetTest(){

        if(MenuUtils.MenuEdit.size != 0){
            for( i in 0 .. MenuUtils.MenuEdit.size-1){
                editList.get(i).setText(MenuUtils.MenuEdit.get(i))
            }
        }



    }


}