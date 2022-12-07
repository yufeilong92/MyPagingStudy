package com.example.mypaging

import android.graphics.pdf.PdfDocument.Page
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn

class MyPagingViewModel:ViewModel() {
    fun getData():LiveData<PagingData<DemoBean>>{
        return Pager(PagingConfig(10), initialKey = 0) { MyPagingSource() }
            .flow
            .cachedIn(viewModelScope)
            .asLiveData(viewModelScope.coroutineContext)
    }
}