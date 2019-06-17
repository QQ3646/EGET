package com.twodauns.eget;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;

public class historyActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    HistorySQLite db;
    int[] date;
    ArrayList<String> names;
    TableLayout tableLayout;
    TableRow.LayoutParams params;
    View subjectsPicker;
    boolean haveFiltered = false;
    boolean isDataWasSet[];
    CalendarView calendar[];
    CheckBox[] checkBoxes;
    String[] namesFromFiltr;
    byte counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.5f);
        db = new HistorySQLite(this);
        setSupportActionBar(toolbar);
        isDataWasSet = new boolean[]{false, false};
        calendar = new CalendarView[]{null, null};
        tableLayout = findViewById(R.id.tableLay);
        update(null, null);
        date = new int[2];
        date[0] = 0;
        date[1] = 0; //Даты на ноль
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void update(String[] names, int[] dates) {
        tableLayout.removeAllViews();
        ArrayList<HistorySQLite.NameFromSQL> nameFromSQLS = db.getLessons(names, dates);
        for (HistorySQLite.NameFromSQL name :
                nameFromSQLS) {
            Integer fakeDate = name.Date;
            SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
            try {
                Date date = originalFormat.parse(fakeDate.toString());
                Button button = new Button(this);
                button.setText(new SimpleDateFormat("dd.MM.yyyy").format(date) + "    " + name.NOS + "    " + name.TP);
                if (name.NOS.contains("Математика"))
                    button.setTextSize(12.5f);
                else
                    button.setTextSize(18f);
                button.setOnClickListener(view -> {

                });
                tableLayout.addView(button);
            } catch (ParseException e) {
                e.printStackTrace();
            }
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
        getMenuInflater().inflate(R.menu.history, menu);
        return true;
    }

    //@RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatemento

        if (id == R.id.action_sort) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            int j = 0;
            LayoutInflater inflater = this.getLayoutInflater();
            subjectsPicker = inflater.inflate(R.layout.alertfilter, null);
            LinearLayout listOfLessons = subjectsPicker.findViewById(R.id.listOfLessons);
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
            builder.setView(subjectsPicker)
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
                            namesFromFiltr = new String[names.size()];
                            for (String name :
                                    names) {
                                namesFromFiltr[k] = name;
                                k++;
                            }
                            update(namesFromFiltr, null);
                            haveFiltered = true;
                        } else {
                            update(null, null);
                            names.clear();
                            haveFiltered = false;
                        }
                    })
                    .setNegativeButton("Отмена", (dialog, id1) -> dialog.cancel());
            if (haveFiltered) {
                builder.setNeutralButton("Сбросить", ((dialogInterface, i) -> {
                    update(null, null);
                    names.clear();
                    haveFiltered = false;
                }));
            }
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            return false;
        } else if (id == R.id.Ot || id == R.id.doo) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            View dateAlert = inflater.inflate(R.layout.date_alert, null);
            TableRow calendarViewTR = dateAlert.findViewById(R.id.anotherTR);
            if (id == R.id.Ot) counter = 0;
            else counter = 1;

            if (!isDataWasSet[counter]) {
                calendar[counter] = new CalendarView(this);
                calendar[counter].setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                        date[counter] = getDate(i, i1, i2);
                    }
                });
                calendarViewTR.addView(calendar[counter], 1);
            } else {
                ((TableRow) calendar[counter].getParent()).removeView(calendar[counter]);
                calendarViewTR.addView(calendar[counter], 1);
            }
            builder.setView(dateAlert)
                    .setNegativeButton("Отмена", (dialogInterface, i) -> {
                        date[counter] = 0;
                        dialogInterface.cancel();
                    })
                    .setPositiveButton("Применить", (dialogInterface, i) -> {
                        update(namesFromFiltr, date);
                        isDataWasSet[counter] = true;
                    });
            if (isDataWasSet[counter]) {
                builder.setNeutralButton("Сбросить", (dialogInterface, i) -> {
                    date[counter] = 0;
                    update(namesFromFiltr, null);
                    isDataWasSet[counter] = false;
                });
            }
            builder.show();

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

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (date[0] != 0) {
            try {
                setTitle(menu.findItem(R.id.Ot),"От: " + new SimpleDateFormat("dd.MM.yyyy").format(new SimpleDateFormat("yyyyMMdd").parse(String.valueOf(date[0]))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            setTitle(menu.findItem(R.id.Ot),"От");
        }
        if (date[1] != 0) {
            try {
                setTitle(menu.findItem(R.id.doo), "До: " + new SimpleDateFormat("dd.MM.yyyy").format(new SimpleDateFormat("yyyyMMdd").parse(String.valueOf(date[1]))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            setTitle(menu.findItem(R.id.doo),"До");;
        }
        return super.onPrepareOptionsMenu(menu);
    }

    public int getDate(int year, int month, int day) {
        Date date = new Date(year - 1900, month, day);
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
        Integer integer = Integer.parseInt(originalFormat.format(date));
        return integer;
    }

    public void setTitle(MenuItem menuItem, String name){
        menuItem.setTitle(name);
        invalidateOptionsMenu();
    }
}
