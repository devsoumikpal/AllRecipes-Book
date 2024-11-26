package com.example.racipeapp


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.racipeapp.databinding.RvSearchBinding

class SearchAdapter(var dataList: ArrayList<Recipe>, var context: Activity):
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: RvSearchBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=RvSearchBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  dataList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filterList(filterList:ArrayList<Recipe>){
        dataList=filterList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem=dataList[position]
        holder.binding.searchtv.text=currentItem.tittle
        Glide.with(context).load(currentItem.img).into(holder.binding.searchimg)

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