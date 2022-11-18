package com.olympus.athena;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Adapter;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setando a action bar da tela
        Toolbar tbMain = findViewById(R.id.tbMain);
        setSupportActionBar(tbMain);
    }

    public void setActivityTitle(String title) {
        //método para setar o nome da activity
        setTitle(title);
    }
    public void navegarInfoLivro(Integer id){
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

    public void setarFragmentoListaLivro(int id){
        BookListFragment gridViewFragment = BookListFragment.newInstance();
        setFragment(gridViewFragment);
    }
}