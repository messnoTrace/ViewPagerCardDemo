package com.notrace.demo

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.widget.CardView
import android.view.ViewGroup

/**
 * Created by smile-up on 2017/10/18.
 */
class CardFragmentPagerAdapter:FragmentPagerAdapter,CardAdapter {
    var mBaseElevation:Float =0f
    var mFragments:ArrayList<CardFragment> =ArrayList()

    constructor(fm: FragmentManager,baseElevation:Float):super(fm){
        mBaseElevation=baseElevation

        for (i in 0..3){
            addCardFragment(CardFragment())
        }
    }






    override fun getBaseElevation(): Float {
        return mBaseElevation
    }

    override fun getCardViewAt(position: Int): CardView? {
       return mFragments[position].getCardView()
    }

    override fun getCountSize(): Int {
       return mFragments.size
    }

    override fun getCount(): Int {
        return mFragments.size
    }

    override fun getItem(position: Int): Fragment {
      return mFragments[position]
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        var fragment =super.instantiateItem(container, position)
        mFragments.set(position,fragment as CardFragment)

        
        return fragment
    }

    fun addCardFragment(fragment: CardFragment){
        mFragments.add(fragment)
    }
}