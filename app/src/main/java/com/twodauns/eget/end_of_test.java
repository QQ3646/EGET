package com.twodauns.eget;

import android.content.Intent;
import android.graphics.Color;
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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static com.twodauns.eget.tester.questions;

public class end_of_test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_of_test);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int line;

        int primaryScore = 0;
        int secondaryScore = 0;
        Button close = findViewById(R.id.close);
        close.setOnClickListener(this::save);

        LinearLayout mainLayout = findViewById(R.id.viewer);

        TextView finalMark = findViewById(R.id.finalScore);

        if (MainActivity.contentDescription.equals("История")) {

            line = 4;
            int i = 0;
            // if(tester.questions[0].)
            for (tester.Question q :
                    questions) {
                if (q != null && i < tester.notC) {
                    Button show = new Button(this);
                    show.setText("Посмотреть");
                    int finalI = i;

                    TableRow tableRow = new TableRow(this);
                    TextView number = new TextView(this);
                    number.setGravity(Gravity.CENTER);
                    number.setText("№" + (i + 1));
                    number.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2f));

                    TextView correctOrNot = new TextView(this);
                    correctOrNot.setGravity(Gravity.CENTER);
                    correctOrNot.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.5f));
                    show.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2f));
                    switch (i) {
                        case 0: {
                            for (CharSequence answ :
                                    q.correctAnswer) {
                                if (answ.equals(tester.answers[i])) {
                                    correctOrNot.setTextColor(Color.GREEN);
                                    correctOrNot.setText("Правильно");
                                    primaryScore++;
                                    break;
                                } else {
                                    correctOrNot.setTextColor(Color.RED);
                                    correctOrNot.setText("Не правильно");
                                }
                            }
                            break;
                        }
                        case 1: {
                            String answ = tester.answers[i].replace(" ", "");
                            if (q.correctAnswer.get(0).equals(answ)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore++;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 2: {
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore++;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 3: {
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 4: {
                            if (q.correctAnswer.get(0).equals(tester.answers[i])) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 5: {
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 6: {
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 7:{
                            if (q.correctAnswer.get(0).equals(tester.answers[i])) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 8:{
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }

                    }
                    show.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(end_of_test.this, end_of_test_viewer.class);
                            Bundle bundle = new Bundle();
                            if(correctOrNot.getText().equals("Правильно")) {
                                intent.putExtra("trueOrFalse", true);
//                                bundle.putBoolean("trueOrFalse", true);
                            } else {
                                intent.putExtra("trueOrFalse", false);
//                                bundle.putBoolean("trueOrFalse", false);
                            }
//                            bundle.putInt("Number", finalI);
                            intent.putExtra("Number", finalI);
                            startActivity(intent);
                        }
                    });
                    if(questions[i].question.getParent() != null)
                        ((TableLayout)questions[i].question.getParent()).removeAllViews();
                    TableLayout.LayoutParams params = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT, .5f);
                    //  params.setMargins(1,2,1,2);
                    tableRow.setLayoutParams(params);
                    //tableRow.setBackgroundResource(R.drawable.border);
                    tableRow.addView(number);
                    tableRow.addView(correctOrNot);
                    tableRow.addView(show);
                    mainLayout.addView(tableRow);
                }
                i++;
            }
        } else if (MainActivity.contentDescription.equals("Биология")) {
            line = 3;
            int i = 0;
            // if(tester.questions[0].)
            for (tester.Question q :
                    questions) {
                if (q != null && i < tester.notC) {
                    Button show = new Button(this);
                    show.setText("Посмотреть");
                    int finalI = i;

                    TableRow tableRow = new TableRow(this);
                    TextView number = new TextView(this);
                    number.setGravity(Gravity.CENTER);
                    number.setText("№" + (i + 1));
                    number.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2f));

                    TextView correctOrNot = new TextView(this);
                    correctOrNot.setGravity(Gravity.CENTER);
                    correctOrNot.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.5f));
                    show.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2f));
                    switch (i) {
                        case 0: {
                            for (CharSequence answ :
                                    q.correctAnswer) {
                                if (answ.equals(tester.answers[i])) {
                                    correctOrNot.setTextColor(Color.GREEN);
                                    correctOrNot.setText("Правильно");
                                    primaryScore++;
                                    break;
                                } else {
                                    correctOrNot.setTextColor(Color.RED);
                                    correctOrNot.setText("Не правильно");
                                }
                            }
                            break;
                        }
                        case 1: {
                            String answ = tester.answers[i].replace(" ", "");
                            if (q.correctAnswer.get(0).equals(answ)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore++;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 2: {
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore++;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 3: {
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 4: {
                            if (q.correctAnswer.get(0).equals(tester.answers[i])) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 5: {
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 6: {
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 7:{
                            if (q.correctAnswer.get(0).equals(tester.answers[i])) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 8:{
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }

                    }
                    show.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(end_of_test.this, end_of_test_viewer.class);
                            Bundle bundle = new Bundle();
                            if(correctOrNot.getText().equals("Правильно")) {
                                intent.putExtra("trueOrFalse", true);
//                                bundle.putBoolean("trueOrFalse", true);
                            } else {
                                intent.putExtra("trueOrFalse", false);
//                                bundle.putBoolean("trueOrFalse", false);
                            }
//                            bundle.putInt("Number", finalI);
                            intent.putExtra("Number", finalI);
                            startActivity(intent);
                        }
                    });
                    if(questions[i].question.getParent() != null)
                        ((TableLayout)questions[i].question.getParent()).removeAllViews();
                    TableLayout.LayoutParams params = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT, .5f);
                    //  params.setMargins(1,2,1,2);
                    tableRow.setLayoutParams(params);
                    //tableRow.setBackgroundResource(R.drawable.border);
                    tableRow.addView(number);
                    tableRow.addView(correctOrNot);
                    tableRow.addView(show);
                    mainLayout.addView(tableRow);
                }
                i++;
            }
        } else if (MainActivity.contentDescription.equals("Математика: профильный уровень")) {
            line = 1;
            int i = 0;
            // if(tester.questions[0].)
            for (tester.Question q :
                    questions) {
                if (q != null && i < tester.notC) {
                    Button show = new Button(this);
                    show.setText("Посмотреть");
                    int finalI = i;

                    TableRow tableRow = new TableRow(this);
                    TextView number = new TextView(this);
                    number.setGravity(Gravity.CENTER);
                    number.setText("№" + (i + 1));
                    number.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2f));

                    TextView correctOrNot = new TextView(this);
                    correctOrNot.setGravity(Gravity.CENTER);
                    correctOrNot.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.5f));
                    show.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2f));
                    switch (i) {
                        case 0: {
                            for (CharSequence answ :
                                    q.correctAnswer) {
                                if (answ.equals(tester.answers[i])) {
                                    correctOrNot.setTextColor(Color.GREEN);
                                    correctOrNot.setText("Правильно");
                                    primaryScore++;
                                    break;
                                } else {
                                    correctOrNot.setTextColor(Color.RED);
                                    correctOrNot.setText("Не правильно");
                                }
                            }
                            break;
                        }
                        case 1: {
                            String answ = tester.answers[i].replace(" ", "");
                            if (q.correctAnswer.get(0).equals(answ)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore++;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 2: {
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore++;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 3: {
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 4: {
                            if (q.correctAnswer.get(0).equals(tester.answers[i])) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 5: {
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 6: {
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 7:{
                            if (q.correctAnswer.get(0).equals(tester.answers[i])) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 8:{
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }

                    }
                    show.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(end_of_test.this, end_of_test_viewer.class);
                            Bundle bundle = new Bundle();
                            if(correctOrNot.getText().equals("Правильно")) {
                                intent.putExtra("trueOrFalse", true);
//                                bundle.putBoolean("trueOrFalse", true);
                            } else {
                                intent.putExtra("trueOrFalse", false);
//                                bundle.putBoolean("trueOrFalse", false);
                            }
//                            bundle.putInt("Number", finalI);
                            intent.putExtra("Number", finalI);
                            startActivity(intent);
                        }
                    });
                    if(questions[i].question.getParent() != null)
                        ((TableLayout)questions[i].question.getParent()).removeAllViews();
                    TableLayout.LayoutParams params = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT, .5f);
                    //  params.setMargins(1,2,1,2);
                    tableRow.setLayoutParams(params);
                    //tableRow.setBackgroundResource(R.drawable.border);
                    tableRow.addView(number);
                    tableRow.addView(correctOrNot);
                    tableRow.addView(show);
                    mainLayout.addView(tableRow);
                }
                i++;
            }
        } else if (MainActivity.contentDescription.equals("Математика: базовый уровень")) {
            line = 2;
            int i = 0;
            // if(tester.questions[0].)
            for (tester.Question q :
                    questions) {
                if (q != null && i < tester.notC) {
                    Button show = new Button(this);
                    show.setText("Посмотреть");
                    int finalI = i;

                    TableRow tableRow = new TableRow(this);
                    TextView number = new TextView(this);
                    number.setGravity(Gravity.CENTER);
                    number.setText("№" + (i + 1));
                    number.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2f));

                    TextView correctOrNot = new TextView(this);
                    correctOrNot.setGravity(Gravity.CENTER);
                    correctOrNot.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.5f));
                    show.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2f));
                    switch (i) {
                        case 0: {
                            for (CharSequence answ :
                                    q.correctAnswer) {
                                if (answ.equals(tester.answers[i])) {
                                    correctOrNot.setTextColor(Color.GREEN);
                                    correctOrNot.setText("Правильно");
                                    primaryScore++;
                                    break;
                                } else {
                                    correctOrNot.setTextColor(Color.RED);
                                    correctOrNot.setText("Не правильно");
                                }
                            }
                            break;
                        }
                        case 1: {
                            String answ = tester.answers[i].replace(" ", "");
                            if (q.correctAnswer.get(0).equals(answ)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore++;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 2: {
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore++;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 3: {
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 4: {
                            if (q.correctAnswer.get(0).equals(tester.answers[i])) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 5: {
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 6: {
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 7:{
                            if (q.correctAnswer.get(0).equals(tester.answers[i])) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 8:{
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }

                    }
                    show.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(end_of_test.this, end_of_test_viewer.class);
                            Bundle bundle = new Bundle();
                            if(correctOrNot.getText().equals("Правильно")) {
                                intent.putExtra("trueOrFalse", true);
//                                bundle.putBoolean("trueOrFalse", true);
                            } else {
                                intent.putExtra("trueOrFalse", false);
//                                bundle.putBoolean("trueOrFalse", false);
                            }
//                            bundle.putInt("Number", finalI);
                            intent.putExtra("Number", finalI);
                            startActivity(intent);
                        }
                    });
                    if(questions[i].question.getParent() != null)
                        ((TableLayout)questions[i].question.getParent()).removeAllViews();
                    TableLayout.LayoutParams params = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT, .5f);
                    //  params.setMargins(1,2,1,2);
                    tableRow.setLayoutParams(params);
                    //tableRow.setBackgroundResource(R.drawable.border);
                    tableRow.addView(number);
                    tableRow.addView(correctOrNot);
                    tableRow.addView(show);
                    mainLayout.addView(tableRow);
                }
                i++;
            }
        } else if (MainActivity.contentDescription.equals("Английский язык")) {
            line = 7;
            int i = 0;
            // if(tester.questions[0].)
            for (tester.Question q :
                    questions) {
                if (q != null && i < tester.notC) {
                    Button show = new Button(this);
                    show.setText("Посмотреть");
                    int finalI = i;

                    TableRow tableRow = new TableRow(this);
                    TextView number = new TextView(this);
                    number.setGravity(Gravity.CENTER);
                    number.setText("№" + (i + 1));
                    number.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2f));

                    TextView correctOrNot = new TextView(this);
                    correctOrNot.setGravity(Gravity.CENTER);
                    correctOrNot.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.5f));
                    show.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2f));
                    switch (i) {
                        case 0: {
                            for (CharSequence answ :
                                    q.correctAnswer) {
                                if (answ.equals(tester.answers[i])) {
                                    correctOrNot.setTextColor(Color.GREEN);
                                    correctOrNot.setText("Правильно");
                                    primaryScore++;
                                    break;
                                } else {
                                    correctOrNot.setTextColor(Color.RED);
                                    correctOrNot.setText("Не правильно");
                                }
                            }
                            break;
                        }
                        case 1: {
                            String answ = tester.answers[i].replace(" ", "");
                            if (q.correctAnswer.get(0).equals(answ)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore++;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 2: {
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore++;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 3: {
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 4: {
                            if (q.correctAnswer.get(0).equals(tester.answers[i])) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 5: {
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 6: {
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 7:{
                            if (q.correctAnswer.get(0).equals(tester.answers[i])) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 8:{
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }

                    }
                    show.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(end_of_test.this, end_of_test_viewer.class);
                            Bundle bundle = new Bundle();
                            if(correctOrNot.getText().equals("Правильно")) {
                                intent.putExtra("trueOrFalse", true);
//                                bundle.putBoolean("trueOrFalse", true);
                            } else {
                                intent.putExtra("trueOrFalse", false);
//                                bundle.putBoolean("trueOrFalse", false);
                            }
//                            bundle.putInt("Number", finalI);
                            intent.putExtra("Number", finalI);
                            startActivity(intent);
                        }
                    });
                    if(questions[i].question.getParent() != null)
                        ((TableLayout)questions[i].question.getParent()).removeAllViews();
                    TableLayout.LayoutParams params = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT, .5f);
                    //  params.setMargins(1,2,1,2);
                    tableRow.setLayoutParams(params);
                    //tableRow.setBackgroundResource(R.drawable.border);
                    tableRow.addView(number);
                    tableRow.addView(correctOrNot);
                    tableRow.addView(show);
                    mainLayout.addView(tableRow);
                }
                i++;
            }
        } else if (MainActivity.contentDescription.equals("Русский язык")) {
            line = 0;
        } else if (MainActivity.contentDescription.equals("Немецкий язык") || MainActivity.contentDescription.equals("Французкий язык") || MainActivity.contentDescription.equals("Испанский язык")) {
            line = 7;
        } else if (MainActivity.contentDescription.equals("Информатика")) {
            line = 10;
        } else if (MainActivity.contentDescription.equals("Химия")) {
            line = 6;
        } else if (MainActivity.contentDescription.equals("Физика")) {
            line = 5;
            int i = 0;
            // if(tester.questions[0].)
            for (tester.Question q :
                    questions) {
                if (q != null && i < tester.notC) {
                    Button show = new Button(this);
                    show.setText("Посмотреть");
                    int finalI = i;

                    TableRow tableRow = new TableRow(this);
                    TextView number = new TextView(this);
                    number.setGravity(Gravity.CENTER);
                    number.setText("№" + (i + 1));
                    number.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2f));

                    TextView correctOrNot = new TextView(this);
                    correctOrNot.setGravity(Gravity.CENTER);
                    correctOrNot.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.5f));
                    show.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2f));
                    switch (i) {
                        case 0: {
                            for (CharSequence answ :
                                    q.correctAnswer) {
                                if (answ.equals(tester.answers[i])) {
                                    correctOrNot.setTextColor(Color.GREEN);
                                    correctOrNot.setText("Правильно");
                                    primaryScore++;
                                    break;
                                } else {
                                    correctOrNot.setTextColor(Color.RED);
                                    correctOrNot.setText("Не правильно");
                                }
                            }
                            break;
                        }
                        case 1: {
                            String answ = tester.answers[i].replace(" ", "");
                            if (q.correctAnswer.get(0).equals(answ)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore++;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 2: {
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore++;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 3: {
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 4: {
                            if (q.correctAnswer.get(0).equals(tester.answers[i])) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 5: {
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 6: {
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 7:{
                            if (q.correctAnswer.get(0).equals(tester.answers[i])) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 8:{
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }

                    }
                    show.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(end_of_test.this, end_of_test_viewer.class);
                            Bundle bundle = new Bundle();
                            if(correctOrNot.getText().equals("Правильно")) {
                                intent.putExtra("trueOrFalse", true);
//                                bundle.putBoolean("trueOrFalse", true);
                            } else {
                                intent.putExtra("trueOrFalse", false);
//                                bundle.putBoolean("trueOrFalse", false);
                            }
//                            bundle.putInt("Number", finalI);
                            intent.putExtra("Number", finalI);
                            startActivity(intent);
                        }
                    });
                    if(questions[i].question.getParent() != null)
                        ((TableLayout)questions[i].question.getParent()).removeAllViews();
                    TableLayout.LayoutParams params = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT, .5f);
                    //  params.setMargins(1,2,1,2);
                    tableRow.setLayoutParams(params);
                    //tableRow.setBackgroundResource(R.drawable.border);
                    tableRow.addView(number);
                    tableRow.addView(correctOrNot);
                    tableRow.addView(show);
                    mainLayout.addView(tableRow);
                }
                i++;
            }
        } else if (MainActivity.contentDescription.equals("Литература")) {
            line = 9;
            int i = 0;
            // if(tester.questions[0].)
            for (tester.Question q :
                    questions) {
                if (q != null && i < tester.notC) {
                    Button show = new Button(this);
                    show.setText("Посмотреть");
                    int finalI = i;

                    TableRow tableRow = new TableRow(this);
                    TextView number = new TextView(this);
                    number.setGravity(Gravity.CENTER);
                    number.setText("№" + (i + 1));
                    number.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2f));

                    TextView correctOrNot = new TextView(this);
                    correctOrNot.setGravity(Gravity.CENTER);
                    correctOrNot.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.5f));
                    show.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2f));
                    switch (i) {
                        case 0: {
                            for (CharSequence answ :
                                    q.correctAnswer) {
                                if (answ.equals(tester.answers[i])) {
                                    correctOrNot.setTextColor(Color.GREEN);
                                    correctOrNot.setText("Правильно");
                                    primaryScore++;
                                    break;
                                } else {
                                    correctOrNot.setTextColor(Color.RED);
                                    correctOrNot.setText("Не правильно");
                                }
                            }
                            break;
                        }
                        case 1: {
                            String answ = tester.answers[i].replace(" ", "");
                            if (q.correctAnswer.get(0).equals(answ)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore++;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 2: {
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore++;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 3: {
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 4: {
                            if (q.correctAnswer.get(0).equals(tester.answers[i])) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 5: {
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 6: {
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 7:{
                            if (q.correctAnswer.get(0).equals(tester.answers[i])) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 8:{
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }

                    }
                    show.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(end_of_test.this, end_of_test_viewer.class);
                            Bundle bundle = new Bundle();
                            if(correctOrNot.getText().equals("Правильно")) {
                                intent.putExtra("trueOrFalse", true);
//                                bundle.putBoolean("trueOrFalse", true);
                            } else {
                                intent.putExtra("trueOrFalse", false);
//                                bundle.putBoolean("trueOrFalse", false);
                            }
//                            bundle.putInt("Number", finalI);
                            intent.putExtra("Number", finalI);
                            startActivity(intent);
                        }
                    });
                    if(questions[i].question.getParent() != null)
                        ((TableLayout)questions[i].question.getParent()).removeAllViews();
                    TableLayout.LayoutParams params = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT, .5f);
                    //  params.setMargins(1,2,1,2);
                    tableRow.setLayoutParams(params);
                    //tableRow.setBackgroundResource(R.drawable.border);
                    tableRow.addView(number);
                    tableRow.addView(correctOrNot);
                    tableRow.addView(show);
                    mainLayout.addView(tableRow);
                }
                i++;
            }
        } else if (MainActivity.contentDescription.equals("Обществознание")) {
            int i = 0;
            line = 2;
            // if(tester.questions[0].)
            for (tester.Question q :
                    questions) {
                if (q != null && i < tester.notC) {
                    Button show = new Button(this);
                    show.setText("Посмотреть");
                    int finalI = i;

                    TableRow tableRow = new TableRow(this);
                    TextView number = new TextView(this);
                    number.setGravity(Gravity.CENTER);
                    number.setText("№" + (i + 1));
                    number.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2f));

                    TextView correctOrNot = new TextView(this);
                    correctOrNot.setGravity(Gravity.CENTER);
                    correctOrNot.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.5f));
                    show.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2f));
                    switch (i) {
                        case 0: {
                            for (CharSequence answ :
                                    q.correctAnswer) {
                                if (answ.equals(tester.answers[i])) {
                                    correctOrNot.setTextColor(Color.GREEN);
                                    correctOrNot.setText("Правильно");
                                    primaryScore++;
                                    break;
                                } else {
                                    correctOrNot.setTextColor(Color.RED);
                                    correctOrNot.setText("Не правильно");
                                }
                            }
                            break;
                        }
                        case 1: {
                            String answ = tester.answers[i].replace(" ", "");
                            if (q.correctAnswer.get(0).equals(answ)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore++;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 2: {
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore++;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 3: {
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 4: {
                            if (q.correctAnswer.get(0).equals(tester.answers[i])) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 5: {
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 6: {
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 7:{
                            if (q.correctAnswer.get(0).equals(tester.answers[i])) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 8:{
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }

                    }
                    show.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(end_of_test.this, end_of_test_viewer.class);
                            Bundle bundle = new Bundle();
                            if(correctOrNot.getText().equals("Правильно")) {
                                intent.putExtra("trueOrFalse", true);
//                                bundle.putBoolean("trueOrFalse", true);
                            } else {
                                intent.putExtra("trueOrFalse", false);
//                                bundle.putBoolean("trueOrFalse", false);
                            }
//                            bundle.putInt("Number", finalI);
                            intent.putExtra("Number", finalI);
                            startActivity(intent);
                        }
                    });
                    if(questions[i].question.getParent() != null)
                    ((TableLayout)questions[i].question.getParent()).removeAllViews();
                    TableLayout.LayoutParams params = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT, .5f);
                    //  params.setMargins(1,2,1,2);
                    tableRow.setLayoutParams(params);
                    //tableRow.setBackgroundResource(R.drawable.border);
                    tableRow.addView(number);
                    tableRow.addView(correctOrNot);
                    tableRow.addView(show);
                    mainLayout.addView(tableRow);
                }
                i++;
            }

        } else { //География
            line = 11;
            int i = 0;
            // if(tester.questions[0].)
            for (tester.Question q :
                    questions) {
                if (q != null && i < tester.notC) {
                    Button show = new Button(this);
                    show.setText("Посмотреть");
                    int finalI = i;

                    TableRow tableRow = new TableRow(this);
                    TextView number = new TextView(this);
                    number.setGravity(Gravity.CENTER);
                    number.setText("№" + (i + 1));
                    number.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2f));

                    TextView correctOrNot = new TextView(this);
                    correctOrNot.setGravity(Gravity.CENTER);
                    correctOrNot.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.5f));
                    show.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2f));
                    switch (i) {
                        case 0: {
                            for (CharSequence answ :
                                    q.correctAnswer) {
                                if (answ.equals(tester.answers[i])) {
                                    correctOrNot.setTextColor(Color.GREEN);
                                    correctOrNot.setText("Правильно");
                                    primaryScore++;
                                    break;
                                } else {
                                    correctOrNot.setTextColor(Color.RED);
                                    correctOrNot.setText("Не правильно");
                                }
                            }
                            break;
                        }
                        case 1: {
                            String answ = tester.answers[i].replace(" ", "");
                            if (q.correctAnswer.get(0).equals(answ)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore++;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 2: {
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore++;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 3: {
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 4: {
                            if (q.correctAnswer.get(0).equals(tester.answers[i])) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 5: {
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 6: {
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 7:{
                            if (q.correctAnswer.get(0).equals(tester.answers[i])) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }
                        case 8:{
                            char[] sol = q.correctAnswer.get(0).toString().toCharArray();
                            Arrays.sort(sol);
                            char[] ans = tester.answers[i].toCharArray();
                            Arrays.sort(ans);
                            if (Arrays.equals(ans, sol)) {
                                correctOrNot.setTextColor(Color.GREEN);
                                correctOrNot.setText("Правильно");
                                primaryScore += 2;
                                break;
                            } else {
                                correctOrNot.setTextColor(Color.RED);
                                correctOrNot.setText("Не правильно");
                            }
                            break;
                        }

                    }
                    show.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(end_of_test.this, end_of_test_viewer.class);
                            Bundle bundle = new Bundle();
                            if(correctOrNot.getText().equals("Правильно")) {
                                intent.putExtra("trueOrFalse", true);
//                                bundle.putBoolean("trueOrFalse", true);
                            } else {
                                intent.putExtra("trueOrFalse", false);
//                                bundle.putBoolean("trueOrFalse", false);
                            }
//                            bundle.putInt("Number", finalI);
                            intent.putExtra("Number", finalI);
                            startActivity(intent);
                        }
                    });
                    if(questions[i].question.getParent() != null)
                        ((TableLayout)questions[i].question.getParent()).removeAllViews();
                    TableLayout.LayoutParams params = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT, .5f);
                    //  params.setMargins(1,2,1,2);
                    tableRow.setLayoutParams(params);
                    //tableRow.setBackgroundResource(R.drawable.border);
                    tableRow.addView(number);
                    tableRow.addView(correctOrNot);
                    tableRow.addView(show);
                    mainLayout.addView(tableRow);
                }
                i++;
            }
        }
        byte[] buffer = null;
        InputStream is;
        try {
            is = getAssets().open("pIntos.csv");
            int size = is.available();
            buffer = new byte[size];
            is.read(buffer);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String str = new String(buffer);
        int b = 0;
        for (int j = line; j < str.split(";").length && b <= primaryScore; j += 12) {
            secondaryScore = Integer.parseInt(str.split(";")[j]);
            b++;
        }

//        try {
//            BufferedReader br = new BufferedReader(new FileReader(classLoader.getResource("pIntos.csv").getFile()));
//            while ((lineFromFile = br.readLine()) != null) {
//                String[] set = lineFromFile.split(cvsSplitBy);
//                primeInToSec[i] = Integer.parseInt(set[line]);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        while ((lineFromFile = br.) != null) {
//                String[] set = lineFromFile.split(cvsSplitBy);
//
//
//            }
        finalMark.setText("Итоговый первичный балл " + primaryScore + "\n" + "Итоговый балл " + secondaryScore);

    }

    //  @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBackPressed() {
        save(null);
    }

    public void save(View view) {
        HistorySQLite historySQLite = new HistorySQLite(end_of_test.this);
        historySQLite.setLesson((String) MainActivity.contentDescription, Integer.parseInt(new SimpleDateFormat("yyyyMMdd").format(new Date())));
        Intent intent = new Intent(end_of_test.this, MainActivity.class);
        startActivity(intent);
    }
}
