package com.example.nizamuddinshamrat.librarymanagement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nizam Uddin Shamrat on 12/25/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "library_db";
    public static final int DATABASE_VERSION = 1;


    //User Table
    public static final String TABLE_USER = "tbl_user";
    public static final String USER_COL_ID = "user_id";
    public static final String USER_COL_NAME = "user_name";
    public static final String USER_COL_GENDER = "user_gender";
    public static final String USER_COL_BIRTHDAY = "user_birthday";
    public static final String USER_COL_EMAIL = "user_email";
    public static final String USER_COL_PASSWORD = "user_password";

    public static final String CREATE_TABLE_USER = "CREATE TABLE "+TABLE_USER+"("+
            USER_COL_ID+" INTEGER PRIMARY KEY, "+
            USER_COL_NAME+" TEXT, "+
            USER_COL_GENDER+" TEXT, "+
            USER_COL_BIRTHDAY+" TEXT, "+
            USER_COL_EMAIL+" TEXT, "+
            USER_COL_PASSWORD+" TEXT);";


    //BOOK table
    public static final String TABLE_BOOK = "tbl_book";
    public static final String BOOK_COL_ID = "book_id";
    public static final String BOOK_COL_NAME = "book_name";
    public static final String BOOK_COL_WRITER = "book_writer";
    public static final String BOOK_COL_CATEGORY = "book_category";
    public static final String BOOK_COL_LANGUAGE = "book_language";


    public static final String CREATE_TABLE_BOOK = "CREATE TABLE "+TABLE_BOOK+"("+
            BOOK_COL_ID+" INTEGER PRIMARY KEY, "+
            BOOK_COL_NAME+" TEXT, "+
            BOOK_COL_WRITER+" TEXT, "+
            BOOK_COL_CATEGORY+" TEXT, "+
            BOOK_COL_LANGUAGE+" TEXT);";


    //feed_BAck Table
    public static final String TABLE_FEEDBACK = "tbl_feedback";
    public static final String FEEDBACK_COL_ID = "feedback_id";
    public static final String FEEDBACK_COL_COMMENT = "feedback_comment";
    public static final String FEEDBACK_COL_RATE = "feedback_rate";
    public static final String FEEDBACK_COL_BOOK_ID = "feedback_book_id";
    public static final String FEEDBACK_COL_USER_ID = "feedback_user_id";

    public static final String CREATE_TABLE_FEEDBACK = "CREATE TABLE "+TABLE_FEEDBACK+"("+
            FEEDBACK_COL_ID+" INTEGER PRIMARY KEY, "+
            FEEDBACK_COL_COMMENT+" TEXT, "+
            FEEDBACK_COL_RATE+" REAL, "+
            FEEDBACK_COL_BOOK_ID+" INTEGER, "+
            FEEDBACK_COL_USER_ID+" INTEGER);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_BOOK);
        db.execSQL(CREATE_TABLE_FEEDBACK);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_BOOK);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_FEEDBACK);
        onCreate(db);
    }
}
