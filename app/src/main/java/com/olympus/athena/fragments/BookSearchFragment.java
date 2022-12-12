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

import com.olympus.athena.adapter.ListaLivroAdapter;
import com.olympus.athena.adapter.LivroComparator;
import com.olympus.athena.model.Livro;
import com.olympus.athena.model.MainViewModel;
import com.olympus.athena.R;
import com.olympus.athena.activity.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookSearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PESQUISA_USUARIO = "pesquisaUsuario";

    // TODO: Rename and change types of parameters
    private String pesquisaUsuario;

    public BookSearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param pesquisaUsuario Parameter 1.
     * @return A new instance of fragment BookListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BookSearchFragment newInstance(String pesquisaUsuario) {
        BookSearchFragment fragment = new BookSearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PESQUISA_USUARIO, pesquisaUsuario);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pesquisaUsuario = getArguments().getString(ARG_PESQUISA_USUARIO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_livro, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainViewModel mainViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
        LiveData<PagingData<Livro>> livrosLd = mainViewModel.getBooksLd(pesquisaUsuario);
        ListaLivroAdapter adapter = new ListaLivroAdapter((MainActivity) getActivity(), new LivroComparator());

        RecyclerView rv = view.findViewById(R.id.rvListFrag);
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
                adapter.submitData(getLifecycle(),livroPagingData);
            }
        });


    }
}