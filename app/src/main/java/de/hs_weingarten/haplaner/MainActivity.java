package de.hs_weingarten.haplaner;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        stundenplanIntent = new Intent(this, StundenplanEinstellung.class);
        switch(v.getId()){
            //Zeile 2
            case R.id.z2eins:
                stundenplanIntent.putExtra("ID",v.getId());
                break;
            case R.id.z2zwei:
                stundenplanIntent.putExtra("ID",v.getId());
                break;
            case R.id.z2drei:
                break;
            case R.id.z2vier:
                break;
            //Zeile 3
            case R.id.z3eins:
                break;
            case R.id.z3zwei:
                break;
            case R.id.z3drei:
                break;
            case R.id.z3vier:
                break;
            //Zeile 4
            case R.id.z4eins:
                break;
            case R.id.z4zwei:
                break;
            case R.id.z4drei:
                break;
            case R.id.z4vier:
                break;
            //Zeile 5
            case R.id.z5eins:
                break;
            case R.id.z5zwei:
                break;
            case R.id.z5drei:
                break;
            case R.id.z5vier:
                break;
            //Zeile 6
            case R.id.z6eins:
                break;
            case R.id.z6zwei:
                break;
            case R.id.z6drei:
                break;
            case R.id.z6vier:
                break;
            //Zeile 7
            case R.id.z7eins:
                break;
            case R.id.z7zwei:
                break;
            case R.id.z7drei:
                break;
            case R.id.z7vier:
                break;
        }
        //Klick Zelle Stundenplan
        startActivity(stundenplanIntent);

    }

}
