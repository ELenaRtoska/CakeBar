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
import kotlinx.android.synthetic.main.dessets_fragment.recyclerView
import java.util.*
private const val TAG = "Desserts Fragment"
class DessertsFragment : Fragment(), MyItemsClickListener {

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
        val desserts = mItemsDBModel.getAllDessertsNumber(mRealmDB)

        if (desserts == 0) {
            AddDesserts()
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.dessets_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        init()

    }

    private fun init() {

        mItems = mItemsDBModel.getAllDesserts(mRealmDB)
        mItemsRecyclerViewAdapter = ItemsRecyclerViewAdapter(mItems, activity!!, this, mPrefs)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = mItemsRecyclerViewAdapter
        mItemsRecyclerViewAdapter!!.notifyDataSetChanged()
    }

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
            mItems = mItemsDBModel.getAllDesserts(mRealmDB)
            mItemsRecyclerViewAdapter = ItemsRecyclerViewAdapter(mItems, activity!!, this, mPrefs)
            recyclerView.layoutManager = LinearLayoutManager(activity)
            recyclerView.adapter = mItemsRecyclerViewAdapter
            mItemsRecyclerViewAdapter!!.notifyDataSetChanged()
            dialog.hide()

        }
        dialog .show()



    }
    private fun AddDesserts(){
        mItemsDBModel.saveItem(mRealmDB, 21,"ECLAIR", 18, R.drawable.eclair.toString(), false, Types.Desserts.value,1)
        mItemsDBModel.saveItem(mRealmDB, 22,"HOKKAIDO CHEESE", 20, R.drawable.hocaico_cheese.toString(),false, Types.Desserts.value,1)
        mItemsDBModel.saveItem(mRealmDB, 23,"TIRAMISU CUP", 30, R.drawable.tiramisu_cup.toString(),false, Types.Desserts.value,1)
        mItemsDBModel.saveItem(mRealmDB, 24,"PANNA COTTA", 25, R.drawable.panna_cotta.toString(),false, Types.Desserts.value,1)
        mItemsDBModel.saveItem(mRealmDB, 25,"STRAWBERRY TART", 24, R.drawable.strawberry_tart.toString(), false, Types.Desserts.value,1)
        mItemsDBModel.saveItem(mRealmDB, 26,"CHOCOLATE TRUFFLE", 13, R.drawable.chocolate_truffle.toString(),false, Types.Desserts.value,1)
        mItemsDBModel.saveItem(mRealmDB, 27,"CUSTARD PUDDING", 13, R.drawable.custard_pudding.toString(),false, Types.Desserts.value,1)
        mItemsDBModel.saveItem(mRealmDB, 28,"CREAM PUFF", 16, R.drawable.cream_puff.toString(),false, Types.Desserts.value,1)
        mItemsDBModel.saveItem(mRealmDB, 29,"FRUIT CREAM PUFF", 20, R.drawable.fruit_cream_puff.toString(), false, Types.Desserts.value,1)
        mItemsDBModel.saveItem(mRealmDB, 30,"PIE CREAM PUFF", 18, R.drawable.pie_cream_puff.toString(),false, Types.Desserts.value,1)
    }

}

