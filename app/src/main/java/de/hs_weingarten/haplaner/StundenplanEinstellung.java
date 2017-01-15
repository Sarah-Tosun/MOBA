package de.hs_weingarten.haplaner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import de.hs_weingarten.haplaner.datenbank_Aufgaben.AufgabenDBHelper;
import de.hs_weingarten.haplaner.datenbank_Faecher.Fach;
import de.hs_weingarten.haplaner.datenbank_Faecher.FaecherDBHelper;

/**
 * Created by Sarah on 04.01.2017.
 * MOAB 1
 */

public class StundenplanEinstellung extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Context context;
    TextView textView;
    Fach fach;

    private FaecherDBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.einstellung_stundenplan);
        Intent myIntent =getIntent();
        fach=(Fach)myIntent.getSerializableExtra("fach");

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.fächer, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        if(!fach.getFach().equals("")){
            int spinnerPosition=adapter.getPosition(fach.getFach());
            spinner.setSelection(spinnerPosition);
        }



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
            case R.id.fb_ok_stundenplan:
                Toast toast = Toast.makeText(getApplicationContext(), "Klick", Toast.LENGTH_SHORT);
                toast.show();
                updateFach();
                break;
        }
    }
    public void updateFach() {
        db=new FaecherDBHelper(this);
        //Get Fach and Datum, Beschreibung
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        fach.setFach(spinner.getSelectedItem().toString());
        db.updateFach(fach);
        startActivity(new Intent(this,MainActivity.class));
    }
}

