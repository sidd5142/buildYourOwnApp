package com.example.quickity.models

//This contains the data class
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.VersionedParcelize
import kotlinx.parcelize.Parcelize
import org.jetbrains.annotations.NonNls

@Parcelize
@Entity(tableName = "employees")
data class Employee(

    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "employeeId")
    var employeeId: Long,

    @ColumnInfo(name = "employeeName")
    var employeeName: String,

    @ColumnInfo(name = "designation")
    var employeeDesignation: String,
) : Parcelable
