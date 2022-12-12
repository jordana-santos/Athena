package com.olympus.athena.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InfoViewModel extends AndroidViewModel {
    public InfoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Livro> getBookDetailsLD(String codigoLivro) {
        MutableLiveData<Livro> bookDetailLD = new MutableLiveData<>();

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                AthenaRepository athenaRepository = new AthenaRepository(getApplication());
                Livro livro = athenaRepository.loadBookDetail(codigoLivro);
                bookDetailLD.postValue(livro);
            }
        });
        return bookDetailLD;
    }
}
