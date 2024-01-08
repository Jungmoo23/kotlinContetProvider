package com.example.workkotlien3

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.example.workkotlien3.databinding.ActivityMainBinding
import com.example.workkotlien3.db.SqliteHelperUser
import com.example.workkotlien3.information.firstMain
import com.example.workkotlien3.login.AddCustomer

class MainActivity : AppCompatActivity() {

    // 전역 변수로 바인딩 객체 선언
    private var mBinding: ActivityMainBinding? = null
    // 매번 null 체크를 할 필요 없이 편의성을 위해 바인딩 변수 재 선언
    private val binding get() = mBinding!!
    private val TAG = "JM Main"
    val helper = SqliteHelperUser(this, "user", 1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        Log.d(TAG,"초기 화면")
        initLisner()
        ischeckIdPw()
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

    private fun initLisner(){
        // 로그인
        mBinding!!.loginbtn.setOnClickListener {
            val intent:Intent = Intent(this,firstMain::class.java)

            // id null 체크
            if(mBinding!!.ideditbox.text.toString().isNullOrEmpty() &&
               mBinding!!.pwditbox.text.toString().isNullOrEmpty() ) {
                val builder = AlertDialog.Builder(this)

                    builder.setTitle("")
                        .setMessage("아이디 및 비밀번호 입력 해주세요.")
                        .setPositiveButton("확인",DialogInterface.OnClickListener { dialogInterface, i ->

                        }).show()

                return@setOnClickListener
            } else if(mBinding!!.ideditbox.text.toString().isNullOrEmpty()){
                val builder = AlertDialog.Builder(this)
                builder.setTitle("")
                    .setMessage("아이디 입력 해주세요.")
                    .setPositiveButton("확인",DialogInterface.OnClickListener { dialogInterface, i ->
                    }).show()
                return@setOnClickListener
            } else if (mBinding!!.pwditbox.text.toString().isNullOrEmpty()){
                val builder = AlertDialog.Builder(this)
                builder.setTitle("")
                    .setMessage("비밀번호 입력 해주세요.")
                    .setPositiveButton("확인",DialogInterface.OnClickListener { dialogInterface, i ->
                    }).show()
                return@setOnClickListener
            }

            if(helper.ischeckLogin(mBinding!!.ideditbox.text.toString(),
                                   mBinding!!.pwditbox.text.toString())) {
                Log.d(TAG,"로그인 성공")
                startActivity(intent)
            } else {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("")
                    .setMessage("id 및 pw가 다릅니다.")
                    .setPositiveButton("확인",DialogInterface.OnClickListener { dialogInterface, i ->
                    }).show()
            }

            Log.d(TAG,"메인 화면으로 이동")
        }
        //회원가입
        mBinding!!.infobtn.setOnClickListener {
            val intent:Intent = Intent(this,AddCustomer::class.java)
            startActivity(intent)
            Log.d(TAG,"회원가입 창으로이동")
        }
    }

    private fun ischeckIdPw(){

    }
}