package com.example.myapplication.views.dialog

import android.app.AlertDialog
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import androidx.core.view.isVisible
import com.example.thecoach.R
import com.example.thecoach.databinding.DialogNotifyBinding

fun showNotifyDialog(
    mContext: Context,
    src: Drawable?,
    title: String,
    note: String?,
    textButton: String,
    isSuccess: Boolean,
    callback: () -> Unit
) {
    val factory = LayoutInflater.from(mContext)
    val binding = DialogNotifyBinding.inflate(factory)
    val deleteDialog = AlertDialog.Builder(mContext, R.style.CustomAlertDialogStyle).create()
    deleteDialog.setView(binding.root)

    val width = (mContext.resources.displayMetrics.widthPixels * 0.80).toInt()
    val height = (mContext.resources.displayMetrics.heightPixels * 0.80).toInt()
    deleteDialog.window?.setLayout(width, height)

    deleteDialog.setCancelable(false)

    binding.img.setImageDrawable(src)
    binding.txtTitle.text = title
    binding.txtNote.text = note
    binding.btnAction.text = textButton
//    binding.txtNote.isVisible = isSuccess
    binding.btnAction.setOnClickListener {
        callback()
        deleteDialog.dismiss()
    }
    deleteDialog.show()
}