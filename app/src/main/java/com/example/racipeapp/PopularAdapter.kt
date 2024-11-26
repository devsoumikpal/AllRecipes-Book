package com.example.racipeapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.racipeapp.databinding.PopularRvItemsBinding

class PopularAdapter(var dataList: ArrayList<Recipe>, var context:Context):RecyclerView.Adapter<PopularAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: PopularRvItemsBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding=PopularRvItemsBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return dataList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var currentItem=dataList[position]

        Glide.with(context).load(currentItem.img).into(holder.binding.popularImg)
        holder.binding.popularTxt.text=currentItem.tittle
        var time=currentItem.ing.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        holder.binding.popularTime.text=time.get(0)

        holder.itemView.setOnClickListener {
            var intent= Intent(context, RecipeActivity::class.java)
            intent.putExtra("img", currentItem.img)
            intent.putExtra("tittle", currentItem.tittle)
            intent.putExtra("des", currentItem.des)
            intent.putExtra("ing", currentItem.ing)
            intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }
}