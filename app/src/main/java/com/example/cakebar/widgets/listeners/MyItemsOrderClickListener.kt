package com.example.cakebar.widgets.listeners

import com.example.cakebar.widgets.items.Items

interface MyItemsOrderClickListener {

    fun onItemClick(item: Items)

    fun onItemLongClick(item: Items)
}