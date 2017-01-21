package de.hs_weingarten.haplaner;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import de.hs_weingarten.haplaner.datenbank_Faecher.FaecherDBHelper;
import de.hs_weingarten.haplaner.datenbank_Faecher.SpinnerDBHelper;
import de.hs_weingarten.haplaner.datenbank_Faecher.Fach;


public class StundenplanFragment extends Fragment {

    private GridView gridView;
    private FaecherDBHelper db;

    GridViewAdapter adapter;
    private List<Fach> myDataset;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView=inflater.inflate(R.layout.fragment_stundenplan, container, false);
        gridView = (GridView) rootView.findViewById(R.id.gridview_StundenplanFragment);

        db = new FaecherDBHelper(getActivity());
        myDataset=new ArrayList<>();
        myDataset=db.getAllFaecher();

        adapter=new GridViewAdapter(getActivity(),myDataset);
        gridView.setAdapter(adapter);
        return rootView;
    }
    @Override
    public void onResume(){
        super.onResume();
        myDataset.clear();
        myDataset=db.getAllFaecher();
        adapter.refresh(myDataset);
    }

}
