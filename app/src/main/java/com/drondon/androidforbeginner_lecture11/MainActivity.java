package com.drondon.androidforbeginner_lecture11;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.stetho.Stetho;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity_";

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.button:
                insertUser();
                break;

            case R.id.button2:
                updateUser();
                break;

            case R.id.button3:
                deleteUser();
                break;

        }
    }

    private void deleteUser() {
        Log.d(TAG, "deleteUser: ");
    }

    private void updateUser() {
        Log.d(TAG, "updateUser: ");

    }

    private void insertUser() {
        Log.d(TAG, "insertUser: ");
        User user = readFromUi();

        ContentValues cv = new ContentValues();
        //cv.put("_id", user.id);
        cv.put("firstName", user.firstName);
        cv.put("lastName", user.lastName);
        cv.put("age", user.age);

        long rowId = db.insert("users", null, cv);
        if (rowId >= 0) {

        }
    }

    private User readFromUi() {

        String id = etId.getText().toString();
        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();
        String age = etAge.getText().toString();

        User user = new User();
        //user.id = Long.valueOf(id);
        user.firstName = firstName;
        user.lastName = lastName;
        user.age = Long.valueOf(age);

        return user;
    }

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_main);
        DataBaseHelper helper = new DataBaseHelper(this);
        db = helper.getWritableDatabase();
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    EditText etId, etFirstName, etLastName, etAge;
    Button btnUpdate, btnInsert, btnDelete;

    private void initView() {
        etId = findViewById(R.id.id);
        etFirstName = findViewById(R.id.firstname);
        etLastName = findViewById(R.id.lastname);
        etAge = findViewById(R.id.age);

        btnInsert = findViewById(R.id.button);
        btnUpdate = findViewById(R.id.button2);
        btnDelete = findViewById(R.id.button3);

        btnInsert.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    private User readUser(int userId, SQLiteDatabase db) {
        User user = new User();
        Cursor cursor = db.query("users", new String[]{"_id",
                "firstName",
                "lastName",
                "age"}, "_id=" + userId, null, null, null, null);
        if (cursor.moveToFirst()) {
            user.id = cursor.getLong(cursor.getColumnIndex("_id"));
            user.firstName = cursor.getString(cursor.getColumnIndex("firstName"));
            user.lastName = cursor.getString(cursor.getColumnIndex("lastName"));
            user.age = cursor.getLong(cursor.getColumnIndex("age"));
        }
        cursor.close();
        return user;
    }

}
