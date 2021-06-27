package com.example.wiseleap

import android.app.ProgressDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.wiseleap.Adapter.UserAdapter
import com.example.wiseleap.Model.markets
import com.example.wiseleap.ViewModel.UserViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var users_recyclerView:RecyclerView
    private var progressDialog: ProgressDialog? = null
    private lateinit var userAdapter:UserAdapter
    lateinit var  list :List<markets>
    lateinit var  Users_LinearLayoutManager: LinearLayoutManager
    private lateinit var userViewModel :UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showdialog(this)
        val swipe:SwipeRefreshLayout =findViewById(R.id.swipe_refresh_layout)
        swipe.setOnRefreshListener {
             showdialog(this)
            userViewModel =ViewModelProvider(this).get(UserViewModel::class.java)
            userViewModel.usersData.observe(this, Observer {
                list=it as List<markets>
                userAdapter= UserAdapter(this, list)
                users_recyclerView.adapter=userAdapter
                userAdapter.notifyDataSetChanged()
                DismissDialog()
            })
            swipe.isRefreshing = false
        }

        users_recyclerView = findViewById(R.id.recyclerview)

        Users_LinearLayoutManager= LinearLayoutManager(this)
        users_recyclerView.layoutManager=Users_LinearLayoutManager
        users_recyclerView.setHasFixedSize(true)

        //markets list

        userViewModel =ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.usersData.observe(this, Observer {
            list=it as List<markets>
            userAdapter= UserAdapter(this, list)
            DismissDialog()
            users_recyclerView.adapter=userAdapter
            userAdapter.notifyDataSetChanged()

        })





    }

    fun  showdialog(context: Context){
        progressDialog =  ProgressDialog(context)
        progressDialog!!.show()
        progressDialog!!.setContentView(R.layout.progress_dialog)
        progressDialog!!.setCanceledOnTouchOutside(false)
        progressDialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)

    }

    fun DismissDialog(){
        progressDialog!!.dismiss()

    }


}