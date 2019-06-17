package com.twodauns.eget;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    static CharSequence contentDescription;
//    static
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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void selectOnClick(View view) {
        Button button = (Button) view;
        CharSequence massage = null;
        CharSequence[] nomination = new CharSequence[0];
        String nSubject = (String) button.getContentDescription();
        switch (nSubject) {
            case "Математика": {
                massage = "Выберите уровень";
                nomination = new CharSequence[]{"Математика: базовый уровень", "Математика: профильный уровень"};
                break;
            }
            case "Остальные языки": {
                massage = "Выберите конкретный язык";
                nomination = new CharSequence[]{"Немецкий язык", "Французский язык", "Испанский язык"};
                break;
            }
        }
        onCreateDialog(nomination, massage);
    }

    public void onClick(View view) {
        Button button = (Button) view;
        contentDescription = button.getContentDescription();
        selectSubject();
    }

    @NonNull
    public Dialog onCreateDialog(final CharSequence[] nomination, final CharSequence massage) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(massage)
                .setItems(nomination, (dialogInterface, i) -> {
                    contentDescription = nomination[i];
//                    if(selectSubject()) {
//                        Intent intent = new Intent(MainActivity.this, TestsPicker.class);
//                        startActivity(intent);
//                    }
                    selectSubject();
                })
                .setNegativeButton("Отмена", (dialogInterface, i) -> dialogInterface.cancel());
        return builder.show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void selectSubject(){
        File file = new File(this.getFilesDir().getAbsolutePath() + File.separator + contentDescription);
        if(!file.exists()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Скачать варианты?")
                    .setPositiveButton("Подтвердить", (dialog, which) -> {
                        file.mkdirs();
//                            file.list();
                        Intent intent = new Intent(MainActivity.this, TestsPicker.class);
                        intent.putExtra("Month count", file.list().length);
                        for (int i = 0; i<file.list().length; i++) {
                            File f = new File(MainActivity.this.getFilesDir().getAbsolutePath() + File.separator + file.list()[i]);
                            f.list();
                        }
                        startActivity(intent);
                    })
                    .setNegativeButton("Отмена", (dialog, which) -> dialog.cancel());
            builder.show();
        } else {
            Intent intent = new Intent(MainActivity.this, TestsPicker.class);
            startActivity(intent);
        }
    }
    public static int color;
//    @Override
//    protected void onDestroy() {
//
//        super.onDestroy();
//    }

    @Override
    protected void onPause() {
        color = selectColor();
        super.onPause();
    }

    public int selectColor(){

        switch ((String) contentDescription){
            case "Русский язык":
                return ContextCompat.getColor(this, R.color.rus);
            case "Информатика":
                return ContextCompat.getColor(this ,R.color.inf);
            case "Английский язык":
                return ContextCompat.getColor(this ,R.color.eng);
            case "Математика: профильный уровень":
                return ContextCompat.getColor(this ,R.color.mthP);
            case "Математика: базовый уровень":
                return ContextCompat.getColor(this ,R.color.mthB);
            case "География":
                return ContextCompat.getColor(this ,R.color.geo);
            case "Физика":
                return ContextCompat.getColor(this ,R.color.phs);
            case "Химия":
                return ContextCompat.getColor(this ,R.color.chm);
            case "Биология":
                return ContextCompat.getColor(this ,R.color.bio);
            case "Обществознание":
                return ContextCompat.getColor(this ,R.color.scl);
            case "Литература":
                return ContextCompat.getColor(this ,R.color.ltr);
            case "История":
                return ContextCompat.getColor(this ,R.color.hst);
            case "Немецкий язык":
                return ContextCompat.getColor(this ,R.color.german);
            case "Французский язык":
                return ContextCompat.getColor(this ,R.color.france);
            case "Испанский язык":
                return ContextCompat.getColor(this ,R.color.spanish);
//            case "Русский язык":
//                return ContextCompat.getColor(this ,R.color.rus);
//            case "Русский язык":
//                return ContextCompat.getColor(this ,R.color.rus);
        }
        return 0;
    }
}

