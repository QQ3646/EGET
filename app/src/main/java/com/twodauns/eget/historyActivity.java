package com.twodauns.eget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;

import static java.security.AccessController.getContext;

public class historyActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    SQLiteHelper db;
    Date[] date;
    ArrayList<String> names;
    TableLayout tableLayout;
    TableRow.LayoutParams params;
    View vieww;
    boolean haveFiltered = false;
    boolean dataWasSet = false;
    CheckBox[] checkBoxes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.5f);
        db = new SQLiteHelper(this);
        setSupportActionBar(toolbar);
        tableLayout = findViewById(R.id.tableLay);
        onUpdate();
        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(view -> {
            EditText editText = new EditText(this);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(editText)
                    .setTitle("Введи что-то")
                    .setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            db.setLessons(editText.getText().toString());
                            onUpdate();
                        }
                    })
                    .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
            builder.show();

        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();
        View view1 = inflater.inflate(R.layout.date_alert, null);
        builder.setView(inflater.inflate(R.layout.date_alert, null))
                .setPositiveButton("Применить", (dialog, id1) -> {
                    builder.create();
                    dataWasSet = true;
                })
                .setNegativeButton("Отмена", (dialog, id1) -> dialog.cancel());

        builder.show();
    }

    public void onUpdate(String[] names) {
        tableLayout.removeAllViews();
        ArrayList<SQLiteHelper.NameFromSQL> nameFromSQLS;
        if (names == null)
            nameFromSQLS = db.getLessons();
        else nameFromSQLS = db.getLessons(names, null);
        for (SQLiteHelper.NameFromSQL name :
                nameFromSQLS) {
            Button button = new Button(this);
            button.setText(name.Date + "      " + name.NOS + "        " + name.TP);
            button.setTextSize(20f);
            button.setOnClickListener(view -> {

            });
            tableLayout.addView(button);
        }
    }

    public void onUpdate() {
        onUpdate(null);
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
        getMenuInflater().inflate(R.menu.history, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatemento
        if (id == R.id.action_sort) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            // Get the layout inflater
//            LayoutInflater inflater = this.getLayoutInflater();
//            vieww = inflater.inflate(R.layout.alertfilter, null);
////            TableRow tableRow = vieww.findViewById(R.id.tableRow2);
////            Button button = new Button(this);
////            button.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.01f));
////            button.setOnClickListener(this::onClick);
////            button.setText("Настроить дату");
////            Thread thread = new Thread(() -> {
////                runOnUiThread(() -> {
////                    if (dataWasSet) {
////                        button.setBackgroundColor(getResources().getColor(R.color.datePlus));
////                    } else {
////                        button.setBackgroundColor(getResources().getColor(R.color.dateMinus));
////                    }
////                });
////            });
////            thread.run();
//            //tableRow.addView(button, 1);
//            ImageView button = vieww.findViewById(R.id.imageView2);
//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if(!changeImage)
//                        button.setImageDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_up_24));
//                    else button.setImageDrawable(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down_24));
//                }
//            });
//            LinearLayout linearLayout = vieww.findViewById(R.id.linearLayout);
            int j = 0;
            LayoutInflater inflater = this.getLayoutInflater();
            vieww = inflater.inflate(R.layout.alertfilter, null);
            LinearLayout listOfLessons = vieww.findViewById(R.id.listOfLessons);
            if (!haveFiltered) {
                TreeSet<String> nameSet = db.getName();
                checkBoxes = new CheckBox[nameSet.size()];
                for (String name :
                        nameSet) {
                    checkBoxes[j] = new CheckBox(this);
                    checkBoxes[j].setText(name);
                    checkBoxes[j].setTextSize(20f);
                    listOfLessons.addView(checkBoxes[j]);
                    j++;
                }
            } else {
                int i = 0;
                for (CheckBox name : checkBoxes) {
                    ((LinearLayout) name.getParent()).removeView(name);
                    listOfLessons.addView(name, i);
                    i++;
                }
            }
//            else {
//                listOfLessons.removeAllViews();
//                for (CheckBox name :
//                        checkBoxes) {
//                    listOfLessons.addView(name);
//                }
//            }
//            scrollView.removeAllViews();
//            scrollView.addView(listOfLessons);
            builder.setView(vieww)
                    .setPositiveButton("Применить", (dialog, id1) -> {
                        names = new ArrayList<>();
                        for (CheckBox checkBox :
                                checkBoxes) {
                            if (checkBox.isChecked()) {
                                names.add((String) checkBox.getText());
                            }
                        }
                        if (names.size() != 0) {
                            int k = 0;
                            String[] namesFromFiltr = new String[names.size()];
                            for (String name :
                                    names) {
                                namesFromFiltr[k] = name;
                                k++;
                            }
                            onUpdate(namesFromFiltr);
                            haveFiltered = true;
                        } else {
                            onUpdate();
                            names.clear();
                            haveFiltered = false;
                        }
                    })
                    .setNegativeButton("Отмена", (dialog, id1) -> dialog.cancel());
            if (haveFiltered) {
                builder.setNeutralButton("Сбросить", ((dialogInterface, i) -> {
                    onUpdate();
                    names.clear();
                    haveFiltered = false;
                }));
            }
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            return false;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.main) {
            Intent intent = new Intent(historyActivity.this, MainActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
