package com.nizar.abdelhedi.accesscontrol;


import android.app.Dialog;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;

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


import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.views.ButtonIcon;
import com.gc.materialdesign.views.ButtonRectangle;
import com.gc.materialdesign.views.Switch;
import com.nizar.abdelhedi.accesscontrol.fragmentMenu.StatisqueDePresenceFragment;
import com.nizar.abdelhedi.accesscontrol.fragmentMenu.TableauDeBordFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    android.app.FragmentManager manager;
    Switch switch_ITG;
    Switch switch_REC;
    Switch switch_REC2;
    ButtonIcon buttonIcon_ITG;
    ButtonIcon buttonIcon_REC;
    ButtonIcon buttonIcon_REC2;
    TextInputEditText ip1, ip2, ip3, ip4, ipPort;
    ButtonFlat cancelEditButton;
    ButtonRectangle editButton;

    public final static String ITG_KEY = "ITG";
    public static String REC_KEY = "REC";
    public static String REC2_KEY = "REC2";
    public static String theURL_KeY = "URL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //ITG_URL = setString(R.string.ITG_REC2);
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

        saveSettingsEnvironment();

        TableauDeBordFragment tableauDeBordFragment = new TableauDeBordFragment();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.mylayout, tableauDeBordFragment, "test");
            transaction.commit();


        }

    public void saveSettingsEnvironment() {
        if (URLStorage.getDefaults(ITG_KEY, MainActivity.this) == null) {
            URLStorage.setDefaults(ITG_KEY, getResources().getString(R.string.ITG_URL), MainActivity.this);
        }
        if (URLStorage.getDefaults(REC_KEY, MainActivity.this) == null) {
            URLStorage.setDefaults(REC_KEY, getResources().getString(R.string.REC_URL), MainActivity.this);
            Log.d("nizarab", URLStorage.getDefaults(REC_KEY, MainActivity.this));

        }
        if (URLStorage.getDefaults(REC2_KEY, MainActivity.this) == null) {
            URLStorage.setDefaults(REC2_KEY, getResources().getString(R.string.REC2_URL), MainActivity.this);
        }
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
        // as you specify a parent activity in Andr;oidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.settings_dialog);
            dialog.setTitle("choose your environnment !");
            switch_ITG = (Switch) dialog.findViewById(R.id.switchView_ITG);
            switch_REC = (Switch) dialog.findViewById(R.id.switchView_REC);
            switch_REC2 = (Switch) dialog.findViewById(R.id.switchView_REC2);
            findEnvironnement();
            MainActivity.this.switch_ITG.setOncheckListener(new Switch.OnCheckListener() {
                @Override
                public void onCheck(Switch aSwitch, boolean b) {
                    if (b) {
                        switch_REC2.setChecked(false);
                        switch_REC.setChecked(false);
                        URLStorage.setDefaults(theURL_KeY, URLStorage.getDefaults(ITG_KEY, MainActivity.this), MainActivity.this);
                        Toast.makeText(MainActivity.this, URLStorage.getDefaults("URL", MainActivity.this), Toast.LENGTH_LONG).show();
                    }
                }
            });
            MainActivity.this.switch_REC.setOncheckListener(new Switch.OnCheckListener() {
                @Override
                public void onCheck(Switch aSwitch, boolean b) {
                    if (b) {
                        switch_REC2.setChecked(false);
                        switch_ITG.setChecked(false);
                        URLStorage.setDefaults(theURL_KeY, URLStorage.getDefaults(REC_KEY, MainActivity.this), MainActivity.this);
                        Toast.makeText(MainActivity.this, URLStorage.getDefaults("URL", MainActivity.this), Toast.LENGTH_LONG).show();
                    }
                }
            });
            MainActivity.this.switch_REC2.setOncheckListener(new Switch.OnCheckListener() {
                @Override
                public void onCheck(Switch aSwitch, boolean b) {
                    if (b) {
                        switch_REC.setChecked(false);
                        switch_ITG.setChecked(false);
                        URLStorage.setDefaults(theURL_KeY, URLStorage.getDefaults(REC2_KEY, MainActivity.this), MainActivity.this);
                        Toast.makeText(MainActivity.this, URLStorage.getDefaults("URL", MainActivity.this), Toast.LENGTH_LONG).show();
                    }
                }
            });

            buttonIcon_ITG = (ButtonIcon) dialog.findViewById(R.id.ITGIconButton);
            buttonIcon_REC = (ButtonIcon) dialog.findViewById(R.id.RECIconButton);
            buttonIcon_REC2 = (ButtonIcon) dialog.findViewById(R.id.REC2IconButton);

            buttonIcon_ITG.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editEnvironementSettings(ITG_KEY);
                }
            });
            buttonIcon_REC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editEnvironementSettings(REC_KEY);
                }
            });
            buttonIcon_REC2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editEnvironementSettings(REC2_KEY);
                }
            });

            dialog.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void editEnvironementSettings(final String Key) {
        final Dialog editITG_URLDialog = new Dialog(MainActivity.this);
        editITG_URLDialog.setContentView(R.layout.edit_environnment_dialog);
        editITG_URLDialog.setTitle("edit " + Key + " IP adresse !");

        ip1 = (TextInputEditText) editITG_URLDialog.findViewById(R.id.ip1EditText);
        ip2 = (TextInputEditText) editITG_URLDialog.findViewById(R.id.ip2EditText);
        ip3 = (TextInputEditText) editITG_URLDialog.findViewById(R.id.ip3EditText);
        ip4 = (TextInputEditText) editITG_URLDialog.findViewById(R.id.ip4EditText);
        ipPort = (TextInputEditText) editITG_URLDialog.findViewById(R.id.ipPortEditText);

        cancelEditButton = (ButtonFlat) editITG_URLDialog.findViewById(R.id.cancelEditButton);
        cancelEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editITG_URLDialog.dismiss();
            }
        });

        editButton = (ButtonRectangle) editITG_URLDialog.findViewById(R.id.editButoon);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEnvironnement(Key);
            }
        });
        editITG_URLDialog.show();


    }

    private void saveEnvironnement(String KEY) {

        if ((ip1.getText().toString().equals("") || ip2.getText().toString().equals("") || ip3.getText().toString().equals("") || ip4.getText().toString().equals("") || ipPort.getText().toString().equals(""))) {
            ipPort.setError("This Field is required");
        } else if (Integer.parseInt(ip1.getText().toString()) > 256) {
            ip1.setError(getString(R.string.Ip_adress_erreur));
        } else if (Integer.parseInt(ip2.getText().toString()) > 256) {
            ip2.setError(getString(R.string.Ip_adress_erreur));
        } else if (Integer.parseInt(ip3.getText().toString()) > 256) {
            ip3.setError(getString(R.string.Ip_adress_erreur));
        } else if (Integer.parseInt(ip4.getText().toString()) > 256) {
            ip4.setError(getString(R.string.Ip_adress_erreur));
        } else {
            URLStorage.setDefaults(KEY, "http://" + ip1.getText().toString() + "." + ip2.getText().toString() + "." + ip3.getText().toString() + "." + ip4.getText().toString() + ":" + ipPort.getText().toString() + "/access-control-web/rest/services/attendance", MainActivity.this);
            Toast.makeText(MainActivity.this, "http://" + ip1.getText().toString() + "." + ip2.getText().toString() + "." + ip3.getText().toString() + "." + ip4.getText().toString() + ":" + ipPort.getText().toString() + "/access-control-web/rest/services/attendance", Toast.LENGTH_LONG).show();
        }
    }

    public void findEnvironnement() {
        if ((URLStorage.getDefaults(theURL_KeY, MainActivity.this) + "").equals(URLStorage.getDefaults(ITG_KEY, MainActivity.this))) {
            switch_ITG.setChecked(true);
        }
        if ((URLStorage.getDefaults(theURL_KeY, MainActivity.this) + "").equals(URLStorage.getDefaults(REC_KEY, MainActivity.this))) {
            switch_REC.setChecked(true);
        }
        if ((URLStorage.getDefaults(theURL_KeY, MainActivity.this) + "").equals(URLStorage.getDefaults(REC2_KEY, MainActivity.this))) {
            switch_REC2.setChecked(true);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Tableaudebord) {
            TableauDeBordFragment tableauDeBordFragment = new TableauDeBordFragment();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.mylayout, tableauDeBordFragment).commit();
        }

        else if (id == R.id.statistiquedepresence) {
            StatisqueDePresenceFragment statisqueDePresenceFragment = new StatisqueDePresenceFragment();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.mylayout, statisqueDePresenceFragment).commit();
        }
        /*else if (id == R.id.horairesdetravail) {

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
