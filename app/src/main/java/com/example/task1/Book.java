package com.example.task1;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(tableName = "book_table")
public class Book implements Parcelable {
    private String imageBook;
    @PrimaryKey(autoGenerate = true)
    private int bookId;
    private String title;
    private String subtitle;
    private String description;

    public Book( String title, String subtitle, String description,String imageBook) {

        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.imageBook = imageBook;
    }
    @Ignore
    public Book(int bookId, String title, String subtitle, String description,String imageBook) {
        this.bookId=bookId;
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.imageBook = imageBook;
    }




    protected Book(Parcel in) {
        bookId=in.readInt();
        imageBook = in.readString();
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
        dest.writeInt(bookId);
        dest.writeString(imageBook);
        dest.writeString(title);
        dest.writeString(subtitle);
        dest.writeString(description);
    }
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getImageBook() {
        return imageBook;
    }

    public void setImageBook(String imageBook) {
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

    public static Book[] populateData() {
        return new Book[] {
                new Book("English","I want to successful","orem ipsum dolor sit amet, consectetur adipiscing elit. Etiam in placerat urna. Phasellus varius","http://images.amazon.com/images/P/0596004478.01._PE30_PI_SCMZZZZZZZ_.jpg")

        };
    }

}
