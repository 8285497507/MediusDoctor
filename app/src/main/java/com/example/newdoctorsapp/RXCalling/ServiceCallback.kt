package com.example.demod.RXCalling

/**
 * Created by Anil Tiwari on 15/12/2021.
 */
interface ServiceCallback {

    fun onSuccess(o: Any)

    fun onFail(t: String)

}