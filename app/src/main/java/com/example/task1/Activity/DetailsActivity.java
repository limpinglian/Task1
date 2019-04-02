package com.example.task1.Activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.task1.Book;
import com.example.task1.BookViewModel;
import com.example.task1.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    public static final int EDIT_BOOK_REQUEST = 2;
    private LayoutInflater inflater;
    private BookViewModel bookViewModel;
    List<Book> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        FloatingActionButton fbEdit = (FloatingActionButton) findViewById(R.id.fabEdit);
        final ImageView ivBook = (ImageView) findViewById(R.id.ivBook_Details);
        TextView tvTitle = (TextView) findViewById(R.id.title_details);
        TextView tvSubtitle = (TextView) findViewById(R.id.subtitle_details);
        TextView tvDescription = (TextView) findViewById(R.id.description_details);
        bookViewModel = ViewModelProviders.of(this).get(BookViewModel.class);
        Bundle data = getIntent().getExtras();
        final Book book = (Book) data.getParcelable("Book");

        Glide.with(this).load(book.getImageBook())
                .into(ivBook);
        tvTitle.setText(book.getTitle());
        tvSubtitle.setText(book.getSubtitle());
        tvDescription.setText(book.getDescription());

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        fbEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this, EditActivity.class);
                intent.putExtra(EditActivity.EXTRA_ID, book.getBookId());
                intent.putExtra(EditActivity.EXTRA_TITLE, book.getTitle());
                intent.putExtra(EditActivity.EXTRA_SUBTITLE, book.getSubtitle());
                intent.putExtra(EditActivity.EXTRA_DESCRIPTION, book.getDescription());
                intent.putExtra(EditActivity.EXTRA_URL, book.getImageBook());
                startActivityForResult(intent, EDIT_BOOK_REQUEST);


            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        DetailsActivity.super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_BOOK_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(EditActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Note can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String title = data.getStringExtra(EditActivity.EXTRA_TITLE);
            String subtitle = data.getStringExtra(EditActivity.EXTRA_SUBTITLE);
            String description = data.getStringExtra(EditActivity.EXTRA_DESCRIPTION);
            String url = data.getStringExtra(EditActivity.EXTRA_URL);

            Book book = new Book(title, subtitle, description, url);
            book.setBookId(id);
            bookViewModel.update(book);

            final ImageView ivBook = (ImageView) findViewById(R.id.ivBook_Details);
            TextView tvTitle = (TextView) findViewById(R.id.title_details);
            TextView tvSubtitle = (TextView) findViewById(R.id.subtitle_details);
            TextView tvDescription = (TextView) findViewById(R.id.description_details);
            Picasso.get().load(url).into(ivBook);
            tvTitle.setText(title);
            tvSubtitle.setText(subtitle);
            tvDescription.setText(description);


            Toast.makeText(this, "Note Updated", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
