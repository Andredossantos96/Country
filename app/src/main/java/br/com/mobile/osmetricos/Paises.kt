package br.com.mobile.osmetricos

import java.io.Serializable

class Paises : Serializable {

    var id:Long = 0
    var bandeira = ""
    var capital = ""
    var continente = ""
    var latitude = ""
    var longitude = ""
    var nome = ""
    var populacao = ""


   /* override fun toString(): String {
        return "Pais(nome='$nome')"
    }*/
}