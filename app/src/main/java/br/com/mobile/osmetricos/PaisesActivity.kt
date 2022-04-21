package br.com.mobile.osmetricos

import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_paises.*
import kotlinx.android.synthetic.main.toolbar.*

class PaisesActivity : DebugActivity() {

    var paises: Paises? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paises)

        // recuperar onjeto de Disciplina da Intent
        paises = intent.getSerializableExtra("paises") as Paises

        // configurar título com nome da Disciplina e botão de voltar da Toobar
        // colocar toolbar
        setSupportActionBar(toolbar)

        // alterar título da ActionBar
        supportActionBar?.title = paises?.nome

        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // atualizar dados da disciplina
        nomeDisciplina.text = paises?.nome
        Picasso.with(this).load(paises?.foto).fit().into(imagemDisciplina,
                object: com.squareup.picasso.Callback{
                    override fun onSuccess() {}

                    override fun onError() { }
                })
    }
}
