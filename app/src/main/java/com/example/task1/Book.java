package com.example.task1;



public class Book  {
    private int imageBook;
    private String title;
    private String subtitle;

    public Book( String title, String subtitle, String description,int imageBook) {

        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.imageBook = imageBook;
    }

    private String description;

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
