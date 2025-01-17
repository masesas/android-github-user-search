package com.assesment.githubuser.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.assesment.githubuser.R

fun loadingImgPlaceHolder(context: Context): Drawable {
    return CircularProgressDrawable(context).apply {
        setColorSchemeColors(
            R.color.colorPrimary,
            R.color.colorPrimaryDark,
            R.color.colorAccent
        )
        setCenterRadius(30f)
        setStrokeWidth(5f)
        start()
    }
}