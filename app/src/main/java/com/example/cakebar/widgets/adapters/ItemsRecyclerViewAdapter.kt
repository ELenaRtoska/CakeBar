package com.example.cakebar.widgets.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cakebar.Data.AppPrefs
import com.example.cakebar.Data.realm.tables.ItemsTable
import com.example.cakebar.R
import com.example.cakebar.utils.Types
import com.example.cakebar.widgets.items.Items
import com.example.cakebar.widgets.listeners.MyItemsClickListener
import kotlinx.android.synthetic.main.cakes_fragment.*
import kotlinx.android.synthetic.main.item.view.*

class ItemsRecyclerViewAdapter(
    private var items: List<ItemsTable>,
    private val context: Context,
    private val listener: MyItemsClickListener,
    private var mAppPrefs: AppPrefs) : RecyclerView.Adapter<MyItemsViewHolder>() {

    /**
     * This method creates different RecyclerView.MainViewHolder objects based on the item view type.\
     *
     * @param parent   ViewGroup container for the item
     * @param viewType type of view to be inflated
     * @return viewHolder to be inflated
     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyItemsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)

        if (!mAppPrefs.dessertsFlag){
           view.relativeLayout.setBackgroundColor(Color.parseColor("#D7DCCA"))
           // mHolderItem.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }else{
            //      mHolderItem.setBackgroundColor(Color.parseColor("#A1B055"))
            view.relativeLayout.setBackgroundColor(Color.parseColor("#D7DCCA"))
        }
        return MyItemsViewHolder(view, context, mAppPrefs)
    }

    // Involves populating data into the item through holder - it is called by LayoutManager when it wants new data in an existing view
    override fun onBindViewHolder(holder: MyItemsViewHolder, position: Int) {
        val item = items[position]

        holder.apply {

            mName.text = item.name
            mPrice.text = String.format(context.getString(R.string.s_s_s_s, item.price.toString(), "$", "Quantity: ", item.quantity.toString()))
            mImage.setImageResource(item.image.toInt())
            mImage.visibility = View.VISIBLE
            val appPrefs = AppPrefs(context)
            if(!item.isChecked){
                mHolderItem.relativeLayout.setBackgroundColor(Color.parseColor("#EEF5DE"))
            }else{
                mHolderItem.relativeLayout.setBackgroundColor(Color.parseColor("#D7DCCA"))
            }
          //  mHolderItem


                mImage.visibility = View.VISIBLE

            setListener(listener, item)
        }
    }

    // Returns the total count of itemsOriginal in the list
    override fun getItemCount(): Int {
        return if (items.isNotEmpty()) items.size else 0
    }
}

class MyItemsViewHolder(val view: View,
                        private val context: Context,
                        private var mAppPrefs: AppPrefs) : RecyclerView.ViewHolder(view) {
    val mName: TextView = view.name
    val mPrice: TextView = view.price
    val mImage: ImageView = view.image
   val mHolderItem: RelativeLayout = view.relativeLayout

    fun setListener(listener: MyItemsClickListener?, item: ItemsTable) {
        mAppPrefs = AppPrefs(context)
        mAppPrefs.dessertsFlag
        //= item.isChecked
//        if (!mAppPrefs.dessertsFlag){
//
//            mHolderItem.setBackgroundColor(Color.parseColor("#FFFFFF"))
//        }else{
//            mHolderItem.setBackgroundColor(Color.parseColor("#A1B055"))
//        }
        view.setOnClickListener {

//            if (!item.isChecked){
//                val color = when (item.type) {
//                    Types.Cakes.value -> "#6A791B"
//                    Types.Desserts.value -> "#616F19"
//                    Types.Coffee.value -> "#576416"
//                    Types.Drinks.value -> "#4F5B14"
//                    else -> "#6A791B"
//                }
//                mHolderItem.setBackgroundColor(Color.parseColor(color))
//            }else{
//                mHolderItem.setBackgroundColor(Color.parseColor("#A1B055"))
//            }
            if (!mAppPrefs.dessertsFlag){
//            val color = when (item.type) {
//                Types.Cakes.value -> "#6A791B"
//                Types.Desserts.value -> "#616F19"
//                Types.Coffee.value -> "#576416"
//                Types.Drinks.value -> "#4F5B14"
//                else -> "#6A791B"
//            }
                mHolderItem.setBackgroundColor(Color.parseColor("#D7DCCA"))
            }else{
                mHolderItem.setBackgroundColor(Color.parseColor("#EEF5DE"))
            }

            listener?.onItemClick(item)

        }

        view.setOnLongClickListener {
            listener?.onItemLongClick(item)
            true
        }
    }
}