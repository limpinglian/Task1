package com.example.task1;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;

import java.util.List;

public class Repository {
    private DAO dao;
    private LiveData<List<Book>> allBooks;

    Repository(Application application) {
        AppDatabase db=AppDatabase.getInstance(application);
        dao=db.dao();
        allBooks=dao.getAllBooks();

    }
    public void insert(Book books){
        new InsertBookAsyncTask(dao).execute(books);
    }
    public void update(Book books){ new UpdateBookAsyncTask(dao).execute(books);
    }

    public LiveData<List<Book>> getAllBooks() {
        return allBooks;
    }

    private static class InsertBookAsyncTask extends AsyncTask<Book,Void,Void>{
        private DAO dao;

        private InsertBookAsyncTask(DAO dao){
            this.dao=dao;
        }
        protected Void doInBackground(Book...books){
            dao.insertAll(books[0]);
            return null;
        }
    }
    private static class UpdateBookAsyncTask extends AsyncTask<Book,Void,Void>{
        private DAO dao;

        private UpdateBookAsyncTask(DAO dao){
            this.dao=dao;
        }
        protected Void doInBackground(Book...books){
            dao.update(books[0]);
            return null;
        }
    }
}
