package com.example.bitebyte.views

import android.content.Context
import android.graphics.Canvas
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import com.example.bitebyte.R
import com.google.android.material.textfield.TextInputEditText

class PasswordEditText: TextInputEditText {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Do nothing
            }

            override fun onTextChanged(pass: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!pass.isNullOrEmpty()) {
                    error = if (pass.count() <= 7) {
                        context.getString(R.string.password_less_then_8)
                    } else {
                        null
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                // Do nothing
            }
        })
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }
}