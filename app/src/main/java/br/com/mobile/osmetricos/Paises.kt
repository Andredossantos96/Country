package br.com.mobile.osmetricos

import java.io.Serializable

class Paises : Serializable {

    var id:Long = 0
    var nome = ""
    var ementa = ""
    var foto = ""


    override fun toString(): String {
        return "Pais(nome='$nome')"
    }
}