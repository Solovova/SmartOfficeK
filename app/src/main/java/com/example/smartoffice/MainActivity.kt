package com.example.smartoffice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.smartoffice.fragments.FragmentStart
import android.R
import android.util.Log

class MainActivity : AppCompatActivity() {
    var fragments: Map<String, Fragment>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            fragments = HashMap()
            //(application as SOApplication).dataContainer!!.load()
            this.showStartScreen(false)
        }
    }

    //a?.equals(b) ?: (b === null)
    private fun fragmentsShow(fragmentName: String) {
        val ft = supportFragmentManager.beginTransaction()


        fragments?.let {
            //hide all fragments
            for (entry in it.entries) {
                if (entry.value != null && entry.key!=null) {
                    if (!entry.key?.equals(fragmentName)) ft.hide(entry.value)
                }
            }

            var fragment:Fragment? = it[fragmentName]

            if (fragment == null ) {
                when (fragmentName) {
                    "FragmentStart" -> {
                        fragment = FragmentStart.newInstance()
                    }
                }
            }

//            if (fragment != null) {
//
//                it.remove(fragmentName)
//                fragments.put(fragmentName, fragment)
//                Log.i("show", fragmentName)
//                if (!fragment.isAdded) {
//                    Log.i("add", "FragmentStartBlank")
//                    ft.add(R.id.container, fragment, fragmentName)
//                }
//                ft.show(fragment)
//            }

            ft.commit()
            supportFragmentManager.executePendingTransactions()
        }



    }

    private fun showStartScreen(showButtons: Boolean) {
        this.fragments?.let {
            this.fragmentsShow("FragmentStart")
        }
    }
}
