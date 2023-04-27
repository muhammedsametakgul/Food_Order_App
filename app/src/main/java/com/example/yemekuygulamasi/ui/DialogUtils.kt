package com.example.yemekuygulamasi.ui

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import androidx.core.graphics.drawable.toDrawable

class DialogUtils {
    companion object{
        fun dialogGoster(context: Context, id:Int) : Dialog {
            val dialog = Dialog(context)

            dialog.let {
                it.show()
                it.setContentView(id)
                it.setCancelable(false)
                it.setCanceledOnTouchOutside(true)
                it.window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())

                return dialog
            }

        }
    }
}