package com.example.mypaging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mypaging.databinding.ActivityMainBinding
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding
    private val viewModel by lazy { ViewModelProvider(this).get(MyPagingViewModel::class.java) }
    private var mAdapter: PagingAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        initAdapter()
        mainBinding.btnRefresh.setOnClickListener {
            addRefresh()
        }

    }

    private fun addRefresh() {
        viewModel.getData().observe(this, Observer { data ->
            lifecycleScope.launch {
                mAdapter?.let {
                    it.submitData(data)
                }
            }
        })
    }

    private fun initAdapter() {

        val gl = GridLayoutManager(this, GridLayoutManager.VERTICAL)
        mAdapter = PagingAdapter(this)
        mainBinding.rlvContent.apply {
            layoutManager = gl
            adapter = mAdapter
        }
    }

}