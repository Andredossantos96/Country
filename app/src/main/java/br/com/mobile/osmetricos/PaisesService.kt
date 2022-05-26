package br.com.mobile.osmetricos

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URL

object PaisesService {

    val host = "https://osmetricos.com.br"
    val TAG = "WS_LMSApp"


    /*fun getPaises (context: Context, id: Long): Paises? {

        if (AndroidUtils.isInternetDisponivel()) {
            val url = "$host/paises/${id}"
            val json = HttpHelper.get(url)
            val paises = parserJson<Paises>(json)

            return paises
        } else {
            val dao = DatabaseManager.getPaisesDAO()
            val paises = dao.getById(id)
            return paises
        }*/

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


    fun savePaises(disciplina: Paises): Response {
        if (AndroidUtils.isInternetDisponivel()) {
            val json = HttpHelper.post("$host/paises", disciplina.toJson())
            return parserJson(json)
        }
        else {
            saveOffline(disciplina)
            return Response("OK", "Disciplina salva no dispositivo")
        }
    }

   /* fun savePaises(paises: Paises): Response {
        try {
            val json = HttpHelper.post("$host/paises", paises.toJson())
            return parserJson(json)
        } catch (ex: Exception) {
            DatabaseManager.getPaisesDAO().insert(paises)
            return Response("ok", "ok")
        }
    }*/

    fun saveOffline(disciplina: Paises) : Boolean {
        val dao = DatabaseManager.getPaisesDAO()

        if (! existePais(disciplina)) {
            dao.insert(disciplina)
        }

        return true

    }

    fun existePais(disciplina: Paises): Boolean {
        val dao = DatabaseManager.getPaisesDAO()
        return dao.getById(disciplina.id) != null
    }

    fun delete(disciplina: Paises): Response {
        if (AndroidUtils.isInternetDisponivel()) {
            val url = "$host/paises/${disciplina.id}"
            val json = HttpHelper.delete(url)

            return parserJson(json)
        } else {
            val dao = DatabaseManager.getPaisesDAO()
            dao.delete(disciplina)
            return Response(status = "OK", msg = "Dados salvos localmente")
        }

    }


    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }

}