package de.hs_weingarten.haplaner;

import android.content.Context;
import android.support.v4.app.*;

public class FragmentAdapter extends FragmentPagerAdapter {
    final private int PAGE_COUNT = 2;
    private String fragments[] = new String[] { "Stundenplan", "Aufgaben"};
    private Context context;

    public FragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new StundenplanFragment();
            case 1:
                return new AufgabenFragment();
            default:
                return null;
        }
        //return StundenplanFragment.newInstance(position + 1);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return fragments[position];
    }
}
