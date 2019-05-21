package com.twodauns.eget

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.Gravity
import android.view.View
import android.widget.*
import com.twodauns.eget.tester.answer
import com.twodauns.eget.tester.questions

import kotlinx.android.synthetic.main.activity_end_of_test_viewer.*
import kotlinx.android.synthetic.main.content_end_of_test_viewer.*


class EndOfTestViewer : AppCompatActivity() {
    private lateinit var quest: LinearLayout
    private lateinit var mb: Array<Boolean?>
    private var tryCatch: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_of_test_viewer)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        try {
            mb = intent.extras.get("Array") as Array<Boolean?>
            tryCatch = true
        } catch (exept: IllegalStateException) {
        } catch (exept: TypeCastException) {

        }
        quest = findViewById(R.id.linn)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.title = "Просмотр результатов"
        supportActionBar!!.subtitle = "Задание ${intent.getIntExtra("Number", 1) + 1}"
        toolbar!!.setNavigationOnClickListener(View.OnClickListener { onBackPressed() })
//        toolbar!!.title = "Просмотр результатов"
        solveOrAnswer.setOnCheckedChangeListener { _, b ->
            changeViewOfQuestion(intent.getIntExtra("Number", 1), b, intent.getBooleanExtra("trueOrFalse", false))
        }
//        selectButton = findViewById(R.id.questionsPicker)
//        ((questions[intent.getIntExtra("Number", 1)].question.parent) as TableLayout).removeAllViews()
        changeViewOfQuestion(intent.getIntExtra("Number", 1), false, intent.getBooleanExtra("trueOrFalse", false))
    }

    private fun changeViewOfQuestion(number: Int, b: Boolean, trueOrFalse: Boolean) {
        if (b) {
            answerLayout.removeAllViews()
            quest.removeAllViews()
            quest.addView(questions[number].solve)
//            var ed = EditText(this)
//            ed.setText(questions[number].solveForView.text)
            var ed = TextView(this)
            var span: Spannable = SpannableString(questions[number].solveForView.text.filter { it != '.' })
            if (tryCatch) {
//                var span: Spannable = SpannableString(questions[number].solveForView.text.filter { it != '.' })
                for ((i, bool) in mb.withIndex()) {
//                    var span: Spannable = SpannableString(questions[number].solveForView.text)
                    if (bool!!)
                        span.setSpan(ForegroundColorSpan(Color.GREEN), i + 7, i + 8, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
                    else
                        span.setSpan(ForegroundColorSpan(Color.RED), i + 7, i + 8, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
                }
                ed.text = span
            } else {
                if (trueOrFalse)
                    span.setSpan(ForegroundColorSpan(Color.GREEN), 7, span.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
                else
                    span.setSpan(ForegroundColorSpan(Color.RED), 7, span.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
                ed.text = span
            }
            ed.gravity = Gravity.CENTER
            ed.textSize = 21f
            answerLayout.addView(ed)
        } else {
            answerLayout.removeAllViews()
            quest.removeAllViews()
            quest.addView(questions[number].question)
            var ed = TextView(this)
//            ed.isFocusable = false
            if (answer[number].text.toString().equals(""))
                ed.text = "Вы не дали ответа"
            else
                ed.text = "Ваш ответ: " + answer[number].text.toString()
            ed.gravity = Gravity.CENTER
            ed.textSize = 21f
            answerLayout.addView(ed)
        }
    }

    override fun onDestroy() {
        quest.removeAllViews()
        answerLayout.removeAllViews()
        super.onDestroy()
    }
}


