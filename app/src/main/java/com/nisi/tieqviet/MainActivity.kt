package com.nisi.tieqviet

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.regex.Pattern


class MainActivity : AppCompatActivity() {


    var maps = hashMapOf(
            "kh" to "x",
            "c(?!h)" to "k",
            "q" to "k",
            "tr" to "c",
            "ch" to "c",
            "d" to "z",
            "gi" to "z",
            "r" to "z",
            "đ" to "d",
            "ph" to "f",
            "ngh?" to "q",
            "gh" to "g",
            "th" to "w",
            "nh" to "n\'"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtContentOld.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                tvContentConvert.text = convert(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {


            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })
        edtContentOld.setText("Bộ chuyển đổi tiếng việt")
        btnCopy.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("tiengviet", tvContentConvert.text.toString())
            clipboard.primaryClip = clip
            Toast.makeText(this, "Copy thành công", Toast.LENGTH_SHORT).show()
        }
    }




    private fun convert(input: String): String {
        var inputX: String
        inputX = input
        maps.forEach {
            val m = Pattern.
                    compile(it.key)
                    .matcher(inputX)

            if (m.find()) {
                inputX = m.replaceFirst(it.value)
            }
        }
        return inputX
    }
}