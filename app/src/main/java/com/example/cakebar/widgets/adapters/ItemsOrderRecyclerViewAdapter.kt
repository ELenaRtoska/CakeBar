package com.example.cakebar.widgets.adapters

import android.content.Context
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
import com.example.cakebar.widgets.items.Items
import kotlinx.android.synthetic.main.item.view.*

class ItemsOrderRecyclerViewAdapter(
    private var items: List<ItemsTable>,
    private val context: Context,
    private var mAppPrefs: AppPrefs
) :
    RecyclerView.Adapter<MyItemsOrderViewHolder>() {

    /**
     * This method creates different RecyclerView.MainViewHolder objects based on the item view type.\
     *
     * @param parent   ViewGroup container for the item
     * @param viewType type of view to be inflated
     * @return viewHolder to be inflated
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyItemsOrderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return MyItemsOrderViewHolder(view, context, mAppPrefs)
    }

    // Involves populating data into the item through holder - it is called by LayoutManager when it wants new data in an existing view
    override fun onBindViewHolder(holder: MyItemsOrderViewHolder, position: Int) {
        val item = items[position]

        holder.apply {

            mName.text = item.name
            mPrice.text = String.format(context.getString(R.string.s_s_s_s, item.price.toString(), "$", "Quantity: ", item.quantity.toString()))
                //item.price.toString()
            mImage.setImageResource(item.image.toInt())
            mImage.visibility = View.VISIBLE


        }
    }

    // Returns the total count of itemsOriginal in the list
    override fun getItemCount(): Int {
        return if (items.isNotEmpty()) items.size else 0
    }
}

class MyItemsOrderViewHolder(val view: View,
                        private val context: Context,
                        private var mAppPrefs: AppPrefs
) : RecyclerView.ViewHolder(view) {
    val mName: TextView = view.name
    val mPrice: TextView = view.price
    val mImage: ImageView = view.image
    val mHolderItem: RelativeLayout = view.relativeLayout

}