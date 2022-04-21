package br.com.mobile.osmetricos

import android.content.Context

object PaisesService {

    fun getDisciplinas (context: Context): List<Paises> {
        val paises = mutableListOf<Paises>()


        val brasil = Paises()
        brasil.nome = "Brasil "
        brasil.ementa = "Ementa Pais "
        brasil.foto = "https://cdn.pixabay.com/photo/2016/09/10/22/14/flag-of-brazil-1660257_960_720.jpg"
        paises.add(brasil)

        val japao = Paises()
        japao.nome = "Japão "
        japao.ementa = "Ementa Pais "
        japao.foto = "https://cdn.pixabay.com/photo/2013/07/13/14/15/japan-162328_960_720.png"
        paises.add(japao)

        val peru = Paises()
        peru.nome = "Peru "
        peru.ementa = "Ementa Pais "
        peru.foto = "https://cdn.pixabay.com/photo/2013/07/13/14/16/peru-162390_960_720.png"
        paises.add(peru)

        val canada = Paises()
        canada.nome = "Canada"
        canada.ementa = "Ementa Pais "
        canada.foto = "https://cdn.pixabay.com/photo/2012/04/10/23/27/canada-27003_960_720.png"
        paises.add(canada)

        val usa = Paises()
        usa.nome = "Estados Unidos"
        usa.ementa = "Ementa Pais "
        usa.foto = "https://cdn.pixabay.com/photo/2017/01/07/16/55/usa-1960922_960_720.jpg"
        paises.add(usa)

        val reinoUnido = Paises()
        reinoUnido.nome = "Reino Unido"
        reinoUnido.ementa = "Ementa Pais "
        reinoUnido.foto = "https://cdn.pixabay.com/photo/2015/11/06/13/29/union-jack-1027898_960_720.jpg"
        paises.add(reinoUnido)

        val argentina = Paises()
        argentina.nome = "Argentina"
        argentina.ementa = "Ementa Pais "
        argentina.foto = "https://cdn.pixabay.com/photo/2013/07/13/14/14/argentina-162229_960_720.png"
        paises.add(argentina)


        return paises
    }

}