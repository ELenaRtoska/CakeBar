package com.example.cakebar.fragments

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cakebar.Data.AppPrefs
import com.example.cakebar.Data.realm.models.ItemsDBModel
import com.example.cakebar.Data.realm.tables.ItemsTable
import com.example.cakebar.R
import com.example.cakebar.utils.Types
import com.example.cakebar.utils.Utils
import com.example.cakebar.widgets.adapters.ItemsRecyclerViewAdapter
import com.example.cakebar.widgets.items.Items
import com.example.cakebar.widgets.listeners.MyItemsClickListener
import io.realm.Realm
import kotlinx.android.synthetic.main.cakes_fragment.*
import kotlinx.android.synthetic.main.dessets_fragment.*
import kotlinx.android.synthetic.main.drinks_fragment.*
import kotlinx.android.synthetic.main.drinks_fragment.recyclerView
import java.util.*
private const val TAG = "Drinks Fragment"
class DrinksFragment : Fragment(), MyItemsClickListener {

    private var mItemsRecyclerViewAdapter: ItemsRecyclerViewAdapter? = null
    private var mItems = ArrayList<ItemsTable>()
    private lateinit var mRealmDB: Realm
    private lateinit var mPrefs: AppPrefs
    private lateinit var mAppCompatActivity: AppCompatActivity
    private var mSelectedItem: Items? = null
    private val mItemsDBModel = ItemsDBModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        mAppCompatActivity = activity as AppCompatActivity
        mPrefs = AppPrefs(mAppCompatActivity)
        mRealmDB = Utils.openRealm(mAppCompatActivity)
        val drinks = mItemsDBModel.getAllDrinksNumber(mRealmDB)

        if (drinks == 0) {
            AddDrinks()
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.drinks_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        init()

    }

    private fun init() {

        mItems = mItemsDBModel.getAllDrinks(mRealmDB)
        mItemsRecyclerViewAdapter = ItemsRecyclerViewAdapter(mItems, activity!!, this, mPrefs)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = mItemsRecyclerViewAdapter

        mItemsRecyclerViewAdapter!!.notifyDataSetChanged()
    }
//    override fun onItemClick(item: ItemsTable) {
//
//        item.id
//
//        mItemsDBModel.editItem(mRealmDB, item,true)
//    }

    private fun getItemById(realm: Realm, id: Long): ItemsTable? {
        return realm.where(ItemsTable::class.java).equalTo("id", id).findFirst()
    }
    override fun onItemClick(item: ItemsTable) {

        if (item != null) {
            // item.id

            val item = getItemById(mRealmDB, item.id)

            val check = item!!.isChecked
            if (!check) {
                mPrefs.dessertsFlag = true
                mItemsDBModel.editItemTable(mRealmDB, item, true)
            } else {
                mPrefs.dessertsFlag = false
                mItemsDBModel.editItemTable(mRealmDB, item, false)

            }

        }
    }

    override fun onItemLongClick(item: ItemsTable) {

        val dialog = Dialog(mAppCompatActivity)
        dialog .requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog .setCancelable(true)
        dialog .setContentView(R.layout.dialog_quantity)
//        val body = dialog .findViewById(R.id.body) as TextView
        //   body.text = title
        val etQuantity = dialog .findViewById(R.id.editTextTextPersonName) as EditText
        val save = dialog .findViewById(R.id.btn_save) as Button

        save.setOnClickListener {
            val intQuantity = etQuantity.text.toString().toInt()
            if (intQuantity != 0){
                if (item != null) {
                    // item.id

                    val item = getItemById(mRealmDB, item.id)


                    mPrefs.dessertsFlag = false
                    mItemsDBModel.editItemTableQuantity(mRealmDB, item!!, etQuantity.text.toString().toInt(), true)


                }
            }
            mItems = mItemsDBModel.getAllDrinks(mRealmDB)
            mItemsRecyclerViewAdapter = ItemsRecyclerViewAdapter(mItems, activity!!, this, mPrefs)
            recyclerView.layoutManager = LinearLayoutManager(activity)
            recyclerView.adapter = mItemsRecyclerViewAdapter
            mItemsRecyclerViewAdapter!!.notifyDataSetChanged()
            dialog.hide()

        }
        dialog .show()


    }
    private fun AddDrinks(){
        mItemsDBModel.saveItem(mRealmDB, 31,"MOJITO", 10, R.drawable.mojito.toString(), false, Types.Drinks.value,1)
        mItemsDBModel.saveItem(mRealmDB, 32,"MARTINI", 13, R.drawable.martini.toString(),false, Types.Drinks.value,1)
        mItemsDBModel.saveItem(mRealmDB, 33,"CUBA LIBRE", 10, R.drawable.cuba_libre.toString(),false, Types.Drinks.value,1)
        mItemsDBModel.saveItem(mRealmDB, 34,"GIN TONIC", 11, R.drawable.gin_tonic.toString(),false, Types.Drinks.value,1)
        mItemsDBModel.saveItem(mRealmDB, 35,"DAIQUIRI", 12, R.drawable.daiquiri.toString(), false, Types.Drinks.value,1)
        mItemsDBModel.saveItem(mRealmDB, 36,"MANHATTAN", 11, R.drawable.manhattan.toString(),false, Types.Drinks.value,1)
        mItemsDBModel.saveItem(mRealmDB, 37,"BLUE LAGOON", 10, R.drawable.blue_lagoon.toString(),false, Types.Drinks.value,1)
        mItemsDBModel.saveItem(mRealmDB, 38,"COCA-COLA", 3, R.drawable.coca_cola.toString(),false, Types.Drinks.value,1)
        mItemsDBModel.saveItem(mRealmDB, 39,"FANTA", 3, R.drawable.fanta.toString(), false, Types.Drinks.value,1)
        mItemsDBModel.saveItem(mRealmDB, 40,"ICED TEA", 2, R.drawable.iced_tea.toString(),false, Types.Drinks.value,1)
    }

}

