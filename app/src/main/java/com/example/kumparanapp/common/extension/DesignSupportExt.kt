@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.example.kumparanapp.common.extension

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.util.TypedValue
import android.view.View
import android.view.WindowManager
import androidx.annotation.IntegerRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.kumparanapp.R
import com.google.android.material.snackbar.Snackbar


fun View.snackBar(
    text: CharSequence,
    duration: Int = Snackbar.LENGTH_SHORT,
    init: Snackbar.() -> Unit = {}
): Snackbar {
    val snack = Snackbar.make(this, text, duration)
    snack.init()
    snack.show()
    return snack
}

fun View.snackBar(
    @StringRes() text: Int,
    duration: Int = Snackbar.LENGTH_SHORT,
    init: Snackbar.() -> Unit = {}
): Snackbar {
    val snack = Snackbar.make(this, text, duration)
    snack.init()
    snack.show()
    return snack
}

fun Fragment.snackBar(
    text: CharSequence,
    duration: Int = Snackbar.LENGTH_LONG,
    init: Snackbar.() -> Unit = {}
): Snackbar =
    view?.snackBar(text, duration, init)!!

fun Fragment.snackBar(
    @StringRes text: Int,
    duration: Int = Snackbar.LENGTH_LONG,
    init: Snackbar.() -> Unit = {}
): Snackbar =
    view?.snackBar(text, duration, init)!!

fun Activity.snackBar(
    @StringRes text: Int,
    duration: Int = Snackbar.LENGTH_SHORT,
    init: Snackbar.() -> Unit = {}
): Snackbar {
    val v = this.findViewById<View>(android.R.id.content)
    return v.snackBar(text, duration, init)
}

fun Activity.snackBar(
    text: CharSequence,
    duration: Int = Snackbar.LENGTH_SHORT,
    init: Snackbar.() -> Unit = {}
): Snackbar {
    val v = this.findViewById<View>(android.R.id.content)
    return v.snackBar(text, duration, init)
}

@SuppressLint("ResourceType")
fun Snackbar.action(@IntegerRes actionRes: Int, color: Int? = null, listener: (View) -> Unit) {
    action(view.resources.getString(actionRes), color, listener)
}

fun Snackbar.action(action: String, color: Int? = null, listener: (View) -> Unit) {
    setAction(action, listener)
    color?.let { setActionTextColor(ContextCompat.getColor(context, color)) }
}

fun FragmentActivity.setContentFragment(containerViewId: Int, f: () -> Fragment): Fragment? {
    val manager = supportFragmentManager
    val fragment = manager.findFragmentById(containerViewId)
    fragment?.let { return it }

    return f().apply {
        manager.beginTransaction().add(containerViewId, this).commit()
    }
}

inline fun Activity.getScreenHeight(): Int {
    (this.applicationContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay?.let {
        val size = Point()
        it.getSize(size)
        return size.y
    }
    return 0
}

fun SwipeRefreshLayout.applyAppTheme() {
    setProgressBackgroundColorSchemeResource(R.color.purple_700)
    setColorSchemeResources(R.color.purple_700)
    setProgressViewOffset(
        true, 0, TypedValue
            .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24f, resources.displayMetrics).toInt()
    )
}