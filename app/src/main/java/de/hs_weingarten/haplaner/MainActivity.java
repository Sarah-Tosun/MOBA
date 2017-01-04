package de.hs_weingarten.haplaner;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;

public class MainActivity extends AppCompatActivity {

    ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // PagerAdapter - display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.seite1);
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(),
                MainActivity.this));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        TableLayout table = (TableLayout) findViewById(R.id.tabelle);
    }

    public void onClick(View v){
            // Tab - neues Fenster
            Intent myIntent = new Intent(this, StundenplanEinstellung.class);
            startActivity(myIntent);

    }

}
