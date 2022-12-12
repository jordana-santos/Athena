package com.olympus.athena.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagingData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.olympus.athena.R;
import com.olympus.athena.activity.MainActivity;
import com.olympus.athena.adapter.ListaLivroAdapter;
import com.olympus.athena.adapter.LivroComparator;
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
        LiveData<PagingData<Livro>> livrosLd = mainViewModel.getBooksHistLd();
        ListaLivroAdapter adapter = new ListaLivroAdapter((MainActivity) getActivity(), new LivroComparator());

        RecyclerView rv = view.findViewById(R.id.rvHist);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        livrosLd.observe((MainActivity) getActivity(), new Observer<PagingData<Livro>>() {
            /**
             * Esse método é chamado sempre que uma nova página de livros é entregue à app pelo
             * servidor web.
             * @param livroPagingData contém uma página de livros
             */
            @Override
            public void onChanged(PagingData<Livro> livroPagingData) {

                // Adiciona a nova página de produtos ao Adapter do RecycleView. Isso faz com que
                // novos produtos apareçam no RecycleView.
                adapter.submitData(getLifecycle(), livroPagingData);
            }
        });
    }
}