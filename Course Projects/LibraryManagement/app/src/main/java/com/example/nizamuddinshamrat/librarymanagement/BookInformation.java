package com.example.nizamuddinshamrat.librarymanagement;

import java.io.Serializable;

/**
 * Created by Nizam Uddin Shamrat on 12/22/2017.
 */

public class BookInformation implements Serializable {

    private int bookId;
    private String bookName;
    private String writerName;
    private String bookCategory;
    private String bookLanguage;
    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public BookInformation(int bookId, String bookName, String writerName, String bookCategory, String bookLanguage) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.writerName = writerName;
        this.bookCategory = bookCategory;
        this.bookLanguage = bookLanguage;
    }

    public BookInformation(String bookName, String writerName, String bookCategory, String bookLanguage) {
        this.bookName = bookName;
        this.writerName = writerName;
        this.bookCategory = bookCategory;
        this.bookLanguage = bookLanguage;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public String getBookLanguage() {
        return bookLanguage;
    }

    public void setBookLanguage(String bookLanguage) {
        this.bookLanguage = bookLanguage;
    }
}