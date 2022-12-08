package com.olympus.athena.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagingData;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.olympus.athena.adapter.CategoriaComparator;
import com.olympus.athena.model.CatViewModel;
import com.olympus.athena.model.Categoria;
import com.olympus.athena.adapter.CategoriaAdapter;
import com.olympus.athena.model.MainViewModel;
import com.olympus.athena.R;
import com.olympus.athena.activity.MainActivity;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookCatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookCatFragment extends Fragment {

    public BookCatFragment() {
        // Required empty public constructor
    }

    public static BookCatFragment newInstance() {
        BookCatFragment fragment = new BookCatFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categoria, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //MainViewModel mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        CatViewModel mainViewModel = new ViewModelProvider(this).get(CatViewModel.class);

        // TODO erro não sei pq
        //List<Categoria> categorias = mainViewModel.pegarCategoriasServer();
        LiveData<PagingData<Categoria>> categorias = mainViewModel.getProductsLd();
        CategoriaAdapter adapter = new CategoriaAdapter((MainActivity) getActivity(), categorias);

        RecyclerView rv = view.findViewById(R.id.rvCatFrag);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }
}