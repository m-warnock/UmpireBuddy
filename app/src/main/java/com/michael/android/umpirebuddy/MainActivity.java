package com.michael.android.umpirebuddy;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button mStrikeButton;
    private Button mBallButton;
    private TextView mStrikeCounter;
    private TextView mBallCounter;

    private Counter[] mStrikeCounterViews = new Counter[] {
            new Counter(R.string.counter_0),
            new Counter(R.string.counter_1),
            new Counter(R.string.counter_2),
            new Counter(R.string.counter_3)
    };

    private Counter[] mBallCounterViews = new Counter[] {
            new Counter(R.string.counter_0),
            new Counter(R.string.counter_1),
            new Counter(R.string.counter_2),
            new Counter(R.string.counter_3),
            new Counter(R.string.counter_4)
    };

    private int mCurrentStrikeIndex = 0;
    private int mCurrentBallIndex = 0;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStrikeCounter = (TextView) findViewById(R.id.strike_counter);
        final int strikeCount = mStrikeCounterViews[mCurrentStrikeIndex].getTextResId();
        mStrikeCounter.setText(strikeCount);

        mBallCounter = (TextView)findViewById(R.id.ball_counter);
        int ballCount = mBallCounterViews[mCurrentBallIndex].getTextResId();
        mBallCounter.setText(ballCount);

        //Define Strike Button and Set Listener
        mStrikeButton = (Button) findViewById(R.id.strike_button);
        mStrikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentStrikeIndex = (mCurrentStrikeIndex + 1) % mStrikeCounterViews.length;
                int strikeCount = mStrikeCounterViews[mCurrentStrikeIndex].getTextResId();
                mStrikeCounter.setText(strikeCount);
                //if strike count == 3 pop up alert
                if (mCurrentStrikeIndex % mStrikeCounterViews.length == 3) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertBuilder.setCancelable(false)
                            .setTitle("OUT!")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog strikeOutAlert = alertBuilder.create();
                    strikeOutAlert.show();

                    //reset to 0
                    mCurrentStrikeIndex = 0;
                    strikeCount = mStrikeCounterViews[mCurrentStrikeIndex].getTextResId();
                    mStrikeCounter.setText(strikeCount);

                    mCurrentBallIndex = 0;
                    int ballCount = mBallCounterViews[mCurrentBallIndex].getTextResId();
                    mBallCounter.setText(ballCount);
                }
            }
        });

        //Define Ball Buttin and set Listener
        mBallButton = (Button) findViewById(R.id.ball_button);
        mBallButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mCurrentBallIndex = (mCurrentBallIndex + 1) % mBallCounterViews.length;
                int ballCount = mBallCounterViews[mCurrentBallIndex].getTextResId();
                mBallCounter.setText(ballCount);
                //if ball count == 4 pop up alert
                if (mCurrentBallIndex % mBallCounterViews.length == 4) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertBuilder.setCancelable(false)
                            .setTitle("WALK!")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog WalkAlert = alertBuilder.create();
                    WalkAlert.show();

                    //reset to 0
                    mCurrentStrikeIndex = 0;
                    int strikeCount = mStrikeCounterViews[mCurrentStrikeIndex].getTextResId();
                    mStrikeCounter.setText(strikeCount);

                    mCurrentBallIndex = 0;
                    ballCount = mBallCounterViews[mCurrentBallIndex].getTextResId();
                    mBallCounter.setText(ballCount);

                }
            }
        });
    }
}
