package com.example.bitebyte

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.bitebyte.databinding.ActivityMainBinding
import com.example.bitebyte.utils.SessionManager
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sessionManager = SessionManager(this)

        val fragmentManager = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = fragmentManager.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.splashFragment -> binding.bottomNavigationView.visibility = View.GONE
                R.id.onBoardingFragment -> binding.bottomNavigationView.visibility = View.GONE
                R.id.loginFragment -> binding.bottomNavigationView.visibility = View.GONE
                R.id.registerFragment -> binding.bottomNavigationView.visibility = View.GONE
                R.id.questionStartFragment -> binding.bottomNavigationView.visibility = View.GONE
                R.id.questionOneFragment -> binding.bottomNavigationView.visibility = View.GONE
                R.id.questionTwoFragment -> binding.bottomNavigationView.visibility = View.GONE
                R.id.questionThreeFragment -> binding.bottomNavigationView.visibility = View.GONE
                R.id.generateFragment -> binding.bottomNavigationView.visibility = View.GONE
                R.id.questionFourFragment -> binding.bottomNavigationView.visibility = View.GONE
                R.id.detailRecipeFragment -> binding.bottomNavigationView.visibility = View.GONE
                R.id.premiumFragment -> binding.bottomNavigationView.visibility = View.GONE
                R.id.editAccountFragment -> binding.bottomNavigationView.visibility = View.GONE
                R.id.shoppingListFragment -> binding.bottomNavigationView.visibility = View.GONE
                else -> binding.bottomNavigationView.visibility = View.VISIBLE
            }
        }

        binding.bottomNavigationView.setupWithNavController(navController)

        if(sessionManager.getToken() != null){
            fragmentManager.navController.popBackStack()
            if(sessionManager.getFillInput() != null){
                fragmentManager.navController.navigate(R.id.homeFragment)
            }else{
                fragmentManager.navController.navigate(R.id.questionStartFragment)
            }
        }
    }
}