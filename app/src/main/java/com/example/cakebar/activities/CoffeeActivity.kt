package com.example.cakebar.activities


import android.app.AppComponentFactory
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cakebar.Data.AppPrefs
import com.example.cakebar.Data.realm.models.ItemsDBModel
import com.example.cakebar.R
import com.example.cakebar.fragments.CoffeeFragment
import com.example.cakebar.utils.Utils
import com.example.cakebar.widgets.items.Items
import io.realm.Realm

class CoffeeActivity: AppCompatActivity() {
    private lateinit var mRealmDB: Realm
    private lateinit var mPrefs: AppPrefs
    private val mItemsDBModel = ItemsDBModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        mRealmDB = Utils.openRealm(this)
        openCoffeeFragment()
    }
    private fun openCoffeeFragment() {
        title = "Coffee"
        supportFragmentManager
            .beginTransaction()
            .addToBackStack("Coffee Fragment")
            .replace(R.id.container, CoffeeFragment())
            .commit()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.order, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when {
            item.itemId == R.id.action_order -> {
                sendOffer()
                true
            }
            item.itemId == android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun sendOffer(){

        startActivity(Intent(this, OrderActivity::class.java))

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}
