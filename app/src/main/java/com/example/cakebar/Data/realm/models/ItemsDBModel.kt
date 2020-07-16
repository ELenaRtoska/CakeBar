package com.example.cakebar.Data.realm.models

import com.example.cakebar.Data.realm.tables.ItemsTable
import com.example.cakebar.utils.Types
import com.example.cakebar.widgets.items.Items
import io.realm.Realm
import io.realm.RealmResults

class ItemsDBModel {

    private fun getAllItemsResults(realm: Realm): RealmResults<ItemsTable>? {
        return realm.where(ItemsTable::class.java).findAll()
    }

    fun getAllItemsTable(realm: Realm): ArrayList<ItemsTable> {
        val items = ArrayList<ItemsTable>()
        val itemsRealmResults = getAllItemsResults(realm)
        if (itemsRealmResults != null && itemsRealmResults.isNotEmpty()) {
            for (item in itemsRealmResults) {
                items.add(item)
            }
        }
        return items
    }
    fun getAllItems(realm: Realm): ArrayList<Items> {
        val items = ArrayList<Items>()
        val itemsRealmResults = getAllItemsResults(realm)
        if (itemsRealmResults != null && itemsRealmResults.isNotEmpty()) {
            for (item in itemsRealmResults) {
                val item1 = Items(item.id, item.name, item.price, item.image, item.isChecked, item.type, item.quantity)
                items.add(item1)
            }
        }
        return items
    }

    fun getAllCoffee(realm: Realm): ArrayList<ItemsTable> {
        val items = ArrayList<ItemsTable>()
        val itemsRealmResults = getAllItemsResults(realm)
        if (itemsRealmResults != null && itemsRealmResults.isNotEmpty()) {
            for (item in itemsRealmResults) {
                val item1 = ItemsTable(item.id, item.name, item.price, item.image, item.isChecked, item.type, item.quantity)
                if(item1.type==Types.Coffee.value)
                {
                    items.add(item1)
                }
            }
        }
        return items
    }

    fun getAllCakes(realm: Realm): ArrayList<ItemsTable> {
        val items = ArrayList<ItemsTable>()
        val itemsRealmResults = getAllItemsResults(realm)
        if (itemsRealmResults != null && itemsRealmResults.isNotEmpty()) {
            for (item in itemsRealmResults) {
                val item1 = ItemsTable(item.id, item.name, item.price, item.image, item.isChecked, item.type, item.quantity)
                if(item1.type==Types.Cakes.value)
                {
                    items.add(item1)
                }
            }
        }
        return items
    }

    fun getAllDrinks(realm: Realm): ArrayList<ItemsTable> {
        val items = ArrayList<ItemsTable>()
        val itemsRealmResults = getAllItemsResults(realm)
        if (itemsRealmResults != null && itemsRealmResults.isNotEmpty()) {
            for (item in itemsRealmResults) {
                val item1 = ItemsTable(item.id, item.name, item.price, item.image, item.isChecked, item.type, item.quantity)
                if(item1.type==Types.Drinks.value)
                {
                    items.add(item1)
                }
            }
        }
        return items
    }

    fun getAllDesserts(realm: Realm): ArrayList<ItemsTable> {
        val items = ArrayList<ItemsTable>()
        val itemsRealmResults = getAllItemsResults(realm)
        if (itemsRealmResults != null && itemsRealmResults.isNotEmpty()) {
            for (item in itemsRealmResults) {
                val item1 = ItemsTable(item.id, item.name, item.price, item.image, item.isChecked, item.type, item.quantity)
                if(item1.type==Types.Desserts.value)
                {
                    items.add(item1)
                }
            }
        }
        return items
    }
    fun getAllOrder(realm: Realm): ArrayList<ItemsTable> {
        val items = ArrayList<ItemsTable>()
        val itemsRealmResults = getAllItemsResults(realm)
        if (itemsRealmResults != null && itemsRealmResults.isNotEmpty()) {
            for (item in itemsRealmResults) {
                val item1 = ItemsTable(item.id, item.name, item.price, item.image, item.isChecked, item.type, item.quantity)
                if(item1.isChecked) {
                    items.add(item1)
                }
            }
        }
        return items
    }

    fun getAllCakesNumber(realm: Realm): Int {
        val allTrackingStatuses: Array<Int> = arrayOf(Types.Cakes.value)


        return realm.where(ItemsTable::class.java).`in`("type", allTrackingStatuses).count().toInt()
    }
    fun getAllDessertsNumber(realm: Realm): Int {
        val allTrackingStatuses: Array<Int> = arrayOf(Types.Desserts.value)


        return realm.where(ItemsTable::class.java).`in`("type", allTrackingStatuses).count().toInt()
    }
    fun getAllCoffeeNumber(realm: Realm): Int {
        val allTrackingStatuses: Array<Int> = arrayOf(Types.Coffee.value)


        return realm.where(ItemsTable::class.java).`in`("type", allTrackingStatuses).count().toInt()
    }
    fun getAllDrinksNumber(realm: Realm): Int {
        val allTrackingStatuses: Array<Int> = arrayOf(Types.Drinks.value)


        return realm.where(ItemsTable::class.java).`in`("type", allTrackingStatuses).count().toInt()
    }
    fun saveItem(realm: Realm, addId: Long, addName: String, addPrice:Int, addImage:String, addIsChecked: Boolean, addType: Int, addQuantity: Int): Boolean {
        return try {

                realm.beginTransaction()
                val item = realm.createObject(ItemsTable::class.java, addId)
                item.name = addName
                item.price = addPrice
                item.image = addImage
                item.isChecked = addIsChecked
                item.type = addType
                item.quantity = addQuantity
                realm.commitTransaction()
            true
        } catch (e: Exception) {
            println(e)
            false
        }
    }
    fun deleteItemsById(realm: Realm, id: Long): Boolean {
        return try {
            val item = getItemById(realm, id)
            if (item != null) {
                realm.beginTransaction()
                item.deleteFromRealm()
                realm.commitTransaction()
            }

            true
        } catch (e: Exception) {
            println(e)
            false
        }
    }

    fun editItem(realm: Realm, items:ItemsTable, addIsChecked: Boolean): Boolean {
        return try {
            if (items != null) {
                val item = getItemById(realm, items.id)
                if (item != null) {
                    realm.beginTransaction()
                   item.isChecked = addIsChecked
                    realm.commitTransaction()
                }
            }
            true
        } catch (e: Exception) {
            println(e)
            false
        }
    }
    fun editItemTable(realm: Realm, items:ItemsTable, addIsChecked: Boolean): Boolean {
        return try {
            if (items != null) {
                val item = getItemById(realm, items.id)
                if (item != null) {
                    realm.beginTransaction()
                    item.isChecked = addIsChecked
                    realm.commitTransaction()
                }
            }
            true
        } catch (e: Exception) {
            println(e)
            false
        }
    }
    fun editItemTableQuantity(realm: Realm, items:ItemsTable, addQuantity:Int, addIsChecked: Boolean): Boolean {
        return try {
            if (items != null) {
                val item = getItemById(realm, items.id)
                if (item != null) {
                    realm.beginTransaction()
                    item.quantity= addQuantity
                    item.isChecked = addIsChecked
                    realm.commitTransaction()
                }
            }
            true
        } catch (e: Exception) {
            println(e)
            false
        }
    }
    fun editItemArray(realm: Realm, items:ArrayList<ItemsTable>, addIsChecked: Boolean, addQuantity: Int): Boolean {
        return try {
            if (items != null) {
                for(item in items.indices){
                    val item = getItemById(realm, items[item].id)
                    if (item != null) {
                        realm.beginTransaction()
                        item.isChecked = addIsChecked
                        item.quantity = addQuantity
                        realm.commitTransaction()
                    }
                }
            }
            true
        } catch (e: Exception) {
            println(e)
            false
        }
    }
    private fun getItemById(realm: Realm, id: Long): ItemsTable? {
        return realm.where(ItemsTable::class.java).equalTo("id", id).findFirst()
    }

   fun getItemByChecked(realm: Realm, isChecked: Boolean): RealmResults<ItemsTable?> {
        return realm.where(ItemsTable::class.java).equalTo("isChecked", true).findAll()

//        val allTrackingStatuses: Array<Int> = arrayOf(Types.Drinks.value)
//
//
//        return realm.where(ItemsTable::class.java).`in`("type", allTrackingStatuses)

    }

    private fun deleteOrder(realm: Realm, items: ItemsTable?): Boolean {
        return try {
            if (items != null) {
                realm.beginTransaction()
                items.deleteFromRealm()
                realm.commitTransaction()
                true
            } else {
                false
            }
        } catch (e: Exception) {
            println(e)
            false
        }

    }
}
