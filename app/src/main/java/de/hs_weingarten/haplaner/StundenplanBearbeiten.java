package de.hs_weingarten.haplaner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import de.hs_weingarten.haplaner.datenbank_Faecher.Fach;
import de.hs_weingarten.haplaner.datenbank_Faecher.FaecherDBHelper;
import de.hs_weingarten.haplaner.datenbank_Spinner.SpinnerDBHelper;
import de.hs_weingarten.haplaner.datenbank_Spinner.SpinnerValue;

/**
 * Created by Patrick P. on 14.01.2017.
 */

public class StundenplanBearbeiten extends AppCompatActivity{
    private SpinnerDBHelper dbSpinner;
    private FaecherDBHelper dbFaecher;
    private Fach fach;
    private Spinner spinner;
    private List<String> faecher;
    private List<SpinnerValue> myDataset;
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
        dbSpinner=new SpinnerDBHelper(this);
        myDataset=dbSpinner.getAllFaecher();
        faecher=getAllFaecherAsString();
        Collections.reverse(faecher);
        //Spinner init
        spinner = (Spinner) findViewById(R.id.spinner_bearbeiten_stundp);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,faecher);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Gewähltes Fach als default Value einstellen
        spinner.setAdapter(adapter);
        int spinnerPosition=adapter.getPosition(fach.getFach());
        spinner.setSelection(spinnerPosition);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.fb_ok_stundenplan_bearbeiten_stundp:
                if(!spinner.getSelectedItem().toString().equals("neues Fach")){
                    updateFach();
                }
                else{
                    addNeuesFach();
                }
                finish();
                break;
        }
    }
    public void updateFach() {
        //Get Fach and Datum, Beschreibung
        fach.setFach(spinner.getSelectedItem().toString());
        dbFaecher=new FaecherDBHelper(this);
        dbFaecher.updateFach(fach);
        //startActivity(new Intent(this,MainActivity.class));
    }
    public void addNeuesFach(){
        EditText neuesFach=(EditText) findViewById(R.id.neuesFach_bearbeiten_stundp);
        EditText kuerzel=(EditText) findViewById(R.id.kürzel_bearbeiten_stundp);
        String fachString=neuesFach.getText().toString();
        if(neuesFach.getText().toString().equals("")){
            Toast toast = Toast.makeText(getApplicationContext(), "Geben Sie bitte ein Namen ein", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            fach.setFach(neuesFach.getText().toString());
            SpinnerValue spinnerFach=new SpinnerValue(neuesFach.getText().toString(),kuerzel.getText().toString());
            dbSpinner.addFach(spinnerFach);
            dbFaecher=new FaecherDBHelper(this);
            dbFaecher.updateFach(fach);
        }

    }private List<String> getAllFaecherAsString() {
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
