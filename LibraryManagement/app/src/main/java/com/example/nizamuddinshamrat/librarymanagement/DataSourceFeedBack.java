package com.example.nizamuddinshamrat.librarymanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Nizam Uddin Shamrat on 12/27/2017.
 */

public class DataSourceFeedBack {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;

    public DataSourceFeedBack(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public void open(){
        db = databaseHelper.getWritableDatabase();
    }
    public void close(){
        db.close();
    }
    public boolean incertFeedback(FeedbackInfo feedbackInfo){

        this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.FEEDBACK_COL_RATE,feedbackInfo.getRate());
        contentValues.put(DatabaseHelper.FEEDBACK_COL_COMMENT,feedbackInfo.getComment());
        contentValues.put(DatabaseHelper.FEEDBACK_COL_BOOK_ID,feedbackInfo.getFeedbackBookId());
        contentValues.put(DatabaseHelper.FEEDBACK_COL_USER_ID,feedbackInfo.getFeedbackUserId());
        long incertedRow =  db.insert(DatabaseHelper.TABLE_FEEDBACK,null,contentValues);
        if (incertedRow>0)
            return true;
        else return false;
    }
    public ArrayList<FeedbackInfo> getAllFeedbackById(int feedBackBookId) {
        db = databaseHelper.getReadableDatabase();
        ArrayList<FeedbackInfo> feedbackInfos = new ArrayList<>();

        Cursor fullTableData = db.query(DatabaseHelper.TABLE_FEEDBACK,null,DatabaseHelper.FEEDBACK_COL_BOOK_ID+"="+feedBackBookId,null,null,null,null);

        if (fullTableData !=null&&fullTableData.getCount()>0){
            fullTableData.moveToFirst();
            do {
                float rate = fullTableData.getFloat(fullTableData.getColumnIndex(DatabaseHelper.FEEDBACK_COL_RATE));
                String comment = fullTableData.getString(fullTableData.getColumnIndex(DatabaseHelper.FEEDBACK_COL_COMMENT));
                int feedbackBookId = fullTableData.getInt(fullTableData.getColumnIndex(DatabaseHelper.FEEDBACK_COL_BOOK_ID));
                int feedbackUserId = fullTableData.getInt(fullTableData.getColumnIndex(DatabaseHelper.FEEDBACK_COL_USER_ID));
                feedbackInfos.add(new FeedbackInfo(rate,comment,feedbackBookId,feedbackUserId));
            }
            while (fullTableData.moveToNext());
        }
        fullTableData.close();
        this.close();

        return feedbackInfos;
    }
    public ArrayList<FeedbackInfo> getAllFeedbackInfos() {
        db = databaseHelper.getReadableDatabase();
        ArrayList<FeedbackInfo> feedbackInfos = new ArrayList<>();

        Cursor fullTableData = db.query(DatabaseHelper.TABLE_FEEDBACK,null,null,null,null,null,null);

        if (fullTableData !=null&&fullTableData.getCount()>0){
            fullTableData.moveToFirst();
            do {
                int bookId = fullTableData.getInt(fullTableData.getColumnIndex(DatabaseHelper.FEEDBACK_COL_ID));
                float rate = fullTableData.getFloat(fullTableData.getColumnIndex(DatabaseHelper.FEEDBACK_COL_RATE));
                String comment = fullTableData.getString(fullTableData.getColumnIndex(DatabaseHelper.FEEDBACK_COL_COMMENT));
                int feedbackBookId = fullTableData.getInt(fullTableData.getColumnIndex(DatabaseHelper.FEEDBACK_COL_BOOK_ID));
                int feedbackUserId = fullTableData.getInt(fullTableData.getColumnIndex(DatabaseHelper.FEEDBACK_COL_USER_ID));
                feedbackInfos.add(new FeedbackInfo(bookId,rate,comment,feedbackBookId,feedbackUserId));
            }
            while (fullTableData.moveToNext());
        }
        fullTableData.close();
        this.close();

        return feedbackInfos;
    }

}
