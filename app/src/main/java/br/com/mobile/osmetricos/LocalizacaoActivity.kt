package br.com.mobile.osmetricos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

class LocalizacaoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_localizacao2)
        supportActionBar?.title = "Detalhes"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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