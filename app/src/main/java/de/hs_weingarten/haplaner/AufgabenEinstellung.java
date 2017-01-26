package de.hs_weingarten.haplaner;

import android.app.DatePickerDialog;
import android.content.Intent;
import java.util.Calendar;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import de.hs_weingarten.haplaner.datenbank_Aufgaben.Aufgabe;
import de.hs_weingarten.haplaner.datenbank_Aufgaben.AufgabenDBHelper;
import de.hs_weingarten.haplaner.datenbank_Spinner.SpinnerDBHelper;
import de.hs_weingarten.haplaner.datenbank_Spinner.SpinnerValue;

/**
 * Created by Sarah on 09.01.2017.
 */

public class AufgabenEinstellung extends AppCompatActivity {
    private AufgabenDBHelper db;

    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    private Spinner spinner;
    private SpinnerDBHelper dbSpinner;
    private List<String> faecher;
    private List<SpinnerValue> myDataset;
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

        dbSpinner=new SpinnerDBHelper(this);
        myDataset=dbSpinner.getAllFaecher();
        faecher=getAllFaecherAsString();
        Collections.reverse(faecher);
        //Spinner init
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,faecher);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Set List
        spinner.setAdapter(adapter);
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
                finish();
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

    }
    private List<String> getAllFaecherAsString() {
        List<String> string = new LinkedList<>();
        int j=0;
        for (int i = 0; i < myDataset.size(); i++) {
            if(!myDataset.get(i).getFach().equals("")&&!string.contains(myDataset.get(i).getFach())){
                string.add(j, myDataset.get(i).getFach());
                j++;
            }

        }
        return string;
    }
}



