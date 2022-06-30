package com.example.demod.RXCalling

/**
 * Created by Anil Tiwari on 15/12/2021.
 */
class ServiceModel : BaseModelData(), ServiceCallback {

    override fun onSuccess(o: Any) {
        notifyObservers(o)
    }

    override fun onFail(t: String) {
        notifyObservers(t)
    }


    fun doPostJSonRequest(jsonValue: Any, serviceName: String) {
        ServiceRequests(this, serviceName, jsonValue).execute()
    }

    fun doPostJSonRequest(serviceName: String) {
        ServiceRequests(this, serviceName).execute()
    }

    fun doPostJSonRequest(hashMap: HashMap<String, String>, serviceName: String) {
        ServiceRequests(this, serviceName, hashMap).execute()
    }

    fun doPostJSonRequest(header: String, serviceName: String) {
        ServiceRequests(this, serviceName, header).execute()
    }

    fun doPostJSonRequest(jsonValue: Any, token: String, serviceName: String) {
        ServiceRequests(this, serviceName, jsonValue, token).execute()
    }

    fun doGetCheckStatusRequest(serviceName: String, url: String, header: String) {
        ServiceRequests(this, serviceName, url, header).execute()
    }

    fun doGetCheckStatusRequest2(serviceName: String,url : String,header: String,jsonValue : Any) {
        ServiceRequests(this,serviceName,url,header,jsonValue).execute()
    }

    fun doPostJSonRequest(myvalue: String, url: String, serviceName: String, token: String) {
        ServiceRequests(this, serviceName, myvalue, url, token).execute()
    }

    fun doPostJSonRequest(jsonValue: Any, hashMap: HashMap<String, String>, serviceName: String) {
        ServiceRequests(this, serviceName, hashMap, jsonValue).execute()
    }
}
