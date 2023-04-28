package com.example.yemekuygulamasi.ui

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.yemekuygulamasi.R

class LoadingDialog(val activity: Activity ) {
    private lateinit var  isdialog : AlertDialog
    fun startLoading(){
        val infalter = activity.layoutInflater
        val dialogView = infalter.inflate(R.layout.loading_item,null)
        val builder = AlertDialog.Builder(activity)
        builder.setView(dialogView)
        builder.setCancelable(false)
        isdialog = builder.create()
        isdialog.show()
    }
    fun isDismiss(){
        isdialog.dismiss()
    }

}