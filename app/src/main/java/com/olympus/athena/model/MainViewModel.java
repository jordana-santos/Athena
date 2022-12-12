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

import com.olympus.athena.R;

import kotlinx.coroutines.CoroutineScope;

public class MainViewModel extends AndroidViewModel {
    AthenaRepository athenaRepository;
    int opSelected = R.id.opHome;
    LiveData<PagingData<Categoria>> catLd;
    LiveData<PagingData<Livro>> booksLd;
    LiveData<PagingData<Livro>> booksFavLd;
    LiveData<PagingData<Livro>> booksHistLd;

    public MainViewModel(@NonNull Application application) {
        super(application);
        athenaRepository = new AthenaRepository(application);

    }


    public int getOpSelected() {
        return opSelected;
    }

    public void setOpSelected(int opSelected) {
        this.opSelected = opSelected;
    }


    public LiveData<PagingData<Categoria>> getCatLd(){

        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this);
        Pager<Integer, Categoria> pager = new Pager(new PagingConfig(10), () -> new CategoriaPagingSource(athenaRepository));
        catLd = PagingLiveData.cachedIn(PagingLiveData.getLiveData(pager), viewModelScope);
        return catLd;
    }

    public LiveData<PagingData<Livro>> getBooksLd(String id){

        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this);
        Pager<Integer, Livro> pager = new Pager(new PagingConfig(10), () -> new LivroPesquisaPagingSource(athenaRepository, id));
        booksLd = PagingLiveData.cachedIn(PagingLiveData.getLiveData(pager), viewModelScope);
        return booksLd;
    }

    public LiveData<PagingData<Livro>> getBooksFavLd(){

        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this);
        Pager<Integer, Livro> pager = new Pager(new PagingConfig(10), () -> new FavLivrosPagingSource(athenaRepository));
        booksFavLd = PagingLiveData.cachedIn(PagingLiveData.getLiveData(pager), viewModelScope);
        return booksFavLd;
    }

    public LiveData<PagingData<Livro>> getBooksHistLd(){

        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this);
        Pager<Integer, Livro> pager = new Pager(new PagingConfig(10), () -> new HistLivroPagingSource(athenaRepository));
        booksHistLd = PagingLiveData.cachedIn(PagingLiveData.getLiveData(pager), viewModelScope);
        return booksHistLd;
    }

}
