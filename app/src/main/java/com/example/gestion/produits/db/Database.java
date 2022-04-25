package com.example.gestion.produits.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public static final String DBNAME = "produit.db";

    public static final String TABLENAME = "produits";

    public static final int VERSION = 1;

    public Database(@Nullable Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLENAME + "(id integer primary key autoincrement, name varchar(50) not null unique, price double not null default 0, quantity int not null default 0, famille varchar(50))";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS " + TABLENAME + "";
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String name, double price, int quantity, String famille) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("price", price);
        cv.put("quantity", quantity);
        cv.put("famille", famille);

        long result = db.insert(TABLENAME, null, cv);

        return result != -1;
    }

    public Cursor viewData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLENAME + "";

        return db.rawQuery(query, null);
    }

    public boolean updateData(long id, String name, double price, int quantity, String famille) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("price", price);
        cv.put("quantity", quantity);
        cv.put("famille", famille);

        long result = db.update(TABLENAME, cv, "id = ?", new String[]{String.valueOf(id)});
        return result != -1;
    }

    public boolean deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLENAME, "id = ?", new String[]{id});
        return result != -1;
    }

}
