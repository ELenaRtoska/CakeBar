package com.example.cakebar.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.cakebar.Data.AppPrefs
import com.example.cakebar.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var mPrefs: AppPrefs
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPrefs = AppPrefs(this)
        cakes.setOnClickListener {
            startActivity(Intent(this, CakesActivity::class.java))
        }

        desserts.setOnClickListener{
            startActivity(Intent(this, DessertsActivity::class.java))
        }

        coffee.setOnClickListener{
            startActivity(Intent(this, CoffeeActivity::class.java))
        }

        drinks.setOnClickListener{
            startActivity(Intent(this, DrinksActivity::class.java))
        }


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


//    private fun openCakesFragment() {
//        title = "Cakes"
//        supportFragmentManager
//            .beginTransaction()
//            .addToBackStack("Cakes Fragment")
//            .replace(R.id.container, CakesFragment())
//            .commit()
//    }
//    private fun openDessertsFragment() {
//        title = "Desserts"
//        supportFragmentManager
//            .beginTransaction()
//            .addToBackStack("")
//            .replace(R.id.container, DessertsFragment())
//            .commit()
//    }
//    private fun openCoffeeFragment() {
//        title = "Coffee"
//        supportFragmentManager
//            .beginTransaction()
//            .addToBackStack("")
//            .replace(R.id.container, CoffeeFragment())
//            .commit()
//    }
//    private fun openDrinksFragment() {
//        title = "Drinks"
//        supportFragmentManager
//            .beginTransaction()
//            .addToBackStack("")
//            .replace(R.id.container, DrinksFragment())
//            .commit()
//    }

}