package com.example.demod.RXCalling


import java.util.*

/**
 * Created by Anil Tiwari on 15/12/2021.
 */

open class BaseModelData : Observable() {

    override fun notifyObservers(arg: Any?) {
        setChanged()
        super.notifyObservers(arg)
    }

    override fun notifyObservers() {
        setChanged()
        super.notifyObservers()
    }
}