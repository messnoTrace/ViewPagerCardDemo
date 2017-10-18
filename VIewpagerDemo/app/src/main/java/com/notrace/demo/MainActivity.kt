package com.notrace.demo

import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : FragmentActivity(){




    val mButton:Button by lazy{
        findViewById<Button>(R.id.btnCardType)
    }
    val  mViewPager:ViewPager by lazy {
        findViewById<ViewPager>(R.id.viewpager)
    }


    var mCardAdapter:CardPagerAdapter?=null
    var mCardShadowTransformer:ShadowTransformer?=null
    var mFragmentCardAdapter:CardFragmentPagerAdapter?=null
    var mFragmentCardShadowTransformer:ShadowTransformer?=null

    var mShowingFragments=false




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        mCardAdapter= CardPagerAdapter()
        mCardAdapter!!.addCardItem(CardItem(R.string.title_1,R.string.text))
        mCardAdapter!!.addCardItem(CardItem(R.string.title_2,R.string.text))
        mCardAdapter!!.addCardItem(CardItem(R.string.title_3,R.string.text))
        mCardAdapter!!.addCardItem(CardItem(R.string.title_4,R.string.text))
        mFragmentCardAdapter =CardFragmentPagerAdapter(supportFragmentManager,dip2pixels(2,this))
        mCardShadowTransformer= ShadowTransformer(mViewPager,mCardAdapter as CardAdapter)
        mFragmentCardShadowTransformer= ShadowTransformer(mViewPager,mFragmentCardAdapter as CardAdapter)
        mViewPager.adapter=mCardAdapter
        mViewPager.setPageTransformer(false,mCardShadowTransformer)
        mViewPager.offscreenPageLimit=3





        initListener()



    }

    fun initListener(){
        checkbox.setOnCheckedChangeListener({
            compoundButton, b ->
            mCardShadowTransformer?.enableScaling(b)
            mFragmentCardShadowTransformer?.enableScaling(b)
        })

        btnCardType.setOnClickListener({
            view ->
            if(!mShowingFragments){
                btnCardType.setText("views")
                mViewPager.adapter=mFragmentCardAdapter
                mViewPager.setPageTransformer(false,mFragmentCardShadowTransformer)
            }else{
                btnCardType.setText("Fragments")
                mViewPager.adapter=mCardAdapter
                mViewPager.setPageTransformer(false,mCardShadowTransformer)
            }

            mShowingFragments=!mShowingFragments
        })
    }

    fun dip2pixels(dp:Int,ctx:Context):Float{
        return dp*(ctx.resources.displayMetrics.density)
    }

}
