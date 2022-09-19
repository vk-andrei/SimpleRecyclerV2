package com.example.simplerecyclerv2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.simplerecyclerv2.databinding.ActivityEditPersonBinding

class EditPersonActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditPersonBinding
    private var indexImage = 0
    private var imageIdRes = R.drawable.man_01
    private val listImages = listOf(
        R.drawable.man_01, R.drawable.man_02, R.drawable.man_03, R.drawable.man_04,
        R.drawable.man_05, R.drawable.man_06, R.drawable.man_07,
        R.drawable.girl_01, R.drawable.girl_02, R.drawable.girl_03, R.drawable.girl_04,
        R.drawable.girl_05, R.drawable.girl_06, R.drawable.girl_07, R.drawable.girl_08
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditPersonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        setListeners()
    }

    private fun initView() {
        binding.ivEditMode.setImageResource(listImages[indexImage])
    }

    private fun setListeners() {
        binding.btnNextImage.setOnClickListener {
            indexImage++
            if (indexImage >= listImages.size) indexImage = 0
            imageIdRes = listImages[indexImage]
            binding.ivEditMode.setImageResource(imageIdRes)
        }

        binding.btnOk.setOnClickListener() {
            binding.apply {
                val person =
                    Person(etName.text.toString(), imageIdRes, etDescription.text.toString())
                val editIntent = Intent().apply {
                    putExtra("person", person)
                }
                setResult(RESULT_OK, editIntent)
                finish()
            }
        }
    }
}