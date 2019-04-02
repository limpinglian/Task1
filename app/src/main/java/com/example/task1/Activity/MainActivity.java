package com.example.task1.Activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.task1.Book;
import com.example.task1.BookViewModel;
import com.example.task1.R;
import com.example.task1.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_BOOK_REQUEST=1;

    private BookViewModel bookViewModel;
    private RecyclerView mRecyclerView;
    private ArrayList<Book> mBookData;
    private RecyclerViewAdapter mAdapter;
    List<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fabAdd=findViewById(R.id.fab);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, AddBookActivity.class);
                startActivityForResult(intent,1);
            }
        });
        bookViewModel= ViewModelProviders.of(this).get(BookViewModel.class);
        mRecyclerView = findViewById(R.id.recycler);

        // Set the Layout Manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        // Initialize the ArrayList that will contain the data.
        mBookData = new ArrayList<>();

        // Initialize the adapter and set it to the RecyclerView.
        mAdapter = new RecyclerViewAdapter(getApplicationContext(), books);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);

        bookViewModel.getAllBooks().observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(@Nullable List<Book> books) {
                mAdapter.setBook(books);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==ADD_BOOK_REQUEST&&resultCode==RESULT_OK){
            String id=data.getStringExtra(AddBookActivity.EXTRA_ID);
            String title=data.getStringExtra(AddBookActivity.EXTRA_TITLE);
            String subtitle=data.getStringExtra(AddBookActivity.EXTRA_SUBTITLE);
            String description=data.getStringExtra(AddBookActivity.EXTRA_DESCRIPTION);
            String url=data.getStringExtra(AddBookActivity.EXTRA_URL);

            Book book=new Book(title,subtitle,description,url);
            bookViewModel.insert(book);

            Toast.makeText(this,"Book Saved!",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Book Not Saved!",Toast.LENGTH_SHORT).show();
        }
    }
}








