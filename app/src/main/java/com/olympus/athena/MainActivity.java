package com.olympus.athena;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

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
}