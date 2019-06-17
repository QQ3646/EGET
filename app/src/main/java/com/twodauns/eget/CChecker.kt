package com.twodauns.eget

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.Toolbar
import android.view.View
import com.twodauns.eget.tester.*
import kotlinx.android.synthetic.main.activity_cchecker.*
import java.lang.Integer.parseInt
import java.util.zip.Inflater
import android.view.LayoutInflater
import android.widget.*
//import android.widget.*
import kotlinx.android.synthetic.main.actions_buttons.*
import kotlinx.android.synthetic.main.actions_buttons.view.*


class CChecker : AppCompatActivity(), MainActiveButtons {
    //    lateinit var spinner: Spinner
    //    var number: Int = 0
    var number: Int = 0
    var countCQuestion = tester.stringOfNames.size - notC
    var controllerTableRows = arrayOfNulls<TableRow?>(countCQuestion)
    var spinners = arrayOfNulls<Spinner>(countCQuestion)
    override lateinit var answerLayout: LinearLayout
    override lateinit var answer: Array<EditText?> //поля ответов
    override lateinit var nameList: Array<CharSequence>
    override var ctn: Context = this
    override lateinit var selectButton: Button //+
    override lateinit var toolbar: Toolbar //+
    override lateinit var questLayout: TableLayout //+
    override lateinit var questions: Array<Question?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cchecker)
        selectButton = findViewById<Button>(R.id.questionsPicker)
        toolbar = tlb
        questLayout = linn
        selectButton.setOnLongClickListener {
            endOfTest()
            false
        }
        PreviousQuestion.setOnClickListener { onClickPreviousButton() }
        val inflater = this.layoutInflater
        for (i in 0 until countCQuestion) {
            controllerTableRows[i] =  inflater.inflate(R.layout.actions_buttons, null) as TableRow
            spinners[i] = controllerTableRows[i]!!.points2
            var points = Array(tester.maxPoints.size) {it+1}
            spinners[i]!!.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, points)
            controllerTableRows[i]!!.ToggleQ.setOnClickListener(this::toggleQ) //задание
            controllerTableRows[i]!!.ToggleC.setOnClickListener(this::toggleC) //критерии
            controllerTableRows[i]!!.ToggleS.setOnClickListener(this::toggleS) //решение
        }
    }

    fun toggleQ(view: View) {
        controllerTableRows[number]!!.ToggleC.isChecked = false
        controllerTableRows[number]!!.ToggleS.isChecked = false

    }

    fun toggleC(view: View) {
        controllerTableRows[number]!!.ToggleQ.isChecked = false
        controllerTableRows[number]!!.ToggleS.isChecked = false

    }

    fun toggleS(view: View) {
        controllerTableRows[number]!!.ToggleQ.isChecked = false
        controllerTableRows[number]!!.ToggleC.isChecked = false

    }

    override fun onBackPressed() {}

//    fun onClickPreviousButton(view: View) {
//        if (parseInt(questionsPicker.text as String) == 1) {
//            changeQuestion(stringOfNames.size.toString())
//            changeViewOfQuestion(parseInt(questionsPicker.text as String) - 1)
//        } else {
//            changeQuestion((parseInt(questionsPicker.text as String) - 1).toString())
//            changeViewOfQuestion(parseInt(questionsPicker.text as String) - 1)
//        }
//    }
//
//    fun onClickNextButton(view: View) {
//        if (parseInt(questionsPicker.text as String) == stringOfNames.size) {
//            changeQuestion("1")
//            changeViewOfQuestion(parseInt(questionsPicker.text as String) - 1)
//        } else {
//            changeQuestion((parseInt(questionsPicker.text as String) + 1).toString())
//            changeViewOfQuestion(parseInt(questionsPicker.text as String) - 1)
//            //тоже самое
//        }
//    }
//
//    fun onClickSelectButton(view: View) {
//        val temporaryStringOfNames = arrayOfNulls<CharSequence>(stringOfNames.size)
//        for (i in stringOfNames.indices) {
//            temporaryStringOfNames[i] = (i + 1).toString() + ". " + stringOfNames[i]
//        }
//        val builder = AlertDialog.Builder(this)
//        builder.setItems(temporaryStringOfNames) { _, i ->
//            changeQuestion((i + 1).toString())
//            changeViewOfQuestion(i)
//        }
//        builder.show()
//    }
//
    override fun changeQuestion(number: String) {
        answerLayout.removeAllViews()

        questionsPicker.text = number
        toolbar.subtitle = stringOfNames[parseInt(number) - 1]

    }

    override fun changeViewOfQuestion(number: Int) {
        linn.removeAllViews()
        linn.addView(questions[number]!!.question)
        containerForButtons.removeAllViews()
        containerForButtons.addView(controllerTableRows[number])
    }

    fun endOfTest() {
        for ((i, spinner) in spinners.withIndex()) {
            answers[i] = spinner!!.selectedItem.toString()
        }
        val intent = Intent(this, EndOfTest::class.java)
        startActivity(intent)

    }
}
