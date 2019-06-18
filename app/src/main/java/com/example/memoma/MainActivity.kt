package com.example.memoma

import android.Manifest
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import java.io.File

class MainActivity : AppCompatActivity() ,FilesListFragment.OnFileSelectListener,
    InputFragment.OnFileOutputListener{





    private var drawerToggle:ActionBarDrawerToggle?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(hasPermission()) setViews()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        drawerToggle?.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        drawerToggle?.onConfigurationChanged(newConfig)
    }

    private fun setupDrawer(drawer: DrawerLayout){
        val toggle=ActionBarDrawerToggle(this,drawer,R.string.app_name,R.string.app_name)
        toggle.isDrawerIndicatorEnabled=true
        drawer.addDrawerListener(toggle)

        drawerToggle=toggle

        supportActionBar?.apply{
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(drawerToggle?.onOptionsItemSelected(item)==true){
            return true
        }else{
            return super.onOptionsItemSelected(item)
        }
    }

    private fun setViews(){
        setContentView(R.layout.activity_main)

        val drawerLayout=findViewById<DrawerLayout>(R.id.drawerlayout)

        if (drawerLayout!=null){
            setupDrawer(drawerLayout)
        }
    }

    private fun hasPermission():Boolean{
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),1)
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (!grantResults.isEmpty()&&
                grantResults[0]==PackageManager.PERMISSION_GRANTED){
            setViews()
            drawerToggle?.syncState() }else{
            finish()
        }
    }

    override fun onFileSelected(file: File) {
        val fragment=supportFragmentManager.findFragmentById(R.id.fact) as InputFragment
        fragment.show(file)
    }

    override fun onFileOutput() {
        val fragment=supportFragmentManager.findFragmentById(R.id.fact) as FilesListFragment
        fragment.show()?:fragment
//        val fragment2=supportFragmentManager.findFragmentById(R.id.chushou) as FilesListFragment
//        fragment2.show()?:fragment
//        val fragment3=supportFragmentManager.findFragmentById(R.id.tenyou) as FilesListFragment
//        fragment3.show()?:fragment
    }

}


