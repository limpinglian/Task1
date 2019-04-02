package com.example.task1;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Update;
import android.support.annotation.NonNull;

import java.util.List;

public class BookViewModel extends AndroidViewModel {
    private Respiratory respiratory;
    private LiveData<List<Book>>allBooks;
    public BookViewModel(@NonNull Application application) {
        super(application);
        respiratory=new Respiratory(application);
        allBooks=respiratory.getAllBooks();
    }
    public void insert(Book books){
        respiratory.insert(books);
    }

    public void update(Book books){
        respiratory.update(books);
    }
    public LiveData<List<Book>>getAllBooks(){
        return allBooks;
    }

}
