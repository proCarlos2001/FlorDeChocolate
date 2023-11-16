package com.example.flordechocolate.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "services")
data class ServiceItemModel(
    @PrimaryKey var id: String = "",
    var title: String = "",
    var icon: String = ""



)

