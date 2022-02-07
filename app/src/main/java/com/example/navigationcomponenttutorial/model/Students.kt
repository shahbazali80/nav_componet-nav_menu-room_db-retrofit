package com.example.navigationcomponenttutorial.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stdTable")
class Students
    (@ColumnInfo(name = "name") val stdName: String,
    @ColumnInfo(name = "email") val stdEmail: String,
    @ColumnInfo(name = "phone") val stdPhone: String)  {

        @PrimaryKey(autoGenerate = true)
        var id=0
}