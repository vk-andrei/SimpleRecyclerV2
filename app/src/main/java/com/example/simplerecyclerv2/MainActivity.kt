package com.example.simplerecyclerv2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import com.example.simplerecyclerv2.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var personAdapter: PersonAdapter = PersonAdapter()
    //private lateinit var personNames: Array<String>
    //private lateinit var personImgIds: IntArray
    //private var currentPerson : Person? = null

    private var editModeLauncher: ActivityResultLauncher<Intent>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //initData()
        initAdapter()
        setListeners()

        editModeLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK && it.data != null) {
                    val currentPerson = it.data!!.getParcelableExtra<Person>("person")
                    Log.d("TAG", "CurPersonIMG: $currentPerson")
                    personAdapter.addPerson(currentPerson as Person)
                }
            }
    }

    /*   private fun initData() {
        personNames = resources.getStringArray(R.array.personsNames)
        personImgIds = intArrayOf(
            R.drawable.man_01, R.drawable.man_02, R.drawable.man_03, R.drawable.man_04,
            R.drawable.girl_01, R.drawable.girl_02, R.drawable.girl_03, R.drawable.girl_04
        )
    }*/

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_search -> {
                Snackbar.make(binding.root, "SEARCH", Snackbar.LENGTH_SHORT).show()
            }
            R.id.app_bar_remove_all -> {
                Snackbar.make(binding.root, "REMOVE ALL", Snackbar.LENGTH_SHORT).show()
            }
            R.id.app_bar_exit -> {finish()}
        }
        return true
    }

    private fun initAdapter() {
        binding.rvFaces.layoutManager = GridLayoutManager(this@MainActivity, 3)
        binding.rvFaces.adapter = personAdapter
    }

    private fun setListeners() {
        //var index = 0
        binding.btnAddItem.setOnClickListener {
            val i = Intent(this@MainActivity, EditPersonActivity::class.java)
            editModeLauncher?.launch(i)

            /* personAdapter.addPerson(Person(personNames[index], personImgIds[index]))
                binding.rvFaces.scrollToPosition(personAdapter.itemCount - 1)
                index++
                if (index > 7) index = 0*/
        }
    }

}