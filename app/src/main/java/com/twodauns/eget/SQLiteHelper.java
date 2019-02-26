package com.twodauns.eget;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.TreeSet;

public class SQLiteHelper extends SQLiteOpenHelper {
    private Context ctn;

    public SQLiteHelper(Context context) {
        super(context, "database.db", null, 1);
        ctn = context;
    }


    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE \"history\" (\n" +
                "\t\"_id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,\n" +
                "\t\"Date\"\tTEXT,\n" +
                "\t\"NOS\"\tTEXT,\n" +
                "\t\"TP\"\tINTEGER\n" +
                ");";
        sqLiteDatabase.execSQL(query);
    }

    class NameFromSQL {
        String NOS; //Name of subject - название предмета
        String Date; //Дата
        String TP; //true points - количество баллов
        byte Rating; //оценка

        NameFromSQL(String NOS, String Date, String TP) {
            this.Date = Date;
            this.NOS = NOS;
            this.TP = TP;
        }

        public String[] getInfo() {
            return new String[]{Date, NOS, TP};
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<NameFromSQL> getLessons(String[] name, String[] date) {
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        boolean where = false;
        String sql = "SELECT NOS, Date, TP FROM history";
        if (name != null) {
            sql += " WHERE ";
            where = true;
            int i = 0;
            for (String names :
                    name) {
                sql += "NOS == \"" + names + "\"" ;
                if(name.length != 1 && i != name.length-1)
                    sql += " OR ";
                i++;
            }
        }
        Cursor cursor = readableDatabase.rawQuery(sql, null);
        cursor.moveToFirst();
        ArrayList<NameFromSQL> arrayList = new ArrayList<>();
        int position = cursor.getPosition();
        while (cursor.moveToPosition(position)) {
            NameFromSQL nameFromSQL = new NameFromSQL(cursor.getString(0), cursor.getString(1), cursor.getString(2));
            arrayList.add(nameFromSQL);
            position++;
        }
        return arrayList;
    }

    public ArrayList<NameFromSQL> getLessons() {
        return getLessons(null, null);
    }

    public void setLessons(String name) {
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        readableDatabase.execSQL("INSERT INTO history (NOS, Date, TP, Rating) VALUES ('" + name +  "','20.02.2019', 32, 35);");
    }

    public TreeSet<String> getName() {
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery("SELECT NOS FROM history", null);
        cursor.moveToFirst();
        int position = cursor.getPosition();
        TreeSet<String> strings = new TreeSet<String>();
        while (cursor.moveToPosition(position)) {
            strings.add(cursor.getString(0));
            position++;
        }
        return strings;
    }


}
