package de.hs_weingarten.haplaner;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import de.hs_weingarten.haplaner.datenbank_Aufgaben.Aufgabe;
import de.hs_weingarten.haplaner.datenbank_Aufgaben.AufgabenDBHelper;

public class AufgabenFragment extends Fragment implements View.OnClickListener {
    private FloatingActionButton fab;
    private CheckBox checkBox;
    private AufgabenDBHelper db;

    //List of Books
    private List<Aufgabe> myDataset;

    //List to show in App
    ListView listView;

    //Adapter for the ListView
    ListViewAdapter adapter;
    List<String> fach;
    List<String> datum;
    List<String> beschreibung;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View rootview = inflater.inflate(R.layout.fragment_aufgaben, container, false);
        //Floating ActionButton Listener
        fab= (FloatingActionButton) rootview.findViewById(R.id.fb_ok_aufgaben);
        fab.setOnClickListener(this);
        ListView listView = (ListView) rootview.findViewById(R.id.listview_aufg);
        //Bearbeiten der Aufgabe mit LongClick
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent aufgabeBearbeiten=new Intent(getActivity(),AufgabeBearbeiten.class);
                aufgabeBearbeiten.putExtra("aufgabe", db.getAufgabe(position+1));
                startActivity(aufgabeBearbeiten);
                return true;
            }
        });
        //Hole alle vorhandenen Datensätze
        db= new AufgabenDBHelper(getActivity());
        //Test Aufgabe
        //Aufgabe aufg = new Aufgabe("asd", "TestISBN","dsad");
        //db.addAufgabe(aufg);

        myDataset= new ArrayList<>();
        myDataset=db.getAllAufgaben();

        fach=getAllAufgabenFachAsString();
        datum=getAllAufgabenDatumAsString();
        beschreibung=getAllAufgabenBeschreibungAsString();
        adapter=new ListViewAdapter(getActivity(),myDataset);

        listView.setAdapter(adapter);
        return rootview;
    }

    @Override
    public void onClick(View v) {
        if (R.id.fb_ok_aufgaben == v.getId()) {
            //Klick Floating Button Aufgaben
            Intent myIntent = new Intent(getActivity(), AufgabenEinstellung.class);
            startActivity(myIntent);
        }
    }
    private List<String> getAllAufgabenFachAsString() {
        List<String> faecher = new LinkedList<>();
        for (int i = 0; i < myDataset.size(); i++) {
            faecher.add(i, myDataset.get(i).getFach());
        }
        return faecher;
    }
    private List<String> getAllAufgabenDatumAsString() {
        List<String> datum = new LinkedList<>();
        for (int i = 0; i < myDataset.size(); i++) {
            datum.add(i, myDataset.get(i).getFach());
        }
        return datum;
    }
    private List<String> getAllAufgabenBeschreibungAsString() {
        List<String> beschreibung = new LinkedList<>();
        for (int i = 0; i < myDataset.size(); i++) {
            beschreibung.add(i, myDataset.get(i).getFach());
        }
        return beschreibung;
    }
    private void refreshDataset() {
        myDataset = new ArrayList<>();
        myDataset = db.getAllAufgaben();
        adapter.notifyDataSetChanged();
    }

}
