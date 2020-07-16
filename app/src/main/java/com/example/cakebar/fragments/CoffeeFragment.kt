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
import kotlinx.android.synthetic.main.coffee_fragment.*
import kotlinx.android.synthetic.main.coffee_fragment.recyclerView
import java.util.*
private const val TAG = "Coffee Fragment"
class CoffeeFragment : Fragment(), MyItemsClickListener {

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
        val coffee = mItemsDBModel.getAllCoffeeNumber(mRealmDB)

        if (coffee == 0) {
            AddCoffee()
        }



    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.coffee_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        init()

    }

    private fun init() {


        mItems = mItemsDBModel.getAllCoffee(mRealmDB)
        mItemsRecyclerViewAdapter = ItemsRecyclerViewAdapter(mItems, activity!!, this, mPrefs)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = mItemsRecyclerViewAdapter

    }


//    var flagCoffee = false
//    override fun onItemClick(item: ItemsTable) {
//        item.id
//        if (!flagCoffee){
//            mItemsDBModel.editItem(mRealmDB, item,true)
//            flagCoffee = true
//        }else{
//            flagCoffee = false
//            mItemsDBModel.editItem(mRealmDB, item,false)
//        }
//
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
            mItems = mItemsDBModel.getAllCoffee(mRealmDB)
            mItemsRecyclerViewAdapter = ItemsRecyclerViewAdapter(mItems, activity!!, this, mPrefs)
            recyclerView.layoutManager = LinearLayoutManager(activity)
            recyclerView.adapter = mItemsRecyclerViewAdapter
            mItemsRecyclerViewAdapter!!.notifyDataSetChanged()
            dialog.hide()

        }
        dialog .show()



    }
    private fun AddCoffee(){
        mItemsDBModel.saveItem(mRealmDB, 11, "LATE", 6,R.drawable.late.toString(), false, Types.Coffee.value,1)
        mItemsDBModel.saveItem(mRealmDB, 12,"IRISH",7, R.drawable.irish.toString(),false, Types.Coffee.value,1)
        mItemsDBModel.saveItem(mRealmDB, 13,"FREDO",7, R.drawable.freddo.toString(),false, Types.Coffee.value,1)
        mItemsDBModel.saveItem(mRealmDB, 14,"FRAPPE",7, R.drawable.frappe.toString(),false, Types.Coffee.value,1)
        mItemsDBModel.saveItem(mRealmDB, 15,"ESPRESSO MACCHIATO",4, R.drawable.esspresso_macchiato.toString(), false, Types.Coffee.value,1)
        mItemsDBModel.saveItem(mRealmDB, 16,"ESPRESSO CONPANA",4, R.drawable.espresso_conpana.toString(),false, Types.Coffee.value,1)
        mItemsDBModel.saveItem(mRealmDB, 17,"AMERICANO",5, R.drawable.americano.toString(),false, Types.Coffee.value,1)
        mItemsDBModel.saveItem(mRealmDB, 18,"CAPPUCCINO",6, R.drawable.cappuccino.toString(),false, Types.Coffee.value,1)
        mItemsDBModel.saveItem(mRealmDB, 19,"ESPRESSO",2, R.drawable.espresso.toString(), false, Types.Coffee.value,1)
        mItemsDBModel.saveItem(mRealmDB, 20,"DOPPIO",4, R.drawable.doppio.toString(),false, Types.Coffee.value,1)
    }

}

