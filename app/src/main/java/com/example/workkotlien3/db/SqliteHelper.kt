package com.example.workkotlien3.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.text.Editable
import android.util.Log

class SqliteHelperUser(context: Context, name: String, version: Int) :
    SQLiteOpenHelper(context, name, null, version) {

    private val TAG = "JM DB"

    override fun onCreate(db: SQLiteDatabase?) {
        val create = "create table user (" +
                "no integer primary key, " +
                "username text, " +
                "userid text," +
                "userpw text" +
                ")"
        db?.execSQL(create)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertUser(user: User) {
        val values = ContentValues()
        values.put("username", user.userName)
        values.put("userid", user.userid)
        values.put("userpw", user.userpw)

        Log.d(TAG,"DB Insert 접근 ")

        val wd = writableDatabase
        wd.insert("user", null, values)
        wd.close()
        Log.d(TAG,"DB Insert 완료")

    }

    fun selectId(isCheckId: String): Boolean{
        val select = "select userid from user"
        val rd = readableDatabase
        val cursor = rd.rawQuery(select, null)

        while (cursor.moveToNext()){
            val userIdIdx = cursor.getString(0) // 3
            Log.d(TAG,"selectId 접근 value: "+userIdIdx.toString())

            if (isCheckId.equals(userIdIdx)){
                //있을 경우
                return true
            }
        }
        // 없을 경우
        return false
    }

    fun ischeckLogin(id:String, pw:String):Boolean{
        val select = "select userid,userpw from user"
        val rd = readableDatabase
        val cursor = rd.rawQuery(select, null)

        Log.d(TAG,"ischeckLogin 접근 하는 방법 ")
        while (cursor.moveToNext()){
            val userIdIdx = cursor.getString(0) // id
            val userpwIdx = cursor.getString(1) // pw
            Log.d(TAG,"ischeckLogin DB 값 0: "+userIdIdx.toString()+" userpwIdx: "+userpwIdx)


            if (id.equals(userIdIdx) && pw.equals(userpwIdx))
                return true

        }
        // 없을 경우
        return false


    }

    fun selectuser(): MutableList<User> {
        val list = mutableListOf<User>()
        val select = "select * from user"
        val rd = readableDatabase
        val cursor = rd.rawQuery(select, null)
        while (cursor.moveToNext()) {
            // 먼저 컬럼의 이름으로 각각의 위치를 가져온다.
            val noIdx = cursor.getColumnIndex("no") // 1 : 테이블에서 no 컬럼의 순서
            val userNameIdx = cursor.getColumnIndex("username") // 2
            val userIdIdx = cursor.getColumnIndex("userid") // 3
            val userPwIdx = cursor.getColumnIndex("userpw") // 4

            val no = cursor.getLong(noIdx) // 값은 위에서 저장해 둔 컬럼의 위치로 가져온다
            val userName = cursor.getString(userNameIdx)
            val userID = cursor.getString(userIdIdx)
            val userPw = cursor.getString(userPwIdx)

            list.add(User(no, userName, userID,userPw))
        }

        cursor.close()
        rd.close()
        return list
    }

    fun updateUser(user:User) {
        val values = ContentValues()
        values.put("username", user.userName)
        values.put("userid", user.userid)
        values.put("userpw", user.userpw)

        val wd = writableDatabase
        try{
            wd.update("User", values, "no = ${user.no}", null)
        }catch (e: java.lang.Exception){
            Log.d(TAG,"DB 에러 발생 : "+e.printStackTrace().toString())
        }
        wd.close()
    }

    fun deleteUser(user:User) {
        val delete = "delete from user where no = ${user.no}"
        val db = writableDatabase
        db.execSQL(delete)
        db.close()
    }
}

data class User(var no:Long?, var userName:String, var userid:String,var userpw:String)