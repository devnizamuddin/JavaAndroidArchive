package com.example.nizamuddinshamrat.librarymanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Nizam Uddin Shamrat on 12/25/2017.
 */

public class DataSourceUser {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;

    public DataSourceUser(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public void open(){
        db = databaseHelper.getWritableDatabase();
    }
    public void close(){
        db.close();
    }
    public boolean incertUserData(UserInformation userInformation){

        this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.USER_COL_NAME,userInformation.getUserName());
        contentValues.put(DatabaseHelper.USER_COL_GENDER,userInformation.getUserGender());
        contentValues.put(DatabaseHelper.USER_COL_BIRTHDAY,userInformation.getUserBirthdayDate());
        contentValues.put(DatabaseHelper.USER_COL_EMAIL,userInformation.getUserEmail());
        contentValues.put(DatabaseHelper.USER_COL_PASSWORD,userInformation.getUserPassword());
        long incertedRow =  db.insert(DatabaseHelper.TABLE_USER,null,contentValues);
        if (incertedRow>0)
            return true;
        else return false;
    }
    public UserInformation getUserByEmail(String email){
        this.open();
        UserInformation userInformation = null;
        Cursor cursor = db.query(DatabaseHelper.TABLE_USER,
                null,
                DatabaseHelper.USER_COL_EMAIL+"="+email,
                null,null,null,null);
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            int userId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.USER_COL_ID));
            String userName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_COL_NAME));
            String userGender = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_COL_GENDER));
            String userBirthday = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_COL_BIRTHDAY));
            String userEmail = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_COL_EMAIL));
            String userPasword = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_COL_PASSWORD));
            userInformation = new UserInformation(userId,userName,userGender,userBirthday,userEmail,userPasword);
        }
        this.close();
        return userInformation;
    }
    public UserInformation getUserId(int id){
        this.open();
        UserInformation userInformation = null;
        Cursor cursor = db.query(DatabaseHelper.TABLE_USER,
                null,
                DatabaseHelper.USER_COL_ID+"="+id,
                null,null,null,null);
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            int userId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.USER_COL_ID));
            String userName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_COL_NAME));
            String userGender = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_COL_GENDER));
            String userBirthday = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_COL_BIRTHDAY));
            String userEmail = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_COL_EMAIL));
            String userPasword = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_COL_PASSWORD));
            userInformation = new UserInformation(userId,userName,userGender,userBirthday,userEmail,userPasword);
        }
        this.close();
        return userInformation;
    }


        //Method For Login
        public String getPasswordByEmail(String email){

            db = databaseHelper.getReadableDatabase();
            String [] columns = {DatabaseHelper.USER_COL_EMAIL,DatabaseHelper.USER_COL_PASSWORD};
            Cursor cursor = db.query(DatabaseHelper.TABLE_USER,columns,null,null,null,null,null);

            String eml,pass;

            pass = "not found";

            if (cursor.moveToFirst()){
                do {

                    eml = cursor.getString(0);

                    if (eml.equals(email)){
                        pass = cursor.getString(1);
                        break;
                    }

                }
                while (cursor.moveToNext());
            }
            return pass;
        }
    public boolean updateUser(UserInformation userInformation){

        this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.USER_COL_NAME,userInformation.getUserName());
        contentValues.put(DatabaseHelper.USER_COL_GENDER,userInformation.getUserGender());
        contentValues.put(DatabaseHelper.USER_COL_BIRTHDAY,userInformation.getUserBirthdayDate());
        contentValues.put(DatabaseHelper.USER_COL_EMAIL,userInformation.getUserEmail());
        contentValues.put(DatabaseHelper.USER_COL_PASSWORD,userInformation.getUserPassword());

        int updateRow =  db.update(DatabaseHelper.TABLE_USER,contentValues,
                DatabaseHelper.USER_COL_ID+"="+userInformation.getUserId(),null);
        if (updateRow>0)
            return true;
        else return false;

    }
    public boolean deleteUser(int userId){
        this.open();
        int deletedRow = db.delete(DatabaseHelper.TABLE_USER,
                DatabaseHelper.USER_COL_ID+"="+userId,null);
        this.close();
        if(deletedRow > 0){
            return true;
        }else{
            return false;
        }
    }

}
