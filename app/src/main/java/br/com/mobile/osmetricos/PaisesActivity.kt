package br.com.mobile.osmetricos

import android.os.Bundle
import android.view.MenuItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_paises.*
import kotlinx.android.synthetic.main.toolbar.*

class PaisesActivity : DebugActivity() {
    var paises: Paises? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_paises)
        // recuperar onjeto de Disciplina da Intent
//        paises = intent.getSerializableExtra("paises") as Paises
        // configurar título com nome da Disciplina e botão de voltar da Toobar
        // colocar toolbar
//        setSupportActionBar(toolbar)
//        supportActionBar?.title = "Paises"
        // alterar título da ActionBar
//        supportActionBar?.title = paises?.nome
        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // atualizar dados da disciplina
//        nomeDisciplina.text = paises?.nome
//        Picasso.with(this).load(paises?.bandeira).fit().into(imagemDisciplina,
//                object: com.squareup.picasso.Callback{
//                    override fun onSuccess() {}
//
//                    override fun onError() { }
//                })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
