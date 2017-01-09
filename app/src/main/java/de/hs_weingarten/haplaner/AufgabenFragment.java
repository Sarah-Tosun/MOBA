package de.hs_weingarten.haplaner;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AufgabenFragment extends Fragment{
    ArrayAdapter<String> mAufgabenAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootview = inflater.inflate(R.layout.fragment_aufgaben, container, false);
        //Create the fake data
        String[] fakeData = {
                "Revise for exam",
                "Buy milk",
                "Do laundry",
                "Call Melissa",
                "Buy stamps",
        };
        List<String> tasks = new ArrayList<String>(Arrays.asList(fakeData));

        //Create the ArrayAdapter by specifying context, "how" (layout),"where" (textview), and " what" (data)
        mAufgabenAdapter = new ArrayAdapter<String>(
                getActivity(),
                R.layout.list_aufgabe,
                R.id.fach_text_aufg,
                tasks);

        //Still need to bind adapter to the ListView
        ListView listView = (ListView) rootview.findViewById(R.id.listview_aufg);
        listView.setAdapter(mAufgabenAdapter);
        return rootview;
    }



}
