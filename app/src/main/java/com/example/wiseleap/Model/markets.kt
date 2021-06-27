package com.example.wiseleap.Model

data class markets(
    val exchange_id :String?= null,
    val symbol :String  = "",
    val price_unconverted:String ="",
    val price:String = "",
    val change_24h:String = "",
    val spread:String="",
    val volume_24h:String= "",
    val status:String ="",
    val time:String ?=null
)