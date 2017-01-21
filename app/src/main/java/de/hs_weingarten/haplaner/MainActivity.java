package de.hs_weingarten.haplaner;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import de.hs_weingarten.haplaner.datenbank_Faecher.SpinnerDBHelper;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // PagerAdapter - Fragmente
        ViewPager viewPager = (ViewPager) findViewById(R.id.seite1);
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(),
                MainActivity.this));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

}
