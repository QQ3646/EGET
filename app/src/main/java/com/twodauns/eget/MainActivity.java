package com.twodauns.eget;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    static CharSequence contentDescription;
//    Button buttons[];
//    int id[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        supportRequestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
//        int[] id = {R.id.butBio, R.id.butChm, R.id.butEng, R.id.butGeo, R.id.butGFS, R.id.butHst,
//                R.id.butInf, R.id.butRus, R.id.butPhs, R.id.butLtr, R.id.butMth, R.id.butScl};
//        Button[] buttons = new Button[12];
//        for (int i = 0; i < 12; i++) {
//
//        }
//        int id[];
//        id = new int[]{R.id.butBio, R.id.butChm, R.id.butEng, R.id.butGeo, R.id.butGFS, R.id.butHst,
//                R.id.butInf, R.id.butRus, R.id.butPhs, R.id.butLtr, R.id.butMth, R.id.butScl};
//        for (int i = 0; i < 12; i++) {
//            Button buttons = findViewById(id[i]);
//            //  buttons.setHeight((int)((findViewById(R.id.tableLayout).getHeight()-40-176)/20*0.01));
//            buttons.setMaxHeight(1);
//        }

        Toolbar toolbar = (Toolbar) findViewById(R. id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        int[] id;
//        id = new int[]{R.id.butBio, R.id.butChm, R.id.butEng, R.id.butGeo, R.id.butGFS, R.id.butHst,
//                R.id.butInf, R.id.butRus, R.id.butPhs, R.id.butLtr, R.id.butMth, R.id.butScl};
//        for (int i = 0; i < 12; i++) {
//            Button buttons = findViewById(id[i]);
//          //  buttons.setHeight((int)((findViewById(R.id.tableLayout).getHeight()-40-176)/20*0.01));
//            buttons.setHeight(1);
//        }
//    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//        int d = (findViewById(R.id.tableLayout).getHeight()-40) / 4;
//
//    }
//
    public void selectOnClick(View view) {
        Button button = (Button) view;
        CharSequence massage = null;
        CharSequence[] nomination = new CharSequence[0];
        String nSubject = (String) button.getContentDescription();
        switch (nSubject) {
            case "Математика":{
                massage = "Выберите уровень";
                nomination = new CharSequence[] {"Базовый уровень","Профильный уровень"};
                break;
            }
            case "Остальные языки": {
                massage = "Выберите конкретный язык";
                nomination = new CharSequence[] {"Немецикий язык","Французкий язык","Испанский язык"};
                break;
            }
        }
//        if(nSubject == "Математика") {
//                massage = "Выберите уровень";
//                nomination = new CharSequence[] {"Базовый уровень","Профильный уровень"};
//        } else if(nSubject == "Остальные языки") {
//                massage = "Выберите конкретный язык";
//                nomination = new CharSequence[] {"Немецикий язык","Французкий язык","Испанский язык"};
//        }
        onCreateDialog(nomination, massage);
    }

    public void onClick(View view) {
        Button button = (Button) view;
        contentDescription = button.getContentDescription();
        Intent intent = new Intent(MainActivity.this, TestsPicker.class);
        startActivity(intent);
    }
    @NonNull
    public Dialog onCreateDialog(final CharSequence[] nomination, final CharSequence massage) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(massage)
                .setItems(nomination, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CharSequence[] newNom;
                        if(massage == "Выберите уровень") {
                            newNom = new CharSequence[]{"Математика: базовый уровень", "Математика: профильный уровень"};
                            contentDescription = newNom[i];
                        } else contentDescription = nomination[i];

                        Intent intent = new Intent(MainActivity.this, TestsPicker.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        return builder.show();
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.history) {
            Intent intent = new Intent(MainActivity.this, historyActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

