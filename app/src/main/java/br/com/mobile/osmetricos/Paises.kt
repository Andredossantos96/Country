package br.com.mobile.osmetricos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.GsonBuilder
import java.io.Serializable

@Entity(tableName = "paises")
class Paises : Serializable {

    @PrimaryKey
    var id:Long = 0
    var bandeira = ""
    var capital = ""
    var continente = ""
    var latitude = ""
    var longitude = ""
    var nome = ""
    var populacao = ""

    override fun toString(): String {
        return "Paises(nome='$nome')"
    }

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}