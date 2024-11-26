package com.example.racipeapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.racipeapp.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var rvAdapter: SearchAdapter
    private lateinit var dataList: ArrayList<Recipe>
    private var recipes: List<Recipe?> = listOf()

    @SuppressLint("ClickableViewAccessibility", "ServiceCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        binding=ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.search.requestFocus()

        var db= Room.databaseBuilder(this@SearchActivity,AppDatabase::class.java, "db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()
        var daoObject=db.getDao()
        recipes = daoObject.getAll()

        setUpRecyclerView()

        binding.goBackHome.setOnClickListener {
            finish()
        }

        binding.search.addTextChangedListener(object :TextWatcher{

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //no implementation
            }


            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString()!=""){
                    filterData(s.toString())
                }else{
                    setUpRecyclerView()
                }
            }


            override fun afterTextChanged(s: Editable?) {
                //no implementation
            }

        })

        binding.rvSearch.setOnTouchListener { v, event ->
            var imm=getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

            v.windowToken.let {
                imm.hideSoftInputFromWindow(it,0)
            }

            false
        }
    }

    private fun filterData(filterText: String) {
        var filterData=ArrayList<Recipe>()
        for (recipe in recipes.indices){
            recipe?.let {
                if (recipes[recipe]!!.tittle.lowercase().contains(filterText.lowercase())) {
                    filterData.add(recipes[recipe]!!)
                }
            }

        }
        rvAdapter.filterList(filterList = filterData)
    }

    private fun setUpRecyclerView() {
        dataList= ArrayList()
        recipes.forEach { recipe ->
            recipe?.let {
                if (it.category?.contains("Popular")==true) {
                    dataList.add(it)
                }
            }
        }

        for (i in recipes!!.indices) {

        }

        rvAdapter=SearchAdapter(dataList,this)
        binding.rvSearch.layoutManager=LinearLayoutManager(this)
        binding.rvSearch.adapter=rvAdapter
    }
}