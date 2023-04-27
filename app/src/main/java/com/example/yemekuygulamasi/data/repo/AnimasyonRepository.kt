package com.example.yemekuygulamasi.data.repo

import android.app.Dialog
import android.content.Context
import android.os.Handler
import com.example.yemekuygulamasi.ui.DialogUtils

class AnimasyonRepository {
    companion object {
        var dialog: Dialog? = null

        fun dialogKapat(){
            dialog?.let { if (it.isShowing) it.cancel()}
        }

        fun dialogAc(context: Context, id: Int){
            dialog = DialogUtils.dialogGoster(context,id)
        }

        fun animasyon (context: Context, id: Int){
            dialogAc(context,id)
            Handler().postDelayed({ dialogKapat()},2500)

        }
    }

}
