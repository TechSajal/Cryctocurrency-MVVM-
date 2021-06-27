package com.example.wiseleap.Adapter
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.example.wiseleap.Model.markets
import com.example.wiseleap.R
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToLong

class UserAdapter(val mContext:Context, val userlist : List<markets>):RecyclerView.Adapter<UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_rv,parent,false)
        return UserViewHolder(view)
    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
              val user = userlist[position]
            holder.status.text = user.status
          holder.uxchangeid.text = user.exchange_id
        holder.symbol.text = user.symbol
         val saj = NumberFormat.getInstance().format((user.price.toFloat()))
        holder.price.text = "$ ${saj}"
        holder.vol.text = NumberFormat.getInstance().format(user.volume_24h.toFloat())
        holder.priceun.text = NumberFormat.getInstance().format(user.price_unconverted.toFloat())
        holder.change24.text = "${user.change_24h.toFloat().toLong().toString()} %"

        val mdate =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US).parse(user.time!!)
         val dateformat:String = SimpleDateFormat("HH:mm:ss").format(mdate!!)
         holder.date.text = dateformat
    }


    override fun getItemCount(): Int {
       return userlist.size
    }




}

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val uxchangeid:AppCompatButton = itemView.findViewById(R.id.exchangeid)
    val symbol:TextView = itemView.findViewById(R.id.symbol)
    val status:AppCompatButton = itemView.findViewById(R.id.status)
    val price :TextView = itemView.findViewById(R.id.price)
    val vol:TextView = itemView.findViewById(R.id.volume24h)
    val priceun:TextView = itemView.findViewById(R.id.priceuncovered)
    val change24 :TextView = itemView.findViewById(R.id.change24h)
    val date :AppCompatButton = itemView.findViewById(R.id.date)
}



