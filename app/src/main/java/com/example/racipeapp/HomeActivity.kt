package com.example.racipeapp

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.racipeapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var rvAdapter: PopularAdapter
    private lateinit var dataList: ArrayList<Recipe>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
        binding.search.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }

        binding.salad.setOnClickListener {
            var myIntent=Intent(this@HomeActivity,CategoryActivity::class.java)
            myIntent.putExtra("TITTLE","Salad")
            myIntent.putExtra("CATEGORY","Salad")
            startActivity(myIntent)
        }
        binding.dish.setOnClickListener {
            var myIntent=Intent(this@HomeActivity,CategoryActivity::class.java)
            myIntent.putExtra("TITTLE","Main Dish")
            myIntent.putExtra("CATEGORY","Dish")
            startActivity(myIntent)
        }
        binding.drinks.setOnClickListener {
            var myIntent=Intent(this@HomeActivity,CategoryActivity::class.java)
            myIntent.putExtra("TITTLE","Drinks")
            myIntent.putExtra("CATEGORY","Drinks")
            startActivity(myIntent)
        }
        binding.dessrts.setOnClickListener {
            var myIntent=Intent(this@HomeActivity,CategoryActivity::class.java)
            myIntent.putExtra("TITTLE","Desserts")
            myIntent.putExtra("CATEGORY","Desserts")
            startActivity(myIntent)
        }

        binding.more.setOnClickListener {
            var dialog=Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.buttom_sheet)
            dialog.show()
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.setGravity(Gravity.BOTTOM)
        }
    }

    private fun setUpRecyclerView() {
        dataList= ArrayList()
        binding.rvPopular.layoutManager=LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val db= Room.databaseBuilder(this@HomeActivity,AppDatabase::class.java, "db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()
        val daoObject=db.getDao()
        val recipes=daoObject.getAll()
        recipes?.forEach {
            it?.let {
                if (it.category?.contains("Popular")==true) {
                    dataList.add(it)
                }
            }
        }

        rvAdapter=PopularAdapter(dataList,this)
        binding.rvPopular.adapter=rvAdapter
    }
}