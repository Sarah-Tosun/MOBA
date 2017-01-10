package de.hs_weingarten.haplaner;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import de.hs_weingarten.haplaner.datenbank.Aufgabe;
import de.hs_weingarten.haplaner.datenbank.AufgabenDBHelper;

public class AufgabenFragment extends Fragment implements View.OnClickListener {
    private FloatingActionButton fab;
    private CheckBox checkBox;
    private AufgabenDBHelper db;

    //List of Books
    private List<Aufgabe> myDataset;

    //List to show in App
    ListView listView;

    //Adapter for the ListView
    ArrayAdapter<String> adapter;
    List<String> values;

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View rootview = inflater.inflate(R.layout.fragment_aufgaben, container, false);
        //Floating ActionButton Listener
        fab= (FloatingActionButton) rootview.findViewById(R.id.fb_ok_aufgaben);
        fab.setOnClickListener(this);
        ListView listView = (ListView) rootview.findViewById(R.id.listview_aufg);
        //Hole alle vorhandenen Datensätze
        db= new AufgabenDBHelper(getActivity());
        //Test Aufgabe
        //Aufgabe aufg = new Aufgabe("asd", "TestISBN","dsad");
        //db.addAufgabe(aufg);

        myDataset= new ArrayList<>();
        myDataset=db.getAllAufgaben();

        values=getAllAufgabenAsString();

        adapter=new ArrayAdapter<String>(getActivity(),R.layout.list_aufgabe,R.id.fach_text_aufg,values);

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
    private List<String> getAllAufgabenAsString() {
        List<String> aufgaben = new LinkedList<>();
        for (int i = 0; i < myDataset.size(); i++) {
            aufgaben.add(i, myDataset.get(i).getFach());
        }
        return aufgaben;
    }
    private void refreshDataset() {
        myDataset = new ArrayList<>();
        myDataset = db.getAllAufgaben();
        adapter.notifyDataSetChanged();
    }

}
