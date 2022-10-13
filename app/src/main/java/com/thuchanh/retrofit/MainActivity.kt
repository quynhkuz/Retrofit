package com.thuchanh.retrofit

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var tex  : TextView
    lateinit var diglog : ProgressBar
    var data = MutableLiveData<User>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tex = findViewById(R.id.tex)
        diglog = findViewById(R.id.progressbar)
        diglog.visibility = View.GONE


        findViewById<Button>(R.id.btn).setOnClickListener {
            diglog.visibility = View.VISIBLE
            callapi()
        }




    }


    fun callapi(){

        var  user:User? = null
        var exception = CoroutineExceptionHandler { coroutineContext, throwable ->
            Toast.makeText(this@MainActivity,throwable.message,Toast.LENGTH_SHORT).show()
        }
        var coroutine = CoroutineScope(Job() + Dispatchers.Main + exception)
        coroutine.launch {

            var job = launch( start = CoroutineStart.LAZY  ) {
                Log.d("AAA","chay 2")
                findViewById<TextView>(R.id.tex).append("${user?.fanpage}\n${user?.logo}\n${user?.monhoc}\n${user?.noihoc}\n${user?.website}")
                diglog.visibility = View.GONE


            }
            launch (Dispatchers.IO){



                var retrofit = RetrofitAPI()
                retrofit.apiService.getdata()?.enqueue(object : Callback<User?> {
                    override fun onResponse(call: Call<User?>, response: Response<User?>) {
                        user = response.body()!!
//                  tex.text = user?.website
//                        findViewById<TextView>(R.id.tex).append("${user?.fanpage}\n${user?.logo}\n${user?.monhoc}\n${user?.noihoc}\n${user?.website}")
//                        diglog.visibility = View.GONE
                        Log.d("AAA","chay 1")

                        job.start()

                    }

                    override fun onFailure(call: Call<User?>, t: Throwable) {
                        Toast.makeText(this@MainActivity,t.message,Toast.LENGTH_SHORT).show()
                    }
                })

            }
            data.postValue(user)


//            withContext(Dispatchers.Main)
//            {
//                        Log.d("AAA","chay 2")
////                    findViewById<TextView>(R.id.tex).append("${user?.fanpage}\n${user?.logo}\n${user?.monhoc}\n${user?.noihoc}\n${user?.website}")
//
//
//            }

        }


    }
}