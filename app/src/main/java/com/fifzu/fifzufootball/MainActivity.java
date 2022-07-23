package com.fifzu.fifzufootball;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;

import com.fifzu.fifzufootball.ui.home.HomeViewModel;
import com.google.android.material.navigation.NavigationView;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private int anzahlSpielplaene;
    private List<String> spielplanList;
    private List<String> defaultSpielplanList;
    private SharedPreferences sharedPref;
    private HomeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        viewModel.getSpielplaene().observe(this, item -> {
            // Perform an action with the latest item data
        });

        List<String> spielplanList=new ArrayList<String>();
        defaultSpielplanList=new ArrayList<>();

        defaultSpielplanList.add("premier league");
        defaultSpielplanList.add("primera division");
        defaultSpielplanList.add("serie a");
        defaultSpielplanList.add("champions league");
        defaultSpielplanList.add("league 1");

        sharedPref = getPreferences(Context.MODE_PRIVATE);
        anzahlSpielplaene = sharedPref.getInt("anzahlSpielplaene",5);

        for (int i =0;i<anzahlSpielplaene;i++)
        {
            if (defaultSpielplanList.size()==i) {
                defaultSpielplanList.add("Example League");
            }
            spielplanList.add(sharedPref.getString("spielplan" + i,defaultSpielplanList.get(i)));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_settings, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void saveSpielplane() {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        for (int i =0;i<spielplanList.size();i++)
        {
            editor.putString("spielplan"+i,(String) spielplanList.get(i));
        }
        editor.apply();
    }

    public List<String> getSpielplanList() {
        return spielplanList;
    }

    public void setSpielplanList(List<String> spielplanList) {
        this.spielplanList = spielplanList;
        saveSpielplane();
    }
}