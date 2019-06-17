package com.twodauns.eget

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.*
import kotlinx.android.synthetic.main.tester.*

import java.io.IOException
import java.io.InputStream
import java.util.ArrayList
import java.util.concurrent.TimeUnit

//class testkt(override var nameList: Array<CharSequence>,
//             override var ctn: Context,
//             override var selectButton: Button,
//             override var toolbar: Toolbar,
//             override var questLayout: TableRow) : AppCompatActivity(), MainActiveButtons {
class testkt : AppCompatActivity(), MainActiveButtons {
    private var milSec: Long = 0 //количество миллисекунд до конца теста
    private var isAlreadyEnded = false //конец теста
    lateinit var timer: Thread //таймер окончания
    lateinit var quest: TableLayout //хранение вопросов
    lateinit var stringOfNames: Array<CharSequence>
    lateinit var answers: Array<String?> // ответы
    var notC: Int = 0 //количество вопросов не из части C, т.е. которое нужно вводить
    override lateinit var answerLayout: LinearLayout
    override lateinit var answer: Array<EditText?> //поля ответов
    override lateinit var nameList: Array<CharSequence>
    override lateinit var ctn: Context
    override lateinit var selectButton: Button
    override lateinit var toolbar: Toolbar
    override lateinit var questLayout: TableLayout
    //    boolean oneQuestion[];
    var cList = mutableMapOf<Int, CharSequence>()
    override lateinit var questions: Array<tester.Question?>
    lateinit var maxPoints: ArrayList<Int>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tester)
        ctn = this
        questLayout = linn
        toolbar = findViewById(R.id.toolbar)
        if (MainActivity.contentDescription == "Русский язык" || MainActivity.contentDescription == "Математика: базовый уровень") {
            //            toolbar.setSubtitleTextColor(getResources().getColor(R.color.def));
            toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.def))
            //            toolbar.getContext().setTheme(R.style.Theme_AppCompat_Light_DarkActionBar);  РАБОТАЕТ
            toolbar.context.setTheme(R.style.ThemeOverlay_AppCompat_Light)
            //            getSupportActionBar().setHomeAsUpIndicator(R.attr.actionModeCloseDrawable);
        }

        setSupportActionBar(toolbar)
        toolbar.setBackgroundColor(MainActivity.color)

        answerLayout = findViewById(R.id.answerLayout)
        selectButton = findViewById(R.id.questionsPicker)
        selectButton.setOnLongClickListener {
            endOfTest()
            false
        }


        quest = findViewById(R.id.linn)

        when {
            MainActivity.contentDescription == "Русский язык" ||
                    MainActivity.contentDescription == "Биология" ||
                    MainActivity.contentDescription == "Химия" -> milSec = 14097700
            MainActivity.contentDescription == "Математика: профильный уровень" ||
                    MainActivity.contentDescription == "Литература" ||
                    MainActivity.contentDescription == "Обществознание" ||
                    MainActivity.contentDescription == "Физика" ||
                    MainActivity.contentDescription == "Информатика" ||
                    MainActivity.contentDescription == "История" -> milSec = 14100000
            MainActivity.contentDescription == "Математика: базовый уровень" ||
                    MainActivity.contentDescription == "язык" && MainActivity.contentDescription != "Русский" ||
                    MainActivity.contentDescription == "География" -> milSec = 10800000
        }

        val nameOfSubject: String = if (MainActivity.contentDescription == "Немецкий язык" || MainActivity.contentDescription == "Английский язык" || MainActivity.contentDescription == "Французкий язык" || MainActivity.contentDescription == "Испанский язык") {
            "ГФС"
        } else if (MainActivity.contentDescription == "Математика: профильный уровень") {
            "МатематикаП"
        } else if (MainActivity.contentDescription == "Математика: базовый уровень") {
            "МатематикаБ"
        } else
            MainActivity.contentDescription as String

        stringOfNames = getFromAssets("$nameOfSubject.txt").split("/\r\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //        }
        toolbar.subtitle = stringOfNames[0]

        questions = arrayOfNulls(stringOfNames.size)

        maxPoints = ArrayList()
        notC = 0
        for (i in stringOfNames.indices) {
            if (stringOfNames[i].toString().toCharArray()[0] != '(') {
                maxPoints.add(0)
                notC++
            }
            else {
                maxPoints.add(Integer.parseInt((stringOfNames[i] as String).split("@".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]))
                stringOfNames[i] = (stringOfNames[i] as String).split("@".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
                cList[i+1] = stringOfNames[i]
            }
        }
        nameList = stringOfNames
        answer = arrayOfNulls(stringOfNames.size)
        for (i in answer.indices) {
            answer[i] = EditText(this)
            if (i >= notC) {
                answer[i]!!.isFocusable = false
                answer[i]!!.hint = "Самостоятельная проверка"
            } else {
                answer[i]!!.hint = "Введите ответ"
            }
        }
        answerLayout.addView(answer[0])
        answers = arrayOfNulls(stringOfNames.size)

        PreviousQuestion.setOnClickListener { onClickPreviousButton() }
        nextQuestion.setOnClickListener { onClickNextButton() }
        selectButton.setOnClickListener { onClickSelectButton() }

        questions[0] = setViews(0, this)
        changeViewOfQuestion(0)
    }

    override fun onStart() {
        super.onStart()
        timer = Thread {
            while (milSec >= 0) {
                try {
                    if (Thread.interrupted()) {
                        break
                    }
                    runOnUiThread {
                        supportActionBar!!.title = TimeUnit.MILLISECONDS.toHours(milSec).toString() + ":" + TimeUnit.MILLISECONDS.toMinutes(milSec) % 60 + ":" + TimeUnit.MILLISECONDS.toSeconds(milSec) % 60
                        milSec -= 500
                    }
                    Thread.sleep(500) //1000 - 1 сек
                } catch (ex: InterruptedException) {
                }

            }
            //            if (!isAlreadyEnded)      ВЕРНИ НА МЕСТО БЛЯТЬ
            //                EndOfTest();
        }
        timer.start()

    }

    override fun onResume() {
        super.onResume()
        Thread {
            runOnUiThread {
                for (i in 1 until stringOfNames.size) {
                    questions[i] = setViews(i, this, maxPoint = maxPoints[i])
                }
            }
        }.start()


    }


    override fun onBackPressed() {}

//    fun onClickPreviousButton(view: View) {
//        if (Integer.parseInt(selectButton.text as String) == 1) {
//            changeQuestion(stringOfNames.size.toString())
//            changeViewOfQuestion(Integer.parseInt(selectButton.text as String) - 1)
//        } else {
//            changeQuestion((Integer.parseInt(selectButton.text as String) - 1).toString())
//            changeViewOfQuestion(Integer.parseInt(selectButton.text as String) - 1)
//        }
//    }
//
//    fun onClickNextButton(view: View) {
//        if (Integer.parseInt(selectButton.text as String) == stringOfNames.size) {
//            changeQuestion("1")
//            changeViewOfQuestion(Integer.parseInt(selectButton.text as String) - 1)
//        } else {
//            changeQuestion((Integer.parseInt(selectButton.text as String) + 1).toString())
//            changeViewOfQuestion(Integer.parseInt(selectButton.text as String) - 1)
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
//        builder.setItems(temporaryStringOfNames) { dialogInterface, i ->
//            changeQuestion((i + 1).toString())
//            changeViewOfQuestion(i)
//        }
//        builder.show()
//    }

//    class Question {
//
//        var cQuest: CQuestion
//
//        var question: WebView //вопрос
//        var solve: WebView //решение
//        var solveForView: TextView //Аккуратный ответ
//
//        var criteria: WebView
//
//        var correctAnswer = ArrayList<CharSequence>()
//
//        internal class CQuestion(var qust: ToggleButton //задание
//                                 , var answer: ToggleButton //решение
//                                 , var criteria: ToggleButton //критерии
//                                 , var points: Spinner //выпадающий список баллов за задание
//        )
//
//        fun addCorrectAnswer(string: String) {
//            correctAnswer.add(string)
//        }
//
//        internal constructor(question: WebView, solve: WebView, solveForView: TextView) {
//            this.question = question
//            this.solve = solve
//            this.solveForView = solveForView
//        }
//
//        internal constructor(question: WebView, solve: WebView, criteriea: WebView, cQuest: CQuestion) {
//            this.question = question
//            this.solve = solve
//            this.criteria = criteriea
//            this.cQuest = cQuest
//        }
//    }

    fun getFromAssets(name: String): String {
        var buffer: ByteArray? = null
        val `is`: InputStream
        try {
            `is` = assets.open(name)
            val size = `is`.available()
            buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return String(buffer!!)

    }
//
//    override fun changeQuestion(number: String) {
//        answerLayout.removeAllViews()
//
//        selectButton.text = number
//        answerLayout.addView(answer[Integer.parseInt(number) - 1])
//        toolbar.subtitle = stringOfNames[Integer.parseInt(number) - 1]
//
//        //        if (stringOfNames[Integer.parseInt(number) - 1].toString().toCharArray()[0] == '(') {
//        //
//        //        } else {
//        //            answer.setFocusableInTouchMode(true);
//        //
//        //        }
//    }
//
//    override fun changeViewOfQuestion(number: Int) {
//        quest.removeAllViews()
//        quest.addView(questions[number]!!.question)
//    }

    fun endOfTest() {
        timer.interrupt()
        var i = 0
        isAlreadyEnded = true
        for (ed in answer) {
            answers[i] = ed!!.text.toString()
            i++
        }
        //        Intent intent = new Intent(this, EndOfTest.class);
        //        startActivity(intent);
        val intent = Intent(this, CChecker::class.java)
        startActivity(intent)
    }


}


