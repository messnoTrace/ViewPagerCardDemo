package com.notrace.demo

import android.support.v4.view.ViewPager
import android.view.View

/**
 * Created by smile-up on 2017/10/18.
 */
class ShadowTransformer(viewpager:ViewPager,adapter: CardAdapter) :ViewPager.OnPageChangeListener,ViewPager.PageTransformer {

    var mLastOffset:Float =0f
    var mScalingEnable:Boolean=false

    val mViewPager:ViewPager=viewpager
    val mAdapter:CardAdapter =adapter
    init {
        mViewPager.addOnPageChangeListener(this)
    }


    override fun transformPage(page: View?, position: Float) {
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        var realCurrentPosition:Int
        var nextPosition:Int
        var baseElevation:Float =mAdapter.getBaseElevation()
        var realOffeset:Float
        var goingLeft:Boolean=mLastOffset>positionOffset
        if(goingLeft){
            realCurrentPosition=position+1
            nextPosition=position
            realOffeset=1-positionOffset
        }else {
            nextPosition=position+1
            realCurrentPosition=position
            realOffeset=positionOffset
        }
        if(nextPosition>mAdapter.getCountSize()-1||realCurrentPosition>mAdapter.getCountSize()-1){
            return
        }
        val cardView =mAdapter.getCardViewAt(realCurrentPosition)

        if(cardView!=null){
            if(mScalingEnable){
                cardView.scaleX=(1+0.1*(1-realOffeset)).toFloat()
                cardView.scaleY=(1+0.1*(1-realOffeset)).toFloat()
            }
            //CardAdapter中常量，不会设置啊，，
            cardView.cardElevation=baseElevation+baseElevation*(8-1)*realOffeset

        }
        val nextCardView=mAdapter.getCardViewAt(nextPosition)
        if(nextCardView!=null){
            if(mScalingEnable){
                nextCardView.scaleX=(1+0.1*realOffeset).toFloat()
                nextCardView.scaleY=(1+0.1*realOffeset).toFloat()
            }

            nextCardView.maxCardElevation=baseElevation +baseElevation *(8-1) *realOffeset
        }

        mLastOffset=positionOffset

    }

    override fun onPageSelected(position: Int) {
    }

    fun  enableScaling(enable:Boolean){

        if(mScalingEnable&&!enable){
            val cardView=mAdapter.getCardViewAt(mViewPager.currentItem)
            if(cardView!=null){
                cardView.animate().scaleX(1f)
                cardView.animate().scaleY(1f)
            }
        }else if (!mScalingEnable&&enable){
            val  cardView=mAdapter.getCardViewAt(mViewPager.currentItem)
            if(cardView!=null){
                cardView.animate().scaleX(1.1f)
                cardView.animate().scaleY(1.1f)
            }
        }
        mScalingEnable=enable
    }

}