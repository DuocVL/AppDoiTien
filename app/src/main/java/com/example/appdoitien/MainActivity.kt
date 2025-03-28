package com.example.appdoitien

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.appdoitien.ui.theme.AppDoiTienTheme

class MainActivity : ComponentActivity() {
    private val bangQuyDoi = arrayOf(25550.0,27666.0,171.0,33079.0,1.0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val spinner1 : Spinner = findViewById(R.id.spinner1)
        val spinner2 : Spinner = findViewById(R.id.spinner2)
        val editText1 : EditText = findViewById(R.id.edittext1)
        val editText2 : EditText = findViewById(R.id.edittext2)
        val textView : TextView = findViewById(R.id.textview)
        var position1 = 0
        var position2 = 0

        val items = arrayOf("USD","EUR","JPY","GBP","VND")
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        spinner1.run {
            adapter = arrayAdapter
            onItemSelectedListener = object : OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    position1 = p2
                    val text = editText1.text.toString()
                    if(text.isNotEmpty()){
                        var giaTriMoi = (text.toIntOrNull() ?: return) * bangQuyDoi[position1] / bangQuyDoi[position2]
                        editText2.setText(giaTriMoi.toString())
                    }
                    val quyDoi = bangQuyDoi[position1] / bangQuyDoi[position2]
                    val str = "1 " + items[position1] + " = " + quyDoi.toString() + " " + items[position2]
                    textView.setText(str)

                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
        }
        spinner2.run {
            adapter = arrayAdapter
            onItemSelectedListener = object : OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    position2 = p2
                    val text = editText1.text.toString()
                    if(text.isNotEmpty()){
                        var giaTriMoi = (text.toIntOrNull() ?: return) * bangQuyDoi[position1] / bangQuyDoi[position2]
                        editText2.setText(giaTriMoi.toString())
                    }
                    val quyDoi = bangQuyDoi[position1] / bangQuyDoi[position2]
                    val str = "1 " + items[position1] + " = " + quyDoi.toString() + " " + items[position2]
                    textView.setText(str)
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
        }

        editText1.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var tienQuyDoi: Double? = null
                val text : String = s.toString()
                if(position1 == position2){
                    if (editText2.text.toString() != text) {
                        editText2.setText(text)
                    }
                }
                else{
                    val number = text.toIntOrNull() ?: return
                    tienQuyDoi = bangQuyDoi[position1] * number / bangQuyDoi[position2]
                    if (editText2.text.toString() != tienQuyDoi.toString()) {
                        editText2.setText(tienQuyDoi.toString())
                    }

                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        editText2.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var tienQuyDoi: Double? = null
                val text : String = s.toString()
                if(position1 == position2){
                    if (editText1.text.toString() != text) {
                        editText1.setText(text)
                    }
                }
                else{
                    val number = text.toIntOrNull() ?: return
                    tienQuyDoi = bangQuyDoi[position2] * number / bangQuyDoi[position1]
                    if (editText1.text.toString() != tienQuyDoi.toString()) {
                        editText1.setText(tienQuyDoi.toString())
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

    }
}

