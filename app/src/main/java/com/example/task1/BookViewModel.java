package com.example.task1;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class BookViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<Book>>allBooks;
    public BookViewModel(@NonNull Application application) {
        super(application);
        repository =new Repository(application);
        allBooks= repository.getAllBooks();
    }
    public void insert(Book books){
        repository.insert(books);
    }

    public void update(Book books){
        repository.update(books);
    }
    public LiveData<List<Book>>getAllBooks(){
        return allBooks;
    }

}
