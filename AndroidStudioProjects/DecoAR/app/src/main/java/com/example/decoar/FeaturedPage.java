package com.example.decoar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class FeaturedPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_featured_page);

        Toolbar toolbar = findViewById(R.id.toolbar_menu);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_menu);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

//        if(savedInstanceState == null){
//            //auto bukak so tak tunjuk featured items
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                    new FragmentBrowse()).commit();
//            navigationView.setCheckedItem(R.id.menu_browse);
//        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_browse:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FragmentBrowse()).commit();
                break;
            case R.id.menu_cart:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FragmentMyCart()).commit();
                break;
            case R.id.menu_history:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FragmentHistory()).commit();
                break;
            case R.id.menu_report:
                Toast.makeText(this,"Report sent!", Toast.LENGTH_SHORT).show();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}