package br.com.mobile.osmetricos

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.login.*

class MainActivity : DebugActivity() {
    private lateinit var usuarioNome: EditText
    private lateinit var usuarioSenha: EditText

    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        usuarioNome = findViewById(R.id.campo_usuario)
        usuarioSenha = findViewById(R.id.campo_senha)

        // encontra objeto pelo id
        campo_imagem.setImageResource(R.drawable.mundo_login)

        texto_login.text = getString(R.string.mensagem_login)



        // evento no botao de login forma 1
//        botao_login.setOnClickListener {
//            val valorUsuario = campo_usuario.text.toString()
//            val valorSenha = campo_senha.text.toString()
//            Toast.makeText(this, "$valorUsuario : $valorSenha", Toast.LENGTH_LONG).show()
//        }

        // segunda forma: delegar para método
        botao_login.setOnClickListener {onClickLogin() }


    }

    fun onClickLogin(){
        val valorUsuario = campo_usuario.text.toString()
        val valorSenha = campo_senha.text.toString()
        Toast.makeText(context, "$valorUsuario ", Toast.LENGTH_LONG).show()

        // criar intent
        val intent = Intent(context, TelaInicialActivity::class.java)
        // colocar parâmetros (opcional)
        val params = Bundle()
        params.putString("usuario", valorUsuario)
        intent.putExtras(params)

        // enviar parâmetros simplificado
        intent.putExtra("numero", 10)

        if (valorUsuario != "aluno" || valorSenha != "impacta")  {
            usuarioNome.setError("usuario invalido")
            usuarioSenha.setError("senha invalida")
            Toast.makeText(this, "Insira usuário ou senha corretamente", Toast.LENGTH_LONG).show()
        } else {
            startActivity(intent)
        }


    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            val result = data?.getStringExtra("result")
            Toast.makeText(context, "$result", Toast.LENGTH_LONG).show()
        }
    }
}
