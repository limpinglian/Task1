package com.example.task1;


import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    private int imageBook;
    private String title;
    private String subtitle;
    private String description;

    public Book( String title, String subtitle, String description,int imageBook) {

        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.imageBook = imageBook;
    }


    protected Book(Parcel in) {
        imageBook = in.readInt();
        title = in.readString();
        subtitle = in.readString();
        description = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageBook);
        dest.writeString(title);
        dest.writeString(subtitle);
        dest.writeString(description);
    }

    public int getImageBook() {
        return imageBook;
    }

    public void setImageBook(int imageBook) {
        this.imageBook = imageBook;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}
