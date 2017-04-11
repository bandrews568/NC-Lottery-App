package us.brandonandrews.nclottery.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import us.brandonandrews.nclottery.R;
import us.brandonandrews.nclottery.adapters.Pick4PagerAdapter;

public class Pick4Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pick4, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new Pick4PagerAdapter(getFragmentManager(), getActivity()));
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.slidingTabsPick4);
        tabLayout.setupWithViewPager(viewPager);
    }
}
