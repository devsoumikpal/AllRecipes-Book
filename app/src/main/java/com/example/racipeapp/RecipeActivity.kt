package com.example.racipeapp

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import com.bumptech.glide.Glide
import com.example.racipeapp.databinding.ActivityRecipeBinding

class RecipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeBinding
    var imgCrop=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Properly initialize the binding
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Image loading and other setup
        Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemimg)
        binding.reccipetittle.text=intent.getStringExtra("tittle")
        binding.stepData.text=intent.getStringExtra("des")

        var time= intent.getStringExtra("ing")?.split("\n".toRegex())?.dropLastWhile { it.isEmpty() }
            ?.toTypedArray()
        binding.iteTime.text=time?.get(0)

        for (i in 1 until time!!.size){
            binding.ingData.text = "${binding.ingData.text} 🟢 ${time[i]}\n"

        }

        // Set initial visibility
        binding.ingScroll.visibility=View.VISIBLE
        binding.stepScroll.visibility=View.GONE

//        //Set Buttom Visibility
//        binding.stepsbtm.visibility=View.GONE
//        binding.stepsbtm.background=null

        binding.ingrediantsbtm.setOnClickListener {
            binding.ingrediantsbtm.setBackgroundResource(R.drawable.btn_ing)
            binding.ingrediantsbtm.setTextColor(getColor(R.color.white))
            binding.stepsbtm.setTextColor(getColor(R.color.black))
            binding.stepsbtm.background = null

            // Toggle scroll views
            binding.ingScroll.visibility = View.VISIBLE
            binding.stepScroll.visibility = View.GONE
        }


        binding.stepsbtm.setOnClickListener {
            binding.stepsbtm.setBackgroundResource(R.drawable.btn_ing)
            binding.stepsbtm.setTextColor(getColor(R.color.white))
            binding.ingrediantsbtm.setTextColor(getColor(R.color.black))
            binding.ingrediantsbtm.background = null

            // Toggle scroll views
            binding.stepScroll.visibility = View.VISIBLE
            binding.ingScroll.visibility = View.GONE
        }

        binding.backbtm.setOnClickListener {
            finish()
        }
        binding.fullscreen.setOnClickListener {
            if (imgCrop){
                binding.itemimg.scaleType=ImageView.ScaleType.FIT_CENTER
                Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemimg)
                binding.shade.visibility=View.GONE
                binding.fullscreen.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP)
                imgCrop=!imgCrop
            }else{
                binding.itemimg.scaleType=ImageView.ScaleType.CENTER_CROP
                Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemimg)
                binding.shade.visibility=View.VISIBLE
                binding.fullscreen.setColorFilter(null)
                imgCrop=!imgCrop
            }
        }

        // Inside RecipeActivity.kt
        binding.goUp.setOnClickListener {
            // You can either trigger the MotionLayout transition or perform other actions
            val motionLayout = findViewById<MotionLayout>(R.id.main)
            motionLayout.transitionToStart()
        }

    }
}