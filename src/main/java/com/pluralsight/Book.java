package com.pluralsight;

public class Book {
    private int id;
    private String isbn;
    private String title;
    private boolean isCheckedOut;
    private String checkedOutTo;

    public Book(int id, String isbn, String title) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.isCheckedOut = false;
        this.checkedOutTo = "";
    }

    public void checkOut(String name) {
        isCheckedOut = true;
        checkedOutTo = name;
    }

    public void checkIn() {
        isCheckedOut = false;
        checkedOutTo = "";
    }

    public int getId() { return id; }
    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public boolean isCheckedOut() { return isCheckedOut; }
    public String getCheckedOutTo() { return checkedOutTo; }

    @Override
    public String toString() {
        return id + " | " + isbn + " | " + title;
    }

    public String toDetailedString() {
        return id + " | " + isbn + " | " + title + " | Checked out to: " + checkedOutTo;
    }
}
