package br.com.mobile.osmetricos

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.toolbar.*



class TelaInicialActivity : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val context: Context get() = this
    private var paises = listOf<Paises>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        val args = intent.extras

        val usuario = args?.getString("usuario")

        Toast.makeText(this, "Nome do usuário: $usuario", Toast.LENGTH_LONG).show()




        botaoSair.setOnClickListener {cliqueSair()}

        setSupportActionBar(toolbar)


        supportActionBar?.title = "Países do Mundo"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        configuraMenuLateral()


        recyclerDisciplinas?.layoutManager = LinearLayoutManager(context)
        recyclerDisciplinas?.itemAnimator = DefaultItemAnimator()
        recyclerDisciplinas?.setHasFixedSize(true)

        val intentPaises = Intent(this, PaisesActivity::class.java)
        val intentinformacoes = Intent(this, InformacoesActivity::class.java)
        val intentlocalizacao = Intent(this, LocalizacaoActivity::class.java)
        menu_lateral.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_diciplinas -> startActivity(intentPaises)
                R.id.nav_forum -> startActivity(intentinformacoes)
                R.id.nav_localizacao -> startActivity(intentlocalizacao)
                R.id.nav_sair -> finish()
            }
            true
        }
    }

    override fun onResume() {
        super.onResume()
        taskPaises()
    }

    fun taskPaises() {


        Thread {
            paises = PaisesService.getPaises()
            runOnUiThread {
                recyclerDisciplinas?.adapter = PaisesAdapter(paises) { onClickPais(it) }
                // enviar notificação
                enviaNotificacao(this.paises.get(0))
            }
        }.start()

    }

    fun enviaNotificacao(pais: Paises) {
        // Intent para abrir tela quando clicar na notificação
        val intent = Intent(this, PaisesActivity::class.java)
        // parâmetros extras
        intent.putExtra("pais", pais)
        // Disparar notificação
        NotificationUtil.create(this, 1, intent, "LMSApp", "Você tem nova atividade na ${pais.nome}")
    }


    fun onClickPais(pais: Paises) {
        Toast.makeText(context, "Clicou pais ${pais.nome}", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, PaisesActivity::class.java)
        intent.putExtra("paise", pais)
        startActivity(intent)
    }

    private fun configuraMenuLateral() {

        var toggle = ActionBarDrawerToggle(this, layoutMenuLateral, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        layoutMenuLateral.addDrawerListener(toggle)
        toggle.syncState()

        menu_lateral.setNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_diciplinas -> {
                Toast.makeText(this, "Países", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_forum -> {
                Toast.makeText(this, "Informações", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_localizacao -> {
                Toast.makeText(this, "Localização", Toast.LENGTH_SHORT).show()
            }
        }

        layoutMenuLateral.closeDrawer(GravityCompat.START)
        return true
    }

    fun cliqueSair() {
        val returnIntent = Intent();
        returnIntent.putExtra("result","Saída do BrewerApp");
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        (menu?.findItem(R.id.action_buscar)?.actionView as SearchView?)?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {

                return false
            }

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item?.itemId
        if  (id == R.id.action_buscar) {
            Toast.makeText(context, "Botão de buscar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_atualizar) {
            Toast.makeText(context, "Botão de atualizar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_remover) {
            Toast.makeText(context, "Botão de Remover", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_config) {
            Toast.makeText(context, "Botão de configuracoes", Toast.LENGTH_LONG).show()
        } else if (id==R.id.action_adicionar){
            val intent = Intent(this, PaisesCadastroActivity::class.java)
            startActivity(intent)
        }
        else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
