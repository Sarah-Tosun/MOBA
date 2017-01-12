package de.hs_weingarten.haplaner.datenbank_Aufgaben;

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


public class AufgabenDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "aufgabenDB";
    private static final int DATABASE_VERSION = 4;
    private static final String TABLE_AUFGABEN="aufgaben";

    //Aufgaben Columns
    private static final String KEY_ID="id";
    private static final String KEY_FACH="fach";
    private static final String KEY_DATUM="datum";
    private static final String KEY_BESCHREIBUNG="beschreibung";

    private static final String[] COLUMNS={KEY_ID,KEY_FACH,KEY_DATUM,KEY_BESCHREIBUNG};


    public AufgabenDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_AUFGABEN_TABLE =
                "CREATE TABLE " + TABLE_AUFGABEN + " (" +
                        KEY_ID + " INTEGER PRIMARY KEY," +
                        KEY_FACH + " TEXT," +
                        KEY_DATUM + " TEXT," +
                        KEY_BESCHREIBUNG + " TEXT" +
                        " );";
        db.execSQL(SQL_CREATE_AUFGABEN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AUFGABEN);
        onCreate(db);
    }

    public void addAufgabe(Aufgabe aufgabe){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(KEY_FACH,aufgabe.getFach());
        values.put(KEY_DATUM,aufgabe.getDatum());
        values.put(KEY_BESCHREIBUNG,aufgabe.getBeschreibung());

        db.insert(TABLE_AUFGABEN,null,values);

        db.close();
    }
    public Aufgabe getAufgabe(int id) {
        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_AUFGABEN, // a. table
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
        Aufgabe aufgabe = new Aufgabe();
        aufgabe.setId(Integer.parseInt(cursor.getString(0)));
        aufgabe.setFach(cursor.getString(1));
        aufgabe.setDatum(cursor.getString(2));
        aufgabe.setBeschreibung(cursor.getString(3));

        // 5. return aufgabe
        return aufgabe;
    }
    public List<Aufgabe> getAllAufgaben() {
        List<Aufgabe> aufgaben = new LinkedList<>();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_AUFGABEN;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build aufgabe and add it to list
        Aufgabe aufgabe;
        if (cursor.moveToFirst()) {
            do {
                aufgabe = new Aufgabe();
                aufgabe.setId(Integer.parseInt(cursor.getString(0)));
                aufgabe.setFach(cursor.getString(1));
                aufgabe.setDatum(cursor.getString(2));
                aufgabe.setBeschreibung(cursor.getString(3));

                aufgaben.add(aufgabe);
            } while (cursor.moveToNext());
        }

        // 4. return list
        return aufgaben;
    }
    public int updateAufgabe(Aufgabe aufgabe) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_FACH, aufgabe.getFach());
        values.put(KEY_DATUM, aufgabe.getDatum());
        values.put(KEY_BESCHREIBUNG,aufgabe.getBeschreibung());

        // 3. updating row
        int i = db.update(TABLE_AUFGABEN, //table
                values, // column/value
                KEY_ID + " = ?", // selections
                new String[]{String.valueOf(aufgabe.getId())}); //selection args

        // 4. close
        db.close();

        return i;
    }
    public void deleteAufgabe(Aufgabe aufgabe) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_AUFGABEN, //table name
                KEY_ID + " = ?",  // selections
                new String[]{String.valueOf(aufgabe.getId())}); //selections args

        // 3. close
        db.close();
    }
}
