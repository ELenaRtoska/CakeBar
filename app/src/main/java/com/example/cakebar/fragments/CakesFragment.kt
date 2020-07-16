package com.example.cakebar.fragments

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
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
import kotlinx.android.synthetic.main.item.view.*
import java.util.*
private const val TAG = "Cakes Fragment"
class CakesFragment : Fragment(), MyItemsClickListener {

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
        val cakes = mItemsDBModel.getAllCakesNumber(mRealmDB)

        if (cakes == 0) {
            AddCakes()
        }

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.cakes_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {

        mItems = mItemsDBModel.getAllCakes(mRealmDB)
        mItemsRecyclerViewAdapter = ItemsRecyclerViewAdapter(mItems, activity!!, this, mPrefs)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = mItemsRecyclerViewAdapter
       // mItemsRecyclerViewAdapter!!.notifyDataSetChanged()
   //     val mHolderItem = recyclerView.relativeLayout
        if (!mPrefs.dessertsFlag){

        //    mHolderItem.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }else{
      //      mHolderItem.setBackgroundColor(Color.parseColor("#A1B055"))
        }
    }
//    var flagCake = false
//    override fun onItemClick(item: ItemsTable) {
//        item.id
//        if (!flagCake){
//            mItemsDBModel.editItem(mRealmDB, item,true)
//            flagCake = true
//        }else{
//            flagCake = false
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
            mItems = mItemsDBModel.getAllCakes(mRealmDB)
            mItemsRecyclerViewAdapter = ItemsRecyclerViewAdapter(mItems, activity!!, this, mPrefs)
            recyclerView.layoutManager = LinearLayoutManager(activity)
            recyclerView.adapter = mItemsRecyclerViewAdapter
            mItemsRecyclerViewAdapter!!.notifyDataSetChanged()
            dialog.hide()

        }
        dialog .show()

    }



private fun AddCakes(){
    mItemsDBModel.saveItem(mRealmDB, 1,"STRAWBERRY CAKE",24,R.drawable.strawberry_cream_cake.toString(), false, Types.Cakes.value, 1)
    mItemsDBModel.saveItem(mRealmDB, 2,"CHOCOLATE CAKE", 24, R.drawable.chocolate_cake.toString(),false, Types.Cakes.value, 1)
    mItemsDBModel.saveItem(mRealmDB, 3,"TIRAMISU CAKE", 25, R.drawable.tiramisu_cake.toString(),false, Types.Cakes.value,1)
    mItemsDBModel.saveItem(mRealmDB, 4,"SWEET POTATO TART", 25, R.drawable.sweet_potato_tart.toString(),false, Types.Cakes.value,1)
    mItemsDBModel.saveItem(mRealmDB, 5,"DARK CHOCOLATE", 24, R.drawable.dark_chocolate.toString(), false, Types.Cakes.value,1)
    mItemsDBModel.saveItem(mRealmDB, 6,"RARE CHEESE", 24, R.drawable.rare_cheesecake.toString(),false, Types.Cakes.value,1)
    mItemsDBModel.saveItem(mRealmDB, 7,"CHOCO MOUSSE", 25, R.drawable.choco_mousse.toString(),false, Types.Cakes.value,1)
    mItemsDBModel.saveItem(mRealmDB, 8,"LAMINGTON", 10, R.drawable.lamington.toString(),false, Types.Cakes.value,1)
    mItemsDBModel.saveItem(mRealmDB, 9,"MANGO TART", 24, R.drawable.mango_tart.toString(), false, Types.Cakes.value,1)
    mItemsDBModel.saveItem(mRealmDB, 10,"PUDDING CAKE", 25, R.drawable.pudding_cake.toString(),false, Types.Cakes.value,1)
}

}
