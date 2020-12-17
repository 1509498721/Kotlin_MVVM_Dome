package com.thesame.baselibrary.widget

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.aries.ui.view.title.TitleBarView
import com.thesame.baselibrary.R


/**
 *Created by whz  on 2019-06-27
 */
class HeaderBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private var titleText: String? = null
    private var bottomColor: Int? = null
    private var isShow: Boolean? = null
    private var isWhite: Boolean? = null

    init {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.HeaderBar)
        titleText = typeArray.getString(R.styleable.HeaderBar_titleText)
        bottomColor = typeArray.getColor(R.styleable.HeaderBar_bottomColor, R.color.mainColor!!)
        isShow = typeArray.getBoolean(R.styleable.HeaderBar_isShow, true)
        isWhite = typeArray.getBoolean(R.styleable.HeaderBar_isWhite, false)
        initView()
        typeArray.recycle()
    }

    private fun initView() {
        val view = View.inflate(context, R.layout.layout_header_bar, this)
        val appTitleBar = view.findViewById<TitleBarView>(R.id.appTitleBar)

        appTitleBar.setTitleMainText(titleText)
        appTitleBar.setOnLeftTextClickListener {
            if (context is Activity) {
                (context as Activity).finish()
            }
        }
        if (isShow!!) {
            appTitleBar.visibility = View.VISIBLE
        } else {
            appTitleBar.visibility = View.GONE
        }
        appTitleBar.setDividerBackgroundColor(bottomColor!!)
        appTitleBar.setStatusBarLightMode(isWhite!!)
    }
}