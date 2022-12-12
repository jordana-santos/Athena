package com.olympus.athena.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;

import kotlinx.coroutines.CoroutineScope;

public class CatViewModel extends AndroidViewModel {

    LiveData<PagingData<Categoria>> booksLd;

    public CatViewModel(@NonNull Application application) {
        super(application);
        AthenaRepository athenaRepository = new AthenaRepository(application);
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this);
        Pager<Integer, Categoria> pager = new Pager(new PagingConfig(10), () -> new CategoriaPagingSource(athenaRepository));
        booksLd = PagingLiveData.cachedIn(PagingLiveData.getLiveData(pager), viewModelScope);
    }

    public LiveData<PagingData<Categoria>> getProductsLd() {
        return booksLd;
    }
}

