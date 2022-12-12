package com.olympus.athena.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.ListenableFuturePagingSource;
import androidx.paging.PagingState;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

public class LivroPesquisaPagingSource extends ListenableFuturePagingSource<Integer, Livro> {
    String pesquisaUsuario;
    AthenaRepository athenaRepository;

    Integer initialLoadSize = 0;

    public LivroPesquisaPagingSource(AthenaRepository athenaRepository, String pesquisaUsuario) {
        this.athenaRepository = athenaRepository;
        this.pesquisaUsuario = pesquisaUsuario;
    }

    @Nullable
    @Override
    public Integer getRefreshKey(@NonNull PagingState<Integer, Livro> pagingState) {
        return null;
    }

    @NonNull
    @Override
    public ListenableFuture<LoadResult<Integer, Livro>> loadFuture(@NonNull LoadParams<Integer> loadParams) {
        Integer nextPageNumber = loadParams.getKey();
        if (nextPageNumber == null) {
            nextPageNumber = 1;
            initialLoadSize = loadParams.getLoadSize();
        }

        Integer offSet = 0;
        if(nextPageNumber == 2) {
            offSet = initialLoadSize;
        }
        else {
            offSet = ((nextPageNumber - 1) * loadParams.getLoadSize()) + (initialLoadSize - loadParams.getLoadSize());
        }

        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newSingleThreadExecutor());
        Integer finalOffSet = offSet;
        Integer finalNextPageNumber = nextPageNumber;
        ListenableFuture<LoadResult<Integer, Livro>> lf = service.submit(new Callable<LoadResult<Integer, Livro >>() {
            @Override
            public LoadResult<Integer, Livro> call() {
                List<Livro> booksList = null;
                booksList = athenaRepository.loadBooks(loadParams.getLoadSize(), finalOffSet, pesquisaUsuario);
                Integer nextKey = null;
                if(booksList.size() >= loadParams.getLoadSize()) {
                    nextKey = finalNextPageNumber + 1;
                }
                return new LoadResult.Page<Integer, Livro>(booksList,
                        null,
                        nextKey);

            }
        });

        return lf;
    }
}