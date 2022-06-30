package com.changesNewDesignsDiary

import android.os.Bundle
/*import android.support.v7.app.AppCompatActivity*/
import androidx.appcompat.app.AppCompatActivity
import java.util.*

/**
 * Created by Anil Tiwari on 15/12/2021.
 */

abstract class BaseActivityKot : AppCompatActivity(), Observer {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val model = getModel()
        model.addObserver(this)
    }

    abstract fun getModel(): Observable

}
