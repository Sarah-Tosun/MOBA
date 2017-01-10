package de.hs_weingarten.haplaner;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import de.hs_weingarten.haplaner.datenbank.Aufgabe;
import de.hs_weingarten.haplaner.datenbank.AufgabenDBHelper;

/**
 * Created by Sarah on 09.01.2017.
 */

public class AufgabenEinstellung extends AppCompatActivity {
    private AufgabenDBHelper db;

    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.einstellungen_aufgaben);
        db=new AufgabenDBHelper(this);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent myIntent = getIntent();

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.fächer, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //spinner.setOnItemClickListener((AdapterView.OnItemClickListener) this);

        //Button Listener
        Button changeDatum=(Button) findViewById(R.id.changedatum_aufg);
        changeDatum.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                DatePickerDialog datePickerdialog=new DatePickerDialog(AufgabenEinstellung.this,myDateListener,year,month,day);
                datePickerdialog.show();
            }
        });
        //Datum Feld mit current Date versehen
        dateView = (TextView) findViewById(R.id.datum_textView_aufg);
        calendar = calendar.getInstance();
        year =calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);
    }

    public boolean onOptiosItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        parent.getItemAtPosition(pos);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.fb_ok_aufgaben_einst:
                addAufgabe();
                break;
        }
    }
    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }
    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    public void addAufgabe() {
        //Get Fach and Datum, Beschreibung
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        String fach = spinner.getSelectedItem().toString();
        TextView textView=(TextView)findViewById(R.id.datum_textView_aufg);
        String datum = textView.getText().toString();
        EditText editText= (EditText) findViewById(R.id.beschreibung_edittext_aufg);
        String beschreibung = editText.getText().toString();

        Aufgabe aufgabe = new Aufgabe(fach, datum,beschreibung);
        db.addAufgabe(aufgabe);
        startActivity(new Intent(this,MainActivity.class));
    }
}



