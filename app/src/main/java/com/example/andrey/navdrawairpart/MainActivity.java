package com.example.andrey.navdrawairpart;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // Right navigation menu
    private void displayRightNavigation(){
        final NavigationView navigationViewRight = (NavigationView) findViewById(R.id.nav_view_right);
        navigationViewRight.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;
                // Handle navigation view item clicks here.
                int id = item.getItemId();

                if (id == R.id.nav_metric) {
                        fragment = new MetricFragment();
                    // Handle the camera action
                } else if (id == R.id.nav_pressure) {
                    fragment = new PressureFragment();

                } else if (id == R.id.nav_volume) {
                    //Toast.makeText(MainActivity.this, "fdsffsdaft", Toast.LENGTH_SHORT).show();
                    fragment = new VolumeFragment();

                } else if (id == R.id.nav_tube_diameter_calc) {
                    fragment = new TubeDiameterCalcFragment();

                } else/* (id == R.id.nav_share_right)*/ {

                } /*else if (id == R.id.nav_send_right) {

                }*/

                if (fragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();

                    ft.replace(R.id.screen_area, fragment);
                    ft.commit();
                }


                //Toast.makeText(MainActivity.this, "Handle from navigation right", Toast.LENGTH_SHORT).show();
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.END);
                item.setCheckable(false);
                return true;

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        if (googleSericesAvailable()) {
            //Toast.makeText(this, "Perfecto!!!", Toast.LENGTH_LONG).show();
        }

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                super.onDrawerSlide(drawerView, 0);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, 0); // this disables the animation

            }
        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        NavigationView navigationViewRight = (NavigationView) findViewById(R.id.nav_view_right);
        //navigationViewRight.setNavigationItemSelectedListener(this);

        displayRightNavigation();

        //++++++ Toolbar settings
        getSupportActionBar().setDisplayShowTitleEnabled(false); // remove AppName in title
        for (int i = 0; i < toolbar.getChildCount(); i++) {
            if(toolbar.getChildAt(i) instanceof ImageButton){
                toolbar.getChildAt(i).setScaleX(1.5f);
                toolbar.getChildAt(i).setScaleY(1.5f);
            }
        }
        // toolbar.setLogo(android.R.drawable.ic_menu_call);  // icon in default Title
        //toggle.setDrawerSlideAnimationEnabled(false); // disables hamburger animation
        //------ Toolbar settings

        //++++++ NavigationView color
        navigationView.setItemIconTintList(null);
        navigationViewRight.setItemIconTintList(null);
        //------ NavigationView color

        startFragmentInflate();

    }

    @Override
    public void onBackPressed() {
        /*
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        */
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(drawer.isDrawerOpen(GravityCompat.END)){
            drawer.closeDrawer(GravityCompat.END);
        }else {
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
        /*
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
        */
        int id = item.getItemId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (id == R.id.action_settings) {
            drawer.openDrawer(GravityCompat.END);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Fragment fragment = null;

        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Log.d("ID", "Test id" + id);

        if (id == R.id.nav_product) {
            fragment = new ProductFragment();

        } else if (id == R.id.nav_shipment) {
            fragment = new ShipmentFragment();

        } else if (id == R.id.nav_review) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("http://air-part.ru/reviews/"));
            startActivity(intent);
        } else if (id == R.id.nav_contacts) {
            fragment = new ContactsFragment();
        } else if (id == R.id.nav_about) {
            fragment = new AboutFragment();
        } else /*if (id == R.id.nav_send)*/ {

        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();

            ft.replace(R.id.screen_area, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        item.setCheckable(false);
        //mNavigationView.getMenu().getItem(i).setChecked(false);

        return true;
    }

    private void startFragmentInflate() {
        Fragment fragment = new ProductFragment();
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();

            ft.replace(R.id.screen_area, fragment);
            ft.commit();
        }
    }

    public void product_click(View view) {
        int someTag = (int) view.getTag();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);

        if (someTag == 0) {
            intent.setData(Uri.parse("http://air-part.ru/category/dlja-kompressorov/"));
            Log.d("prodClick", "First product_click: Clicked");
        } else if (someTag == 1) {
            intent.setData(Uri.parse("http://air-part.ru/category/dlja-osushitelej/"));
            Log.d("prodClick", "Second product_click: Clicked");
        } else if (someTag == 2) {
            intent.setData(Uri.parse("http://air-part.ru/category/generatori-gazov/"));
            Log.d("prodClick", "Else product_click: Clicked");
        } else if (someTag == 3) {
            intent.setData(Uri.parse("http://air-part.ru/category/vakuumnye-nasosy/"));
            Log.d("prodClick", "Else product_click: Clicked");
        } else if (someTag == 4) {
            intent.setData(Uri.parse("http://air-part.ru/category/ciklonnye-separatory_n6/"));
            Log.d("prodClick", "Else product_click: Clicked");
        } else if (someTag == 5) {
            intent.setData(Uri.parse("http://air-part.ru/category/dlja-magistralnyh-filtrov/"));
            Log.d("prodClick", "Else product_click: Clicked");
        } else if (someTag == 6) {
            intent.setData(Uri.parse("http://air-part.ru/category/vspomogatelnoe-oborudovanie/"));
            Log.d("prodClick", "Else product_click: Clicked");
        } else if (someTag == 7) {
            intent.setData(Uri.parse("http://air-part.ru/category/maslo/"));
            Log.d("prodClick", "Else product_click: Clicked");
        } else if (someTag == 8) {
            intent.setData(Uri.parse("http://air-part.ru/category/gidravlika/"));
            Log.d("prodClick", "Else product_click: Clicked");
        } else if (someTag == 9) {
            intent.setData(Uri.parse("http://air-part.ru/category/kondensatootvodchiki/"));
            Log.d("prodClick", "Else product_click: Clicked");
        } else if (someTag == 10) {
            intent.setData(Uri.parse("http://air-part.ru/category/podobrat-filtr/"));
            Log.d("prodClick", "Else product_click: Clicked");
        } else if (someTag == 11) {
            intent.setData(Uri.parse("http://air-part.ru/category/adsorbent/"));
            Log.d("prodClick", "Else product_click: Clicked");
        } else if (someTag == 12) {
            intent.setData(Uri.parse("http://air-part.ru/category/filtracija-parker/"));
            Log.d("prodClick", "Else product_click: Clicked");
        } else if (someTag == 13) {
            intent.setData(Uri.parse("http://air-part.ru/category/separatory-vody-i-masla/"));
            Log.d("prodClick", "Else product_click: Clicked");
        } else if (someTag == 14) {
            intent.setData(Uri.parse("http://air-part.ru/category/turbokompressory/"));
            Log.d("prodClick", "Else product_click: Clicked");
        } else if (someTag == 15) {
            intent.setData(Uri.parse("http://air-part.ru/category/dizelnye-generatory/"));
            Log.d("prodClick", "Else product_click: Clicked");
        } else if (someTag == 16) {
            intent.setData(Uri.parse("http://air-part.ru/category/ugolnye-kolonny/"));
            Log.d("prodClick", "Else product_click: Clicked");
        } else if (someTag == 17) {
            intent.setData(Uri.parse("http://air-part.ru/category/poleznaya-informatsiya/"));
            Log.d("prodClick", "Else product_click: Clicked");
        } else if (someTag == 18) {
            intent.setData(Uri.parse("http://air-part.ru/category/spectehnika/"));
            Log.d("prodClick", "Else product_click: Clicked");
        } else if (someTag == 19) {
            intent.setData(Uri.parse("http://air-part.ru/category/servis/"));
            Log.d("prodClick", "Else product_click: Clicked");
        }

        startActivity(intent);
    }

    public boolean googleSericesAvailable() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(this);
        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (api.isUserResolvableError(isAvailable)) {
            Dialog dialog = api.getErrorDialog(this, isAvailable, 0);
            dialog.show();
        } else {
            Toast.makeText(this, "Cant connect to play services", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    public void clearFields(View view) {
        String name = view.getTag().toString();
        Log.d("ButtonClick", "clearFields: " + name);

        if (name.equals("mksB") || name.equals("mkmB") || name.equals("mkhB") || name.equals("lsB") || name.equals("lmB") || name.equals("lhB")) {

            Log.d("ButtonClick", "clearFields: " + name);

            EditText mksText = (EditText) findViewById(R.id.mksET);
            EditText mkmText = (EditText) findViewById(R.id.mkmET);
            EditText mkhText = (EditText) findViewById(R.id.mkhET);
            EditText lsText = (EditText) findViewById(R.id.lsET);
            EditText lmText = (EditText) findViewById(R.id.lmET);
            EditText lhText = (EditText) findViewById(R.id.lhET);
            mksText.setText("");
            mkmText.setText("");
            mkhText.setText("");
            lsText.setText("");
            lmText.setText("");
            lhText.setText("");

        } else if (name.equals("atmB") || name.equals("kpaB") || name.equals("mpaB") || name.equals("psiB") || name.equals("kgsB") || name.equals("barB")) {

            Log.d("ButtonClick", "clearFields: " + name);

            EditText mksText = (EditText) findViewById(R.id.atmET);
            EditText mkmText = (EditText) findViewById(R.id.kpaET);
            EditText mkhText = (EditText) findViewById(R.id.mpaET);
            EditText lsText = (EditText) findViewById(R.id.psiET);
            EditText lmText = (EditText) findViewById(R.id.kgsET);
            EditText lhText = (EditText) findViewById(R.id.barET);
            mksText.setText("");
            mkmText.setText("");
            mkhText.setText("");
            lsText.setText("");
            lmText.setText("");
            lhText.setText("");

        } else if (name.equals("lB") || name.equals("glB") || name.equals("dlB") || name.equals("mlB") || name.equals("kmB") || name.equals("ksB") || name.equals("kyB") || name.equals("kfB") || name.equals("kdB") || name.equals("galB")) {

            Log.d("ButtonClick", "clearFields: " + name);

            EditText lText = (EditText) findViewById(R.id.lET);
            EditText glText = (EditText) findViewById(R.id.glET);
            EditText dlText = (EditText) findViewById(R.id.dlET);
            EditText mlText = (EditText) findViewById(R.id.mlET);
            EditText kmText = (EditText) findViewById(R.id.kmET);
            EditText ksText = (EditText) findViewById(R.id.ksET);
            EditText kyText = (EditText) findViewById(R.id.kyET);
            EditText kfText = (EditText) findViewById(R.id.kfET);
            EditText kdText = (EditText) findViewById(R.id.kdET);
            EditText galText = (EditText) findViewById(R.id.galET);
            lText.setText("");
            glText.setText("");
            dlText.setText("");
            mlText.setText("");
            kmText.setText("");
            ksText.setText("");
            kyText.setText("");
            kfText.setText("");
            kdText.setText("");
            galText.setText("");

        } else if (name.equals("cavB") || name.equals("tlB") || name.equals("pdB") || name.equals("sdpB") || name.equals("idtB") ) {

            Log.d("ButtonClick", "clearFields: " + name);

            EditText cavmmText = (EditText) findViewById(R.id.cavmmET);
            EditText cavmsText = (EditText) findViewById(R.id.cavmsET);
            EditText tlText = (EditText) findViewById(R.id.tlET);
            EditText pdText = (EditText) findViewById(R.id.pdET);
            EditText sdpText = (EditText) findViewById(R.id.sdpET);
            EditText idtmText = (EditText) findViewById(R.id.idtmET);
            EditText idtmsText = (EditText) findViewById(R.id.idtmsET);

            if (name.equals("cavB")) {
                cavmmText.setText("");
                cavmsText.setText("");
                idtmText.setText("");
                idtmsText.setText("");
            } else if (name.equals("tlB")) {
                tlText.setText("");
                idtmText.setText("");
                idtmsText.setText("");
            } if (name.equals("pdB")) {
                pdText.setText("");
                idtmText.setText("");
                idtmsText.setText("");
            } if (name.equals("sdpB")) {
                sdpText.setText("");
                idtmText.setText("");
                idtmsText.setText("");
            } else {
                // do nothingy
            }

            /*cavmmText.setText("");
            cavmsText.setText("");
            tlText.setText("");
            pdText.setText("");
            sdpText.setText("");
            idtmText.setText("");
            idtmsText.setText("");*/

        }
    }
}
