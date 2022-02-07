package com.example.navigationcomponenttutorial.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.navigationcomponenttutorial.model.Students

@Dao
interface DbDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note : Students)

    @Update
    suspend fun update(note: Students)

    @Delete
    suspend fun delete(note: Students)

    @Query("Select * from stdTable order by id ASC")
    fun getAllNotes(): LiveData<List<Students>>
}