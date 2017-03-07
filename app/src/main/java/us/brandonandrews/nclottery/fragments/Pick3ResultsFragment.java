package us.brandonandrews.nclottery.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import us.brandonandrews.nclottery.R;

public class Pick3ResultsFragment extends Fragment {

    public static Pick3ResultsFragment newInstance() {
        Pick3ResultsFragment fragment = new Pick3ResultsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pick3_results_fragment, container, false);
        return view;
    }
}
