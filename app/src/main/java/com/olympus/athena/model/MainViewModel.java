package com.olympus.athena.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;

import com.olympus.athena.R;
import com.olympus.athena.model.Categoria;
import com.olympus.athena.model.Livro;

import java.util.ArrayList;
import java.util.List;

import kotlinx.coroutines.CoroutineScope;

public class MainViewModel extends ViewModel {

    int opSelected = R.id.opHome;

    public int getOpSelected() {
        return opSelected;
    }

    public void setOpSelected(int opSelected) {
        this.opSelected = opSelected;
    }

    public List<Categoria> pegarCategorias(){
        List<Categoria> ListaCategorias = new ArrayList<>();

        Categoria c1 = new Categoria(0, "Drama", R.mipmap.ic_drama);
        Categoria c2 = new Categoria(1, "Aventura", R.mipmap.ic_drama);

        ListaCategorias.add(c1);
        ListaCategorias.add(c2);

        return ListaCategorias;
    }

    LiveData<PagingData<Categoria>> booksLd;
    public void pegarCategoriasServer(@NonNull Application application){
        AthenaRepository athenaRepository = new AthenaRepository(application);
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this);
        Pager<Integer, Categoria> pager = new Pager(new PagingConfig(10), () -> new CategoriaPagingSource(athenaRepository));
        booksLd = PagingLiveData.cachedIn(PagingLiveData.getLiveData(pager), viewModelScope);
    }

    public LiveData<PagingData<Categoria>> getProductsLd() {
        return booksLd;
    }

    public List<Livro> pegarListaLivros(){
        List<Livro> ListaLivros = new ArrayList<>();

        Livro livro1 = new Livro(0, R.drawable.thetimeofcomtempt, "The Witcher: Blood of Elvens", "Aventura", "Muitas coisas", "5.00");
        Livro livro2 = new Livro(1, R.drawable.thetimeofcomtempt, "The Witcher: The Time of Contempt", "Aventura", "Mais coisas", "5.00");
        Livro livro3 = new Livro(2, R.drawable.thetimeofcomtempt, "The Witcher: Sword of Destiny", "Aventura", "More things", "5.00");

        ListaLivros.add(livro1);
        ListaLivros.add(livro2);
        ListaLivros.add(livro3);

        return ListaLivros;
    }

}
