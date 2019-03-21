package com.example.task1;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ImageView ivBook=(ImageView)findViewById(R.id.ivBook_Details);
        TextView tvTitle=(TextView)findViewById(R.id.title_details);
        TextView tvSubtitle=(TextView)findViewById(R.id.subtitle_details);
        TextView tvDescription=(TextView)findViewById(R.id.description_details);

        Bundle data = getIntent().getExtras();
        Book book = (Book) data.getParcelable("Book");

       Glide.with(this).load(book.getImageBook())
                .into(ivBook);
        tvTitle.setText(book.getTitle());
        tvSubtitle.setText(book.getSubtitle());
        tvDescription.setText(book.getDescription());

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
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
