package com.example.db;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DBHelper helper;
    Cursor cursor;
    SQLiteDatabase myDB;
    EditText txtFname, txtLname, txtCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new DBHelper(this);
        myDB = helper.getWritableDatabase();
        txtFname = findViewById(R.id.first);
        txtLname = findViewById(R.id.last);
        txtCourse = findViewById(R.id.course);
        cursor = helper.selectRecords();
        cursor.moveToFirst();
        showData();
        //helper.onCreate(myDB);
    }

    protected void addRecord(View v) {
        String fname = txtFname.getText().toString();
        String lname = txtLname.getText().toString();
        String course = txtCourse.getText().toString();
        long isInserted = helper.insertData(fname, lname, course);

        if (isInserted == -1) {
            Toast.makeText(this, "data not saved", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "successfully saved data", Toast.LENGTH_LONG).show();
        }
    }

    public void goFirst(View v) {
        cursor.moveToFirst();
        showData();
    }

    public void clearEntries(View v) {
        txtFname.setText("");
        txtLname.setText("");
        txtCourse.setText("");
        txtFname.requestFocus();
    }

    private void showData() {
        txtFname.setText(cursor.getString(1));
        txtLname.setText(cursor.getString(2));
        txtCourse.setText(cursor.getString(3));
    }

    public void goNext(View v) {
        if(cursor.getPosition()== cursor.getCount()-1) {
            cursor.moveToLast();
            showData();
            Toast.makeText(this,"last record already", Toast.LENGTH_LONG).show();
        } else {
            cursor.moveToNext();
            showData();
        }
    }

    public void goPrevious(View v) {
        if(cursor.getPosition()== 0) {
            cursor.moveToFirst();
            showData();
            Toast.makeText(this,"First Record", Toast.LENGTH_LONG).show();
        } else {
            cursor.moveToPrevious();
            showData();
        }
    }

    public void goLast(View v) {
        cursor.moveToLast();
        showData();
    }

    public void updateRecord(View v) {
        int id = cursor.getInt(0);
        String fn = txtFname.getText().toString();
        String ln = txtLname.getText().toString();
        String co = txtCourse.getText().toString();
        int isUpdated = helper.updateRecord(""+id, fn, ln, co);
        if (isUpdated != 1) {
            Toast.makeText(this, "Successfully updated.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Update failed.", Toast.LENGTH_LONG).show();
        }
    }

    public void deleteRecord(View v) {
        String id = cursor.getString(0);
        helper.deleteRecord(id);
    }
}
