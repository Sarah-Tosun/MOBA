package de.hs_weingarten.haplaner;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ViewPager viewpager;

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
    public void onClick(View v) {
            Intent stundenplanIntent;
            //Klick Zelle Stundenplan
            stundenplanIntent = new Intent(this, StundenplanEinstellung.class);
            startActivity(stundenplanIntent);

        if (R.id.fb_ok_aufgaben == v.getId()) {
            Toast toast = Toast.makeText(getApplicationContext(), "Klick", Toast.LENGTH_SHORT);
            toast.show();
            //Klick Floating Button Aufgaben
            Intent myIntent = new Intent(this, AufgabenEinstellung.class);
            startActivity(myIntent);
        }
    }

}
