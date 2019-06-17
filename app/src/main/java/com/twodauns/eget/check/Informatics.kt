package com.twodauns.eget.check

import android.graphics.Color
import android.view.Gravity
import android.widget.Button
import android.widget.TableRow
import android.widget.TextView
import com.twodauns.eget.EndOfTest
import com.twodauns.eget.R
import com.twodauns.eget.tester
import com.twodauns.eget.tester.notC
import java.util.*

fun checkINFO(): CheckResult {
    var tasks = arrayOfNulls<Task>(notC)
    EndOfTest.line = 2
    var primaryScore = 0
    for ((i, q) in tester.questions.withIndex()) {
        if (q != null) {
            val show = Button(EndOfTest.ctn)
            show.text = "Посмотреть"
            show.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2f)

            val number = TextView(EndOfTest.ctn)
            number.gravity = Gravity.CENTER
            number.text = "№ ${i+1}"
            number.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 0.2f)

            val correctOrNot = TextView(EndOfTest.ctn)
            correctOrNot.gravity = Gravity.CENTER
            correctOrNot.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 0.5f)
            (correctOrNot.layoutParams as TableRow.LayoutParams).setMargins(3, 3, 3, 3)
            if (i < notC) {
                if (q!!.correctAnswer[0] == tester.answers[i]) {
                    correctOrNot.setTextColor(Color.GREEN)
                    correctOrNot.text = "Правильно"
                    primaryScore++
                } else {
                    correctOrNot.setTextColor(Color.RED)
                    correctOrNot.text = "Не правильно"
                }
                tasks[i] = Task(show, number, correctOrNot, i)
            } else {
                when {
                    Integer.parseInt(tester.answers[i]) == 0 -> {
                        correctOrNot.setTextColor(Color.RED)
                        correctOrNot.text = "Не правильно[0/${tester.maxPoints[i - notC]}]"
                    }
                    Integer.parseInt(tester.answers[i]) == tester.maxPoints[i - notC] -> {
                        correctOrNot.setTextColor(Color.GREEN)
                        correctOrNot.text = "Правильно[${tester.maxPoints[i - notC]}/${tester.maxPoints[i - notC]}]"
                        primaryScore += tester.maxPoints[i - notC]
                    }
                    else -> {
                        correctOrNot.setTextColor(EndOfTest.ctn.resources.getColor(R.color.partiallyTrue))
                        correctOrNot.text = "Частично правильно[${tester.answers[i]}/${tester.maxPoints[i - notC]}]"
                        primaryScore += tester.answers[i] as Int
                    }
                }
                tasks[i] = Task(show, number, correctOrNot, i)
            }
        }
    }
    return CheckResult(tasks, primaryScore, 10)
}
