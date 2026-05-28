package com.example.nizamuddinshamrat.librarymanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Nizam Uddin Shamrat on 12/25/2017.
 */

public class DataSourceBook {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;

    public DataSourceBook(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public void open(){
        db = databaseHelper.getWritableDatabase();
    }
    public void close(){
        db.close();
    }
    public boolean incertBookData(BookInformation bookInformation){

        this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.BOOK_COL_NAME,bookInformation.getBookName());
        contentValues.put(DatabaseHelper.BOOK_COL_WRITER,bookInformation.getWriterName());
        contentValues.put(DatabaseHelper.BOOK_COL_CATEGORY,bookInformation.getBookCategory());
        contentValues.put(DatabaseHelper.BOOK_COL_LANGUAGE,bookInformation.getBookLanguage());
        long incertedRow =  db.insert(DatabaseHelper.TABLE_BOOK,null,contentValues);
        if (incertedRow>0)
            return true;
        else return false;
    }
    public ArrayList<BookInformation> getAllBooks() {
        db = databaseHelper.getReadableDatabase();
        ArrayList<BookInformation> bookInformations = new ArrayList<>();

        Cursor fullTableData = db.query(DatabaseHelper.TABLE_BOOK,null,null,null,null,null,null);

        if (fullTableData !=null&&fullTableData.getCount()>0){
            fullTableData.moveToFirst();
            do {
                int bookId = fullTableData.getInt(fullTableData.getColumnIndex(DatabaseHelper.BOOK_COL_ID));
                String bookName = fullTableData.getString(fullTableData.getColumnIndex(DatabaseHelper.BOOK_COL_NAME));
                String writterName = fullTableData.getString(fullTableData.getColumnIndex(DatabaseHelper.BOOK_COL_WRITER));
                String bookCategory = fullTableData.getString(fullTableData.getColumnIndex(DatabaseHelper.BOOK_COL_CATEGORY));
                String bookLanguage = fullTableData.getString(fullTableData.getColumnIndex(DatabaseHelper.BOOK_COL_LANGUAGE));
                bookInformations.add(new BookInformation(bookId,bookName,writterName,bookCategory,bookLanguage));
            }
            while (fullTableData.moveToNext());
        }
        fullTableData.close();
        this.close();

        return bookInformations;
    }

    public boolean updateBook(BookInformation bookInformation){

        this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.BOOK_COL_NAME,bookInformation.getBookName());
        contentValues.put(DatabaseHelper.BOOK_COL_WRITER,bookInformation.getWriterName());
        contentValues.put(DatabaseHelper.BOOK_COL_CATEGORY,bookInformation.getBookCategory());
        contentValues.put(DatabaseHelper.BOOK_COL_LANGUAGE,bookInformation.getBookLanguage());

        int updateRow =  db.update(DatabaseHelper.TABLE_BOOK,contentValues,
                DatabaseHelper.BOOK_COL_ID+"="+bookInformation.getBookId(),null);
        if (updateRow>0)
            return true;
        else return false;

    }

    public boolean deleteBook(int userId){
        this.open();
        int deletedRow = db.delete(DatabaseHelper.TABLE_BOOK,
                DatabaseHelper.BOOK_COL_ID+"="+userId,null);
        this.close();
        if(deletedRow > 0){
            return true;
        }else{
            return false;
        }
    }

}
