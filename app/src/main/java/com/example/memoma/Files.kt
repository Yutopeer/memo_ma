package com.example.memoma

import android.os.Environment
import java.io.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

private fun getFilesDir() :  File{
    val publicDir= Environment.getExternalStoragePublicDirectory(
        Environment.DIRECTORY_DOCUMENTS
    )

    if (publicDir!=null){
        if(!publicDir.exists()) publicDir.mkdirs()
        return publicDir
    }else{
        val dir=File(Environment.getExternalStorageDirectory(),"MemoFiles")

        if(!dir.exists()) dir.mkdirs()
        return dir
    }
}

fun getFiles()= getFilesDir().listFiles().toList()

fun outputFile(original:File?,content:String):File{
    val timeStamp=SimpleDateFormat("yyyy-MM-dd-hh-mm-ss")

    val file=original ?: File(getFilesDir(),"memo-$timeStamp")

    val writer =BufferedWriter(FileWriter(file))
    writer.use{
        it.write(content)
        it.flush()
    }

    return file
}

fun inputFile(file: File):String{
    val reader=BufferedReader(FileReader(file))
    return reader.readLines().joinToString { "\n" }
}