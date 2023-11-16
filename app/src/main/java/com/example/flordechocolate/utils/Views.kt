package com.example.flordechocolate

import android.content.pm.PackageManager
import android.os.Build
import androidx.fragment.app.Fragment

fun Fragment.checkPermission(permission: String, code: Int): Boolean {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val granted = requireActivity().checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
        if (!granted) this.requestPermissions(arrayOf(permission), code)
        return granted
    }
    return true
}