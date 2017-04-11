package us.brandonandrews.nclottery.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import us.brandonandrews.nclottery.fragments.Cash5ResultsFragment;


public class Cash5PagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] {"Recent", "Filter", "Breakdown"};
    private Context context;

    public Cash5PagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        return new Cash5ResultsFragment().newInstance(context);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}