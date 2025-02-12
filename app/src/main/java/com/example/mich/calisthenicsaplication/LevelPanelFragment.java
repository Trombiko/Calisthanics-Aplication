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

import java.util.Locale;

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
        textLvl = rootView.findViewById(R.id.textFrontLvl);
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

        if (Locale.getDefault().getLanguage().equals(new Locale("en").getLanguage())) {
            getActivity().setTitle("Levels");
        }else{
            getActivity().setTitle("Poziomy trudności");
        }


        switch (whatExerciseWeDo)
        {
            case 1:
                frontLevelsPage();
                break;
            case 2:
                muscleUpLevelsPage();
                break;
            case 3:
                plancheLevelsPage();
                break;
            case 4:
                backLevelsPage();
                break;
            case 5:
                handStandLevelsPage();
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
                textExerciseLvl1.setText(R.string.pull_up);
                textExerciseLvl2.setText(R.string.high_pull_up);
                textExerciseLvl3.setText("Lvl 3: Kipping Muscle Up");
                textExerciseLvl4.setText("Lvl 4: Muscle Up");

                exercises1.setImageDrawable(getResources().getDrawable(R.drawable.muscleuplvl1));
                exercises2.setImageDrawable(getResources().getDrawable(R.drawable.muscleuplvl2));
                exercises3.setImageDrawable(getResources().getDrawable(R.drawable.muscleuplvl3));
                exercises4.setImageDrawable(getResources().getDrawable(R.drawable.muscleuplvl4));
                break;
            case 3:
                textExerciseLvl1.setText("Lvl 1: Tuck Planche");
                textExerciseLvl2.setText(R.string.adv_planche);
                textExerciseLvl3.setText("Lvl 3: Straddle Planche");
                textExerciseLvl4.setText("Lvl 4: Planche");

                exercises1.setImageDrawable(getResources().getDrawable(R.drawable.planchelvl1));
                exercises2.setImageDrawable(getResources().getDrawable(R.drawable.planchelvl2));
                exercises3.setImageDrawable(getResources().getDrawable(R.drawable.planchelvl3));
                exercises4.setImageDrawable(getResources().getDrawable(R.drawable.planchelvl4));
                break;
            case 4:
                textExerciseLvl1.setText("Lvl 1: Skin the Cat");
                textExerciseLvl2.setText("Lvl 2: Tuck Back Lever");
                textExerciseLvl3.setText(R.string.adv_back);
                textExerciseLvl4.setText("Lvl 4: Back Lever");

                exercises1.setImageDrawable(getResources().getDrawable(R.drawable.backlvl1));
                exercises2.setImageDrawable(getResources().getDrawable(R.drawable.backlvl2));
                exercises3.setImageDrawable(getResources().getDrawable(R.drawable.backlvl3));
                exercises4.setImageDrawable(getResources().getDrawable(R.drawable.backlvl4));
                break;
            case 5:
                textExerciseLvl1.setText(R.string.pike_pu);
                textExerciseLvl2.setText(R.string.adv_pike);
                textExerciseLvl3.setText(R.string.wall_pu);
                textExerciseLvl4.setText(R.string.hs_pu);

                exercises1.setImageDrawable(getResources().getDrawable(R.drawable.hslvl1));
                exercises2.setImageDrawable(getResources().getDrawable(R.drawable.hslvl2));
                exercises3.setImageDrawable(getResources().getDrawable(R.drawable.hslvl3));
                exercises4.setImageDrawable(getResources().getDrawable(R.drawable.hslvl4));
                break;
        }
    }




    @Override
    public void onClick(View view)
    {
        final TrainingFragment trainingFragment = new TrainingFragment();
        Bundle exercise = new Bundle();
        int a = 0;
        switch (view.getId())
        {
            case R.id.btnTestTraining:
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new FormLevelFragment())
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.btnTraining1:
                switch (whatExerciseWeDo)
                {
                    case 1:
                        a = 1; break;
                    case 2:
                        a = 5; break;
                    case 3:
                        a = 9; break;
                    case 4:
                        a = 13; break;
                }
                exercise.putInt("exercise", a);
                trainingFragment.setArguments(exercise);

                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, trainingFragment)
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.btnTraining2:
                switch (whatExerciseWeDo)
                {
                    case 1:
                        a = 2; break;
                    case 2:
                        a = 6; break;
                    case 3:
                        a = 10; break;
                    case 4:
                        a = 14; break;
                }
                exercise.putInt("exercise", a);
                trainingFragment.setArguments(exercise);

                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, trainingFragment)
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.btnTraining3:
                switch (whatExerciseWeDo)
                {
                    case 1:
                        a = 3; break;
                    case 2:
                        a = 7; break;
                    case 3:
                        a = 11; break;
                    case 4:
                        a = 15; break;
                }
                exercise.putInt("exercise", a);
                trainingFragment.setArguments(exercise);

                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, trainingFragment)
                        .addToBackStack(null)
                        .commit();
                break;

            case R.id.btnTraining4:
                switch (whatExerciseWeDo)
                {
                    case 1:
                        a = 4; break;
                    case 2:
                        a = 8; break;
                    case 3:
                        a = 12; break;
                    case 4:
                        a = 16; break;
                }
                exercise.putInt("exercise", a);
                trainingFragment.setArguments(exercise);

                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, trainingFragment)
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
            relativeLayout.setVisibility(View.GONE);
        }

        switch (idFront)
        {
            case 1:
                onSwitchCase1Common();
                textLvl.setText("Lvl 1: Tuck Front Lever");
                break;
            case 2:
                onSwitchCase2Common();
                textLvl.setText(R.string.adv_front);
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
                break;
        }
    }

    public void muscleUpLevelsPage()
    {
        imageExercise.setImageDrawable(getResources().getDrawable(R.drawable.muscleuppictogram));
        textExercise.setText("Muscle Up");
        textLvl.setText(R.string.pull_up);
        onChangeExercise(); // zmieniamy tekst z fronta na muscle upa

        SharedPreferences prefMuscleUp = getActivity().getSharedPreferences("NAME_MUSCLE_UP", 0);
        int idMuscleUp = prefMuscleUp.getInt("keyMU",0);

        if (idMuscleUp != 0)
        {
            relativeLayout.setVisibility(View.GONE);
        }

        switch (idMuscleUp)
        {
            case 1:
                onSwitchCase1Common();
                textLvl.setText(R.string.pull_up);
                break;
            case 2:
                onSwitchCase2Common();
                textLvl.setText(R.string.high_pull_up);
                break;
            case 3:
                onSwitchCase3Common();
                textLvl.setText("Lvl 3: Kipping Muscle Up");
                break;
            case 4:
                onSwitchCase4Common();
                textLvl.setText("Lvl 4: Muscle Up");
                break;
            case 5:
                onSwitchCase4Common();
                onSwitchCase5Common();
                break;
        }
    }

    public void plancheLevelsPage()
    {
        imageExercise.setImageDrawable(getResources().getDrawable(R.drawable.planche_icon));
        textExercise.setText("Planche");
        textLvl.setText("Lvl 1: Tuck Planche");
        onChangeExercise(); // zmieniamy tekst z fronta na planche

        SharedPreferences prefPlanche = getActivity().getSharedPreferences("NAME_PLANCHE", 0);
        int idPlanche = prefPlanche.getInt("keyPlanche",0);

        if (idPlanche != 0)
        {
            relativeLayout.setVisibility(View.GONE);
        }

        switch (idPlanche)
        {
            case 1:
                onSwitchCase1Common();
                textLvl.setText("Lvl 1: Tuck Planche");
                break;
            case 2:
                onSwitchCase2Common();
                textLvl.setText(R.string.adv_planche);
                break;
            case 3:
                onSwitchCase3Common();
                textLvl.setText("Lvl 3: Straddle Planche");
                break;
            case 4:
                onSwitchCase4Common();
                textLvl.setText("Lvl 4: Planche");
                break;
            case 5:
                onSwitchCase4Common();
                onSwitchCase5Common();
                break;
        }
    }

    public void backLevelsPage()
    {
        imageExercise.setImageDrawable(getResources().getDrawable(R.drawable.backlever));
        textExercise.setText("Back Lever");
        textLvl.setText("Lvl 1: Skin the Cat");
        onChangeExercise(); // zmieniamy tekst z fronta na backa

        SharedPreferences prefBack = getActivity().getSharedPreferences("NAME_BACK", 0);
        int idBackLever = prefBack.getInt("keyBack",0);

        if (idBackLever != 0)
        {
            relativeLayout.setVisibility(View.GONE);
        }

        switch (idBackLever)
        {
            case 1:
                onSwitchCase1Common();
                textLvl.setText("Lvl 1: Skin the Cat");
                break;
            case 2:
                onSwitchCase2Common();
                textLvl.setText("Lvl 2: Tuck Back Lever");
                break;
            case 3:
                onSwitchCase3Common();
                textLvl.setText(R.string.adv_back);
                break;
            case 4:
                onSwitchCase4Common();
                textLvl.setText("Lvl 4: Back Lever");
                break;
            case 5:
                onSwitchCase4Common();
                onSwitchCase5Common();
        }
    }

    public void handStandLevelsPage()
    {
        imageExercise.setImageDrawable(getResources().getDrawable(R.drawable.hs_pushup));
        textExercise.setText(R.string.hs);
        textLvl.setText(R.string.pike_pu);
        onChangeExercise(); // zmieniamy tekst z fronta na HAND STAND



        SharedPreferences prefHandStandPU = getActivity().getSharedPreferences("NAME_HS", 0);
        int idHandStand = prefHandStandPU.getInt("keyHS",0);

        if (idHandStand != 0)
        {
            relativeLayout.setVisibility(View.GONE);
        }

        switch (idHandStand)
        {
            case 1:
                onSwitchCase1Common();
                textLvl.setText(R.string.pike_pu);
                break;
            case 2:
                onSwitchCase2Common();
                textLvl.setText(R.string.adv_pike);
                break;
            case 3:
                onSwitchCase3Common();
                textLvl.setText(R.string.wall_pu);
                break;
            case 4:
                onSwitchCase4Common();
                textLvl.setText(R.string.hs_pu);
                break;
            case 5:
                onSwitchCase4Common();
                onSwitchCase5Common();
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
        textLvl.setText("Element Nauczony");
    }
}
