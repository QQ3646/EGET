package com.twodauns.eget

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import javax.security.auth.Subject

class AnswersInHistorySQLite(ctn: Context, private var subject: String) : SQLiteOpenHelper(ctn, "$subject.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        val query = "CREATE TABLE \"$subject\" (\n" +
                "\t\"_id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,\n" +
                "\t\"Answer\"\tTEXT,\n" +
                "\t\"COrNotC\"\tBOOLEAN\n" +
                "\t\"Test\"\tTEXT,\n" +
                ");"
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }
}
