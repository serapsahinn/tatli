package com.example.tatli;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TatliDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Tatli";
    private static final int DATABASE_VERSION = 1;
    
    // Tablo ve kolon isimleri
    public static final String TABLE_TATLI = "tatli1";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
   ;

    // Tablo oluşturma SQL cümlesi
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_TATLI + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_DESCRIPTION + " TEXT, " +
                     " REAL);";

    public TatliDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TATLI);
        onCreate(db);
    }


    public long addTatli(String name, String description, double price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DESCRIPTION, description);
        return db.insert(TABLE_TATLI, null, values);
    }


    public Cursor getAllTatli() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_TATLI, null, null, null, null, null, null);
    }
}
