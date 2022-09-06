package com.example.customcomponent

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.core.view.get
import com.example.customcomponent.databinding.MyCustomComponentBinding

class MyCustomComponent @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : ConstraintLayout(context, attrs) {

    private val ctx = context
    private val binding = MyCustomComponentBinding.inflate(LayoutInflater.from(context), this)
    private val radioGroup by lazy {
        binding.myCompRadioGroup
    }

    init {
        attrs?.let {
            val attr = context.obtainStyledAttributes(attrs, R.styleable.MyCustomComponent)

            val title = attr.getString(R.styleable.MyCustomComponent_title)
            val subtitle = attr.getString(R.styleable.MyCustomComponent_subtitle)

            binding.myCompTitle.text = title
            binding.myCompSubtitle.text = subtitle

            binding.myCompTitle.visibility = if(title.isNullOrEmpty()) View.GONE else View.VISIBLE
            binding.myCompSubtitle.visibility = if(subtitle.isNullOrEmpty()) View.GONE else View.VISIBLE
        }
    }

    private fun getPositionByKey(key: String): Int {
        var pos = 0
        radioGroup.children.forEach {
            pos++
            val radio = it as RadioButtonExt
            if (radio.key.equals(key))
                return pos
        }
        return pos
    }

    fun addItems(radioItems: List<RadioItem>) {
        radioItems.forEach { radioItem ->
            val compRadio = RadioButtonExt(ctx)
            compRadio.key = radioItem.key
            compRadio.text = radioItem.text
            radioGroup.addView(compRadio)
        }

        radioItems.findLast { it.checked == true }?.let {
            radioGroup.check(getPositionByKey(it.key))
        }
    }

    fun getSelectedRadioButton(): RadioItem? {
        if (radioGroup.checkedRadioButtonId == -1)
            return null

        val radio = radioGroup.get(radioGroup.checkedRadioButtonId - 1) as RadioButtonExt
        return RadioItem(
            key = radio.key,
            text = radio.text.toString(),
            checked = radio.isChecked
        )
    }
}