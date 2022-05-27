package br.com.mobile.osmetricos

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import br.com.mobile.osmetricos.DebugActivity
import br.com.mobile.osmetricos.Paises
import br.com.mobile.osmetricos.PaisesService
import br.com.mobile.osmetricos.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_paises.*
import kotlinx.android.synthetic.main.toolbar.*

class PaisesActivity : DebugActivity() {

    private val context: Context get() = this
    var paises: Paises? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paises)

        // recuperar onjeto de Disciplina da Intent
        paises = intent.getSerializableExtra("pais") as Paises
        /* if (intent.getSerializableExtra("pais") is Paises)
            paises = intent.getSerializableExtra("pais") as Paises*/

        // configurar título com nome da Disciplina e botão de voltar da Toobar
        // colocar toolbar
        setSupportActionBar(toolbar)

        // alterar título da ActionBar
        supportActionBar?.title = paises?.nome

        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // atualizar dados do carro
        nomePais.text = paises?.nome
        Picasso.with(this).load(paises?.bandeira).fit().into(imagemBandeira,
            object : com.squareup.picasso.Callback {
                override fun onSuccess() {}

                override fun onError() {}
            })
    }

    // método sobrescrito para inflar o menu na Actionbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.menu_main_paises, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // id do item clicado
        val id = item?.itemId
        // verificar qual item foi clicado
        // remover a disciplina no WS
        if (id == R.id.action_remover) {
            // alerta para confirmar a remeção
            // só remove se houver confirmação positiva
            AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage("Deseja excluir esse pais?")
                .setPositiveButton("Sim") { dialog, which ->
                    dialog.dismiss()
                    taskExcluir()
                }.setNegativeButton("Não") { dialog, which ->
                    dialog.dismiss()
                }.create().show()
        }
        // botão up navigation
        else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun taskExcluir() {
        if (this.paises != null && this.paises is Paises) {
            // Thread para remover a disciplina
            Thread {
                PaisesService.delete(this.paises as Paises)
                runOnUiThread {
                    // após remover, voltar para activity anterior
                    finish()
                }
            }.start()
        }
    }
}