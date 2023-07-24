package com.example.showtify

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import com.example.showtify.databinding.ActivityMainLayoutBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainLayoutBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.apply {
            exempleButton.setOnClickListener {
                if (exempleText.text == "Hello, world !"){
                    exempleText.text = getPythonHelloWorldParameter()
                } else exempleText.text = getPythonHelloWorld()
            }
        }

        initPython()
    }

    private fun getPythonHelloWorldParameter(): String {
        val python = Python.getInstance()
        val pythonFile = python.getModule("helloparameterscript")
        return pythonFile.callAttr("helloparameter", "Baptou").toString()
    }

    private fun getPythonHelloWorld(): String {
        val python = Python.getInstance()
        val pythonFile = python.getModule("helloworldscript")
        return pythonFile.callAttr("helloworld").toString()
    }

    private fun initPython() {
        if (! Python.isStarted()) {
            Python.start(AndroidPlatform(this));
        }
    }
}