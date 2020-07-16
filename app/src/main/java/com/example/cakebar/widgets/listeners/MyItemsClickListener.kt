package com.example.cakebar.widgets.listeners

import com.example.cakebar.Data.realm.tables.ItemsTable
import com.example.cakebar.widgets.items.Items

interface MyItemsClickListener {
    fun onItemClick(item: ItemsTable)

    fun onItemLongClick(item: ItemsTable)
}
