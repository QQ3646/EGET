package com.twodauns.eget.check

import android.widget.Button
import android.widget.TextView

class Task(var show: Button, var number: TextView, var correctOrNot: TextView, var i: Int, var array: Array<Boolean?> = arrayOfNulls(0))
class CTask(var show: Button, var number: TextView, var correctOrNot: TextView, var numberOfQuestion : Int, var points : Int, var maxPoints : Int)

class CheckResult(var Tasks: Array<Task?>, var point: Int, var line : Int)

// i - номер задания, массив - если вопрос из нескольких цифр
fun partialCheckWithIgnoreOrder(urSolve: CharArray, correctSolve: CharArray): Array<Boolean?> {
    var bolArr = arrayOfNulls<Boolean>(correctSolve.size) //массив типа правельные цифры, втч по порядку
    var copyOfUr = urSolve
    for ((i, partOfCorrectSolve) in correctSolve.withIndex()) {
        if (copyOfUr.isNotEmpty() &&
                copyOfUr.contains(partOfCorrectSolve)) {
            bolArr[i] = true
            copyOfUr = copyOfUr.filter { it != partOfCorrectSolve }.toCharArray()
        } else {
            bolArr[i] = false
        }
//        bolArr[i] = partOfCorrectSolve == correctSolve[i]
    }
    return bolArr
}

fun partialCheckWithOrder(urSolve: CharArray, correctSolve: CharArray): Array<Boolean?> {
    var bolArr = arrayOfNulls<Boolean>(correctSolve.size)
//    var copyOfUr = urSolve
    for ((i, partOfCorrectSolve) in correctSolve.withIndex()) {
        bolArr[i] = urSolve.isNotEmpty() &&
                urSolve[i] == partOfCorrectSolve
//        bolArr[i] = partOfCorrectSolve == correctSolve[i]
    }
    return bolArr
}