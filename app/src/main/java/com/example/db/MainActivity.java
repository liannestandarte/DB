package com.example.db;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DBHelper helper;
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
        myDB.close();
    }
}
