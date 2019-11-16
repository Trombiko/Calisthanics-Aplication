package com.example.mich.calisthenicsaplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InfoPanelActivity extends YouTubeBaseActivity
{
    private StringBuilder text = new StringBuilder();
    private  StringBuilder text2 = new StringBuilder();
    TextView textView1, textView2, textView3, textView4;
    BufferedReader reader = null, reader2 = null;
    ImageView imageHowHard;


    String tekst1 = "", tekst2 = "";
    int whatNumberDidIGet = 0;
    private static final String TAG = "MainActivity";
    private static String API_KEY = "AIzaSyDEg8i7NZEF237wbnCITZkNKJUATORCY3A";

    YouTubePlayerView mYouTubePlayerView;
    YouTubePlayer.OnInitializedListener mOnInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_panel);

        idConnect();
        whatNumberBePlayed();
        whatTextHaveToRead();
        readFromText();
        readFromSecondText();

    }
    public void idConnect()
    {
        mYouTubePlayerView = findViewById(R.id.youtubePlay);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        imageHowHard = findViewById(R.id.imageView1);
    }



    protected void onStart()
    {

        super.onStart();

        whatYoutubeWillShow();

        mYouTubePlayerView.initialize(getApiKey(), mOnInitializedListener);
        setPolishText();
    }

    public void whatYoutubeWillShow()
    {
        mOnInitializedListener = new YouTubePlayer.OnInitializedListener()
        {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b)
            {
                Log.d(TAG, "onClick: Initialized success");
                switch (whatNumberDidIGet)
                {
                    case 1:
                        youTubePlayer.loadVideo("jDuF7gxP0LU");
                        break;
                    case 2:
                        youTubePlayer.loadVideo("vfAIwmqhYMs");
                        break;
                    case 3:
                        youTubePlayer.loadVideo("uJQEaOeQBMk");
                        break;
                    case 4:
                        youTubePlayer.loadVideo("XvafJkYEayU");
                        break;
                    case 5:
                        youTubePlayer.loadVideo("fMTlYYHSy2A");
                        break;
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d(TAG, "onClick: Failed to initialize");
            }
        };
    }


    public void setPolishText()
    {
        switch(whatNumberDidIGet)
        {
            case 1:
                textView1.setText("FRONT LEVER \n Mięśnie zaangażowane:");
                imageHowHard.setImageDrawable(getResources().getDrawable(R.drawable.ekspertbutton));
                break;
            case 2:
                textView1.setText("MUSCLE UP \n Mięśnie zaangażowane:");
                imageHowHard.setImageDrawable(getResources().getDrawable(R.drawable.zaawansowanybutton));
                break;
            case 3:


                break;
            case 4:


                break;
            case 5:


                break;
        }

        textView2.setText(text);
        textView3.setText(" Ruch: ");
        textView4.setText(text2);

    }

    public void whatTextHaveToRead()
    {
        switch(whatNumberDidIGet)
        {
            case 1:
                tekst1 = "miesnieFrontLeverPL.txt";
                tekst2 = "ruchFrontLeverPL.txt";
                break;
            case 2:
                tekst1 = "miesnieMuscleUpPL.txt";
                tekst2 = "ruchMuscleUpPL.txt";
                break;
            case 3:


                break;
            case 4:


                break;
            case 5:


                break;
        }
    }

    public void readFromText(){
        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open(tekst1)));
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                text.append(mLine); text.append('\n');
            }
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(),"Wystąpił błąd przy czytaniu pliku!",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }}}}

    public void readFromSecondText(){
        try {
            reader2 = new BufferedReader(new InputStreamReader(getAssets().open(tekst2)));
            String mLine2;
            while ((mLine2 = reader2.readLine()) != null) {text2.append(mLine2); text2.append('\n');}
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(),"Wystąpił błąd przy czytaniu pliku!",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } finally {
            if (reader2 != null) {
                try {
                    reader2.close();
                } catch (IOException e){}}}}



    public int whatNumberBePlayed()
    {
        whatNumberDidIGet = getIntent().getIntExtra("info_number",0);

        if(whatNumberDidIGet == 1) {whatNumberDidIGet = 1;}
        else if(whatNumberDidIGet == 2){whatNumberDidIGet = 2;}
        else if(whatNumberDidIGet == 3){whatNumberDidIGet = 3;}
        else if(whatNumberDidIGet == 4){whatNumberDidIGet = 4;}
        else if(whatNumberDidIGet == 5){whatNumberDidIGet = 5;}

        return whatNumberDidIGet;
    }


    public static String getApiKey()
    {
        return API_KEY;
    }


    public void onClickBtnBack(View v)
    {
        Intent intent = new Intent(InfoPanelActivity.this, MainActivity.class);
        finish();
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(InfoPanelActivity.this, MainActivity.class);
        finish();
        startActivity(intent);
        super.onBackPressed();
    }
}
