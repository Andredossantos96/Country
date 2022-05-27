package br.com.mobile.osmetricos

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager

import br.com.mobile.osmetricos.PaisesActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.menu_lateral_cabecalho.*
import kotlinx.android.synthetic.main.toolbar.*



class TelaInicialActivity : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val context: Context get() = this
    private var paises = listOf<Paises>()
    private var REQUEST_CADASTRO = 1
    private var REQUEST_REMOVE= 2



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
                R.id.nav_paises -> startActivity(intentPaises)
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
            paises = PaisesService.getPaises(context)
            runOnUiThread {
                recyclerDisciplinas?.adapter = PaisesAdapter(paises) { onClickPais(it) }
            }
        }.start()

    }


    fun onClickPais(pais: Paises) {
        Toast.makeText(context, "Clicou pais ${pais.nome}", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, PaisesActivity::class.java)
        intent.putExtra("pais", pais)
        startActivityForResult(intent, REQUEST_REMOVE)
    }

    private fun configuraMenuLateral() {

        var toggle = ActionBarDrawerToggle(this, layoutMenuLateral, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        layoutMenuLateral.addDrawerListener(toggle)
        toggle.syncState()

        menu_lateral.setNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_paises -> {
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
        } else if (id == R.id.action_config) {
            Toast.makeText(context, "Botão de configuracoes", Toast.LENGTH_LONG).show()
        } else if (id==R.id.action_adicionar){
            // iniciar activity de cadastro
            val intent = Intent(context, PaisesCadastroActivity::class.java)
            startActivityForResult(intent, REQUEST_CADASTRO)
        }
        else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CADASTRO || requestCode == REQUEST_REMOVE ) {
            // atualizar lista de disciplinas
            taskPaises()
        }
    }
}
