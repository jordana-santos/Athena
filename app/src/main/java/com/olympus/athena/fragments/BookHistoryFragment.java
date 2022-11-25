package com.olympus.athena.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.olympus.athena.R;
import com.olympus.athena.activity.MainActivity;
import com.olympus.athena.adapter.ListaLivroAdapter;
import com.olympus.athena.model.Livro;
import com.olympus.athena.model.MainViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookHistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookHistoryFragment extends Fragment {

    public BookHistoryFragment() {
        // Required empty public constructor
    }

    public static BookHistoryFragment newInstance() {
        BookHistoryFragment fragment = new BookHistoryFragment();
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
        return inflater.inflate(R.layout.fragment_historico, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainViewModel mainViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
        List<Livro> livros = mainViewModel.pegarListaLivros();
        ListaLivroAdapter adapter = new ListaLivroAdapter((MainActivity) getActivity(),livros);

        RecyclerView rv = view.findViewById(R.id.rvListFrag);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}