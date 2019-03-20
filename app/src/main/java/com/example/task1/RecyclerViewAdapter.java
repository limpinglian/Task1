package com.example.task1;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private List<Book> BookList;

    public RecyclerViewAdapter(Context context, List<Book> books) {
        this.context = context;
        this.BookList = books;
        if (BookList == null) {
            BookList = new ArrayList<>();
        }
    }

    @NonNull
    @Override

    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).
                inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Book currentBook = BookList.get(position);

        final ViewHolder holder1 = (ViewHolder) holder;
        holder1.bindTo(currentBook);

    }

    @Override
    public int getItemCount() {
        return BookList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title;
        private TextView subtitle;
        private TextView desciption;
        private ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tvTitle);
            subtitle = itemView.findViewById(R.id.tvSubtitle);
            desciption = itemView.findViewById(R.id.tvDescription);
            imageView = itemView.findViewById(R.id.ivImage);

            itemView.setOnClickListener(this);
        }

        public void bindTo(Book currentBook) {
            title.setText(currentBook.getTitle());
            subtitle.setText(currentBook.getSubtitle());
            desciption.setText(currentBook.getDescription());

            // Load the images into the ImageView using the Glide library.
            Glide.with(context).load(
                    currentBook.getImageBook()).into(imageView);
        }


        @Override
        public void onClick(View v) {
            Book currentBook = BookList.get(getAdapterPosition());
            Intent detailIntent = new Intent(context, DetailsActivity.class);
            detailIntent.putExtra("title", currentBook.getTitle());
            detailIntent.putExtra("subtitle",currentBook.getSubtitle());
            detailIntent.putExtra("description",currentBook.getDescription());
            detailIntent.putExtra("image_resource",
                    currentBook.getImageBook());
            context.startActivity(detailIntent);

        }
    }


}