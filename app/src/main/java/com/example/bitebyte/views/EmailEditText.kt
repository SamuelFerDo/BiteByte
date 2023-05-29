package com.example.bitebyte.views

import android.content.Context
import android.graphics.Canvas
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import com.example.bitebyte.R
import com.google.android.material.textfield.TextInputEditText

class EmailEditText : TextInputEditText {
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

            override fun onTextChanged(email: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!email.isNullOrEmpty()) {
                    error = if (!isEmailValid(email)) {
                        context.getString(R.string.invalid_email_address)
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

    private fun isEmailValid(email: CharSequence): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}