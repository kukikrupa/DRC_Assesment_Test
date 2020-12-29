package com.drc.assesment.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.drc.assesment.model.LoginTableModel

@Dao
interface DAOAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertData(loginTableModel: LoginTableModel)

    @Query("SELECT * FROM Login WHERE Username =:username")
    fun getLoginDetails(username: String?) : LiveData<LoginTableModel>

}