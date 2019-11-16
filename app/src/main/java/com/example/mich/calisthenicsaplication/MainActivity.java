package com.example.mich.calisthenicsaplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("Menu Główne");
        setContentView(R.layout.menu_fragment);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle( this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainPanelFragment()).commit();
        }
    }




    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.nav_menu:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new MainPanelFragment())
                        .addToBackStack(null)
                        .commit();// przelaczamy sie miedzy fragmentami do Menu Głównego
                break;

            case R.id.nav_front:
                Intent intent1 = new Intent(MainActivity.this, InfoPanelActivity.class);
                int x1 = 1; // wlaczy panel informujacy z cwiczeniem numer 1
                intent1.putExtra("info_number", x1);
                startActivity(intent1);
                break;
            case R.id.nav_muscleup:
                Intent intent2 = new Intent(MainActivity.this, InfoPanelActivity.class);
                int x2 = 2; // wlaczy panel informujacy z cwiczeniem numer 2
                intent2.putExtra("info_number", x2);
                startActivity(intent2);
                break;
            case R.id.nav_planche:
                Intent intent3 = new Intent(MainActivity.this, InfoPanelActivity.class);
                int x3 = 3; // wlaczy panel informujacy z cwiczeniem numer 3
                intent3.putExtra("info_number", x3);
                startActivity(intent3);
                break;
            case R.id.nav_back:
                Intent intent4 = new Intent(MainActivity.this, InfoPanelActivity.class);
                int x4 = 4;
                intent4.putExtra("info_number", x4);
                startActivity(intent4);
                break;
            case R.id.nav_hs:
                Intent intent5 = new Intent(MainActivity.this, InfoPanelActivity.class);
                int x5 = 5;
                intent5.putExtra("info_number", x5);
                startActivity(intent5);
                break;
            case R.id.nav_test:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new FormLevelFragment())
                        .addToBackStack(null)
                        .commit();// przelaczamy sie miedzy fragmentami do Testu
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed()
    {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
