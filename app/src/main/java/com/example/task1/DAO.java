package com.example.task1;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface DAO {

    @Insert
    void insertAll(Book...books);
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Book...books);

    @Query("SELECT * FROM BOOK_TABLE")
   LiveData<List<Book> > getAllBooks();


}
