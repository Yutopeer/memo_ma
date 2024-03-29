package com.example.memoma

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.fragment_list.*
import java.io.File
import java.nio.file.Files

class InputFragment:Fragment(){

    interface OnFileOutputListener{
        fun onFileOutput()
    }

    private var currentFile:File?=null

    override fun onSaveInstanceState(outState:Bundle){
        super.onSaveInstanceState(outState)
        currentFile?.let{
            outState.putSerializable("file", it)
        }
    }

    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        if (savedInstanceState!=null && savedInstanceState.containsKey("file")){
            currentFile=savedInstanceState.getSerializable("file") as File
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_input,container,false
        )
        val content=view.findViewById<EditText>(R.id.fact)
        val content2=view.findViewById<EditText>(R.id.chushou)
        val content3=view.findViewById<EditText>(R.id.tenyou)

        val saveButton=view.findViewById<Button>(R.id.save)

        saveButton.setOnClickListener {
            currentFile= outputFile(currentFile,content.text.toString())
            if(context is OnFileOutputListener){
                (context as OnFileOutputListener).onFileOutput()
            }

//            currentFile= outputFile(currentFile,content2.text.toString())
//            if(context is OnFileOutputListener){
//                (context as OnFileOutputListener).onFileOutput()
//            }
//
//            currentFile= outputFile(currentFile,content3.text.toString())
//            if(context is OnFileOutputListener){
//                (context as OnFileOutputListener).onFileOutput()
//            }
        }

        return  view
    }

    fun show(file: File){
        val memo = inputFile(file)

        val content=view?.findViewById<EditText>(R.id.content)?: return
        content.setText(memo)
        val content2=view?.findViewById<EditText>(R.id.content)?: return
        content2.setText(memo)
        val content3=view?.findViewById<EditText>(R.id.content)?: return
        content3.setText(memo)

        currentFile=file
    }



}


