package de.hs_weingarten.haplaner;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import de.hs_weingarten.haplaner.datenbank_Faecher.Fach;
import de.hs_weingarten.haplaner.datenbank_Faecher.FaecherDBHelper;

/**
 * Created by Patrick P. on 14.01.2017.
 */

public class StundenplanBearbeiten extends AppCompatActivity{
    private FaecherDBHelper db;
    private Fach fach;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bearbeiten_stundenplan);
        Intent myIntent=getIntent();
        fach =(Fach) myIntent.getSerializableExtra("fach");
        //Werte aus fach lesen und setzen
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //Spinner init
        spinner = (Spinner) findViewById(R.id.spinner_bearbeiten_stundp);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.fächer, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Gewähltes Fach als default Value einstellen
        spinner.setAdapter(adapter);
        int spinnerPosition=adapter.getPosition(fach.getFach());
        spinner.setSelection(spinnerPosition);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.fb_ok_stundenplan_bearbeiten_stundp:
                Toast toast = Toast.makeText(getApplicationContext(), "Klick", Toast.LENGTH_SHORT);
                toast.show();
                updateFach();
                break;
        }
    }
    public void updateFach() {
        db=new FaecherDBHelper(this);
        //Get Fach and Datum, Beschreibung
        fach.setFach(spinner.getSelectedItem().toString());
        db.updateFach(fach);
        finish();
        //startActivity(new Intent(this,MainActivity.class));
    }
}
