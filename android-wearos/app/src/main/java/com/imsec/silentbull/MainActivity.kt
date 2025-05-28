package com.imsec.silentbull

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val textView = TextView(this).apply {
            text = "Hello SilentBull on Galaxy Watch!"
        }

        setContentView(textView)
    }
}

