

package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.myapplication.fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    // Initialise the DrawerLayout, NavigationView and ToggleBar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarToggle: ActionBarDrawerToggle
    private lateinit var navView: NavigationView
    private lateinit var bottomNavigationView: BottomNavigationView
    private val profileFragment = Profile()
    private val peopleFragment = People()
    private val settingFragment = Settings()
    private val dashboardFragment = Dashboard()
    private val notificationFragment = Notification()
    private val homeFragment = Home()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // set text view and button
        setContentView(R.layout.activity_main)

        // bottom navigation bar
        replaceFragment(dashboardFragment)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.dashboard -> replaceFragment(dashboardFragment)
                R.id.home -> replaceFragment(homeFragment)
                R.id.notification -> replaceFragment(notificationFragment)
            }
            true
        }
        // drawer menu part 2
        // Call findViewById on the DrawerLayout
        var actionBar = supportActionBar
        actionBar!!.title = "Dalin Assignment"
        actionBar.setDisplayHomeAsUpEnabled(true)
        drawerLayout = findViewById(R.id.drawerLayout)

        // Pass the ActionBarToggle action into the drawerListener
        actionBarToggle = ActionBarDrawerToggle(this, drawerLayout, 0, 0)
        drawerLayout.addDrawerListener(actionBarToggle)

        // Display the hamburger icon to launch the drawer
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Call syncState() on the action bar so it'll automatically change to the back button when the drawer layout is open
        actionBarToggle.syncState()


        // Call findViewById on the NavigationView
        navView = findViewById(R.id.navView)

        // Call setNavigationItemSelectedListener on the NavigationView to detect when items are clicked
        navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.myProfile -> replaceFragment(profileFragment)
                R.id.people -> replaceFragment(peopleFragment)
                R.id.settings -> replaceFragment(settingFragment)
            }
            true
            // close drawer
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    // drawer menu part 3
    // override the onSupportNavigateUp() function to launch the Drawer when the hamburger icon is clicked
    override fun onSupportNavigateUp(): Boolean {
        this.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    // override the onBackPressed() function to close the Drawer when the back button is clicked
    override fun onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item != null)
            when(item.itemId){
                R.id.myProfile -> {
                    Toast.makeText(this,"What is Kotlin ?\n" +
                            "Kotlin is a statically-typed programming language for modern multi-platform applications.  Kotlin was developed by JetBrains, a company acclaimed for developing tools for professionals. The foremost goal of Kotlin is to provide a concise, productive and safer alternative to Java. The most common areas to use Kotlin are\n" +
                            "Building server-side code\n" +
                            "Building mobile applications that run on Android devices",Toast.LENGTH_LONG).show()
                }
                R.id.info -> {
                    Toast.makeText(this,"GitHub.com/alirezsbashi",Toast.LENGTH_SHORT).show()
                }
                android.R.id.home -> {
                    drawerLayout.openDrawer(GravityCompat.START)
                    true
                }
            }
        return true
    }



    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}