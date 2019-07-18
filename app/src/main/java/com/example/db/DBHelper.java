package com.example.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DBNAME = "school.db";
    private static final int VER = 1;
    private static final String CREATE_TABLE = "CREATE TABLE student (_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "FIRST_NAME TEXT, LAST_NAME TEXT , COURSE TEXT)";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS student";
    private static final String SELECT_ALL = "SELECT * FROM student";

    public DBHelper(Context context) {
        super(context, DBNAME,null, VER);
        //this.getWritableDatabase();
       // this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        //Toast.makeText(context,"The student table was created.",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public long insertData(String fname, String lname, String course) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("FIRST_NAME", fname);
        cv.put("LAST_NAME", lname);
        cv.put("COURSE", course);
        long result = db.insert("student",null, cv);
        return result;
    }

    public Cursor selectRecords() {
        //SELECT FIRST_NAME, LAST_NAME, COURSE FROM student
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor records = db.rawQuery(SELECT_ALL, null);
       //String[] cols = {"FIRST_NAME", "LAST_NAME", "COURSE"};
        //Cursor records = db.query("student", cols, null, null, null, null, null);
        records.moveToFirst();
        db.close();
        return records;
    }

    public int updateRecord(String id, String lName, String fName, String course) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues val = new ContentValues();
        val.put("_ID", id);
        val.put("FIRST_NAME", fName);
        val.put("LAST_NAME", lName);
        val.put("COURSE", course);
        db.update("student", val, "_ID=?", new String[]{id});
        db.close();
      //  return result;
        return 0;
    }

    public int deleteRecord(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        int result = db.delete("student","_ID=?", new String[]{id});
        db.close();
        return result;
    }
}
