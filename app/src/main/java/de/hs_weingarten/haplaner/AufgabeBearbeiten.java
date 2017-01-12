package de.hs_weingarten.haplaner;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
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

import java.text.ParseException;
import java.util.Date;

import de.hs_weingarten.haplaner.datenbank_Aufgaben.Aufgabe;
import de.hs_weingarten.haplaner.datenbank_Aufgaben.AufgabenDBHelper;

/**
 * Created by Patrick P. on 1/12/17.
 */

public class AufgabeBearbeiten extends AppCompatActivity{
    private AufgabenDBHelper db;

    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private EditText beschreibungField;
    private int year, month, day;
    private Aufgabe aufgabe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bearbeiten_aufgaben);
        Intent myIntent=getIntent();
        aufgabe=(Aufgabe) myIntent.getSerializableExtra("aufgabe");
        //Werte aus aufgabe lesen und setzen
        dateView = (TextView) findViewById(R.id.datum_textView_aufg);
        dateView.setText(aufgabe.getDatum());
        beschreibungField= (EditText) findViewById(R.id.beschreibung_edittext_aufg);
        beschreibungField.setText(aufgabe.getBeschreibung());
        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        try{
            Date startDate= dateFormat.parse(aufgabe.getDatum());
            calendar= Calendar.getInstance();
            calendar.setTime(startDate);

        }
        catch(ParseException e){
            e.printStackTrace();
        }
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //Button Listener
        Button changeDatum=(Button) findViewById(R.id.changedatum_aufg);
        changeDatum.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                DatePickerDialog datePickerdialog=new DatePickerDialog(AufgabeBearbeiten.this,myDateListener,calendar.get(Calendar.DATE),calendar.get(Calendar.MONTH),calendar.get(Calendar.YEAR));
                datePickerdialog.show();
            }
        });
        //Spinner init
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.fächer, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Gewähltes Fach als default Value einstellen
        spinner.setAdapter(adapter);
        int spinnerPosition=adapter.getPosition(aufgabe.getFach());
        spinner.setSelection(spinnerPosition);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.fb_ok_aufgaben_einst:

                Toast toast = Toast.makeText(getApplicationContext(), "Klick", Toast.LENGTH_SHORT);
                toast.show();
                updateAufgabe();
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
    public void updateAufgabe() {
        db=new AufgabenDBHelper(this);
        //Get Fach and Datum, Beschreibung
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        aufgabe.setFach(spinner.getSelectedItem().toString());
        TextView textView=(TextView)findViewById(R.id.datum_textView_aufg);
        aufgabe.setDatum(textView.getText().toString());
        EditText editText= (EditText) findViewById(R.id.beschreibung_edittext_aufg);
        aufgabe.setBeschreibung(editText.getText().toString());
        db.updateAufgabe(aufgabe);
        startActivity(new Intent(this,MainActivity.class));
    }
}
