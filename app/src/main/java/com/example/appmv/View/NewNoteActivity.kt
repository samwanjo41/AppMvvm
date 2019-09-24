package com.example.appmv.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import com.example.appmv.R
import com.example.appmv.Utils.*

class NewNoteActivity : AppCompatActivity() {
private lateinit var  mTitleText: EditText
    private lateinit var  mDescrText: EditText
    private lateinit var  mSaveBtn: Button
    private lateinit var  mDelBtn: Button
    private lateinit var mdateText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)

        mTitleText = findViewById(R.id.editText)
        mDescrText = findViewById(R.id.descrText)
        mdateText = findViewById(R.id.dateText)
        mSaveBtn = findViewById(R.id.btnSave)
        mDelBtn = findViewById(R.id.btnDel)

        mSaveBtn.setOnClickListener {
            val intent = Intent()
            if(TextUtils.isEmpty(mTitleText.text) || TextUtils.isEmpty(mDescrText.text) || TextUtils.isEmpty(mdateText.text)){
                setResult(RESULT_ERROR, intent)
            } else {
                intent.putExtra(EXTRA_KEY_TITLE, mTitleText.text.toString())
                intent.putExtra(EXTRA_KEY_DESCR, mDescrText.text.toString())
                intent.putExtra(EXTRA_KEY_DATE, mdateText.text.toString())
                setResult(RESULT_SAVE, intent)
            }
            finish()
        }

        mDelBtn.setOnClickListener {
            //to implement
        }
    }
}
