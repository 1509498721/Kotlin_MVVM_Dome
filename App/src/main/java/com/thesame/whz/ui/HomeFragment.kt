package com.thesame.whz.ui

import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.thesame.baselibrary.common.BaseFragment
import com.thesame.whz.databinding.FragmentMainBinding
import com.thesame.whz.ui.home.viewmodel.TranslateViewModel
import androidx.lifecycle.Observer
import com.thesame.baselibrary.ext.no
import com.thesame.baselibrary.ext.yes

class HomeFragment : BaseFragment<FragmentMainBinding>() {

    private val mModel: TranslateViewModel by viewModels()

    override fun initView(savedInstanceState: Bundle?) {
        mModel.mDataTranslate.observe(this, Observer {
            it.first.yes {
                mView.tvText.text = "原文:\n${mView.etEnter.text}\n译文:\n ${
                    it.second?.translateResult?.get(0)?.get(0)?.tgt
                }"
            }
            it.first.no {
                mView.tvText.text = "请检查网络，并重试"
            }

        })

        mView.etEnter.addTextChangedListener { text ->
            run {
                if (text.isNullOrEmpty()) {
                    mView.tvText.text = ""
                } else {
                    mModel.translate(text.toString())
                }
            }
        }
    }
}