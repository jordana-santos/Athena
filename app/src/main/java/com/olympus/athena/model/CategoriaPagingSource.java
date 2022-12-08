package com.olympus.athena.model;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.paging.ListenableFuturePagingSource;
        import androidx.paging.PagingState;

        import com.google.common.util.concurrent.ListenableFuture;
        import com.google.common.util.concurrent.ListeningExecutorService;
        import com.google.common.util.concurrent.MoreExecutors;

        import java.io.FileNotFoundException;
        import java.util.List;
        import java.util.concurrent.Callable;
        import java.util.concurrent.Executors;

    public class CategoriaPagingSource extends ListenableFuturePagingSource<Integer, Categoria> {
    {
    }
    AthenaRepository athenaRepository;

    Integer initialLoadSize = 0;

    public CategoriaPagingSource(AthenaRepository athenaRepository) {
        this.athenaRepository = athenaRepository;
    }

    @Nullable
    @Override
    public Integer getRefreshKey(@NonNull PagingState<Integer, Categoria> pagingState) {
        return null;
    }

    @NonNull
    @Override
    public ListenableFuture<LoadResult<Integer, Categoria>> loadFuture(@NonNull LoadParams<Integer> loadParams) {
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
        ListenableFuture<LoadResult<Integer, Categoria>> lf = service.submit(new Callable<LoadResult<Integer, Categoria >>() {
            @Override
            public LoadResult<Integer, Categoria> call() {
                List<Categoria> categoriaList = null;
                categoriaList = athenaRepository.loadCat(loadParams.getLoadSize(), finalOffSet);
                Integer nextKey = null;
                if(categoriaList.size() >= loadParams.getLoadSize()) {
                    nextKey = finalNextPageNumber + 1;
                }
                return new LoadResult.Page<Integer, Categoria>(categoriaList,
                        null,
                        nextKey);

            }
        });

        return lf;
    }
}