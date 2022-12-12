package com.olympus.athena.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.olympus.athena.fragments.BookCatFragment;
import com.olympus.athena.fragments.BookFavFragment;
import com.olympus.athena.fragments.BookHistoryFragment;
import com.olympus.athena.fragments.BookListFragment;
import com.olympus.athena.fragments.PerfilFragment;
import com.olympus.athena.model.MainViewModel;
import com.olympus.athena.R;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setando a action bar da tela
        Toolbar tbMain = findViewById(R.id.tbMain);
        setSupportActionBar(tbMain);

        final MainViewModel vm = new ViewModelProvider(this).get(MainViewModel.class);


        bottomNavigationView = findViewById(R.id.bnvMain);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                vm.setOpSelected(item.getItemId());
                switch (item.getItemId()) {
                    case R.id.opHome:
                        BookCatFragment bookCat = BookCatFragment.newInstance();
                        setFragment(bookCat);
                        setActivityTitle("Categorias");
                        break;
                    case R.id.opFav:
                        BookFavFragment bookFav = BookFavFragment.newInstance();
                        setFragment(bookFav);
                        setActivityTitle("Favoritos");
                        break;
                    case R.id.opHistory:
                        BookHistoryFragment bookHistory = BookHistoryFragment.newInstance();
                        setFragment(bookHistory);
                        setActivityTitle("Histórico");
                        break;
                    case R.id.opUsuario:
                        PerfilFragment perfilFragment = PerfilFragment.newInstance();
                        setFragment(perfilFragment);
                        setActivityTitle("Perfil");
                        break;
                }
                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.opHome);

    }

    public void setActivityTitle(String title) {
        //método para setar o nome da activity
        setTitle(title);
    }

    public void navegarInfoLivro(String id){
        Intent i = new Intent(MainActivity.this, InfoLivroActivity.class);
        i.putExtra("id", id);
        startActivity(i);
    }

    void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flMain, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void setarFragmentoCatLivro(){
        BookCatFragment gridViewFragment = BookCatFragment.newInstance();
        setFragment(gridViewFragment);
    }

    public void setarFragmentoListaLivro(String id ){
        BookListFragment linearViewFragment = BookListFragment.newInstance(id);
        setFragment(linearViewFragment);
    }

}