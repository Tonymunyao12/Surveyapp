package com.mulatya.lib.internal.ext

import android.view.View

internal fun View.showIf(statement: Boolean){
    visibility = if (statement) View.VISIBLE else View.GONE
}