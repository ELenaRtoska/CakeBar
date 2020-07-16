package com.example.cakebar.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cakebar.Data.AppPrefs
import com.example.cakebar.R
import kotlinx.android.synthetic.main.first_screen.*


class FirstScreenActivity: AppCompatActivity() {
    private lateinit var mPrefs: AppPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_screen)


        mPrefs = AppPrefs(this)
        setListener()

    }
    private fun setListener(){

        nextBtn.setOnClickListener {

            var name = etName.text.toString()
            var table = etTableName.text.toString()
            if (name.isNullOrEmpty()){
                Toast.makeText(this,"Please enter Name", Toast.LENGTH_LONG).show()
            }else if (table.isNullOrEmpty()){
                Toast.makeText(this,"Please enter Table Number", Toast.LENGTH_LONG).show()
            }else{

                mPrefs.customerName = name
                mPrefs.tableName = table
                startActivity(Intent(this, MainActivity::class.java))

            }
//            val intent = Intent(applicationContext, MainActivity::class.java)
//            startActivity(intent)

        }
    }
    }