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
import com.example.task1.Activity.DetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private List<Book> bookList=new ArrayList<>();


    public RecyclerViewAdapter(Context context, List<Book> books) {
        this.context = context;
        this.bookList = books;
        if (bookList == null) {
            bookList = new ArrayList<>();
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override

    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).
                inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Book currentBook = bookList.get(position);
        final ViewHolder holder1 = (ViewHolder) holder;

        holder1.title.setText(currentBook.getTitle());
        holder1.subtitle.setText(currentBook.getSubtitle());
        holder1.desciption.setText(currentBook.getDescription());
        if (currentBook.getImageBook() != null) {
            Glide.with(context)
                    .load(currentBook.getImageBook().replace("https", "http"))
                    .asBitmap()
                    .fitCenter()
                    .into(((ViewHolder) holder).imageView);
        }

    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

public void setBook(List<Book>books){
        this.bookList=books;
        notifyDataSetChanged();
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

            Glide.with(context).load(
                    currentBook.getImageBook()).into(imageView);
        }


        @Override
        public void onClick(View v) {
            Book currentBook = bookList.get(getAdapterPosition());


            Book book=new Book(currentBook.getBookId(),currentBook.getTitle(),currentBook.getSubtitle(),currentBook.getDescription(),currentBook.getImageBook());
            Intent detailIntent = new Intent(context, DetailsActivity.class);
            detailIntent.putExtra("Book",book);
            context.startActivity(detailIntent);

        }
    }


}
