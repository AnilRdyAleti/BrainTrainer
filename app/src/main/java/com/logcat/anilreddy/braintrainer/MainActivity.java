package com.logcat.anilreddy.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

        Button go_btn,ansPos0,ansPos1,ansPos2,ansPos3,playAgainBtn;
        TextView secView,scoreView,totalView,corrOrWrg;
        GridLayout layout;
        ArrayList<Integer> answer = new ArrayList<Integer>();;
        int locationOfCorrectAnswer;
        int score = 0;
        int totalNoOfQuestion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            ansPos0 = (Button) findViewById(R.id.Answer1);
            ansPos1 = (Button) findViewById(R.id.Answer2);
            ansPos2 = (Button) findViewById(R.id.Answer3);
            ansPos3 = (Button) findViewById(R.id.Answer4);
            secView = (TextView) findViewById(R.id.timeView);
            scoreView = (TextView) findViewById(R.id.scoreView);
            totalView = (TextView) findViewById(R.id.totalScore);
            layout = (GridLayout) findViewById(R.id.gridLayout);
            playAgainBtn = (Button) findViewById(R.id.playAgain);
            corrOrWrg = (TextView) findViewById(R.id.rwView);
            corrOrWrg.setVisibility(View.INVISIBLE);

            //OnClickListener for Go! Button
            go_btn = (Button) findViewById(R.id.goBtn);

            go_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    layout.setVisibility(View.VISIBLE);
                    secView.setVisibility(View.VISIBLE);
                    scoreView.setVisibility(View.VISIBLE);
                    totalView.setVisibility(View.VISIBLE);
                    go_btn.setVisibility(View.INVISIBLE);
                    corrOrWrg.setVisibility(View.VISIBLE);
                }
            });

        playAgain(findViewById(R.id.playAgain));

    }

     public  void  playAgain(final View view)
     {
         score = 0;
         totalNoOfQuestion = 0;
         secView.setText("30s");
         scoreView.setText("0/0");
         corrOrWrg.setText("");
         playAgainBtn.setVisibility(View.INVISIBLE);

         generateQuestion();

         new CountDownTimer(30100, 1000){

             @Override
             public void onTick(long millisUntilFinished) {

                 secView.setText(String.valueOf(millisUntilFinished/1000)+"s");

             }

             @Override
             public void onFinish() {

                 playAgainBtn.setVisibility(View.VISIBLE);
                 secView.setText("0s");
                 corrOrWrg.setTextSize(20f);
                 corrOrWrg.setText("Your Score: "+Integer.toString(score) + "/" + Integer.toString(totalNoOfQuestion));

             }
         }.start();

     }

    public void chooseAnswer(View view)
    {
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer)))
        {
            score++;
            corrOrWrg.setText("Correct!");

        } else {
            corrOrWrg.setText("Wrong!");
        }
        totalNoOfQuestion++;
        scoreView.setText(Integer.toString(score) + "/" + Integer.toString(totalNoOfQuestion));

        generateQuestion();
    }

    public void generateQuestion()
    {
        //Random numbers for totalView
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);
        totalView.setText(Integer.toString(a)+ " + "  +Integer.toString(b));

        //Checking for Correct Answer
        locationOfCorrectAnswer = random.nextInt(4);

        answer.clear();

        int inCorrectAnswer;

        for(int i=0;i<4;i++)
        {
            if(i==locationOfCorrectAnswer)
            {
                answer.add(a+b);
            }
            else
            {
                inCorrectAnswer = random.nextInt(42);

                while (inCorrectAnswer == a+b )
                {
                    inCorrectAnswer = random.nextInt(42);
                }
                answer.add(inCorrectAnswer);
            }
        }
        //Changing every button text with random number
        ansPos0.setText(Integer.toString(answer.get(0)));
        ansPos1.setText(Integer.toString(answer.get(1)));
        ansPos2.setText(Integer.toString(answer.get(2)));
        ansPos3.setText(Integer.toString(answer.get(3)));
    }
}
