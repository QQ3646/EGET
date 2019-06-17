package com.twodauns.eget;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;

public class HistorySQLite extends SQLiteOpenHelper {
    private Context ctn;

    public HistorySQLite(Context context) {
        super(context, "database.db", null, 1);
        ctn = context;
    }

    boolean isWherePasted = false;

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE \"history\" (\n" +
                "\t\"_id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,\n" +
                "\t\"Date\"\tINTEGER,\n" +
                "\t\"NOS\"\tTEXT,\n" +
                "\t\"TP\"\tINTEGER\n" +
                ");";
        sqLiteDatabase.execSQL(query);


    }

    class NameFromSQL {
        String NOS; //Name of subject - название предмета
        int Date; //Дата
        String TP; //true points - количество баллов

        NameFromSQL(String NOS, int Date, String TP) {
            this.Date = Date;
            this.NOS = NOS;
            this.TP = TP;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<NameFromSQL> getLessons(String[] name, int[] dates) {
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        String sql = "SELECT NOS, Date, TP FROM history";
        boolean nameANDdates = false;
        if (name != null) {
            sql += " WHERE ";
            isWherePasted = true;
            int i = 0;
            if (name != null) {
                for (String names :
                        name) {
                    sql += "NOS == \"" + names + "\" ";
                    if (name.length != 1 && i != name.length - 1)
                        sql += "OR ";
                    i++;
                }
                nameANDdates = true;
            }
        }
        if (dates != null) {
            if (nameANDdates) sql += "AND";
            if (!isWherePasted) sql += " WHERE ";
            if (dates[1] != 0 && dates[0] != 0) {
                sql += "(" + dates[0] + " <= Date AND Date <= " + dates[1] + ")";
            } else {
                if (dates[0] != 0) {
                    sql += "(Date >=" +dates[0] + " )";
                } else sql += "( Date <= " + dates[1] + ")";
            }
        }
        Cursor cursor = readableDatabase.rawQuery(sql, null);
        cursor.moveToFirst();
        ArrayList<NameFromSQL> arrayList = new ArrayList<>();
        int position = cursor.getPosition();
        while (cursor.moveToPosition(position)) {
            NameFromSQL nameFromSQL = new NameFromSQL(cursor.getString(0), cursor.getInt(1), cursor.getString(2));
            arrayList.add(nameFromSQL);
            position++;
        }
        return arrayList;
    }

    public void setLesson(String name, int date) {
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        readableDatabase.execSQL("INSERT INTO history (NOS, Date, TP) VALUES ('" + name + "'," + date + ", 32);");
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
