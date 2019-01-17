package com.example.simplesqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "myLogs";
    private Button bAdd, bRead, bClear, bUpd, bDel;
    private EditText etName, etEmail, etID;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bAdd = findViewById(R.id.btnAdd);
        bAdd.setOnClickListener(this);

        bRead = findViewById(R.id.btnRead);
        bRead.setOnClickListener(this);

        bClear = findViewById(R.id.btnClear);
        bClear.setOnClickListener(this);

        bUpd = findViewById(R.id.btnUpd);
        bUpd.setOnClickListener(this);

        bDel = findViewById(R.id.btnDel);
        bDel.setOnClickListener(this);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etID = findViewById(R.id.etID);

        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {

        ContentValues cv = new ContentValues();

        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String id = etID.getText().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        switch (v.getId()) {
            case R.id.btnAdd:
                Log.d(TAG, "---Insert in mytable:---");

                cv.put("name", name);
                cv.put("email", email);

                long rowID = db.insert("mytable", null, cv);
                Log.d(TAG, "row inserted, ID = " + rowID);
                break;
            case R.id.btnRead:
                Log.d(TAG, "---Rows in mytable:---");

                Cursor c = db.query("mytable", null, null,null, null,
                null, null);

                if (c.moveToFirst()) {
                    int idColIndex = c.getColumnIndex("id");
                    int nameColIndex = c.getColumnIndex("name");
                    int emailColIndex = c.getColumnIndex("email");

                    do {
                        Log.d(TAG,
                                "ID = " + c.getInt(idColIndex) +
                        ", name = " + c.getString(nameColIndex) +
                        ", email = " + c.getString(emailColIndex));
                    } while (c.moveToNext());
                } else Log.d(TAG, "0 rows");

                c.close();
                break;
            case R.id.btnClear:
                Log.d(TAG, "---Clear mytable:---");
                int clearCount = db.delete("mytable", null, null);
                Log.d(TAG, "deleted rows count = " + clearCount);
                break;
            case R.id.btnUpd:
                if (id.equalsIgnoreCase("")) {
                    break;
                }
                Log.d(TAG, "--- Update mytable: ---");
                // подготовим значения для обновления
                cv.put("name", name);
                cv.put("email", email);
                // обновляем по id
                int updCount = db.update("mytable", cv, "id = ?",
                        new String[] { id });
                Log.d(TAG, "updated rows count = " + updCount);
                break;
            case R.id.btnDel:
                if (id.equalsIgnoreCase("")) {
                    break;
                }
                Log.d(TAG, "--- Delete from mytable: ---");
                // удаляем по id
                int delCount = db.delete("mytable", "id = " + id, null);
                Log.d(TAG, "deleted rows count = " + delCount);
                break;
        }
        dbHelper.close();
    }

    class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, "myDB", null, 1);
            Log.d(TAG, "---constructor database---");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(TAG, "---onCreate database---");
            db.execSQL("create table mytable ("
            + "id integer primary key autoincrement,"
            + "name text,"
            + "email text" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
