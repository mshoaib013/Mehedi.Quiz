package com.example.dark.quiz;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class homescreen extends AppCompatActivity{

    //All Textview
    private TextView q,c1,c2,c3,c4;
    private TextView timeCountdown;
    private TextView scoreview;
    private TextView over;
    private TextView finalScoreTextview;
    private TextView timeleft;
    private TextView startextview;


    //All Imageview
    private ImageView speakQuestionButton;
    private ImageView scoreStar;


    //Button
    private Button button;
    private Button nextQuestionSetButton;




    private static final String FORMAT = "%02d:%02d";
    TextToSpeech tts;
    private int clicked=0;


    public String[][] que=new String[100][100];
    private int questionNo=0;
    private String runningQuestion;

    private int score=0;

    private int quizCompleted=0;
    private LinearLayout bg;
    private LinearLayout bg2;
    private int timerResetNeed =0;
    private CountDownTimer timeout;
    private CountDownTimer timeOutRed;
    private int timeTaken=0;





    //timer countdown
    private void timeHandler(){
        timeout=new CountDownTimer(60000, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {


                    timeCountdown.setText(""+String.format(FORMAT,
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                    timeTaken++;


            }

            public void onFinish() {
                if(questionNo<9)
                {
                    timeCountdown.setText("00:00");
                    bg.setVisibility(LinearLayout.INVISIBLE);
                    //scoreview.setVisibility(TextView.INVISIBLE);
                    over.setVisibility(TextView.VISIBLE);
                    bg2.setVisibility(LinearLayout.VISIBLE);
                    finalScoreTextview.setText("Score : "+String.valueOf(score));



                    if (score==0) {
                        //scoreStar.setVisibility(ImageView.VISIBLE);
                        startextview.setText("Seriouly!! 0 Star!!!");
                        scoreStar.setImageResource(R.drawable.onestar);
                    }
                    else if(score<3){
                        startextview.setText("Try Harder1");
                        scoreStar.setImageResource(R.drawable.onestar);
                    }
                    else if(score<5){
                        startextview.setText("Try More and More");
                        scoreStar.setImageResource(R.drawable.twostar);
                    }
                    else if(score<7){
                        startextview.setText("Try Your Best");
                        scoreStar.setImageResource(R.drawable.threestar);
                    }
                    else if(score<9){
                        startextview.setText("Keep trying");
                        scoreStar.setImageResource(R.drawable.fourstar);
                    }
                    else{
                        startextview.setText("You are Genios Man!!!");
                        scoreStar.setImageResource(R.drawable.fivestar);
                    }



                }
            }
        }.start();

        timeOutRed=new CountDownTimer(30000, 1000) { // adjust the milli seconds here
            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                //timeCountdown.setBackgroundColor(Color.RED);
                    timeCountdown.setBackgroundResource(R.drawable.rectangle_shape_red);

            }
        }.start();
    }





    //set every question method
    public void setQuestion(){
        runningQuestion=que[questionNo][0];
        q.setText(String.valueOf(questionNo+1)+".  "+que[questionNo][0]);
        c1.setText(que[questionNo][1]);
        c2.setText(que[questionNo][2]);
        c3.setText(que[questionNo][3]);
        c4.setText(que[questionNo][4]);
    }




    private void ConvertTextToSpeech() {
        tts.speak(runningQuestion+"", TextToSpeech.QUEUE_FLUSH, null);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        //Sound wrong right
        final MediaPlayer wrong = MediaPlayer.create(homescreen.this, R.raw.wrong);
        final MediaPlayer correct = MediaPlayer.create(homescreen.this, R.raw.correct);

        //Speak out Question
        speakQuestionButton=(ImageView) findViewById(R.id.audioButton);

        //next Button
        button=(Button) findViewById(R.id.next);

        //QuizScreen and Score screen
        bg = (LinearLayout) findViewById(R.id.quizFullScreen);
        bg2 = (LinearLayout) findViewById(R.id.second_resultboard);


        //Question and choice
        //bg.setBackgroundColor(Color.WHITE);
        q=(TextView) findViewById(R.id.question);
        c1=(TextView) findViewById(R.id.choice1);
        c2=(TextView) findViewById(R.id.choice2);
        c3=(TextView) findViewById(R.id.choice3);
        c4=(TextView) findViewById(R.id.choice4);
        startextview = (TextView) findViewById(R.id.startextview);

        //TimerTextview
        timeCountdown=(TextView) findViewById(R.id.timer);
        scoreview=(TextView) findViewById(R.id.score);
        over=(TextView) findViewById(R.id.overMessage);
        timeleft=(TextView) findViewById(R.id.timeleft);
        over.setVisibility(TextView.INVISIBLE);
        scoreStar=(ImageView) findViewById(R.id.scoreStar);
        finalScoreTextview=(TextView) findViewById(R.id.finalScoreTextview);
        nextQuestionSetButton=(Button) findViewById(R.id.goForNextSet);



        //question declear
        que[0][0]="Whats Your name??";
        que[0][1]="Shoab";
        que[0][2]="Mehedi";
        que[0][3]="WTF!!!!";
        que[0][4]="I";
        que[0][5]="1";

        que[1][0]="Your NSU CGPA??";
        que[1][1]="4.00";
        que[1][2]="3.00";
        que[1][3]="Less than 2";
        que[1][4]="Between 2 & 3";
        que[1][5]="4";

        que[2][0]="Fav category";
        que[2][1]="w";
        que[2][2]="e";
        que[2][3]="r";
        que[2][4]="t";
        que[2][5]="3";

        que[3][0]="worst teacher of ECE";
        que[3][1]="SaM";
        que[3][2]="Mle";
        que[3][3]="SvA";
        que[3][4]="All";
        que[3][5]="4";

        que[4][0]="Greatest musicians of BD";
        que[4][1]="Mahafujur Rahman";
        que[4][2]="Tahasan";
        que[4][3]="Ali G STAR";
        que[4][4]="None of them";
        que[4][5]="2";

        que[5][0]="Whats Your name??";
        que[5][1]="Shoab";
        que[5][2]="Mehedi";
        que[5][3]="WTF!!!!";
        que[5][4]="I";
        que[5][5]="1";

        que[6][0]="Your NSU CGPA??";
        que[6][1]="4.00";
        que[6][2]="3.00";
        que[6][3]="Less than 2";
        que[6][4]="Between 2 & 3";
        que[6][5]="4";

        que[7][0]="Fav  category";
        que[7][1]="ab";
        que[7][2]="a";
        que[7][3]="s";
        que[7][4]="d";
        que[7][5]="3";

        que[8][0]="worst teacher of ECE";
        que[8][1]="SaM";
        que[8][2]="Mle";
        que[8][3]="SvA";
        que[8][4]="All";
        que[8][5]="4";

        que[9][0]="Greatest musicians of BD";
        que[9][1]="Mahafujur Rahman";
        que[9][2]="Tahasan";
        que[9][3]="Ali G STAR";
        que[9][4]="None of them";
        que[9][5]="2";




        //init score to 0 on open
        scoreview.setText(String.valueOf(score)+"/10");

        //1st time question set
        setQuestion();

        //Speak Question button
        speakQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tts=new TextToSpeech(homescreen.this, new TextToSpeech.OnInitListener() {

                    @Override
                    public void onInit(int status) {
                        // TODO Auto-generated method stub
                        if(status == TextToSpeech.SUCCESS){
                            int result=tts.setLanguage(Locale.US);
                            if(result==TextToSpeech.LANG_MISSING_DATA ||
                                    result==TextToSpeech.LANG_NOT_SUPPORTED){
                                Log.e("error", "This Language is not supported");
                            }
                            else{
                                ConvertTextToSpeech();
                            }
                        }
                        else
                            Log.e("error", "Initilization Failed!");
                    }
                });
            }
        });


        //nextbutton setup new question,choice & reset color of datafield
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //bg.setBackgroundColor(Color.GRAY);
                /*c1.setBackgroundColor(Color.WHITE);
                c2.setBackgroundColor(Color.WHITE);
                c3.setBackgroundColor(Color.WHITE);
                c4.setBackgroundColor(Color.WHITE);*/
                if(questionNo>9)
                {
                    bg.setVisibility(LinearLayout.INVISIBLE);
                    //scoreview.setVisibility(TextView.INVISIBLE);
                    timeout.cancel();
                    timeOutRed.cancel();
                    bg2.setVisibility(LinearLayout.VISIBLE);
                    finalScoreTextview.setText("Score : "+String.valueOf(score));
                    timeleft.setText("Time : "+String.valueOf(timeTaken));
                }
                c1.setBackgroundResource(R.drawable.frame);
                c2.setBackgroundResource(R.drawable.frame);
                c3.setBackgroundResource(R.drawable.frame);
                c4.setBackgroundResource(R.drawable.frame);
                setQuestion();
                clicked=0;


                if (score==0) {
                    //scoreStar.setVisibility(ImageView.VISIBLE);
                    startextview.setText("Seriouly!! 0 Star!!!");
                    scoreStar.setImageResource(R.drawable.onestar);
                }
                else if(score<3){
                    startextview.setText("Try Harder1");
                    scoreStar.setImageResource(R.drawable.onestar);
                }
                else if(score<5){
                    startextview.setText("Try More and More");
                    scoreStar.setImageResource(R.drawable.twostar);
                }
                else if(score<7){
                    startextview.setText("Try Your Best");
                    scoreStar.setImageResource(R.drawable.threestar);
                }
                else if(score<9){
                    startextview.setText("Keep trying");
                    scoreStar.setImageResource(R.drawable.fourstar);
                }
                else{
                    startextview.setText("You are Genios Man!!!");
                    scoreStar.setImageResource(R.drawable.fivestar);
                }
            }
        });



        //choice 1
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked++;
                if (clicked>1) {
                    Toast.makeText(homescreen.this, "Already Answered" + "Correction not Possible", Toast.LENGTH_SHORT).show();
                }
                else if ((que[questionNo][5]).equals(String.valueOf(1))) {
                    //bg.setBackgroundColor(Color.GREEN);
                    c1.setBackgroundResource(R.drawable.rectangle_shape_green);
                    questionNo++;
                    score++;
                    scoreview.setText(String.valueOf(score)+"/10");
                    correct.start();
                }
                else {
                    //bg.setBackgroundColor(Color.RED);
                    c1.setBackgroundResource(R.drawable.rectangle_shape_red);
                    if (que[questionNo][5].equals(String.valueOf(2)))
                        c2.setBackgroundResource(R.drawable.rectangle_shape_green);
                    else if (que[questionNo][5].equals(String.valueOf(3)))
                        c3.setBackgroundResource(R.drawable.rectangle_shape_green);
                    else
                        c4.setBackgroundResource(R.drawable.rectangle_shape_green);
                    questionNo++;
                    wrong.start();
                }

            }
        });


        //choice 2
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked++;
                if (clicked>1) {
                    Toast.makeText(homescreen.this, "Already Answered" + "Correction not Possible", Toast.LENGTH_SHORT).show();
                }
                else if ((que[questionNo][5]).equals(String.valueOf(2))) {
                    //bg.setBackgroundColor(Color.GREEN);
                    c2.setBackgroundResource(R.drawable.rectangle_shape_green);
                    correct.start();
                    questionNo++;
                    score++;
                    scoreview.setText(String.valueOf(score)+"/10");
                }
                else {
                    //bg.setBackgroundColor(Color.RED);
                    c2.setBackgroundResource(R.drawable.rectangle_shape_red);
                    if (que[questionNo][5].equals(String.valueOf(1)))
                        c1.setBackgroundResource(R.drawable.rectangle_shape_green);
                    else if (que[questionNo][5].equals(String.valueOf(3)))
                        c3.setBackgroundResource(R.drawable.rectangle_shape_green);
                    else
                        c4.setBackgroundResource(R.drawable.rectangle_shape_green);
                    questionNo++;
                    wrong.start();
                }

            }
        });


        //choice 3
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked++;
                if (clicked>1) {
                    Toast.makeText(homescreen.this, "Already Answered" + "Correction not Possible", Toast.LENGTH_SHORT).show();
                }
                else if ((que[questionNo][5]).equals(String.valueOf(3))) {
                    //bg.setBackgroundColor(Color.GREEN);
                    c3.setBackgroundResource(R.drawable.rectangle_shape_green);
                    questionNo++;
                    correct.start();
                    score++;
                    scoreview.setText(String.valueOf(score)+"/10");
                }
                else {
                    //bg.setBackgroundColor(Color.RED);
                    c3.setBackgroundResource(R.drawable.rectangle_shape_red);
                    if (que[questionNo][5].equals(String.valueOf(2)))
                        c2.setBackgroundResource(R.drawable.rectangle_shape_green);
                    else if (que[questionNo][5].equals(String.valueOf(1)))
                        c1.setBackgroundResource(R.drawable.rectangle_shape_green);
                    else
                        c4.setBackgroundResource(R.drawable.rectangle_shape_green);
                    questionNo++;
                    wrong.start();
                }

            }
        });


        //choice 4
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked++;
                if (clicked>1) {
                    Toast.makeText(homescreen.this, "Already Answered!! Correction not Possible", Toast.LENGTH_SHORT).show();
                }
                else if ((que[questionNo][5]).equals(String.valueOf(4))) {
                    //bg.setBackgroundColor(Color.GREEN);
                    c4.setBackgroundResource(R.drawable.rectangle_shape_green);
                    questionNo++;
                    correct.start();
                    score++;
                    scoreview.setText(String.valueOf(score)+"/10");
                }
                else {
                    //bg.setBackgroundColor(Color.RED);
                    c4.setBackgroundResource(R.drawable.rectangle_shape_red);
                    if (que[questionNo][5].equals(String.valueOf(2)))
                        c2.setBackgroundResource(R.drawable.rectangle_shape_green);
                    else if (que[questionNo][5].equals(String.valueOf(3)))
                        c3.setBackgroundResource(R.drawable.rectangle_shape_green);
                    else
                        c1.setBackgroundResource(R.drawable.rectangle_shape_green);
                    questionNo++;
                    wrong.start();
                }

            }
        });



        timeHandler();



        //nextRound Button
        nextQuestionSetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked=0;
                questionNo=0;
                score=0;
                quizCompleted=0;
                timerResetNeed=0;
                timeTaken=0;
                bg2.setVisibility(View.INVISIBLE);
                bg.setVisibility(View.VISIBLE);
                scoreview.setText("0/10");
                timeCountdown.setVisibility(View.VISIBLE);
                over.setVisibility(View.INVISIBLE);
                setQuestion();
                q.setVisibility(View.VISIBLE);
                timeout.cancel();
                timeOutRed.cancel();
                timeHandler();
                timeCountdown.setBackgroundResource(R.drawable.frame);
                c1.setBackgroundResource(R.drawable.frame);
                c2.setBackgroundResource(R.drawable.frame);
                c3.setBackgroundResource(R.drawable.frame);
                c4.setBackgroundResource(R.drawable.frame);

            }
        });



    }
    public void  test(){
//test
    }
}
