package br.com.mobile.osmetricos

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object PaisesService {

    val host = "https://osmetricos.com.br"
    val TAG = "WS_LMSApp"


    fun getPaises (context: Context): List<Paises> {
        var paises = ArrayList<Paises>()
        try {
            val url = "$host/paises"
            val json = HttpHelper.get(url)
            paises = parserJson(json)
            // salvar offline
            for (d in paises) {
                saveOffline(d)
            }
            return paises
        } catch (ex: Exception) {
            val dao = DatabaseManager.getPaisesDAO()
            val paises = dao.findAll()
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

    fun saveOffline(paises: Paises) : Boolean {
        val dao = DatabaseManager.getPaisesDAO()

        if (! existeDisciplina(paises)) {
            dao.insert(paises)
        }

        return true

    }

    fun existeDisciplina(paises: Paises): Boolean {
        val dao = DatabaseManager.getPaisesDAO()
        return dao.getById(paises.id) != null
    }


    fun delete(paises: Paises): Response {
        try {

            val url = "$host/paises/${paises.id}"
            val json = HttpHelper.delete(url)

            return parserJson(json)


        } catch (ex: Exception) {
            val dao = DatabaseManager.getPaisesDAO()
            dao.delete(paises)
            return Response(status = "OK", msg = "Dados salvos localmente")
        }
    }


    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }

}