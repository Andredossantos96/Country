package br.com.mobile.osmetricos

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URL
import kotlin.math.log

object PaisesService {

    val host = "https://osmetricos.com.br"
    val TAG = "WS_LMSApp"

    fun getPaises (): List<Paises> {
        //val paises = mutableListOf<Paises>()
        try {
            var url = "$host/paises"
            val json = URL(url).readText()
            Log.d(TAG, json)
            return parserJson<List<Paises>>(json)
        } catch (ex: Exception) {
            var paises = DatabaseManager.getPaisesDAO().findAll()
            return paises
        }

    }

    fun savePaises(paises: Paises): Response {
        try {
            val json = HttpHelper.post("$host/paises", paises.toJson())
            return parserJson(json)
        } catch (ex: Exception) {
            DatabaseManager.getPaisesDAO().insert(paises)
            return Response("ok", "ok")
        }
    }


    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }

}