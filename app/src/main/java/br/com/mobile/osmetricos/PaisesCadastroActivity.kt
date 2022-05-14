package br.com.mobile.osmetricos

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cadastro_paises.*

class PaisesCadastroActivity : DebugActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_paises)
        setTitle("Novo Pais")

        salvarPais.setOnClickListener {
            val paises = Paises()
            paises.bandeira = urlFotoBandeira.text.toString()
            paises.nome = nomePais.text.toString()
            paises.continente = continente.text.toString()
            paises.capital = nomecapital.text.toString()
            paises.longitude = nomeLongitude.text.toString()
            paises.latitude = nomeLatidude.text.toString()
            paises.populacao = nomePopulacao.text.toString()


            taskAtualizar(paises)
        }
    }

    private fun taskAtualizar(paises: Paises) {
        // Thread para salvar a discilpina
        Thread {
            PaisesService.savePaises(paises)
            runOnUiThread {
                // após cadastrar, voltar para activity anterior
                finish()
            }
        }.start()
    }
}
