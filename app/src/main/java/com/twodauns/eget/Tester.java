//package com.twodauns.eget;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.view.View;
//import android.webkit.WebView;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.Spinner;
//import android.widget.TableLayout;
//import android.widget.TextView;
//import android.widget.ToggleButton;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.concurrent.TimeUnit;
//
//public class tester extends AppCompatActivity {
//    public static CharSequence[] stringOfNames;
//    Button selectButton;
//    Toolbar toolbar;
//    long milSec; //количество миллисекунд до конца теста
//    public static String[] answers; // ответы
//    public static int notC; //количество вопросов не из части C, т.е. которое нужно вводить
//    static LinearLayout answerLayout;
//    static EditText[] answer; //поля ответов
//
//    boolean isAlreadyEnded = false; //конец теста
//
////    boolean oneQuestion[];
//
//    public static Question[] questions;
//
//    Thread timer; //таймер окончания
//
//    TableLayout quest; //хранение вопросов
//
//    public static ArrayList<Integer> maxPoints;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.tester);
//        toolbar = findViewById(R.id.toolbar);
//        if(MainActivity.contentDescription.equals("Русский язык") ||
//                MainActivity.contentDescription.equals("Математика: базовый уровень")) {
////            toolbar.setSubtitleTextColor(getResources().getColor(R.color.def));
//            toolbar.setTitleTextColor(getResources().getColor(R.color.def));
////            toolbar.getContext().setTheme(R.style.Theme_AppCompat_Light_DarkActionBar);  РАБОТАЕТ
//            toolbar.getContext().setTheme(R.style.ThemeOverlay_AppCompat_Light);
////            getSupportActionBar().setHomeAsUpIndicator(R.attr.actionModeCloseDrawable);
//        }
//
//        setSupportActionBar(toolbar);
//        toolbar.setBackgroundColor(MainActivity.color);
//
//        answerLayout = findViewById(R.id.answerLayout);
//        selectButton = findViewById(R.id.questionsPicker);
//        selectButton.setOnLongClickListener(view -> {
//            endOfTest();
//            return false;
//        });
//
//
//        quest = findViewById(R.id.linn);
//
//        if (MainActivity.contentDescription.equals("Русский язык") ||
//                MainActivity.contentDescription.equals("Биология") ||
//                MainActivity.contentDescription.equals("Химия")) {
//            milSec = 14097700;
//        } else if(MainActivity.contentDescription.equals("Математика: профильный уровень") ||
//                    MainActivity.contentDescription.equals("Литература") ||
//                    MainActivity.contentDescription.equals("Обществознание") ||
//                    MainActivity.contentDescription.equals("Физика") ||
//                    MainActivity.contentDescription.equals("Информатика") ||
//                    MainActivity.contentDescription.equals("История")) {
//            milSec = 14100000;
//        } else if(MainActivity.contentDescription.equals("Математика: базовый уровень") ||
//                (MainActivity.contentDescription.equals("язык") && !MainActivity.contentDescription.equals("Русский")) ||
//                MainActivity.contentDescription.equals("География")) {
//            milSec = 10800000;
//        }
//
//        String nameOfSubject;
//        if (MainActivity.contentDescription.equals("Немецкий язык") || MainActivity.contentDescription.equals("Английский язык") || MainActivity.contentDescription.equals("Французкий язык") || MainActivity.contentDescription.equals("Испанский язык")) {
//            nameOfSubject = "ГФС";
//        } else if (MainActivity.contentDescription.equals("Математика: профильный уровень")) {
//            nameOfSubject = "МатематикаП";
//        } else if (MainActivity.contentDescription.equals("Математика: базовый уровень")) {
//            nameOfSubject = "МатематикаБ";
//        } else nameOfSubject = (String) MainActivity.contentDescription;
//
////        try {
//        stringOfNames = getFromAssets(nameOfSubject + ".txt").split("/\r\n");
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//        toolbar.setSubtitle(stringOfNames[0]);
//
//        questions = new Question[stringOfNames.length];
//
//        maxPoints = new ArrayList<>();
//        notC = 0;
//        for (int i = 0; i < stringOfNames.length; i++) {
//            if (stringOfNames[i].toString().toCharArray()[0] != '(')
//                notC++;
//            else {
//                maxPoints.add(Integer.parseInt(((String)stringOfNames[i]).split("@")[1]));
//                stringOfNames[i] = ((String)stringOfNames[i]).split("@")[0];
//            }
//        }
//        answer = new EditText[stringOfNames.length];
//        for (int i = 0; i < answer.length; i++) {
//            answer[i] = new EditText(this);
//            if (i >= notC) {
//                answer[i].setFocusable(false);
//                answer[i].setHint("Самостоятельная проверка");
//            } else {
//                answer[i].setHint("Введите ответ");
//            }
//        }
//        answerLayout.addView(answer[0]);
//        answers = new String[stringOfNames.length];
////        if (!(MainActivity.contentDescription.equals("Испанский язык") || MainActivity.contentDescription.equals("Немецкий язык") || MainActivity.contentDescription.equals("Французкий язык") || MainActivity.contentDescription.equals("Английский язык"))) {
//////            try {
//////                XmlPullParser parser = getResources().getXml(R.xml.obsh2);
//////                while (n < stringOfNames.length) {
//////                    if (parser.getEventType() == XmlPullParser.START_TAG
//////                            && parser.getName().contains("sect1")) {
//////                        parser.next();
//////                        while (!(parser.getEventType() == XmlPullParser.END_TAG &&
//////                                parser.getName().contains("sect1"))) {
//////                            if (parser.getEventType() == XmlPullParser.START_TAG
//////                                    && parser.getName().contains("sect2")
//////                                    && parser.getAttributeValue(0).contains("body")) {
//////                                b[0] = false;
//////                                TableLayout tableLayout = new TableLayout(this);
//////                                while (!(parser.getEventType() == XmlPullParser.END_TAG &&
//////                                        parser.getName().contains("sect2"))) {
//////                                    if (parser.getName().contains("para")) {
//////                                        TextView textView = new TextView(this);
//////                                        textView.setText(parser.nextText());
//////                                        tableLayout.addView(textView);
//////                                    } else if (parser.getName().contains("informaltable")) {
//////                                        TableLayout tableLayout1 = new TableLayout(this);
//////                                        tableLayout1.setColumnShrinkable(1, true);
//////                                        while (!(parser.getEventType() == XmlPullParser.END_TAG &&
//////                                                parser.getName().contains("informaltable"))) {
//////                                            if (parser.getName().contains("row")) {
//////                                                int plusOneRow = 0;
//////                                                TableRow tableRow = new TableRow(this);
//////                                                tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
//////                                                while (!(parser.getEventType() == XmlPullParser.END_TAG &&
//////                                                        parser.getName().contains("row"))) {
//////                                                    if (parser.getName().contains("entry")) {
//////                                                        TextView textView = new TextView(this);
//////                                                        String s = new String();
//////                                                        boolean b1 = false; // проверка на символы
//////                                                        boolean b2 = false; //проверка на пустоту
//////
//////                                                        TableRow.LayoutParams params = null;
//////                                                        while (!(parser.getEventType() == XmlPullParser.END_TAG &&
//////                                                                parser.getName().contains("entry"))) {
//////                                                            if (parser.getName().contains("para")) {
//////
//////                                                                CharSequence str;
//////                                                                str = parser.nextText();
//////                                                                if (str.equals(" ") || str.equals("  ")) {
//////                                                                    params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.1f);
//////                                                                    b2 = true;
//////                                                                } else {
//////                                                                    params = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 0.5f);
//////                                                                    if (!((String) str).toUpperCase().equals(str)) {
//////                                                                        textView.setPadding(8,2, 8, 2);
//////                                                                        s += str + "\n";
//////                                                                        b1 = true;
//////                                                                    } else {
////////                                                                        textView.setTextSize();
//////                                                                        s += str;
//////                                                                        textView.setGravity(Gravity.CENTER_HORIZONTAL);
//////                                                                    }
//////                                                                }
//////                                                            }
//////                                                            parser.next();
//////                                                        }
//////                                                        if (b1) {
//////                                                            s = s.substring(0, (s.length()) - 1);
//////                                                        }
//////                                                        if (!b2) {
//////                                                            textView.setText(s);
//////                                                            params.setMargins(1, 1, 1, 1);
//////
//////                                                            textView.setLayoutParams(params);
//////                                                            textView.setBackgroundResource(R.drawable.border);
//////                                                            tableRow.addView(textView);
//////                                                        }
//////                                                        if (s.length() == 1 &&
//////                                                                ((s.toCharArray()[0] >= 'A' && s.toCharArray()[0] <= 'Z') || ((s.toCharArray()[0] >= 'А' && s.toCharArray()[0] <= 'Я')))) {
//////                                                            plusOneRow++;
//////                                                        }
//////                                                    }
//////                                                    parser.next();
//////                                                }
//////
//////                                                if (tableRow.getChildCount() != 0) {
//////                                                    tableLayout1.addView(tableRow);
//////                                                }
//////                                                if (plusOneRow != 0) {
//////                                                    TableRow anotherTableRow = new TableRow(this);
//////                                                    for (int i = 0; i < plusOneRow; i++) {
//////
//////                                                        TextView textView = new TextView(this);
//////                                                        TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.5f);
////////                                                        textView.setPadding(2,2, 2, 2);
//////                                                        textView.setText(" ");
//////                                                        params.setMargins(1, 1, 1, 1);
//////                                                        textView.setLayoutParams(params);
//////                                                        textView.setBackgroundResource(R.drawable.border);
//////                                                        anotherTableRow.addView(textView);
//////                                                    }
//////                                                    tableLayout1.addView(anotherTableRow);
//////                                                }
//////                                            }
//////                                            parser.next();
//////                                        }
//////                                        tableLayout.addView(tableLayout1);
//////                                    }
//////                                    parser.next();
//////                                }
//////                                questions[n] = new Question(tableLayout);
//////                                b[0] = true;
//////                                //   parser.next();
//////                            } else if (parser.getEventType() == XmlPullParser.START_TAG
//////                                    && parser.getName().contains("sect2")
//////                                    && parser.getAttributeValue(0).contains("sol")) {
//////                                b[1] = false;
//////                                TableLayout sol = new TableLayout(this);
//////                                while (!(parser.getEventType() == XmlPullParser.END_TAG &&
//////                                        parser.getName().contains("sect2"))) {
//////                                    if (parser.getName().contains("para")) {
//////                                        TextView textView = new TextView(this);
//////                                        textView.setText(parser.nextText());
//////
//////                                        sol.addView(textView);
//////                                    }
//////                                    parser.next();
//////                                }
//////                                questions[n].solveForView = (TextView) sol.getChildAt(sol.getChildCount() - 1);
//////                                sol.removeViewAt(sol.getChildCount() - 1);
//////                                questions[n].solve = sol;
//////                                b[1] = true;
//////                            } else if (parser.getEventType() == XmlPullParser.START_TAG
//////                                    && parser.getName().contains("para")) {
//////                                b[2] = false;
//////                                String str = parser.nextText();
//////                                if (str != "") {
//////                                    str = str.split(": ")[1];
//////                                    for (String s :
//////                                            str.split(String.valueOf("@"))) {
//////                                        if (!s.isEmpty()) {
//////                                            questions[n].addAnswer(s);
//////                                        }
//////                                    }
//////                                    b[2] = true;
//////                                }
//////                            }
//////                            if (b[1] && b[0] && b[2]) {
//////                                n++;
//////                                b[1] = false;
//////                                b[0] = false;
//////                                b[2] = false;
//////                            }
//////                            parser.next();
//////                        }
//////                    }
//////                    parser.next();
//////                }
//////            } catch (
//////                    Throwable t) {
//////                Log.e("Error", "onCreate: ", t);
//////            }
//////            try {
//////                XmlPullParser parser = getResources().getXml(R.xml.gg);
//////                while (!(parser.getEventType() == XmlPullParser.END_DOCUMENT)){
//////                    if (parser.getEventType() == XmlPullParser.START_TAG &&
//////                            parser.getName().equals("question")){
//////                        WebView webView = new WebView(this);
//////                        webView.loadData(parser.nextText(), "text/html", "utf-8");
//////                        questions[n] = new Question(webView);
//////                        b[0] = true;
//////                    } else if(parser.getEventType() == XmlPullParser.START_TAG &&
//////                            parser.getName().equals("solve")){
//////                        WebView webView = new WebView(this);
//////                        webView.loadData(parser.nextText(), "text/html", "utf-8");
//////                        questions[n].solve = webView;
//////                        b[1] = true;
//////                    } else if(parser.getEventType() == XmlPullParser.START_TAG &&
//////                            parser.getName().equals("answer")){
//////                        String text = parser.nextText();
//////                        WebView webView = new WebView(this);
//////                        webView.loadData(text, "text/html", "utf-8");
//////                        questions[n].solve = webView;
//////                        TextView textView = new TextView(this);
//////                        textView.setText(text);
//////                        questions[n].solveForView = textView;
//////                        b[2] = true;
//////                    }
//////                    if(b[0]&&b[1]&&b[2]){
//////                        n++;
//////                    }
//////                    parser.next();
//////
//////                }
//////            } catch (XmlPullParserException e) {
//////                e.printStackTrace();
//////            } catch (IOException e) {
//////                e.printStackTrace();
//////            }
////
//////                WebView quest = new WebView(this);
//////                WebView solve = new WebView(this);
//////                TextView answer = new TextView(this);
//////                quest.loadData("file:///android_asset/razd/"+ "answ" + i +".html", "text/html", "utf-8");
//////                solve.loadData("file:///android_asset/razd/"+ "solv" + i +".html","text/html", "utf-8");
//////                answer.setText();
//////                Bundle bundle = new Bundle();
//////                try {
//////                    viewsSetter.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, i, tester.this);
//////                    questions[i] = viewsSetter.get();
////////                    e.printStackTrace();
//////                } catch (InterruptedException e) {
//////                    e.printStackTrace();
//////                } catch (ExecutionException e) {
//////                    e.printStackTrace();
//////                }
//////            for (int i = 0; i < stringOfNames.length; i++){
//        //        } else {
////
////        }
//        questions[0] = ViewsSetterKt.setViews(0, this);
//        changeViewOfQuestion(0);
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        timer = new Thread(() -> {
//            while (milSec >= 0) {
//                try {
//                    if (Thread.interrupted()) {
//                        break;
//                    }
//                    runOnUiThread(() -> {
//                        getSupportActionBar().setTitle(TimeUnit.MILLISECONDS.toHours(milSec) + ":" + TimeUnit.MILLISECONDS.toMinutes(milSec) % 60 + ":" + TimeUnit.MILLISECONDS.toSeconds(milSec) % 60);
//                        milSec -= 500;
//                    });
//                    Thread.sleep(500); //1000 - 1 сек
//                } catch (InterruptedException ex) {
//                }
//            }
////            if (!isAlreadyEnded)      ВЕРНИ НА МЕСТО БЛЯТЬ
////                EndOfTest();
//        });
//        timer.start();
//
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        new Thread(() -> {
//            runOnUiThread(() -> {
//                for (int i = 1; i < stringOfNames.length; i++) {
//                    questions[i] = ViewsSetterKt.setViews(i, this);
//                }
//            });
//        }).start();
//
//
//
//    }
//
//
//    @Override
//    public void onBackPressed() {
//    }
//
//    public void onClickPreviousButton(View view) {
//        if (Integer.parseInt((String) selectButton.getText()) == 1) {
//            changeQuestion(String.valueOf(stringOfNames.length));
//            changeViewOfQuestion(Integer.parseInt((String) selectButton.getText()) - 1);
//        } else {
//            changeQuestion(String.valueOf(Integer.parseInt((String) selectButton.getText()) - 1));
//            changeViewOfQuestion(Integer.parseInt((String) selectButton.getText()) - 1);
//        }
//    }
//
//    public void onClickNextButton(View view) {
//        if (Integer.parseInt((String) selectButton.getText()) == stringOfNames.length) {
//            changeQuestion("1");
//            changeViewOfQuestion(Integer.parseInt((String) selectButton.getText()) - 1);
//        } else {
//            changeQuestion(String.valueOf(Integer.parseInt((String) selectButton.getText()) + 1));
//            changeViewOfQuestion(Integer.parseInt((String) selectButton.getText()) - 1);
//            //тоже самое
//        }
//    }
//
//
//
//
//    public void onClickSelectButton(View view) {
//        CharSequence[] temporaryStringOfNames = new CharSequence[stringOfNames.length];
//        for (int i = 0; i < stringOfNames.length; i++) {
//            temporaryStringOfNames[i] = (i + 1) + ". " + stringOfNames[i];
//        }
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setItems(temporaryStringOfNames, (dialogInterface, i) -> {
//            changeQuestion(String.valueOf(i + 1));
//            changeViewOfQuestion(i);
//        });
//        builder.show();
//    }
//
//    public static class Question {
//        static class CQuestion {
//            ToggleButton qust; //задание
//            ToggleButton answer; //решение
//            ToggleButton criteria; //критерии
//            Spinner points; //выпадающий список баллов за задание
//            CQuestion(ToggleButton qust, ToggleButton answer, ToggleButton criteria, Spinner points){
//                this.qust = qust;
//                this.answer = answer;
//                this.criteria = criteria;
//                this.points = points;
//            }
//        }
//
//        public CQuestion cQuest;
//
//        public WebView question; //вопрос
//        public WebView solve; //решение
//        public TextView solveForView; //Аккуратный ответ
//
//        public WebView criteria;
//
//        public void addCorrectAnswer(String string) {
//            correctAnswer.add(string);
//        }
//
//        public ArrayList<CharSequence> correctAnswer = new ArrayList<>();
//
//        Question(WebView question, WebView solve, TextView solveForView) {
//            this.question = question;
//            this.solve = solve;
//            this.solveForView = solveForView;
//        }
//        Question(WebView question, WebView solve, WebView criteriea, CQuestion cQuest){
//            this.question = question;
//            this.solve =solve;
//            this.criteria = criteriea;
//            this.cQuest = cQuest;
//        }
//    }
//
//    public String getFromAssets(String name) {
//        byte[] buffer = null;
//        InputStream is;
//        try {
//            is = getAssets().open(name);
//            int size = is.available();
//            buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        String str_data = new String(buffer);
//        return str_data;
//
//    }
//
//    public void changeQuestion(String number) {
//        answerLayout.removeAllViews();
//
//        selectButton.setText(number);
//        answerLayout.addView(answer[Integer.parseInt(number) - 1]);
//        toolbar.setSubtitle(stringOfNames[Integer.parseInt(number) - 1]);
//
////        if (stringOfNames[Integer.parseInt(number) - 1].toString().toCharArray()[0] == '(') {
////
////        } else {
////            answer.setFocusableInTouchMode(true);
////
////        }
//    }
//
//    public void changeViewOfQuestion(int number) {
//        quest.removeAllViews();
//        quest.addView(questions[number].question);
//    }
//
//    public void endOfTest() {
//        timer.interrupt();
//        int i = 0;
//        isAlreadyEnded = true;
//        for (EditText ed :
//                answer) {
//            answers[i] = ed.getText().toString();
//            i++;
//        }
////        Intent intent = new Intent(this, EndOfTest.class);
////        startActivity(intent);
//        Intent intent = new Intent(this, CChecker.class);
//        startActivity(intent);
//    }
//
//}
