package com.twodauns.eget;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class    TestsPicker extends AppCompatActivity {
    int counter = 1;
    TableRow.LayoutParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests_picker);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setSubtitle(MainActivity.contentDescription);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        int monthAmount = 1; //дописать
        int variantAmount = 15; //дописать   // 1-15
        int rowAmount = (int) Math.ceil((double) variantAmount / 5);
        LinearLayout monthList = findViewById(R.id.monthList);
        params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.5f);
        CharSequence[] monthName = {"Февраль"}; //дописать
        TableRow[] rows = new TableRow[rowAmount + 1];
        for (int i = 0; i < monthAmount; i++) {
            int ExtraAmount = 0;
            TextView textView = new TextView(this);
            textView.setText(monthName[i]);
            textView.setTextSize(17f);
            TableLayout tableLayout = new TableLayout(this);
            rows[0] = new TableRow(this);
            rows[0].addView(textView);
            rows[0].setGravity(Gravity.CENTER_HORIZONTAL);
            tableLayout.addView(rows[0], 0);
            for (int j = 1; j <= rowAmount; j++) {
                rows[j] = new TableRow(this);
                for (int k = 0; k < variantAmount / rowAmount; k++) {
                    if (variantAmount % rowAmount == 0) {
                        addButton(rows[j]);
                    } else if (variantAmount == 14) {
                        for (int l = 0; l < 5 && counter <= variantAmount; l++) {
                            addButton(rows[j]);
                        }
                        ExtraAmount++;
                        break;
                    } else {
                        addButton(rows[j]);
                        while (ExtraAmount < variantAmount % rowAmount) {
                            addButton(rows[j]);
                            ExtraAmount++;
                        }
                    }
                }
                tableLayout.addView(rows[j], j);
            }
            monthList.addView(tableLayout);
        }
    }

    public void addButton(TableRow row) {
        Button button = new Button(this);
        button.setText("Вариант " + counter);
        button.setContentDescription("Вариант " + counter);
        button.setTextSize(7f);
        counter++;
        button.setOnClickListener(view -> {
            Intent intent = new Intent(TestsPicker.this, tester.class);
            startActivity(intent);
        });
        button.setLayoutParams(params);
        row.addView(button);
    }

}
