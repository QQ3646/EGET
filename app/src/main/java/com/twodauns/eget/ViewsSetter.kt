package com.twodauns.eget

import android.content.Context
import android.graphics.Color
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.*
import com.twodauns.eget.tester.Question.CQuestion
import com.twodauns.eget.tester.notC
import java.util.ArrayList

@JvmOverloads fun setViews(number: Int, ctn: Context, maxPoint: Int = 0) : tester.Question? {
    val quest = WebView(ctn)
    val solve = WebView(ctn)
    val answer = TextView(ctn)
    var question : tester.Question
    quest.setBackgroundColor(Color.TRANSPARENT)
    quest.settings.defaultFontSize = 13
//    quest.setLayerType(View.LAYER_TYPE_HARDWARE, null)
    solve.setBackgroundColor(Color.TRANSPARENT)
    solve.settings.defaultFontSize = 13
            quest.settings.apply {
            allowFileAccess = true
            allowFileAccessFromFileURLs = true
            allowUniversalAccessFromFileURLs = true
//            setRenderPriority(WebSettings.RenderPriority.HIGH)
            cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
            setAppCacheEnabled(true)
        }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        quest.scrollIndicators = View.SCROLLBARS_INSIDE_OVERLAY
    }
//    solve.setLayerType(View.LAYER_TYPE_HARDWARE, null)
    var newNum : Int = number
//    if(number >= 23 && number+1 < 27) {
//         newNum = number+1
//    } else {
//         newNum = number
//    }
    quest.loadUrl("file:///android_asset/nahui/answ$newNum.html")
    solve.loadUrl("file:///android_asset/nahui/solv$newNum.html")
    var file = ctn.resources.assets.open("nahui/que$newNum.html").bufferedReader().readLine()
    if(number < notC) {
        question = tester.Question(quest, solve, answer)
        var corr = file.replace("Ответ: ", "")
        answer.text = file
        var correctAnswers = corr.split("|")
        for (answ in correctAnswers) {
            question.addCorrectAnswer(answ)
        }

    } else {
        var spinner = Spinner(ctn)
        var criteria = LayoutInflater.from(ctn).inflate(R.layout.toggle_criteria, null) as ToggleButton
        var answer =  LayoutInflater.from(ctn).inflate(R.layout.toggle_answer, null) as ToggleButton
        var questionToggleButton =  LayoutInflater.from(ctn).inflate(R.layout.toggle_question, null) as ToggleButton
//        var spinnerAdapter = SpinnerAdapter<>
        spinner.adapter = ArrayAdapter<Int>(ctn, android.R.layout.simple_spinner_item, maxPoint)
        var cQuestion = CQuestion(questionToggleButton, answer, criteria, spinner)
        var criteriaa = WebView(ctn)
        criteriaa.loadUrl("file:///android_asset/nahui/que$newNum.html")
        question = tester.Question(quest, solve, criteriaa, cQuestion)
    }
    return question
}