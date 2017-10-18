package com.notrace.demo

import android.support.v4.view.PagerAdapter
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by smile-up on 2017/10/18.
 */
class CardPagerAdapter :PagerAdapter,CardAdapter {
    constructor()


    var mViews:ArrayList<CardView?> = ArrayList()
    var mData:ArrayList<CardItem?> = ArrayList()
    var mBaseElevation=0f




    fun addCardItem(item: CardItem){
        mViews.add(null)
        mData.add(item)
    }

    override fun getBaseElevation(): Float {
        return mBaseElevation
    }

    override fun getCardViewAt(position: Int): CardView? {
        return mViews[position]
    }

    override fun getCountSize(): Int {
        return mData.size
    }
    override fun getCount(): Int {
        return mData.size
    }
    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view==`object`
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        container!!.removeView(`object`as View)
        mViews.set(position,null)
    }



    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        var view=LayoutInflater.from(container?.context).inflate(R.layout.item,container,false)
        container?.addView(view)
        bind(mData.get(position),view)

        var  cardView=view.findViewById<CardView>(R.id.cardView)
        if(mBaseElevation ==0f){
            mBaseElevation=cardView.cardElevation
        }
        cardView.maxCardElevation=mBaseElevation*8
        mViews.set(position,cardView)

        return view
    }

    fun bind(item: CardItem?,view: View?){
        var titleTextView=view?.findViewById<TextView>(R.id.tv_item_title)
        var contentTextview =view?.findViewById<TextView>(R.id.tv_item_content)
        titleTextView?.setText(item!!.mTitleResource)
        contentTextview?.setText(item!!.mTextResource)

    }
}