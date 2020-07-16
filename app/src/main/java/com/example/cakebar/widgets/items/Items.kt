package com.example.cakebar.widgets.items

import android.os.Parcelable
import android.widget.ImageView
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Items(
    val id: Long,
    val name: String,
    val price: Int,
    val image: String,
    val isChecked: Boolean,
    val type: Int,
    val quantity: Int
): Parcelable