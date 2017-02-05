package com.michael.android.umpirebuddy;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button mStrikeButton;
    private Button mBallButton;
    private Button mResetButton;
    private TextView mStrikeCounter;
    private TextView mBallCounter;
    private TextView outCount;
    private int intOutCount = 0;
    private int strikeCount = 0;
    private int ballCount = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStrikeCounter = (TextView) findViewById(R.id.strike_counter);
        mStrikeCounter.setText(Integer.toString(strikeCount));

        mBallCounter = (TextView)findViewById(R.id.ball_counter);
        mBallCounter.setText(Integer.toString(ballCount));

        outCount = (TextView) findViewById(R.id.out_count);
        //check for saved outcount in shared preferences
        intOutCount = getValue(MainActivity.this);
        if(intOutCount != 0 ) {
            outCount.setText(Integer.toString(intOutCount));
        }
        else {
            intOutCount = 0;
            outCount.setText(Integer.toString(intOutCount));
        }

        //Define Strike Button and Set Listener
        mStrikeButton = (Button) findViewById(R.id.strike_button);
        mStrikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strikeCount++;
                mStrikeCounter.setText(Integer.toString(strikeCount));
                //if strike count == 3 pop up alert
                if (strikeCount == 3) {
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


                    //reset strike & ball count to 0
                    strikeCount = 0;
                    mStrikeCounter.setText(Integer.toString(strikeCount));

                    ballCount = 0;
                    mBallCounter.setText(Integer.toString(ballCount));

                    //increase outs count
                    intOutCount++;
                    outCount.setText(Integer.toString(intOutCount));

                }
            }
        });

        //Define Ball Button and set Listener
        mBallButton = (Button) findViewById(R.id.ball_button);
        mBallButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ballCount++;
                mBallCounter.setText(Integer.toString(ballCount));
                //if ball count == 4 pop up alert
                if (ballCount == 4) {
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
                    strikeCount = 0;
                    mStrikeCounter.setText(Integer.toString(strikeCount));

                    ballCount = 0;
                    mBallCounter.setText(Integer.toString(ballCount));

                    //increase outs count
                    intOutCount++;
                    outCount.setText(Integer.toString(intOutCount));
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.about_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.menu_item_about){
            startActivity(new Intent(this, AboutActivity.class));
        }
        else if(id == R.id.reset_button){
            clearSharedPreference(MainActivity.this);
            intOutCount = strikeCount = ballCount = 0;
            outCount.setText(Integer.toString(intOutCount));
            mStrikeCounter.setText(Integer.toString(strikeCount));
            mBallCounter.setText(Integer.toString(ballCount));
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        save(MainActivity.this, intOutCount);

    }

    public void save(Context context, int intnumber) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences("outcount", Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.putInt("outcount", intnumber);
        editor.commit();
    }

    public int getValue(Context context) {
        SharedPreferences settings;
        int intnumber;
        settings = context.getSharedPreferences("outcount", Context.MODE_PRIVATE); //1
        intnumber = settings.getInt("outcount", 0); //2
        return intnumber;
    }

    public void clearSharedPreference(Context context) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences("outcount", Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.clear();
        editor.commit();
    }
}
