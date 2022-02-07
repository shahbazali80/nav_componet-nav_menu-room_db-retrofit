package com.example.navigationcomponenttutorial.repository

import androidx.lifecycle.LiveData
import com.example.navigationcomponenttutorial.dao.DbDao
import com.example.navigationcomponenttutorial.model.Students

class DbRepository (private val dbDao: DbDao) {

    val allStd: LiveData<List<Students>> = dbDao.getAllNotes()

    suspend fun insert(students: Students) {
        dbDao.insert(students)
    }

    suspend fun delete(students: Students){
        dbDao.delete(students)
    }

    suspend fun update(students: Students){
        dbDao.update(students)
    }

}