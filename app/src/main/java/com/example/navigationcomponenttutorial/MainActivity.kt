package com.example.navigationcomponenttutorial

import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.annotation.GravityInt
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    lateinit var drawerLayout: DrawerLayout
    lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var listener: NavController.OnDestinationChangedListener

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController=findNavController(R.id.fragmentContainerView)
        drawerLayout=findViewById(R.id.drawer_layout)
        my_nav_menu.setupWithNavController(navController)

        appBarConfiguration=AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)

        my_nav_menu.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_firstFragment -> Toast.makeText(applicationContext, "Home Clicked", Toast.LENGTH_SHORT).show()
                R.id.menu_secondFragment -> {
                    drawerLayout.closeDrawer(Gravity.LEFT)
                    navController.navigate(R.id.action_oneFragment_to_secondFragment)
                }
            }
            true
        }

        listener = NavController.OnDestinationChangedListener { controller, destination, arguments ->
            if(destination.id == R.id.oneFragment){
                supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.purple_500)))
            } else if (destination.id == R.id.secondFragment){
                supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.teal_200)))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(listener)
    }

    override fun onPause() {
        super.onPause()
        navController.removeOnDestinationChangedListener(listener)
    }

    override fun onSupportNavigateUp(): Boolean {
        val naviController = findNavController(R.id.fragmentContainerView)
        return naviController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}