package br.com.mobile.osmetricos

import androidx.room.Room


object DatabaseManager {

    // singleton
    private var dbInstance: LMSDatabase
    init {
        val appContext = LMSApplication.getInstance().applicationContext
        dbInstance = Room.databaseBuilder(
            appContext, // contexto global
            LMSDatabase::class.java, // Referência da classe do banco
            "teste.sqlite" // nome do arquivo do banco
        ).build()
    }

    fun getPaisesDAO(): PaisesDAO {
        return dbInstance.paisesDAO()
    }
}