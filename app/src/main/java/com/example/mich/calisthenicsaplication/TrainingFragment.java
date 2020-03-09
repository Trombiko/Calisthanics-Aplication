package com.example.mich.calisthenicsaplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mich.calisthenicsaplication.DatabaseHelper;
import com.example.mich.calisthenicsaplication.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.view.View.GONE;

public class TrainingFragment extends Fragment implements View.OnClickListener
{
    Button btnStart, btnFinishTraining;
    TextView textStart, textGetReady, textWithExercise, textWithProgress;
    ImageView imageExercise, imageFinish;
    ProgressBar progressBar;
    String newEntry = " ";
    int willchange = 0;

    DatabaseHelper mDatabaseHelper;

    String treningText = " ";

    private CountDownTimer countDownTimer;
    private long timeLeftInMilliseconds = 3000;
    private boolean timerRunning;
    String cwiczenie = "", timeNow="",dateNow = "";
    int timerCounting = 0, whatLvlWeDoNow = 0, FinishWork = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.training_fragment, container, false);

        btnStart = rootView.findViewById(R.id.btnStart);
        progressBar = rootView.findViewById(R.id.progressBar);
        textWithProgress = rootView.findViewById(R.id.textWithProgress);
        textStart = rootView.findViewById(R.id.textCounter);
        textGetReady = rootView.findViewById(R.id.textGetReady);
        textGetReady.setVisibility(GONE);
        imageExercise = rootView.findViewById(R.id.imageExercise);
        textWithExercise = rootView.findViewById(R.id.textWithExercise);
        imageFinish = rootView.findViewById(R.id.imagedone);
        imageFinish.setOnClickListener(this); imageFinish.setVisibility(View.INVISIBLE);
        btnStart.setOnClickListener(this);
        btnFinishTraining = rootView.findViewById(R.id.buttonFinishTraining); btnFinishTraining.setOnClickListener(this);

        mDatabaseHelper = new DatabaseHelper(getContext());

        Bundle args = getArguments();
        if (args != null)
            whatLvlWeDoNow = args.getInt("exercise");

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        setBarTitle();
        if (Locale.getDefault().getLanguage().equals(new Locale("en").getLanguage())) {
            getActivity().setTitle("Exercise: " + cwiczenie);
        }else {
            getActivity().setTitle("Ćwiczenie: " + cwiczenie);
        }
    }

    public void setBarTitle()
    {
        if(whatLvlWeDoNow > 0 && whatLvlWeDoNow < 5)
        {
            cwiczenie = "Front Lever";
        }else if(whatLvlWeDoNow > 4 && whatLvlWeDoNow < 9)
        {
            cwiczenie = "Muscle Up";
        }else if(whatLvlWeDoNow > 8 && whatLvlWeDoNow < 13)
        {
            cwiczenie = "Planche";
        }else if(whatLvlWeDoNow > 12 && whatLvlWeDoNow < 17)
        {
            cwiczenie = "Back Lever";
        }else if(whatLvlWeDoNow > 14 && whatLvlWeDoNow < 21)
        {
            cwiczenie = "Hand Stand Push Ups";
        }
    }

    public void AddData(String newEntry, String treningText)
    {
        boolean insertData = mDatabaseHelper.addData(newEntry, treningText);

        if (insertData) {
            Toast.makeText(getActivity(),"Data Successfully Inserted!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(),"Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View v) {
        SharedPreferences prefFront = getActivity().getSharedPreferences("NAME_FRONT" , 0);
        SharedPreferences.Editor edtFront = prefFront.edit();

        Bundle args = getArguments();
        if (args != null)
            willchange = args.getInt("keyFront");

        final ListViewDatabase listViewDatabase = new ListViewDatabase();

        switch (v.getId())
        {
            case R.id.btnStart:
                startAndStopTimer();
                textGetReady.setVisibility(View.VISIBLE);
                textGetReady.setText(R.string.ready_for_training);
                btnStart.setVisibility(GONE);
                break;

            case R.id.imagedone:
                FinishWork++;
                if(FinishWork %2 == 1) {Toast.makeText(getActivity(), R.string.click_once_more, Toast.LENGTH_SHORT).show();}
                if(FinishWork %2 == 0)
                {
                    textWithExercise.setVisibility(GONE);
                    imageExercise.setVisibility(GONE);
                    textStart.setVisibility(View.VISIBLE);
                    imageFinish.setVisibility(View.INVISIBLE);
                    startAndStopTimer();
                }
                break;

            case R.id.buttonFinishTraining:
                getCurrentTime();
                switch(whatLvlWeDoNow)
                {
                    case 1:
                        if(willchange > 1 || willchange == 0)
                        {
                            edtFront.putInt("keyFront", 2);
                        }
                        newEntry = "Front Lever - LVL 1/4: "; // dodajemy do COL2 w bazie danych
                        break;

                    case 2:
                        if(willchange > 2 || willchange == 0)
                        {
                            edtFront.putInt("keyFront", 3);
                        }
                        newEntry = "Front Lever - LVL 2/4";
                        break;

                    case 3:
                        if(willchange > 3 || willchange == 0)
                        {
                            edtFront.putInt("keyFront", 4);
                        }
                        newEntry = "Front lever - LVL 3/4";
                        break;

                    case 4:
                        edtFront.putInt("keyFront", 5);
                        newEntry = "Front Lever - LVL 4/4";
                        break;
                }



                if (newEntry.length() != 0 && treningText.length() != 0) // jesli puscty rekord dodajemy nowy tekst
                {
                    AddData(newEntry, treningText);
                }

                edtFront.apply();

                getActivity().getSupportFragmentManager().popBackStack();

                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, listViewDatabase)
                        .addToBackStack(null)
                        .commit();
                break;
        }}


    public void startAndStopTimer()
    {
        if (timerRunning)
        {
            stopTimer();
        } else {
            startTimer();
        }
    }


    public void startTimer()
    {
        countDownTimer = new CountDownTimer
                (timeLeftInMilliseconds, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMilliseconds = millisUntilFinished;
                odswiezLicznik();
            }

            @Override
            public void onFinish() {
                timerCounting++;
                imageFinish.setVisibility(View.VISIBLE);
                timerRunning = false;
                textStart.setText("");
                textGetReady.setVisibility(GONE);
                timeLeftInMilliseconds = 30000;

                trainings();
            }
        }.start();

        timerRunning = true;
    }

    public void stopTimer()
    {
        countDownTimer.cancel();
        timerRunning = false;
    }


    public void odswiezLicznik()
    {
        int minutes = (int) timeLeftInMilliseconds / 60000;
        int seconds = (int) timeLeftInMilliseconds % 60000 / 1000;
        String timeLeftText;

        if (timeLeftInMilliseconds <= 10000)
        {
            if (seconds < 6){
                textStart.setTextColor(getResources()
                        .getColor(R.color.backgroundNotAllowed));
            }
            timeLeftText = "" + seconds;
            textStart.setText(timeLeftText);
        } else {
            timeLeftText = "" + minutes;
            timeLeftText += ":";
            if (seconds < 10) timeLeftText += "0";
            timeLeftText += seconds;
            textStart.setText(timeLeftText);
        }
    }

    public void trainings()
    {
        textWithExercise.setVisibility(View.VISIBLE);
        imageExercise.setVisibility(View.VISIBLE);

        switch (whatLvlWeDoNow)
        {
            case 1: // lvl 1:4 - FRONT LEVER
                if(timerCounting == 1 || timerCounting == 4)
                {
                    textWithExercise.setVisibility(View.VISIBLE); textWithExercise.setText("Tuck Front Lever \n 4 seconds");
                    imageExercise.setVisibility(View.VISIBLE);
                    imageExercise.setImageDrawable(getResources().getDrawable(R.drawable.tuckfront));
                    if(timerCounting == 4)
                    {
                        progressBar.setProgress(75);
                        textWithProgress.setText("75%"); textWithExercise.setText("Tuck Front Lever \n 3 seconds");
                        btnFinishTraining.setVisibility(View.VISIBLE);
                        imageFinish.setVisibility(GONE);
                    }
                }else if(timerCounting == 2)
                {
                    textWithExercise.setText("Pull Up \n 5 times");
                    textWithProgress.setText("25%");
                    progressBar.setProgress(25);
                    imageExercise.setImageDrawable(getResources().getDrawable(R.drawable.pullup));
                }
                else if(timerCounting == 3)
                {
                    textWithProgress.setText("50%");
                    progressBar.setProgress(50);
                    textWithExercise.setText("L-sit on the bar\n 6 seconds");
                    imageExercise.setImageDrawable(getResources().getDrawable(R.drawable.lsit));
                }
                break;

            case 2:
                if(timerCounting == 1 || timerCounting == 4)
                {
                    textWithExercise.setVisibility(View.VISIBLE); textWithExercise.setText("Adv Tuck Front Lever \n 3 - 5 seconds");
                    imageExercise.setVisibility(View.VISIBLE);
                    imageExercise.setImageDrawable(getResources().getDrawable(R.drawable.advtuckfront));
                    if(timerCounting == 4)
                    {
                        progressBar.setProgress(75);
                        textWithProgress.setText("75%"); textWithExercise.setText("Adv Tuck Front Lever \n 2 seconds");
                        btnFinishTraining.setVisibility(View.VISIBLE);
                        imageFinish.setVisibility(GONE);
                    }
                }else if(timerCounting == 2)
                {
                    textWithExercise.setText("Toes to bar \n 6 times");
                    textWithProgress.setText("25%");
                    progressBar.setProgress(25);
                    imageExercise.setImageDrawable(getResources().getDrawable(R.drawable.toestobar));
                }else if(timerCounting == 3)
                {
                    textWithProgress.setText("50%");
                    progressBar.setProgress(50);
                    textWithExercise.setText("L-sit on the bar \n 10 seconds");
                    imageExercise.setImageDrawable(getResources().getDrawable(R.drawable.lsit));
                }
                break;

            case 3:
                if(timerCounting == 1)
                {
                    textWithExercise.setVisibility(View.VISIBLE); textWithExercise.setText("One Leg Front Lever \n 3 seconds");
                    imageExercise.setVisibility(View.VISIBLE);
                    imageExercise.setImageDrawable(getResources().getDrawable(R.drawable.onelegfront));
                }else if(timerCounting == 2)
                {
                    textWithExercise.setText("Pull Up \n 10 times");
                    textWithProgress.setText("25%");
                    progressBar.setProgress(25);
                    imageExercise.setImageDrawable(getResources().getDrawable(R.drawable.pullup));
                }else if(timerCounting == 3)
                {
                    textWithProgress.setText("50%");
                    progressBar.setProgress(50);
                    textWithExercise.setText("Adv Tuck Front Lever \n 5 seconds");
                    imageExercise.setImageDrawable(getResources().getDrawable(R.drawable.advtuckfront));
                }else if(timerCounting == 4)
                {
                    textWithExercise.setText("Toes to bar \n 10 times");
                    textWithProgress.setText("75%");
                    progressBar.setProgress(75);
                    imageExercise.setImageDrawable(getResources().getDrawable(R.drawable.toestobar));
                    btnFinishTraining.setVisibility(View.VISIBLE);
                    imageFinish.setVisibility(GONE);
                }
                break;
            case 4:
                if(timerCounting == 1 || timerCounting == 4)
                {
                    textWithExercise.setVisibility(View.VISIBLE); textWithExercise.setText("Front Lever \n 3 seconds");
                    imageExercise.setVisibility(View.VISIBLE);
                    imageExercise.setImageDrawable(getResources().getDrawable(R.drawable.frontlevericon));
                    if(timerCounting == 4)
                    {
                        progressBar.setProgress(75);
                        textWithProgress.setText("75%"); textWithExercise.setText(" Front Lever \n 2 seconds");
                        btnFinishTraining.setVisibility(View.VISIBLE);
                        imageFinish.setVisibility(GONE);
                    }
                }else if(timerCounting == 2)
                {
                    textWithExercise.setText("Pull Up \n 15 times");
                    textWithProgress.setText("25%");
                    progressBar.setProgress(25);
                    imageExercise.setImageDrawable(getResources().getDrawable(R.drawable.pullup));
                }
                else if(timerCounting == 3)
                {
                    textWithProgress.setText("50%");
                    progressBar.setProgress(50);
                    textWithExercise.setText("Advanced Tuck \nFront Lever\n 12 - 15 seconds");
                    imageExercise.setImageDrawable(getResources().getDrawable(R.drawable.advtuckfront));
                }
                break;
        }
    }

    public void getCurrentTime()
    {

        Date date = new Date(String.valueOf(Calendar.getInstance().getTime()));
        DateFormat dateFormat = android.text.format.DateFormat.getLongDateFormat(getContext());
        dateNow = dateFormat.format(date);


        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
        timeNow = mdformat.format(calendar.getTime());


        switch (whatLvlWeDoNow)
        {
            case 1:
                treningText = timeNow + "     " + dateNow + " \n\n SUMA ĆWICZEŃ:  \n - Tuck Front Lever: 7 seconds \n - Pull Up: 5 times \n - L-sit on the bar: 6 seconds\n";

                break;
            case 2:
                treningText = timeNow + "     " + dateNow + " \n\n SUMA ĆWICZEŃ:  \n - Adv Tuck Front Lever: 5 - 7 seconds \n - Toes to bar: 6 times \n - L-sit on the bar: 10 seconds\n";
                break;
            case 3:
                treningText = timeNow + "     " + dateNow + " \n\n SUMA ĆWICZEŃ:  \n - Adv Tuck Front Lever: 5 seconds \n - One leg Front Lever: 3 seconds\n - Pull Up: 10 times \n - Toes to bar: 10 times\n";
                break;
            case 4:
                treningText = timeNow + "     " + dateNow + " \n\n SUMA ĆWICZEŃ:  \n - Front Lever: 5 seconds \n - Advanced Tuck Front Lever: 12 - 15 seconds\n - Pull Up: 15 times \n";
                break;
        }
    }
}