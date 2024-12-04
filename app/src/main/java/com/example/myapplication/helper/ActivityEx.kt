package com.example.myapplication.helper

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.thecoach.R

fun AppCompatActivity.addFragment(id: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction().apply {
        setCustomAnimations(
            R.anim.slide_in, // enter
            R.anim.fade_out, // exit
            R.anim.fade_in, // popEnter
            R.anim.slide_out // popExit
        )
        add(id, fragment, fragment::class.java.name)
        commit()
    }
}

fun AppCompatActivity.replaceFragmentToBackStack(id: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction().apply {
        setCustomAnimations(
            R.anim.slide_in, // enter
            R.anim.fade_out, // exit
            R.anim.fade_in, // popEnter
            R.anim.slide_out // popExit
        )
        addToBackStack(fragment::class.java.name)
        replace(id, fragment)
        commit()
    }
}

fun Fragment.showToast(mess: String?) {
    Toast.makeText(requireContext(), "Có lỗi xảy ra: $mess", Toast.LENGTH_LONG).show()
}