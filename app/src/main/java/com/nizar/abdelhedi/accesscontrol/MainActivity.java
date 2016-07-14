package com.nizar.abdelhedi.accesscontrol;


import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.gc.materialdesign.views.Switch;
import com.nizar.abdelhedi.accesscontrol.fragmentMenu.TableauDeBordFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    android.app.FragmentManager manager;
    Switch switch_ITG;
    Switch switch_REC;
    Switch switch_REC2;
    SharedPreferences URL_preferences;


    public final static String ITG_URL="http://192.168.1.184:8081/access-control-web";
    public final static String REC_URL="http://192.168.1.184:8092/access-control-web";
    public final static String REC2_URL="http://192.168.1.21:8080/access-control-web";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        manager = getFragmentManager();
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
          Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.settings_dialog);
            dialog.setTitle("choose your environnment !");

           switch_ITG = (Switch) dialog.findViewById(R.id.switchView_ITG);
            switch_REC = (Switch) dialog.findViewById(R.id.switchView_REC);
            switch_REC2 = (Switch) dialog.findViewById(R.id.switchView_REC2);

            MainActivity.this.switch_ITG.setOncheckListener(new Switch.OnCheckListener() {
                @Override
                public void onCheck(Switch aSwitch, boolean b) {
                    if(b) {
                        switch_REC2.setChecked(false);
                        switch_REC.setChecked(false);
                        URLStorage.setDefaults("URL",ITG_URL,MainActivity.this);
                        Toast.makeText(MainActivity.this,URLStorage.getDefaults("URL",MainActivity.this),Toast.LENGTH_LONG).show();
                    }
                }
            });
            MainActivity.this.switch_REC.setOncheckListener(new Switch.OnCheckListener() {
                @Override
                public void onCheck(Switch aSwitch, boolean b) {
                    if(b){

                        switch_REC2.setChecked(false);
                        switch_ITG.setChecked(false);
                        URLStorage.setDefaults("URL",REC_URL,MainActivity.this);
                        Toast.makeText(MainActivity.this,URLStorage.getDefaults("URL",MainActivity.this),Toast.LENGTH_LONG).show();




                    }
                }
            });
            MainActivity.this.switch_REC2.setOncheckListener(new Switch.OnCheckListener() {
                @Override
                public void onCheck(Switch aSwitch, boolean b) {
                    if(b){

                        switch_REC.setChecked(false);
                        switch_ITG.setChecked(false);
                        URLStorage.setDefaults("URL",REC2_URL,MainActivity.this);
                        Toast.makeText(MainActivity.this,URLStorage.getDefaults("URL",MainActivity.this),Toast.LENGTH_LONG).show();

                    }
                }
            });



            dialog.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Tableaudebord) {
            // Handle the camera action
            TableauDeBordFragment tableauDeBordFragment = new TableauDeBordFragment();
            android.app.FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.mylayout,tableauDeBordFragment,"nizar");
            transaction.commit();
        }
       /*
        else if (id == R.id.employé) {

        } else if (id == R.id.horairesdetravail) {

        } else if (id == R.id.conges) {

        } else if (id == R.id.reinitialisermotdepass) {

        } else if (id == R.id.deconnexion) {

        }
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
