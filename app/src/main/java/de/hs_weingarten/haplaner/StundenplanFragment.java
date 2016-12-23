package de.hs_weingarten.haplaner;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StundenplanFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StundenplanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StundenplanFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    public static StundenplanFragment newInstance(int sectionNumber) {
        StundenplanFragment fragment = new StundenplanFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_stundenplan, container, false);
        TextView textView = (TextView) view;
        textView.setText("Fragment #" + mPage);
        return view;
        //return inflater.inflate(R.layout.fragment_stundenplan, container, false);
    }
}
