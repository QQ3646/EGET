package com.twodauns.eget.check

import android.graphics.Color
import android.view.Gravity
import android.widget.Button
import android.widget.TableRow
import android.widget.TextView
import com.twodauns.eget.R
import com.twodauns.eget.EndOfTest
import com.twodauns.eget.EndOfTest.ctn
import com.twodauns.eget.EndOfTest.line
import com.twodauns.eget.tester
import com.twodauns.eget.tester.notC
import com.twodauns.eget.tester.questions
import java.util.*

fun checkSS(): Array<Task?> {
    var tasks = arrayOfNulls<Task>(notC)
    var i = 0
    line = 2
    var primaryScore = 0
    for (q in questions) {
        if (q != null && i < notC) {
            val show = Button(EndOfTest.ctn)
            show.text = "Посмотреть"
            show.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2f)

            val number = TextView(EndOfTest.ctn)
            number.gravity = Gravity.CENTER
            number.text = "№" + (i + 1)
            number.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 0.2f)

            val correctOrNot = TextView(EndOfTest.ctn)
            correctOrNot.gravity = Gravity.CENTER
            correctOrNot.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 0.5f)
            (correctOrNot.layoutParams as TableRow.LayoutParams).setMargins(3,3,3,3)

            var checkWithoutOrder : Array<Boolean?> = arrayOfNulls(1)
            when (i) {
                0 -> {
                    for (answ in q!!.correctAnswer) {
                        if (answ == tester.answers[i]) {
                            correctOrNot.setTextColor(Color.GREEN)
                            correctOrNot.text = "Правильно"
                            primaryScore++
                        } else {
                            correctOrNot.setTextColor(Color.RED)
                            correctOrNot.text = "Не правильно"
                        }
                    }
                }
                1 -> {
                    val answ = tester.answers[i].replace(" ", "")
                    if (q!!.correctAnswer.get(0) == answ) {
                        correctOrNot.setTextColor(Color.GREEN)
                        correctOrNot.text = "Правильно"
                        primaryScore++
                    } else {
                        correctOrNot.setTextColor(Color.RED)
                        correctOrNot.text = "Не правильно"
                    }
                }
                2 -> {
                    val sol = q!!.correctAnswer.get(0).toString().toCharArray()
                    Arrays.sort(sol)
                    val ans = tester.answers[i].toCharArray()
                    Arrays.sort(ans)
                    if (Arrays.equals(ans, sol)) {
                        correctOrNot.setTextColor(Color.GREEN)
                        correctOrNot.text = "Правильно"
                        primaryScore++
                    } else {
                        correctOrNot.setTextColor(Color.RED)
                        correctOrNot.text = "Не правильно"
                    }
                }
                3 -> {
                    val correctSolve = q!!.correctAnswer.get(0).toString().toCharArray() //правильные цифорки
                    val urSolve = tester.answers[i].toCharArray() //то говно, что ты введ
                    var countOfCorrectSolve = 0 //счетчик правильных цифорок
                    checkWithoutOrder = partialCheckWithIgnoreOrder(urSolve,correctSolve)
                    for (bool in checkWithoutOrder)
                    {
                        if (bool!!) {
                            countOfCorrectSolve++
                        }
                    }
                    when (countOfCorrectSolve) {
                        correctSolve.size -> {
                            correctOrNot.setTextColor(Color.GREEN)
                            correctOrNot.text = "Правильно"
                            primaryScore += 2
                        }
                        correctSolve.size-1 -> {
                            correctOrNot.setTextColor(ctn.resources.getColor(R.color.partiallyTrue))
                            correctOrNot.text = "Частично правильно"
                            primaryScore += 1
                        }
                        else -> {
                            correctOrNot.setTextColor(Color.RED)
                            correctOrNot.text = "Не правильно"
                        }
                    }
                }
                4 -> {
                    {
                        //                    if (q!!.correctAnswer[0] == tester.answers[i]) {
//                        correctOrNot.setTextColor(Color.GREEN)
//                        correctOrNot.text = "Правильно"
//                        primaryScore += 2
//                    } else {
//                        correctOrNot.setTextColor(Color.RED)
////                        correctOrNot.setTextColor(Color.RED)
//                        correctOrNot.text = "Не правильно"
//                    }
                    }
                    val correctSolve = q!!.correctAnswer.get(0).toString().toCharArray() //правильные цифорки
                    val urSolve = tester.answers[i].toCharArray() //то говно, что ты введ
                    var countOfCorrectSolve = 0 //счетчик правильных цифорок
                    checkWithoutOrder = partialCheckWithOrder(urSolve, correctSolve)
                    for (bool in checkWithoutOrder) {
                        if (bool!!) {
                            countOfCorrectSolve++
                        }
                    }
                    when (countOfCorrectSolve) {
                        correctSolve.size -> {
                            correctOrNot.setTextColor(Color.GREEN)
                            correctOrNot.text = "Правильно"
                            primaryScore += 2
                        }
                        correctSolve.size - 1 -> {
                            correctOrNot.setTextColor(ctn.resources.getColor(R.color.partiallyTrue))
                            correctOrNot.text = "Частично правильно"
                            primaryScore += 1
                        }
                        else -> {
                            correctOrNot.setTextColor(Color.RED)
                            correctOrNot.text = "Не правильно"
                        }
                    }
                }
                5 -> {
                    val sol = q!!.correctAnswer.get(0).toString().toCharArray()
                    Arrays.sort(sol)
                    val ans = tester.answers[i].toCharArray()
                    Arrays.sort(ans)
                    if (Arrays.equals(ans, sol)) {
                        correctOrNot.setTextColor(Color.GREEN)
                        correctOrNot.text = "Правильно"
                        primaryScore += 2
                    } else {
                        correctOrNot.setTextColor(Color.RED)
                        correctOrNot.text = "Не правильно"
                    }
                }
                6 -> {
                    val sol = q!!.correctAnswer.get(0).toString().toCharArray()
                    Arrays.sort(sol)
                    val ans = tester.answers[i].toCharArray()
                    Arrays.sort(ans)
                    if (Arrays.equals(ans, sol)) {
                        correctOrNot.setTextColor(Color.GREEN)
                        correctOrNot.text = "Правильно"
                        primaryScore += 2
                    } else {
                        correctOrNot.setTextColor(Color.RED)
                        correctOrNot.text = "Не правильно"
                    }
                }
                7 -> {
                    if (q!!.correctAnswer.get(0) == tester.answers[i]) {
                        correctOrNot.setTextColor(Color.GREEN)
                        correctOrNot.text = "Правильно"
                        primaryScore += 2
                    } else {
                        correctOrNot.setTextColor(Color.RED)
                        correctOrNot.text = "Не правильно"
                    }
                }
                8 -> {
                    val sol = q!!.correctAnswer.get(0).toString().toCharArray()
                    Arrays.sort(sol)
                    val ans = tester.answers[i].toCharArray()
                    Arrays.sort(ans)
                    if (Arrays.equals(ans, sol)) {
                        correctOrNot.setTextColor(Color.GREEN)
                        correctOrNot.text = "Правильно"
                        primaryScore += 2
                    } else {
                        correctOrNot.setTextColor(Color.RED)
                        correctOrNot.text = "Не правильно"
                    }
                }
                9 -> {
                    for (answ in q!!.correctAnswer) {
                        if (answ == tester.answers[i]) {
                            correctOrNot.setTextColor(Color.GREEN)
                            correctOrNot.text = "Правильно"
                            primaryScore++
                        } else {
                            correctOrNot.setTextColor(Color.RED)
                            correctOrNot.text = "Не правильно"
                        }
                    }
                }
                10 -> {
                    val answ = tester.answers[i].replace(" ", "")
                    if (q!!.correctAnswer.get(0) == answ) {
                        correctOrNot.setTextColor(Color.GREEN)
                        correctOrNot.text = "Правильно"
                        primaryScore++
                    } else {
                        correctOrNot.setTextColor(Color.RED)
                        correctOrNot.text = "Не правильно"
                    }
                }
                11 -> {
                    val sol = q!!.correctAnswer.get(0).toString().toCharArray()
                    Arrays.sort(sol)
                    val ans = tester.answers[i].toCharArray()
                    Arrays.sort(ans)
                    if (Arrays.equals(ans, sol)) {
                        correctOrNot.setTextColor(Color.GREEN)
                        correctOrNot.text = "Правильно"
                        primaryScore++
                    } else {
                        correctOrNot.setTextColor(Color.RED)
                        correctOrNot.text = "Не правильно"
                    }
                }
                12 -> {
                    val sol = q!!.correctAnswer.get(0).toString().toCharArray()
                    Arrays.sort(sol)
                    val ans = tester.answers[i].toCharArray()
                    Arrays.sort(ans)
                    if (Arrays.equals(ans, sol)) {
                        correctOrNot.setTextColor(Color.GREEN)
                        correctOrNot.text = "Правильно"
                        primaryScore += 2
                    } else {
                        correctOrNot.setTextColor(Color.RED)
                        correctOrNot.text = "Не правильно"
                    }
                }
                13 -> {
                    if (q!!.correctAnswer.get(0) == tester.answers[i]) {
                        correctOrNot.setTextColor(Color.GREEN)
                        correctOrNot.text = "Правильно"
                        primaryScore += 2
                    } else {
                        correctOrNot.setTextColor(Color.RED)
                        correctOrNot.text = "Не правильно"
                    }
                }
                14 -> {
                    val sol = q!!.correctAnswer.get(0).toString().toCharArray()
                    Arrays.sort(sol)
                    val ans = tester.answers[i].toCharArray()
                    Arrays.sort(ans)
                    if (Arrays.equals(ans, sol)) {
                        correctOrNot.setTextColor(Color.GREEN)
                        correctOrNot.text = "Правильно"
                        primaryScore += 2
                    } else {
                        correctOrNot.setTextColor(Color.RED)
                        correctOrNot.text = "Не правильно"
                    }
                }
                15 -> {
                    val sol = q!!.correctAnswer.get(0).toString().toCharArray()
                    Arrays.sort(sol)
                    val ans = tester.answers[i].toCharArray()
                    Arrays.sort(ans)
                    if (Arrays.equals(ans, sol)) {
                        correctOrNot.setTextColor(Color.GREEN)
                        correctOrNot.text = "Правильно"
                        primaryScore += 2
                    } else {
                        correctOrNot.setTextColor(Color.RED)
                        correctOrNot.text = "Не правильно"
                    }
                }
                16 -> {
                    if (q!!.correctAnswer.get(0) == tester.answers[i]) {
                        correctOrNot.setTextColor(Color.GREEN)
                        correctOrNot.text = "Правильно"
                        primaryScore += 2
                    } else {
                        correctOrNot.setTextColor(Color.RED)
                        correctOrNot.text = "Не правильно"
                    }
                }
                17 -> {
                    val sol = q!!.correctAnswer.get(0).toString().toCharArray()
                    Arrays.sort(sol)
                    val ans = tester.answers[i].toCharArray()
                    Arrays.sort(ans)
                    if (Arrays.equals(ans, sol)) {
                        correctOrNot.setTextColor(Color.GREEN)
                        correctOrNot.text = "Правильно"
                        primaryScore += 2
                    } else {
                        correctOrNot.setTextColor(Color.RED)
                        correctOrNot.text = "Не правильно"
                    }
                }
                18 -> {
                    for (answ in q!!.correctAnswer) {
                        if (answ == tester.answers[i]) {
                            correctOrNot.setTextColor(Color.GREEN)
                            correctOrNot.text = "Правильно"
                            primaryScore++
                        } else {
                            correctOrNot.setTextColor(Color.RED)
                            correctOrNot.text = "Не правильно"
                        }
                    }
                }
                19 -> {
                    val answ = tester.answers[i].replace(" ", "")
                    if (q!!.correctAnswer.get(0) == answ) {
                        correctOrNot.setTextColor(Color.GREEN)
                        correctOrNot.text = "Правильно"
                        primaryScore++
                    } else {
                        correctOrNot.setTextColor(Color.RED)
                        correctOrNot.text = "Не правильно"
                    }
                }
                20 -> {
                    val sol = q!!.correctAnswer.get(0).toString().toCharArray()
                    Arrays.sort(sol)
                    val ans = tester.answers[i].toCharArray()
                    Arrays.sort(ans)
                    if (Arrays.equals(ans, sol)) {
                        correctOrNot.setTextColor(Color.GREEN)
                        correctOrNot.text = "Правильно"
                        primaryScore++
                    } else {
                        correctOrNot.setTextColor(Color.RED)
                        correctOrNot.text = "Не правильно"
                    }
                }
                21 -> { //вторая часть, без проверки
                    val sol = q!!.correctAnswer.get(0).toString().toCharArray()
                    Arrays.sort(sol)
                    val ans = tester.answers[i].toCharArray()
                    Arrays.sort(ans)
                    if (Arrays.equals(ans, sol)) {
                        correctOrNot.setTextColor(Color.GREEN)
                        correctOrNot.text = "Правильно"
                        primaryScore += 2
                    } else {
                        correctOrNot.setTextColor(Color.RED)
                        correctOrNot.text = "Не правильно"
                    }
                }
                22 -> {//вторая часть, без проверки
                    if (q!!.correctAnswer.get(0) == tester.answers[i]) {
                        correctOrNot.setTextColor(Color.GREEN)
                        correctOrNot.text = "Правильно"
                        primaryScore += 2
                    } else {
                        correctOrNot.setTextColor(Color.RED)
                        correctOrNot.text = "Не правильно"
                    }
                }
                23 -> {//вторая часть, без проверки
                    val sol = q!!.correctAnswer.get(0).toString().toCharArray()
                    Arrays.sort(sol)
                    val ans = tester.answers[i].toCharArray()
                    Arrays.sort(ans)
                    if (Arrays.equals(ans, sol)) {
                        correctOrNot.setTextColor(Color.GREEN)
                        correctOrNot.text = "Правильно"
                        primaryScore += 2
                    } else {
                        correctOrNot.setTextColor(Color.RED)
                        correctOrNot.text = "Не правильно"
                    }
                }
                24 -> {//вторая часть, без проверки
                    val sol = q!!.correctAnswer.get(0).toString().toCharArray()
                    Arrays.sort(sol)
                    val ans = tester.answers[i].toCharArray()
                    Arrays.sort(ans)
                    if (Arrays.equals(ans, sol)) {
                        correctOrNot.setTextColor(Color.GREEN)
                        correctOrNot.text = "Правильно"
                        primaryScore += 2
                    } else {
                        correctOrNot.setTextColor(Color.RED)
                        correctOrNot.text = "Не правильно"
                    }
                }
                25 -> {//вторая часть, без проверки
                    if (q!!.correctAnswer.get(0) == tester.answers[i]) {
                        correctOrNot.setTextColor(Color.GREEN)
                        correctOrNot.text = "Правильно"
                        primaryScore += 2
                    } else {
                        correctOrNot.setTextColor(Color.RED)
                        correctOrNot.text = "Не правильно"
                    }
                }
                26 -> {//вторая часть, без проверки
                    val sol = q!!.correctAnswer.get(0).toString().toCharArray()
                    Arrays.sort(sol)
                    val ans = tester.answers[i].toCharArray()
                    Arrays.sort(ans)
                    if (Arrays.equals(ans, sol)) {
                        correctOrNot.setTextColor(Color.GREEN)
                        correctOrNot.text = "Правильно"
                        primaryScore += 2
                    } else {
                        correctOrNot.setTextColor(Color.RED)
                        correctOrNot.text = "Не правильно"
                    }
                }
                27 -> {//вторая часть, без проверки
                    if (q!!.correctAnswer.get(0) == tester.answers[i]) {
                        correctOrNot.setTextColor(Color.GREEN)
                        correctOrNot.text = "Правильно"
                        primaryScore += 2
                    } else {
                        correctOrNot.setTextColor(Color.RED)
                        correctOrNot.text = "Не правильно"
                    }
                }
                28 -> {//вторая часть, без проверки
                    val sol = q!!.correctAnswer.get(0).toString().toCharArray()
                    Arrays.sort(sol)
                    val ans = tester.answers[i].toCharArray()
                    Arrays.sort(ans)
                    if (Arrays.equals(ans, sol)) {
                        correctOrNot.setTextColor(Color.GREEN)
                        correctOrNot.text = "Правильно"
                        primaryScore += 2
                    } else {
                        correctOrNot.setTextColor(Color.RED)
                        correctOrNot.text = "Не правильно"
                    }
                }
            }
            tasks[i] = Task(show, number, correctOrNot, i, checkWithoutOrder)
            i++
        }
    }
//    var checkResult = CheckResult(tasks, ,primaryScore, line)
    return tasks
}