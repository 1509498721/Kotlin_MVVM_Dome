package com.thesame.baselibrary.dialog
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentActivity
import com.thesame.baselibrary.R
import com.thesame.baselibrary.action.AnimAction
import com.thesame.baselibrary.common.BaseDialog

class WaitDialog {
    class Builder(activity: FragmentActivity) : BaseDialog.Builder<Builder>(activity) {
        private val mMessageView: TextView

        fun setMessage(@StringRes id: Int): Builder {
            return setMessage(getString(id))
        }

        fun setMessage(text: CharSequence?): Builder {
            mMessageView.text = text
            mMessageView.visibility = if (text == null) View.GONE else View.VISIBLE
            return this
        }

        init {
            setContentView(R.layout.dialog_wait)
            setAnimStyle(com.thesame.baselibrary.action.AnimAction.TOAST)
            setCanceledOnTouchOutside(false)
            mMessageView = findViewById(R.id.tv_wait_message)
        }
    }
}