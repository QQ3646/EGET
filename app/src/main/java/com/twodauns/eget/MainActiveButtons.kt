package com.twodauns.eget

import android.content.Context
import android.support.v7.app.AlertDialog
import android.support.v7.widget.Toolbar
import android.widget.*
import com.twodauns.eget.tester.questions
import com.twodauns.eget.tester.stringOfNames
import com.twodauns.eget.tester.answer
import com.twodauns.eget.tester.answerLayout


interface MainActiveButtons {
    //    var selectButton : TextView
    var nameList: Array<CharSequence> //список имен заданий
    var ctn: Context //контекст
    var selectButton: Button //главная кнопка
    var toolbar: Toolbar //тулбар для смены названий
    var questLayout: TableLayout //контейнер для заданий
    var questions: Array<tester.Question?>
    var answer: Array<EditText?>
    var answerLayout : LinearLayout
    //    @JvmDefault
    fun onClickPreviousButton() {
        if (Integer.parseInt(selectButton.text as String) == 1) {
            changeQuestion(nameList.size.toString())
            changeViewOfQuestion(Integer.parseInt(selectButton.text as String) - 1)
        } else {
            changeQuestion((Integer.parseInt(selectButton.text as String) - 1).toString())
            changeViewOfQuestion(Integer.parseInt(selectButton.text as String) - 1)
        }
    }

    fun onClickNextButton() {
        if (Integer.parseInt(selectButton.text as String) == nameList.size) {
            changeQuestion("1")
            changeViewOfQuestion(Integer.parseInt(selectButton.text as String) - 1)
        } else {
            changeQuestion((Integer.parseInt(selectButton.text as String) + 1).toString())
            changeViewOfQuestion(Integer.parseInt(selectButton.text as String) - 1)
            //тоже самое
        }
    }

    fun onClickSelectButton() {
        val temporaryStringOfNames = arrayOfNulls<CharSequence>(nameList.size)
        for (i in nameList.indices) {
            temporaryStringOfNames[i] = (i + 1).toString() + ". " + nameList[i]
        }
        val builder = AlertDialog.Builder(ctn)
        builder
                .setItems(temporaryStringOfNames) { _, i ->
                    changeQuestion((i + 1).toString())
                    changeViewOfQuestion(i)
                }
                .show()
    }

    fun changeQuestion(number: String) {
        answerLayout.removeAllViews()

        selectButton.text = number
        answerLayout.addView(answer[Integer.parseInt(number) - 1])
        toolbar.subtitle = nameList[Integer.parseInt(number) - 1]
    }

    fun changeViewOfQuestion(number: Int) {
        questLayout.removeAllViews()
        questLayout.addView(questions[number]!!.question)
    }
}