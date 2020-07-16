package com.example.cakebar.Data.realm.tables

import android.os.Parcelable
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
open class ItemsTable(
    @PrimaryKey
    var id: Long = 0,
    var name: String= "",
    var price: Int = 0,
    var image: String = "",   //type = 1 - private vehicle; type = 2 - taxi vehicle
    var isChecked: Boolean = false,
    var type: Int = 0,
    var quantity: Int = 1
) : RealmObject(), Parcelable