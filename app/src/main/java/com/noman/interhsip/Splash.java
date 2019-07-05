package com.noman.interhsip;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Splash extends AppCompatActivity
{


    private long ms=0,
            splashTime=4000;
    private boolean splashActive=true,paused=false;
    private ValueAnimator colorAnim;
    private static final int RED = 0xffFF0000;
    private static final int WHITE = 0xffFFFFFF;


    @BindView (R.id.cLayout) ConstraintLayout cLayout;
    @BindView (R.id.textView) TextView tv;
    //private ConstraintLayout cLayout;
    //private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        //cLayout = (ConstraintLayout) findViewById(R.id.cLayout);
        //tv = (TextView) findViewById(R.id.textView);

        //for animation
        colorAnim = ObjectAnimator.ofInt(tv, "textColor", RED, WHITE);
        colorAnim.setDuration(1500);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();



        //runs splash screen
        Thread thread = new Thread(){
          public void run(){
              try{
                  //or timing of splash
                  while(splashActive && ms < splashTime){
                      if(!paused)
                          ms += 100;

                      sleep(100);
                  }
              }
              catch(Exception e){


              }
              finally{

                  //checks connectivity
                  if(!isConnected()){
                      message("msg");
                  }
                  else
                  {

                      //if internet is connected go to main
                      Intent i = new Intent(Splash.this, MainActivity.class);
                      startActivity(i);
                      finish();
                  }
              }
          }
        };

        //starts thread
        thread.start();
    }


    //displays msg
    public void message(String msg) {
        final String str = msg;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                makeAndShowDialogBox("No internet Connection");
            }
        });
    }


    //checks connection
    private boolean isConnected(){
        ConnectivityManager connectivity = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivity.getActiveNetworkInfo() != null && connectivity.getActiveNetworkInfo().isConnectedOrConnecting();
    }


    //for dialog
    private void makeAndShowDialogBox(String message) {

        AlertDialog.Builder mDialogBox = new AlertDialog.Builder(this);

        // set message, title, and icon
        mDialogBox.setTitle("Warning");
        mDialogBox.setMessage(message);
        mDialogBox.setIcon(R.mipmap.ic_launcher);

        // Set three option buttons
        mDialogBox.setPositiveButton("Close",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // whatever should be done when answering "YES" goes
                        // here

                    }
                });

        mDialogBox.create();
        mDialogBox.show();
    }
}
