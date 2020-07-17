package com.example.test
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.schoolscientistsexample.MichaelITeamScope
import com.example.schoolscientistsexample.ServerCommandMichaelI
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import android.os.SystemClock.sleep
import android.widget.TextView
import android.os.Handler
import android.os.Message

class MainActivity : AppCompatActivity() {

    var t = ServerCommandMichaelI()
    private var thread = Thread()
    private var endThread = false
    fun resetC (view: View){
        t.ResetCount()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
   /* fun toastMe(view: View) {
        // val myToast = Toast.makeText(this, message, duration);
        val myToast = Toast.makeText(this, "Hello Toast!", Toast.LENGTH_SHORT)
        myToast.show()
    }
    fun countMe (view: View) {
        // Get the value of the text view.
        val countString = textView.text.toString()
        // Convert value to a number and increment it
        var count: Int = Integer.parseInt(countString)
        count++
        // Display the new value in the text view.
        textView.text = count.toString();
    }
    fun randomMe (view: View) {

        val randomIntent = Intent(this, SecondActivity2::class.java)

        val countString = textView.text.toString()

        val count = Integer.parseInt(countString)

        randomIntent.putExtra(SecondActivity2.TOTAL_COUNT, count)

        startActivity(randomIntent)

    }*/
    fun Avtor (view: View){
        val randomIntent = Intent(this, ThirdActivity3::class.java)
        startActivity(randomIntent)
    }

    /*fun scoreGet(view: View) {
        var jsonStr = t.gameScoreGet()

        val score: MichaelITeamScope =
            Gson().fromJson<MichaelITeamScope>(jsonStr, MichaelITeamScope::class.java)

        textViewTeam1Score.text = score.team1ScoreGet().toString()
        textViewTeam2Score.text = score.team2ScoreGet().toString()
    }*/
    fun startAutoUpdateScore(view: View) {

        endThread = false

        val runnable = object : Runnable {
            override fun run() {
                var jsonStr : String
                var score: MichaelITeamScope
                while (true) {
                    jsonStr = t.gameScoreGet()

                    score = Gson().fromJson<MichaelITeamScope>(jsonStr, MichaelITeamScope::class.java)

                    var msg = handler.obtainMessage()
                    var bundle = Bundle()
                    bundle.putString("teamScore", score.team1ScoreGet().toString()+"#"+ score.team2ScoreGet().toString())
                    msg.setData(bundle)
                    handler.sendMessage(msg)

                    sleep(2000)

                    if(endThread)
                        break
                }
            }
        }

        button2.isEnabled = false
       // buttonAutoUpdateStop.isEnabled = true

        thread = Thread(runnable)
        thread.start()
    }

   /* fun stopAutoUpdateScore(view: View) {
        endThread = true

        //buttonAutoUpdateStart.isEnabled = true
        //buttonAutoUpdateStop.isEnabled = false
    }*/

    var handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            var bundle = msg.getData()
            var msg = bundle.getString("teamScore")

            var score = msg!!.split("#")

            var infoTextView = findViewById(R.id.textViewTeam1Score) as TextView
            infoTextView.setText(score[0])

            infoTextView = findViewById(R.id.textViewTeam2Score) as TextView
            infoTextView.setText(score[1])
        }
    }
}