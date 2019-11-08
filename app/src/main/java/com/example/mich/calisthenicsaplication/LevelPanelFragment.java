package com.example.mich.calisthenicsaplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LevelPanelFragment extends Fragment implements View.OnClickListener
{
    Button btn1, btn2, btn3, btn4, btnWithTest;
    TextView textWithProgress,percentProgress, textLvl, textExercise, textExerciseLvl1, textExerciseLvl2, textExerciseLvl3, textExerciseLvl4;
    ImageView imageExercise, exercises1, exercises2, exercises3, exercises4;
    RelativeLayout relativeLayout;
    ProgressBar progressBar;

    int whatExerciseWeDo = 0; // zależne co dostaniemy z MainPanelFragment

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.levels_fragment, container, false);

        btn1 = rootView.findViewById(R.id.btnTraining1); btn1.setOnClickListener(null);
        btn2 = rootView.findViewById(R.id.btnTraining2); btn2.setOnClickListener(null);
        btn3 = rootView.findViewById(R.id.btnTraining3); btn3.setOnClickListener(null);
        btn4 = rootView.findViewById(R.id.btnTraining4); btn4.setOnClickListener(null);
        btnWithTest = rootView.findViewById(R.id.btnTestTraining); btnWithTest.setOnClickListener(this);

        progressBar = rootView.findViewById(R.id.progressBar);
        textWithProgress = rootView.findViewById(R.id.textWithProgress);
        percentProgress = rootView.findViewById(R.id.percentProgress);
        textExercise = rootView.findViewById(R.id.textExercise);
        textLvl = rootView.findViewById(R.id.textExerciseLevel);
        imageExercise = rootView.findViewById(R.id.imageExercise);

        exercises1 = rootView.findViewById(R.id.images1);
        exercises2 = rootView.findViewById(R.id.images2);
        exercises3 = rootView.findViewById(R.id.images3);
        exercises4 = rootView.findViewById(R.id.images4);

        textExerciseLvl1 = rootView.findViewById(R.id.textTraining);
        textExerciseLvl2 = rootView.findViewById(R.id.textTraining2);
        textExerciseLvl3 = rootView.findViewById(R.id.textTraining3);
        textExerciseLvl4 = rootView.findViewById(R.id.textTraining4);

        relativeLayout = rootView.findViewById(R.id.relativeLay);

        getExercise();

        return rootView;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        getActivity().setTitle("Poziomy trudności");

        switch (whatExerciseWeDo)
        {
            case 1:
                frontLevelsPage();
                break;
            case 2:
                muscleUpLevelsPage();
                break;
            default:
                break;
        }
    }

    public void getExercise()
    {
        Bundle args = getArguments();
        if (args != null)
            whatExerciseWeDo = args.getInt("exercise choosen");
    }

    public void onChangeExercise()
    {
        switch (whatExerciseWeDo)
        {
            case 2:
                textExerciseLvl1.setText("Lvl 1: Pull Up");
                textExerciseLvl2.setText("Lvl 2: High Pull Up");
                textExerciseLvl3.setText("Lvl 3: Kipping Muscle Up");
                textExerciseLvl4.setText("Lvl 4: Muscle Up");

        }
    }




    @Override
    public void onClick(View view)
    {



        switch (view.getId())
        {
            case R.id.btnTestTraining:

                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new FormLevelFragment())
                        .addToBackStack(null)
                        .commit();
                break;

        }
    }

    public void frontLevelsPage()
    {
        SharedPreferences prefFront = getActivity().getSharedPreferences("NAME_FRONT", 0);
        int idFront = prefFront.getInt("keyFront",0);

        if (idFront != 0)
        {
            relativeLayout.setVisibility(View.GONE); // TODO: DO ODBLOKOWANIA NA SAMYM KONCU
        }

        switch (idFront)
        {
            case 1:
                onSwitchCase1Common();
                textLvl.setText("Lvl 1: Tuck Front Lever");
                break;
            case 2:
                onSwitchCase2Common();
                textLvl.setText("Lvl 2: Advanced Tuck Front Lever");
                break;
            case 3:
                onSwitchCase3Common();
                textLvl.setText("Lvl 3: One Leg Front Lever");
                break;
            case 4:
                onSwitchCase4Common();
                textLvl.setText("Lvl 4: Front Lever");
                break;
            case 5:
                onSwitchCase4Common();
                onSwitchCase5Common();
                textLvl.setText("Element nauczony");
        }
    }

    public void muscleUpLevelsPage()
    {
        imageExercise.setImageDrawable(getResources().getDrawable(R.drawable.muscleuppictogram));
        textExercise.setText("Muscle Up");
        textLvl.setText("Lvl 1: Pull Up");
        onChangeExercise(); // zmieniamy tekst z fronta na muscle upa

        SharedPreferences prefMuscleUp = getActivity().getSharedPreferences("NAME_MUSCLE_UP", 0);
        int idMuscleUp = prefMuscleUp.getInt("keyMU",0);

        switch (idMuscleUp)
        {
            case 1:
                onSwitchCase1Common();
                textLvl.setText("Lvl 1: Pull Up");
                break;
            case 2:
                onSwitchCase2Common();
                textLvl.setText("Lvl 2: High Pull Up");
                break;
            case 3:
                onSwitchCase3Common();
                textLvl.setText("Lvl 3: Kipping Muscle Up");
                break;
            case 4:
                onSwitchCase4Common();
                textLvl.setText("Lvl 4: Muscle Up");
                break;
        }
    }


    public void onSwitchCase1Common()
    {
        btn1.setBackgroundResource(android.R.drawable.btn_default);
        btn1.setOnClickListener(this);
        progressBar.setProgress(0);
        textWithProgress.setText("0%");
    }

    public  void onSwitchCase2Common()
    {
        btn1.setBackgroundColor(getResources().getColor(R.color.backgroundAfterTraining));
        btn1.setOnClickListener(this);
        btn2.setBackgroundResource(android.R.drawable.btn_default);
        btn2.setOnClickListener(this);
        btn3.setBackgroundColor(getResources().getColor(R.color.backgroundNotAllowed));
        progressBar.setProgress(25);
        textWithProgress.setText("25%");
    }

    public void onSwitchCase3Common()
    {
        btn1.setBackgroundColor(getResources().getColor(R.color.backgroundAfterTraining));
        btn2.setBackgroundColor(getResources().getColor(R.color.backgroundAfterTraining));
        btn3.setBackgroundResource(android.R.drawable.btn_default);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        progressBar.setProgress(50);
        textWithProgress.setText("50%");
    }

    public void onSwitchCase4Common()
    {
        btn1.setBackgroundColor(getResources().getColor(R.color.backgroundAfterTraining));
        btn2.setBackgroundColor(getResources().getColor(R.color.backgroundAfterTraining));
        btn3.setBackgroundColor(getResources().getColor(R.color.backgroundAfterTraining));
        btn4.setBackgroundResource(android.R.drawable.btn_default);
        progressBar.setProgress(75);
        textWithProgress.setText("75%");
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }

    public void onSwitchCase5Common()
    {
        btn4.setBackgroundColor(getResources().getColor(R.color.backgroundAfterTraining));
        progressBar.setProgress(100);
        textWithProgress.setText("100%");
    }
}
