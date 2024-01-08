package com.example.workkotlien3.login

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.workkotlien3.MainActivity
import com.example.workkotlien3.databinding.AddCustomerBinding
import com.example.workkotlien3.db.SqliteHelperUser
import com.example.workkotlien3.db.User

class AddCustomer: AppCompatActivity() {

    // 전역 변수로 바인딩 객체 선언
    private var mBinding: AddCustomerBinding? = null
    // 매번 null 체크를 할 필요 없이 편의성을 위해 바인딩 변수 재 선언
    private val binding get() = mBinding!!

    private val TAG = "JM AddCustomer"
    val helper = SqliteHelperUser(this, "user", 1)
    private var idcheck : Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = AddCustomerBinding.inflate(layoutInflater)

        setContentView(binding.root)
        Log.d(TAG,"회원가입 화면")
        initLisnerer()

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

    private fun initLisnerer(){
        mBinding!!.ischeckidbtn.setOnClickListener {
            val ischeckid = mBinding!!.ideditbox.text.toString()

            // 동일한 아이디가 있을 경우 return 없을 경우 플래그 값 false -> true
            if (!isCheckId(ischeckid)) return@setOnClickListener
            else idcheck = true
        }

        mBinding!!.infobtn.setOnClickListener {

            if(!isNullCheckEdit()) return@setOnClickListener

            if(idcheck == false){
                val builder = AlertDialog.Builder(this)
                builder.setTitle("")
                    .setMessage("id 중복 체크 확인 해주세요.")
                    .setPositiveButton("확인", DialogInterface.OnClickListener { dialogInterface, i ->
                    }).show()
                return@setOnClickListener
            }


            val backLoginIntent = Intent(this,MainActivity::class.java)
            startActivity(backLoginIntent)
            Log.d(TAG,"다시 로그인 화면으로 이동 회원 가입 완료")
            val builder = AlertDialog.Builder(this)
            addcustomerDb()
            builder.setTitle("")
                .setMessage("회원가입 완료 했습니다.")
                .setPositiveButton("확인", DialogInterface.OnClickListener { dialogInterface, i ->
                    finish()
                    idcheck=false
                }).show()

        }
    }
    private fun isCheckId(ischeckid:String ) : Boolean{
        if(helper.selectId(ischeckid)){
            val builder = AlertDialog.Builder(this)
            builder.setTitle("")
                .setMessage("동일한 아이디가 있습니다.")
                .setPositiveButton("확인", DialogInterface.OnClickListener { dialogInterface, i ->
                }).show()
            return false
        } else {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("")
                .setMessage("사용이 가능한 아이디 입니다.")
                .setPositiveButton("확인", DialogInterface.OnClickListener { dialogInterface, i ->
                }).show()
            return true
        }
    }

    private fun isNullCheckEdit() :Boolean{
        if(mBinding!!.usernameeditbox.text.toString().isNullOrEmpty() &&
           mBinding!!.ideditbox.text.toString().isNullOrEmpty() &&
           mBinding!!.pwditbox.text.toString().isNullOrEmpty()
                ) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("")
                .setMessage("사용자 이름, 아이디, 비밀번호 작성 해주세요")
                .setPositiveButton("확인",DialogInterface.OnClickListener { dialogInterface, i ->
                }).show()
            return false
        } else if (mBinding!!.usernameeditbox.text.toString().isNullOrEmpty()){
            val builder = AlertDialog.Builder(this)
            builder.setTitle("")
                .setMessage("사용자 이름 작성해주세요.")
                .setPositiveButton("확인",DialogInterface.OnClickListener { dialogInterface, i ->
                }).show()
            return false

        }else if(mBinding!!.ideditbox.text.toString().isNullOrEmpty()){
            val builder = AlertDialog.Builder(this)
            builder.setTitle("")
                .setMessage("아이디 입력 해주세요.")
                .setPositiveButton("확인",DialogInterface.OnClickListener { dialogInterface, i ->
                }).show()
            return false

        } else if (mBinding!!.pwditbox.text.toString().isNullOrEmpty()){
            val builder = AlertDialog.Builder(this)
            builder.setTitle("")
                .setMessage("비밀번호 입력 해주세요.")
                .setPositiveButton("확인",DialogInterface.OnClickListener { dialogInterface, i ->
                }).show()
            return false

        }

        return true

    }


    private fun addcustomerDb(){
        val user = User(null,
                            mBinding!!.usernameeditbox.text.toString(),
                            mBinding!!.ideditbox.text.toString(),
                            mBinding!!.pwditbox.text.toString(),)

        helper.insertUser(user)

    }

}