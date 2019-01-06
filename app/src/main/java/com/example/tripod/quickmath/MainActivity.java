package com.example.tripod.quickmath;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
Button go , button0,button1,button2,button3, playAgain;
    ArrayList<Integer> Answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;
    TextView result, points;
    TextView question, timer, percentage;
    RelativeLayout gamelayout;
    float perResult;


    public void chooseAnswer(View view)
    {
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){

           score++;
            result.setText("Correct!!");

        }
        else {
            result.setText("Incorrect!!");
        }
        numberOfQuestions++;
        points.setText(Integer.toString(score)+ "/" + Integer.toString(numberOfQuestions));
        generateQuestions();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        go = (Button) findViewById(R.id.startButton);
         question = (TextView) findViewById(R.id.sumTextView);
         button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
         button3 = (Button) findViewById(R.id.button3);
        result = (TextView) findViewById(R.id.ResultTextView);
        points = (TextView) findViewById(R.id.PointsTextView);
        timer = (TextView) findViewById(R.id.TimerTextView);
        playAgain = (Button) findViewById(R.id.playButton);
        gamelayout = (RelativeLayout) findViewById(R.id.relative);
        percentage = (TextView) findViewById(R.id.PercentagetextView);



    }
    public void playAgain(View view)
    {
        button0.setAlpha(1);
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button1.setAlpha(1);
        button2.setAlpha(1);
        button3.setAlpha(1);
        score= 0;
        numberOfQuestions = 0;
        timer.setText("30s");
        points.setText("0/0");
        result.setText("");
        playAgain.setVisibility(View.INVISIBLE);
        result.setText("");
        percentage.setText("");
        generateQuestions();


        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                timer.setText(String.valueOf(millisUntilFinished/ 1000)+"s");
            }

            @Override
            public void onFinish() {

                timer.setText("0s");
                result.setText("Your Score:"+ Integer.toString(score)+ "/" + Integer.toString(numberOfQuestions) );
            perResult = (float)score/(float)numberOfQuestions*100;
                String ans = String.format("%.2f",perResult);
                percentage.setText(""+ ans +"%");
                button0.setAlpha(0.5f);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                button1.setAlpha(0.5f);
                button2.setAlpha(0.5f);
                button3.setAlpha(0.5f);
               // gamelayout.setVisibility(View.INVISIBLE);

                playAgain.setVisibility(RelativeLayout.VISIBLE);
            }
        }.start();
    }

    public void start(View view){
         go.setVisibility(View.INVISIBLE);
        gamelayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playButton));
    }

    public void generateQuestions()
    {
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);
        question.setText(Integer.toString(a)+" + "+Integer.toString(b));

        locationOfCorrectAnswer = random.nextInt(4);
        Answers.clear();

        int incorrectanswer;
        for(int i =0; i<4; i++){

            if(i== locationOfCorrectAnswer)
            {
                Answers.add(a+b);
            }
            else
            {   incorrectanswer=random.nextInt(41);
                while(incorrectanswer == a+b)
                {
                    incorrectanswer=random.nextInt(41);
                }

                Answers.add(incorrectanswer);
            }
        }
        button0.setText(Integer.toString(Answers.get(0)));
        button1.setText(Integer.toString(Answers.get(1)));
        button2.setText(Integer.toString(Answers.get(2)));
        button3.setText(Integer.toString(Answers.get(3)));
    }

}
