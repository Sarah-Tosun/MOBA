package de.hs_weingarten.haplaner;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StundenplanFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StundenplanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StundenplanFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static StundenplanFragment newInstance(int sectionNumber) {
        StundenplanFragment fragment = new StundenplanFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_stundenplan, container, false);
    }
}
