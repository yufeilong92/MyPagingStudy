package com.example.mypaging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState

class MyPagingSource: PagingSource<Int, DemoBean>() {
    override fun getRefreshKey(state: PagingState<Int, DemoBean>): Int? {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DemoBean> {
        //当前页码
        val currentPage = params.key ?: 0

        //上一页
        val preKey=if (currentPage==0) null else currentPage
        //下一页
        val nextKey=currentPage+1

        val datalist=createData(currentPage,params.loadSize)

        if (nextKey>10){
            return LoadResult .Page(
                datalist,null,null
            )
        }
        return LoadResult.Page(
            datalist,preKey,nextKey
        )
    }
    private var mItemCount=0
    private fun createData(currentPage: Int, pageSize: Int): MutableList<DemoBean> {
        Log.e("zh", "createData: currentPage = ${currentPage} mItemCount = $mItemCount  pageSize = ${pageSize}" )
        val list= mutableListOf<DemoBean>()
        for (item in 0 until 10){
            list.add(DemoBean("$mItemCount"))
            mItemCount++
        }
        return list
    }
}