package com.notrace.demo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by smile-up on 2017/10/18.
 */
class CardFragment:Fragment() {

    var mCardView:CardView? =null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view=inflater!!.inflate(R.layout.fragment_card,container,false)

        mCardView=view.findViewById(R.id.cardView)
        mCardView?.maxCardElevation=mCardView!!.cardElevation*8

        return view
    }

    fun getCardView():CardView?{
        return mCardView
    }
}