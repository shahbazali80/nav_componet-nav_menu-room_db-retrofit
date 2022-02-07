package com.example.navigationcomponenttutorial.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.navigationcomponenttutorial.database.SchoolDatabase
import com.example.navigationcomponenttutorial.model.Students
import com.example.navigationcomponenttutorial.repository.DbRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DbViewModel(application: Application) : AndroidViewModel(application) {

    val allStds : LiveData<List<Students>>
    val repository : DbRepository

    init {
        val dao = SchoolDatabase.getDatabase(application).getStdDao()
        repository = DbRepository(dao)
        allStds = repository.allStd
    }

    fun deleteStudents (students : Students) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(students)
    }

    fun updateStudents(students : Students) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(students)
    }

    fun addStudents(students : Students) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(students)
    }

}