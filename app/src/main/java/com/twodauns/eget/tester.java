package com.twodauns.eget;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public class tester extends AppCompatActivity {
    CharSequence[] stringOfNames;
    Button selectButton;
    Toolbar toolbar;
    EditText answer;
    long milSec;
    static String[] answers;

    boolean isAlreadyEnded = false;

    boolean oneQuestion[];

    static Question[] questions;

    Thread timer;

    TableLayout quest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tester);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Context ctn = this;
        answer = findViewById(R.id.answerEditText);
        selectButton = findViewById(R.id.questionsPicker);
        selectButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                endOfTest();
                return false;
            }
        });

        quest = findViewById(R.id.linn);

        if (MainActivity.contentDescription.equals("Русский язык") || MainActivity.contentDescription.equals("Биология") || MainActivity.contentDescription.equals("Химия")) {
            milSec = 14097700;
        } else {
            milSec = 30000;
        }

        String nameOfSubject;
        if (MainActivity.contentDescription.equals("Немецкий язык") || MainActivity.contentDescription.equals("Английский язык") || MainActivity.contentDescription.equals("Французкий язык") || MainActivity.contentDescription.equals("Испанский язык")) {
            nameOfSubject = "ГФС";
        } else if (MainActivity.contentDescription.equals("Математика: профильный уровень")) {
            nameOfSubject = "МатематикаП";
        } else if (MainActivity.contentDescription.equals("Математика: базовый уровень")) {
            nameOfSubject = "МатематикаБ";
        } else nameOfSubject = (String) MainActivity.contentDescription;

        try {
            stringOfNames = getFromAssets(nameOfSubject + ".txt").split("/\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        toolbar.setSubtitle(stringOfNames[0]);

        oneQuestion = new boolean[stringOfNames.length];
        questions = new Question[stringOfNames.length];

        int notC = 0;
        for (int i = 0; i < stringOfNames.length; i++) {
            if (stringOfNames[i].toString().toCharArray()[0] != '(')
                notC++;
        }
        boolean ready = false;
        answers = new String[notC];
        int n = 0;
        boolean[] b = new boolean[3];
        try {
            XmlPullParser parser = getResources().getXml(R.xml.obsh2);
            while (n < stringOfNames.length) {
                if (parser.getEventType() == XmlPullParser.START_TAG
                        && parser.getName().contains("sect1")) {
                    parser.next();
                    while (!(parser.getEventType() == XmlPullParser.END_TAG &&
                            parser.getName().contains("sect1"))) {
                        if (parser.getEventType() == XmlPullParser.START_TAG
                                && parser.getName().contains("sect2")
                                && parser.getAttributeValue(0).contains("body")) {
                            b[0] = false;
                            TableLayout tableLayout = new TableLayout(this);
                            while (!(parser.getEventType() == XmlPullParser.END_TAG &&
                                    parser.getName().contains("sect2"))) {
                                if (parser.getName().contains("para")) {
                                    TextView textView = new TextView(this);
                                    textView.setText(parser.nextText());
                                    tableLayout.addView(textView);
                                } else if (parser.getName().contains("informaltable")) {
                                    TableLayout tableLayout1 = new TableLayout(this);
                                    tableLayout1.setColumnShrinkable(1, true);
                                    while (!(parser.getEventType() == XmlPullParser.END_TAG &&
                                            parser.getName().contains("informaltable"))) {
                                        if (parser.getName().contains("row")) {
                                            TableRow tableRow = new TableRow(this);
                                            tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                                            while (!(parser.getEventType() == XmlPullParser.END_TAG &&
                                                    parser.getName().contains("row"))) {
                                                if (parser.getName().contains("entry")) {
                                                    TextView textView = new TextView(this);
                                                    String s = new String();
                                                    boolean b1 = false;
                                                    boolean b2 = false;
                                                    TableRow.LayoutParams params = null;
                                                    while (!(parser.getEventType() == XmlPullParser.END_TAG &&
                                                            parser.getName().contains("entry"))) {
                                                        if (parser.getName().contains("para")) {

                                                            CharSequence str;
                                                            str = parser.nextText();
                                                            if (str.equals(" ") || str.equals("  ")) {
                                                                params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.1f);
                                                                b2 = true;
                                                            } else {
                                                                params = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 0.5f);
                                                                if (!((String) str).toUpperCase().equals(str)) {

                                                                    s += str + "\n";
                                                                    b1 = true;
                                                                } else {
//                                                                    if(b1){
//                                                                        s += "\n" + str;
//                                                                    } else
                                                                    s += str;
                                                                    textView.setGravity(Gravity.CENTER_HORIZONTAL);
                                                                }
                                                            }
                                                        }
                                                        parser.next();
                                                    }
                                                    if (b1) {
                                                        s = s.substring(0, (s.length()) - 2);
                                                    }
                                                    if (!b2) {
                                                        textView.setText(s);
                                                        params.setMargins(1, 1, 1, 1);
                                                        textView.setLayoutParams(params);
                                                        textView.setBackgroundResource(R.drawable.border);
                                                        tableRow.addView(textView);
                                                    }
                                                }
                                                parser.next();
                                            }
                                            if (tableRow.getChildCount() != 0) {
                                                tableLayout1.addView(tableRow);
                                            }
                                        }
                                        parser.next();
                                    }
                                    tableLayout.addView(tableLayout1);
                                }
                                parser.next();
                            }
                            questions[n] = new Question(tableLayout);
                            b[0] = true;
                            //   parser.next();
                        } else if (parser.getEventType() == XmlPullParser.START_TAG
                                && parser.getName().contains("sect2")
                                && parser.getAttributeValue(0).contains("sol")) {
                            b[1] = false;
                            TableLayout sol = new TableLayout(this);
                            while (!(parser.getEventType() == XmlPullParser.END_TAG &&
                                    parser.getName().contains("sect2"))) {
                                if (parser.getName().contains("para")) {
                                    TextView textView = new TextView(this);
                                    textView.setText(parser.nextText());

                                    sol.addView(textView);
                                }
                                parser.next();
                            }
                            sol.removeViewAt(sol.getChildCount() - 1);
                            questions[n].solve = sol;
                            b[1] = true;
                        } else if (parser.getEventType() == XmlPullParser.START_TAG
                                && parser.getName().contains("para")) {
                            b[2] = false;
                            String str = parser.nextText();
                            if (str != "") {
                                str = str.split(": ")[1];
                                questions[n].setAnswer(str.split("|"));
                                b[2] = true;
                            }
                        }
                        if (b[1] && b[0] && b[2]) {
                            n++;
                            b[1] = false;
                            b[0] = false;
                            b[2] = false;
                        }
                        parser.next();
                    }
                }
                parser.next();
            }
        } catch (
                Throwable t) {
            Log.e("Error", "onCreate: ", t);
        }

        changeViewOfQuestion(0);
    }


    @Override
    protected void onResume() {
        super.onResume();
        timer = new Thread(() -> {
            while (milSec >= 0) {
                try {
                    if (Thread.interrupted()) {
                        break;
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            getSupportActionBar().setTitle(TimeUnit.MILLISECONDS.toHours(milSec) + ":" + TimeUnit.MILLISECONDS.toMinutes(milSec) % 60 + ":" + TimeUnit.MILLISECONDS.toSeconds(milSec) % 60);
                            milSec -= 500;
                        }
                    });
                    Thread.sleep(500); //1000 - 1 сек
                } catch (InterruptedException ex) {
                }
            }
//            if (!isAlreadyEnded)      ВЕРНИ НА МЕСТО БЛЯТЬ
//                endOfTest();
        });
        timer.start();
    }


    @Override
    public void onBackPressed() {
    }

    public void onClickPreviousButton(View view) {
        if (Integer.parseInt((String) selectButton.getText()) == 1) {
            changeQuestion(String.valueOf(stringOfNames.length));
            changeViewOfQuestion(Integer.parseInt((String) selectButton.getText()) - 1);
        } else {
            changeQuestion(String.valueOf(Integer.parseInt((String) selectButton.getText()) - 1));
            changeViewOfQuestion(Integer.parseInt((String) selectButton.getText()) - 1);
        }
    }


    public void onClickNextButton(View view) {
        if (Integer.parseInt((String) selectButton.getText()) == stringOfNames.length) {
            changeQuestion("1");
            changeViewOfQuestion(Integer.parseInt((String) selectButton.getText()) - 1);
        } else {
            changeQuestion(String.valueOf(Integer.parseInt((String) selectButton.getText()) + 1));
            changeViewOfQuestion(Integer.parseInt((String) selectButton.getText()) - 1);
            //тоже самое
        }
    }

    public void onClickSelectButton(View view) {
        CharSequence[] temporaryStringOfNames = new CharSequence[stringOfNames.length];
        for (int i = 0; i < stringOfNames.length; i++) {
            temporaryStringOfNames[i] = (i + 1) + ". " + stringOfNames[i];
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(temporaryStringOfNames, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                changeQuestion(String.valueOf(i + 1));
                changeViewOfQuestion(i);
            }
        });
        builder.show();
    }

    public class Question {

        public TableLayout question;

        public TableLayout solve;

        CharSequence[] correctAnswer;


        public void setAnswer(CharSequence[] answer) {
            this.correctAnswer = answer;
        }

        Question(TableLayout question) {
            this.question = question;
        }

    }

    public String getFromAssets(String name) throws IOException {
        byte[] buffer = null;
        InputStream is;
        try {
            is = getAssets().open(name);
            int size = is.available();
            buffer = new byte[size];
            is.read(buffer);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String str_data = new String(buffer);
        return str_data;

    }

    public void changeQuestion(String number) {
        if (!answer.getText().toString().equals(""))
            answers[Integer.parseInt(selectButton.getText().toString()) - 2] = answer.getText().toString();
        //  if(answers[Integer.parseInt(selectButton.getText().toString()) - 1] != null)
        answer.setText(answers[Integer.parseInt(selectButton.getText().toString()) - 1]);

        selectButton.setText(number);
        toolbar.setSubtitle(stringOfNames[Integer.parseInt(number) - 1]);

        if (stringOfNames[Integer.parseInt(number) - 1].toString().toCharArray()[0] == '(') {
            answer.setFocusable(false);
            answer.setHint("Самостоятельная проверка");
        } else {
            answer.setFocusableInTouchMode(true);
            answer.setHint("Введите ответ");
        }
    }

    public void changeViewOfQuestion(int number) {
        quest.removeAllViews();
        quest.addView(questions[number].question);
    }

    public void endOfTest() {
        timer.interrupt();
        isAlreadyEnded = true;

        Intent intent = new Intent(this, end_of_test.class);
        startActivity(intent);
    }

}
