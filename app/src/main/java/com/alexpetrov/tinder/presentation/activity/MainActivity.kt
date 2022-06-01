package com.alexpetrov.tinder.presentation.activity

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alexpetrov.tinder.presentation.utils.AppState
import com.alexpetrov.tinder.presentation.viewmodel.MainViewModel
import com.alexpetrov.tinder.R
import com.alexpetrov.tinder.databinding.ActivityMainBinding
import com.alexpetrov.tinder.data.dto.Image
import com.alexpetrov.tinder.data.dto.Message
import com.alexpetrov.tinder.data.dto.CatRequest
import com.alexpetrov.tinder.presentation.fragment.CatFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var post: Image

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initModel()
        clickListener()
    }

    private fun clickListener() {
        binding.cardLike.setOnClickListener {
            viewModel.postVote(createCat(like))
            viewModel.getData()
        }

        binding.cardDislike.setOnClickListener {
            viewModel.postVote(createCat(dislike))
            viewModel.getData()
        }
    }

    private fun createCat(params: Int): CatRequest {
        return CatRequest(
            post.id,
            id,
            params
        )
    }

    private fun initModel() {
        viewModel = MainViewModel()
        viewModel.liveDataPost.observe(this) { renderData(it) }
        viewModel.liveDataVote.observe(this) { showToast(it) }
        viewModel.getData()
    }

    private fun showToast(messageRequest: Message) {
        Log.d("POST", messageRequest.message)
    }

    private fun renderData(appState: AppState) {
        if (appState is AppState.SuccessMain) {
            post = appState.image[first]
            val uri = Uri.parse(post.url)
            binding.image.setImageURI(uri)
        }
        else if (appState is AppState.Error) {
            Toast.makeText(this, appState.e.message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.tinder_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite -> {
                supportFragmentManager
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.container, CatFragment())
                    .commit()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val first = 0
        private const val id = "from-phone"
        private const val like = 1
        private const val dislike = 0
    }
}