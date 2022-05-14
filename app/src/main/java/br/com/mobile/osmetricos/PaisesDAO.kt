package br.com.mobile.osmetricos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface PaisesDAO {
    @Query("SELECT * FROM paises where id =:id")
    fun getById(id: Long) : Paises?

    @Query("SELECT * FROM paises")
    fun findAll(): List<Paises>

    @Insert
    fun insert(paises: Paises)

    @Delete
    fun delete(paises: Paises)

}