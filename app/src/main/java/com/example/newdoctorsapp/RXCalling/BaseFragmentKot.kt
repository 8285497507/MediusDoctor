package com.changesNewDesignsDiary

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
/*import android.support.v4.app.Fragment*/
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import java.util.*

abstract class BaseFragmentKot : Fragment(), Observer {

    abstract val model: Observable?

    // method use for create fragment view
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val model = model
        model?.addObserver(this)
        return onCreateViewPost(inflater, container, savedInstanceState)
    }

    @SuppressLint("ServiceCast")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

    abstract fun onCreateViewPost(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View
}