package com.notrace.demo

import android.support.v7.widget.CardView

/**
 * Created by smile-up on 2017/10/18.
 */
interface CardAdapter {
//    val MAX_ELEVATION_FACTOR:Int
//    val maxWithImplementation:Int
//    get()=8


    fun  getBaseElevation():Float
    fun getCardViewAt(position:Int):CardView?

    fun getCountSize():Int
}