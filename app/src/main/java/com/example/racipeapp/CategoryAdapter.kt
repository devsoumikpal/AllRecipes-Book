package com.example.racipeapp

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.racipeapp.databinding.CategoryRvBinding

class CategoryAdapter(var dataList: ArrayList<Recipe>, var context: Activity): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: CategoryRvBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding= CategoryRvBinding.inflate(LayoutInflater.from(context),parent, false)
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int {
        return dataList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem=dataList[position]
        Glide.with(context).load(currentItem.img).into(holder.binding.categoryImg)
        holder.binding.categoryTittle.text=currentItem.tittle
        var temp=currentItem.ing.split("\n").dropLastWhile { it.isEmpty() }.toTypedArray()

        holder.binding.categoryTime.text=temp[0]
        holder.binding.goNext.setOnClickListener {
            var intent=Intent(context, RecipeActivity::class.java)
            intent.putExtra("img", currentItem.img)
            intent.putExtra("tittle", currentItem.tittle)
            intent.putExtra("des", currentItem.des)
            intent.putExtra("ing", currentItem.ing)
            intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK

            context.startActivity(intent)
        }
    }
}