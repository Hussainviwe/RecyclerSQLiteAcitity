package com.example.recyclersqliteactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var description: EditText
    private lateinit var age: EditText
    private lateinit var insert: Button
    private lateinit var update: Button
    private lateinit var Delete: Button
    private lateinit var view: Button
    private lateinit var dB: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        name = findViewById(R.id.name)
        description = findViewById(R.id.description)
        age = findViewById(R.id.Age)
        insert = findViewById(R.id.btnInsert)
        update = findViewById(R.id.btnUpdate)
        Delete = findViewById(R.id.btnDelete)
        view = findViewById(R.id.btnView)

        dB = DBHelper(this)

        view.setOnClickListener {
            startActivity(Intent(this@MainActivity, Userlist::class.java))
        }

        insert.setOnClickListener {
            val nameTXT = name.text.toString()
            val descriptionTXT = description.text.toString()
            val ageTXT = age.text.toString()

            val checkInsertData = dB.insertuserdata(nameTXT, descriptionTXT, ageTXT)
            if (checkInsertData) {
                Toast.makeText(this@MainActivity, "New Entry inserted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@MainActivity, "New Entry Not inserted", Toast.LENGTH_SHORT).show()
            }
        }

        update.setOnClickListener {
            val nameTXT = name.text.toString()
            val descriptionTXT = description.text.toString()
            val ageTXT = age.text.toString()

            val checkupdateData = dB.updateuserdata(nameTXT, descriptionTXT, ageTXT)
            if (checkupdateData) {
                Toast.makeText(this@MainActivity, "Entry updated", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@MainActivity, " Entry Not updated", Toast.LENGTH_SHORT).show()
            }
        }

        Delete.setOnClickListener {
            val nameTXT = name.text.toString()
            val checkdeleteData = dB.deleterdata(nameTXT)
            if (checkdeleteData) {
                Toast.makeText(this@MainActivity, "Entry Deleted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@MainActivity, " Entry Not Deleted", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
