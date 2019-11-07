package com.chepsi.navigationuitutorial

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView

class MainActivity: AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var toggle: ActionBarDrawerToggle
    private val multiStartNavigationUi = MultiStartNavigationUI(listOf(
        R.id.homeFragment,
        R.id.aboutFragment,
        R.id.settingsFragment
    ))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.main_toolbar)
        val drawer = findViewById<DrawerLayout>(R.id.main_drawer)
        val host : NavHostFragment = supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment? ?: return
        navController = host.navController

        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toggle = ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close)
        toggle.let { drawer.addDrawerListener(it) }
        toggle.syncState()

        multiStartNavigationUi.setupActionBarWithNavController(this, navController, drawer)
        NavigationUI.setupWithNavController(findViewById<NavigationView>(R.id.main_navigation_view), navController)
    }

    override fun onSupportNavigateUp() = multiStartNavigationUi.navigateUp(findViewById(R.id.main_drawer), navController)

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem):Boolean {
        when (item.itemId) {
            R.id.aboutFragment -> {
                navController.navigate(R.id.aboutFragment)
                return true
            }
        }
        return false
    }

    override fun onBackPressed() {
        if (!multiStartNavigationUi.onBackPressed(this, navController)) {
            super.onBackPressed()
        }
    }
}