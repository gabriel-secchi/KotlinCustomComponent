package com.example.customcomponent

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.RadioButton

@SuppressLint("AppCompatCustomView")
class RadioButtonExt constructor(
    context: Context, attrs: AttributeSet? = null
) : RadioButton(context, attrs) {

    var key: String = ""
        set(value) {
            field = value
        }

}