package com.example.racipeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.racipeapp.databinding.ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {
    private lateinit var rvAdapter: CategoryAdapter
    private lateinit var dataList: ArrayList<Recipe>
    private val binding by lazy {
        ActivityCategoryBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.title.text = intent.getStringExtra("TITTLE") ?: "Category"


        binding.goBackHome.setOnClickListener{
            finish()
        }
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        dataList= ArrayList()
        binding.categoryRv.layoutManager=LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val db= Room.databaseBuilder(this@CategoryActivity,AppDatabase::class.java, "db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()
        val daoObject=db.getDao()
        val recipes=daoObject.getAll()
        recipes?.forEach {
            it?.let {
                if (intent.getStringExtra("CATEGORY")?.let { it1 -> it.category?.contains(it1) } ==true) {
                    dataList.add(it)
                }
            }
        }

        rvAdapter=CategoryAdapter(dataList,this)
        binding.categoryRv.adapter=rvAdapter
    }
}