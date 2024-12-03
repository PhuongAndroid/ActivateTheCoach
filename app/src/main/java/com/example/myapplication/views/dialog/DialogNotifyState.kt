package com.example.myapplication.views.dialog

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.example.thecoach.R
import com.example.thecoach.databinding.DialogNotifyBinding

fun showNotifyDialog(mContext: Context, callback: () -> Unit) {
    val factory = LayoutInflater.from(mContext)
    val binding = DialogNotifyBinding.inflate(factory)
    val deleteDialog = AlertDialog.Builder(mContext, R.style.CustomAlertDialogStyle).create()
    deleteDialog.setView(binding.root)

    val width = (mContext.resources.displayMetrics.widthPixels * 0.80).toInt()
    val height = (mContext.resources.displayMetrics.heightPixels * 0.80).toInt()
    deleteDialog.window?.setLayout(width, height)

    deleteDialog.setCancelable(false)

//    val backStudy = binding.txtContinueStudy
//    val goPlay = binding.txtGoPlay
//
//    backStudy.setOnClickListener {
//        deleteDialog.dismiss()
//    }
//    goPlay.setOnClickListener {
//        callback()
//        deleteDialog.dismiss()
//    }

    deleteDialog.show()
}