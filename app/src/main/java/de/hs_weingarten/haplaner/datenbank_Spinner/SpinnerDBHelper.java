package de.hs_weingarten.haplaner.datenbank_Faecher;

/**
 * Created by Patrick P. on 09.01.2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

import de.hs_weingarten.haplaner.R;


public class SpinnerDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "spinnerDB";
    private static final int DATABASE_VERSION = 4;
    private static final String TABLE_SPINNER ="spinner";

    //Aufgaben Columns
    private static final String KEY_ID="id";
    private static final String KEY_FACH="fach";
    private static final String[] COLUMNS={KEY_ID,KEY_FACH};

    private Context context;

    public SpinnerDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_FAECHER_TABLE =
                "CREATE TABLE " + TABLE_SPINNER + " (" +
                        KEY_ID + " INTEGER PRIMARY KEY," +
                        KEY_FACH + " TEXT" +
                        " );";
        db.execSQL(SQL_CREATE_FAECHER_TABLE);
        ContentValues cv = new ContentValues();
        String[] defaultFaecher= context.getResources().getStringArray(R.array.fächer);
        for(int i = 0; i< defaultFaecher.length; i++){
            cv.put(KEY_FACH,defaultFaecher[i]);
            db.insert(TABLE_SPINNER,null,cv);
            cv.clear();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPINNER);
        onCreate(db);
    }

    public void addFach(Fach fach){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(KEY_FACH,fach.getFach());

        db.insert(TABLE_SPINNER,null,values);

        db.close();
    }
    public Fach getFach(int id) {
        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_SPINNER, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[]{String.valueOf(id)}, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build aufgaben object
        Fach fach = new Fach();
        fach.setId(Integer.parseInt(cursor.getString(0)));
        fach.setFach(cursor.getString(1));

        // 5. return aufgabe
        return fach;
    }
    public List<Fach> getAllFaecher() {
        List<Fach> faecher = new LinkedList<>();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_SPINNER;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build aufgabe and add it to list
        Fach fach;
        if (cursor.moveToFirst()) {
            do {
                fach = new Fach();
                fach.setId(Integer.parseInt(cursor.getString(0)));
                fach.setFach(cursor.getString(1));

                faecher.add(fach);
            } while (cursor.moveToNext());
        }

        // 4. return list
        return faecher;
    }
    public int updateFach(Fach fach) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_FACH, fach.getFach());

        // 3. updating row
        int i = db.update(TABLE_SPINNER, //table
                values, // column/value
                KEY_ID + " = ?", // selections
                new String[]{String.valueOf(fach.getId())}); //selection args

        // 4. close
        db.close();

        return i;
    }
    public void deleteFach(Fach fach) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_SPINNER, //table name
                KEY_ID + " = ?",  // selections
                new String[]{String.valueOf(fach.getId())}); //selections args

        // 3. close
        db.close();
    }
    public void dropTable(){
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPINNER);
    }
}
