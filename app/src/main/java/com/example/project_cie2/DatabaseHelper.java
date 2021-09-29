package com.example.project_cie2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String Database = "Tutorial07";
    public static final String Table = "Student";
    public static final String Col_1 = "id";
    public static final String Col_2 = "username";
    public static final String Col_3 = "password";

    public DatabaseHelper(@Nullable Context context){
        super (context, Database, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "create table if not exists "+Table+ "(id integer "+
                "primary key autoincrement, username text, password integer)";
        Log.i("sql",sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,int i, int i1){
        sqLiteDatabase.execSQL("drop table "+ Table);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String username, String password){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Col_2,username);
        values.put(Col_3,password);
        long result = db.insert(Table, null,values);
        return (result == -1)?false:true;
    }

    public boolean checkUser(String email, String password) {
        // array of columns to fetch
        String[] columns = {
                Col_1
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = Col_2 + " = ?" + " AND " + Col_3 + " = ?";
        // selection arguments
        String[] selectionArgs = {email, password};
        // query user table with conditions
        /**
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(Table, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();

        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

        public Cursor selectData(){
        SQLiteDatabase db = getReadableDatabase();
        return db.query(Table,null,null,null,null,null,null);
    }
}



