package com.twodauns.eget

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.Gravity
import android.view.View
import android.widget.*
import com.twodauns.eget.tester.answer
import com.twodauns.eget.tester.questions

import kotlinx.android.synthetic.main.content_end_of_test_viewer.*


class EndOfTestViewer : AppCompatActivity() {
    private lateinit var quest: LinearLayout
    private lateinit var mb: Array<Boolean?>
    private var tryCatch: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_of_test_viewer)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        if (MainActivity.contentDescription == "Русский язык" || MainActivity.contentDescription == "Математика: базовый уровень") {
            //            toolbar.setSubtitleTextColor(getResources().getColor(R.color.def));
            toolbar.setTitleTextColor(resources.getColor(R.color.def))
            //            toolbar.getContext().setTheme(R.style.Theme_AppCompat_Light_DarkActionBar);  РАБОТАЕТ
            toolbar.context.setTheme(R.style.ThemeOverlay_AppCompat_Light)
            //            getSupportActionBar().setHomeAsUpIndicator(R.attr.actionModeCloseDrawable);
        }
        setSupportActionBar(toolbar)
        toolbar.setBackgroundColor(MainActivity.color)
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
            var span = SpannableString(questions[number].solveForView.text.filter { it != '.' })
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
            if (answer[number].text.toString() == "")
                ed.text = "Вы не дали ответа"
            else
                ed.text = "Ваш ответ: ${answer[number].text}"
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


