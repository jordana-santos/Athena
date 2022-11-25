package com.olympus.athena.model;

import androidx.lifecycle.ViewModel;

import com.olympus.athena.R;
import com.olympus.athena.model.Categoria;
import com.olympus.athena.model.Livro;

import java.util.ArrayList;
import java.util.List;

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
