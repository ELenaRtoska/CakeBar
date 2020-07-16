package com.example.cakebar.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cakebar.Data.AppPrefs
import com.example.cakebar.Data.realm.models.ItemsDBModel
import com.example.cakebar.Data.realm.tables.ItemsTable
import com.example.cakebar.R
import com.example.cakebar.utils.Utils
import com.example.cakebar.widgets.adapters.ItemsOrderRecyclerViewAdapter
import com.example.cakebar.widgets.items.Items
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_order.*

class OrderActivity: AppCompatActivity() {

    private lateinit var mRealmDB: Realm
    private val mItemsDBModel = ItemsDBModel()
    private var mItemsOrderRecyclerViewAdapter: ItemsOrderRecyclerViewAdapter? = null
    private var mItems = ArrayList<ItemsTable>()
    private lateinit var mAppPrefs: AppPrefs
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        mRealmDB = Utils.openRealm(this)
    mAppPrefs = AppPrefs(this)
        init()



    }

    fun summary(): Int
    {
        //vrati gi gi site ceni od order listata i soberi gi i ovaa f-ja povikaj ja vo onCreate()
        var sum=0;

        val itemsRealmResults = mItemsDBModel.getItemByChecked(mRealmDB, true)
        if (itemsRealmResults != null && itemsRealmResults.isNotEmpty()) {
            for (item in itemsRealmResults) {
                if (item != null){
                    val item1 = ItemsTable(item.id, item.name, item.price, item.image, item.isChecked, item.type, item.quantity)
                    sum += (item1.price * item1.quantity)
                }
            }
        }
        return sum
    }

    private fun init() {

        mItems = mItemsDBModel.getAllOrder(mRealmDB)
        mItemsOrderRecyclerViewAdapter = ItemsOrderRecyclerViewAdapter(mItems, this, mAppPrefs)
        recyclerViewOrder.layoutManager = LinearLayoutManager(this)
        recyclerViewOrder.adapter = mItemsOrderRecyclerViewAdapter

        //tuka
        txtSummary.text="Summary: "+summary().toString()+"$";
    txtCustoerName.text = String.format(getString(R.string.s_s, "Customer Name: ", mAppPrefs.customerName))
        txtTableNumber.text = String.format(getString(R.string.s_s, "Table Number: ", mAppPrefs.tableName))
    }
//    private fun showOrder(){
//        val items = ArrayList<ItemsTable>()
//        val itemsRealmResults = mItemsDBModel.getItemByChecked(mRealmDB, true)
//        if (itemsRealmResults != null && itemsRealmResults.isNotEmpty()) {
//            for (item in itemsRealmResults) {
//                if (item != null){
//                    val item1 = ItemsTable(item.id, item.name, item.price, item.image, item.isChecked, item.type, item.quantity)
//                    items.add(item1)}
//            }
//            val string = items.toString()
//            Toast.makeText(this, string, Toast.LENGTH_LONG).show()
//        }
//    }




    //tuka

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.new_order, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when {
            item.itemId == R.id.action_new_order -> {
                newOrder()
                true
            }
            item.itemId == android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun newOrder(){

        val items = ArrayList<ItemsTable>()
        val itemsRealmResults = mItemsDBModel.getItemByChecked(mRealmDB, true)
        if (itemsRealmResults != null && itemsRealmResults.isNotEmpty()) {
            for (item in itemsRealmResults) {
                if (item != null){
                    val item1 = ItemsTable(item.id, item.name, item.price, item.image, item.isChecked, item.type, item.quantity)
                    items.add(item1)}
            }
            if (items != null) {
                mItemsDBModel.editItemArray(mRealmDB, items, false, 1)
            }
        }

        startActivity(Intent(this, FirstScreenActivity::class.java))

    }

   }