package com.twodauns.eget;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class end_of_test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_of_test);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button close = findViewById(R.id.close);
        close.setOnClickListener(this::save);

        if (MainActivity.contentDescription.equals("История")) {

        } else if (MainActivity.contentDescription.equals("Биология")) {

        } else if (MainActivity.contentDescription.equals("Математика: профильный уровень")) {

        } else if (MainActivity.contentDescription.equals("Математика: базовый уровень")) {

        } else if (MainActivity.contentDescription.equals("Английский язык")) {

        } else if (MainActivity.contentDescription.equals("Русский язык")) {

        } else if (MainActivity.contentDescription.equals("Немецкий язык") || MainActivity.contentDescription.equals("Французкий язык") || MainActivity.contentDescription.equals("Испанский язык")) {

        } else if (MainActivity.contentDescription.equals("Информатика")) {

        } else if (MainActivity.contentDescription.equals("Химия")) {

        } else if (MainActivity.contentDescription.equals("Физика")) {

        } else if (MainActivity.contentDescription.equals("Литература")) {

        } else if (MainActivity.contentDescription.equals("Обществознание")) {
           // if(tester.questions[0].)
            for (tester.Question q:
                 tester.questions) {
                if(q != null){
                    for (CharSequence correct:
                         q.correctAnswer) {
                        if(true){
                            
                        }
                    }
                }
            }
        } else { //География

        }
    }

  //  @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBackPressed() {save(null); }

 //   @RequiresApi(api = Build.VERSION_CODES.O)
    public void save(View view) {
        HistorySQLite historySQLite = new HistorySQLite(end_of_test.this);
      //  Date date = new Date(Calendar.YEAR, Calendar.MONTH, Calendar.DATE);
        historySQLite.setLesson((String) MainActivity.contentDescription, Integer.parseInt(new SimpleDateFormat("yyyyMMdd").format(new Date())));
        Intent intent = new Intent(end_of_test.this, MainActivity.class);
        startActivity(intent);
    }
}
