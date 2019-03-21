package com.example.task1;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ArrayList<Book> mBookData;
    private RecyclerViewAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler);

        // Set the Layout Manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the ArrayList that will contain the data.
        mBookData = new ArrayList<>();

        // Initialize the adapter and set it to the RecyclerView.
        mAdapter = new RecyclerViewAdapter(this, mBookData);
        mRecyclerView.setAdapter(mAdapter);

        initializeData();

    }

    private void initializeData() {
        // Get the resources from the XML file.
        String[] titleList = getResources()
                .getStringArray(R.array.titleList);
        String[] subtitle = getResources()
                .getStringArray(R.array.subtitle);
        String[] description = getResources()
                .getStringArray(R.array.description);
        TypedArray BookImage = getResources()
                .obtainTypedArray(R.array.bookImage);

        // Clear the existing data (to avoid duplication).
        mBookData.clear();



        for (int i = 0; i < titleList.length; i++) {
            mBookData.add(new Book(titleList[i], subtitle[i],description[i],
                    BookImage.getResourceId(i, 0)));
        }

        // Recycle the typed array.
        BookImage.recycle();

        // Notify the adapter of the change.
        mAdapter.notifyDataSetChanged();
    }
    public void resetSports(View view) {
        initializeData();
    }
}
