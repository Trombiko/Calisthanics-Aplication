package com.example.mich.calisthenicsaplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainPanelFragment extends Fragment implements View.OnClickListener
{
    ImageView btnInfo1, btnInfo2, btnInfo3, btnInfo4, btnInfo5;
    ImageView btnTraining1, btnTraining2, btnTraining3, btnTraining4, btnTraining5;
    ProgressBar progressBar, progressBar2, progressBarPlanche, progressBarBack, progressBarHS;
    TextView percentProgress, textWithProgress2, textWithProgressPlanche, textWithProgressBack, textWithProgressHS;
    TextView textFrontLvl, textMuscleUpLvl, textPlancheLevel, textBackLevel, textHSLevel;

    int sendTrainingNumber = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.main_fragment, container, false);

        btnInfo1 = rootView.findViewById(R.id.btnInfo1); btnInfo1.setOnClickListener(this);
        btnInfo2 = rootView.findViewById(R.id.btnInfo2); btnInfo2.setOnClickListener(this);
        btnInfo3 = rootView.findViewById(R.id.btnInfo3); btnInfo3.setOnClickListener(this);
        btnInfo4 = rootView.findViewById(R.id.btnInfo4); btnInfo4.setOnClickListener(this);
        btnInfo5 = rootView.findViewById(R.id.btnInfo5); btnInfo5.setOnClickListener(this);

        btnTraining1 = rootView.findViewById(R.id.btnTraining1); btnTraining1.setOnClickListener(this);
        btnTraining2 = rootView.findViewById(R.id.btnTraining2); btnTraining2.setOnClickListener(this);
        btnTraining3 = rootView.findViewById(R.id.btnTraining3); btnTraining3.setOnClickListener(this);
        btnTraining4 = rootView.findViewById(R.id.btnTraining4); btnTraining4.setOnClickListener(this);
        btnTraining5 = rootView.findViewById(R.id.btnTraining5); btnTraining5.setOnClickListener(this);

        percentProgress = rootView.findViewById(R.id.percentProgress);
        textWithProgress2 = rootView.findViewById(R.id.textWithProgress2);
        textWithProgressPlanche = rootView.findViewById(R.id.textWithProgressPlanche);
        textWithProgressBack = rootView.findViewById(R.id.textWithProgressBack);
        textWithProgressHS = rootView.findViewById(R.id.textWithProgressHS);

        textFrontLvl = rootView.findViewById(R.id.textFrontLvl);
        textMuscleUpLvl = rootView.findViewById(R.id.textMuscleUpLvl);
        textPlancheLevel = rootView.findViewById(R.id.textPlancheLevel);
        textBackLevel = rootView.findViewById(R.id.textBackLevel);
        textHSLevel = rootView.findViewById(R.id.textHSLevel);

        progressBar = rootView.findViewById(R.id.progressBar);
        progressBar2 = rootView.findViewById(R.id.progressBar2);
        progressBarPlanche = rootView.findViewById(R.id.progressBarPlanche);
        progressBarBack = rootView.findViewById(R.id.progressBarBack);
        progressBarHS = rootView.findViewById(R.id.progressBarHS);

        return rootView;
    }


    @Override
    public void onStart()
    {
        super.onStart();

        getActivity().setTitle(R.string.menu);
        setProgressFront();
        setProgressMuscleUp();
        setProgressPlanche();
        setProgressBack();
        setProgressHS();
    }

    @Override
    public void onClick(View view)
    {
        final LevelPanelFragment levelPanelFragment = new LevelPanelFragment();
        Bundle args = new Bundle();


        switch (view.getId())
        {
            case R.id.btnTraining1:
                sendTrainingNumber = 1;
                args.putInt("exercise choosen", sendTrainingNumber);
                levelPanelFragment.setArguments(args);

                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, levelPanelFragment)
                        .addToBackStack(null)
                        .commit();
                break;

            case R.id.btnTraining2:
                sendTrainingNumber = 2;
                args.putInt("exercise choosen", sendTrainingNumber);
                levelPanelFragment.setArguments(args);

                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, levelPanelFragment)
                        .addToBackStack(null)
                        .commit();
                break;

            case R.id.btnTraining3:
                sendTrainingNumber = 3;
                args.putInt("exercise choosen", sendTrainingNumber);
                levelPanelFragment.setArguments(args);

                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, levelPanelFragment)
                        .addToBackStack(null)
                        .commit();
                break;

            case R.id.btnTraining4:
                sendTrainingNumber = 4;
                args.putInt("exercise choosen", sendTrainingNumber);
                levelPanelFragment.setArguments(args);

                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, levelPanelFragment)
                        .addToBackStack(null)
                        .commit();
                break;

            case R.id.btnTraining5:
                sendTrainingNumber = 5;
                args.putInt("exercise choosen", sendTrainingNumber);
                levelPanelFragment.setArguments(args);

                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, levelPanelFragment)
                        .addToBackStack(null)
                        .commit();
                break;

            case R.id.btnInfo1:
                Intent intent1 = new Intent(getActivity().getApplication(),InfoPanelActivity.class);
                int x1 = 1; // wlaczy panel informujacy z cwiczeniem numer 1
                intent1.putExtra("info_number", x1);
                getActivity().finish();
                startActivity(intent1);
                break;

            case R.id.btnInfo2:
                Intent intent2 = new Intent(getActivity().getApplication(),InfoPanelActivity.class);
                int x2 = 2; // wlaczy panel informujacy z cwiczeniem numer 1
                intent2.putExtra("info_number", x2);
                getActivity().finish();
                startActivity(intent2);
                break;

            case R.id.btnInfo3:
                Intent intent3 = new Intent(getActivity().getApplication(),InfoPanelActivity.class);
                int x3 = 3; // wlaczy panel informujacy z cwiczeniem numer 1
                intent3.putExtra("info_number", x3);
                getActivity().finish();
                startActivity(intent3);
                break;

            case R.id.btnInfo4:
                Intent intent4 = new Intent(getActivity().getApplication(),InfoPanelActivity.class);
                int x4= 4; // wlaczy panel informujacy z cwiczeniem numer 1
                intent4.putExtra("info_number", x4);
                getActivity().finish();
                startActivity(intent4);
                break;

            case R.id.btnInfo5:
                Intent intent5 = new Intent(getActivity().getApplication(),InfoPanelActivity.class);
                int x5 = 5; // wlaczy panel informujacy z cwiczeniem numer 1
                intent5.putExtra("info_number", x5);
                getActivity().finish();
                startActivity(intent5);
                break;
        }
    }


    public void setProgressFront()
    {
        SharedPreferences prefFront = getActivity().getSharedPreferences("NAME_FRONT", 0);
        int idFront = prefFront.getInt("keyFront",0);
        if (idFront != 0)
        {
            //relativeLayout.setVisibility(View.GONE);
        }

        switch (idFront)
        {
            case 1:
                progressBar.setProgress(0);
                percentProgress.setText("0%");
                textFrontLvl.setText("Lvl 1: Tuck Front Lever ");
                break;
            case 2:
                progressBar.setProgress(25);
                percentProgress.setText("25%");
                textFrontLvl.setText("Lvl 2: Advanced Tuck Front Lever ");
                break;
            case 3:
                progressBar.setProgress(50);
                percentProgress.setText("50%");
                textFrontLvl.setText("Lvl 3: One Leg Front Lever ");
                break;
            case 4:
                progressBar.setProgress(75);
                percentProgress.setText("75%");
                textFrontLvl.setText("Lvl 4: Front Lever ");
                break;
            case 5:
                progressBar.setProgress(100);
                percentProgress.setText("100%");
                textFrontLvl.setText(R.string.element_nauczony);
                break;
        }
    }

    public void setProgressMuscleUp()
    {
        SharedPreferences prefMuscleUp = getActivity().getSharedPreferences("NAME_MUSCLE_UP", 0);
        int idMuscleUp = prefMuscleUp.getInt("keyMU",0);
        switch (idMuscleUp)
        {
            case 1:
                progressBar2.setProgress(0);
                textWithProgress2.setText("0%");
                textMuscleUpLvl.setText(R.string.pull_up);
                break;
            case 2:
                progressBar2.setProgress(25);
                textWithProgress2.setText("25%");
                textMuscleUpLvl.setText(R.string.high_pull_up);
                break;
            case 3:
                progressBar2.setProgress(50);
                textWithProgress2.setText("50%");
                textMuscleUpLvl.setText("Lvl 3: Kipping Muscle Up ");
                break;
            case 4:
                progressBar2.setProgress(75);
                textWithProgress2.setText("75%");
                textMuscleUpLvl.setText("Lvl 4: Muscle Up ");
                break;
            case 5:
                progressBar2.setProgress(100);
                textWithProgress2.setText("100%");
                textMuscleUpLvl.setText(R.string.element_nauczony);
                break;
        }
    }
    public void setProgressPlanche()
    {
        SharedPreferences prefMuscleUp = getActivity().getSharedPreferences("NAME_PLANCHE", 0);
        int idPlanche = prefMuscleUp.getInt("keyPlanche",0);
        switch (idPlanche)
        {
            case 1:
                progressBarPlanche.setProgress(0);
                textWithProgressPlanche.setText("0%");
                textPlancheLevel.setText("Lvl 1: Tuck Planche ");
                break;
            case 2:
                progressBarPlanche.setProgress(25);
                textWithProgressPlanche.setText("25%");
                textPlancheLevel.setText(R.string.adv_planche);
                break;
            case 3:
                progressBarPlanche.setProgress(50);
                textWithProgressPlanche.setText("50%");
                textPlancheLevel.setText("Lvl 3: Straddle Planche ");
                break;
            case 4:
                progressBarPlanche.setProgress(75);
                textWithProgressPlanche.setText("75%");
                textPlancheLevel.setText("Lvl 4: Planche");
                break;
            case 5:
                progressBarPlanche.setProgress(100);
                textWithProgressPlanche.setText("100%");
                textPlancheLevel.setText(R.string.element_nauczony);
                break;
        }
    }

    public void setProgressBack()
    {
        SharedPreferences prefMuscleUp = getActivity().getSharedPreferences("NAME_BACK", 0);
        int idBack = prefMuscleUp.getInt("keyBack",0);
        switch (idBack)
        {
            case 1:
                progressBarBack.setProgress(0);
                textWithProgressBack.setText("0%");
                textBackLevel.setText("Lvl 1: Skin the Cat ");
                break;
            case 2:
                progressBarBack.setProgress(25);
                textWithProgressBack.setText("25%");
                textBackLevel.setText("Lvl 2: Tuck Back Lever ");
                break;
            case 3:
                progressBarBack.setProgress(50);
                textWithProgressBack.setText("50%");
                textBackLevel.setText(R.string.adv_back);
                break;
            case 4:
                progressBarBack.setProgress(75);
                textWithProgressBack.setText("75%");
                textBackLevel.setText("Lvl 4: Back lever ");
                break;
            case 5:
                progressBarBack.setProgress(100);
                textWithProgressBack.setText("100%");
                textBackLevel.setText(R.string.element_nauczony);
                break;
        }
    }

    public void setProgressHS()
    {
        SharedPreferences prefMuscleUp = getActivity().getSharedPreferences("NAME_HS", 0);
        int idHS = prefMuscleUp.getInt("keyHS",0);
        switch (idHS)
        {
            case 1:
                progressBarHS.setProgress(0);
                textWithProgressHS.setText("0%");
                textHSLevel.setText(R.string.pike_pu);
                break;
            case 2:
                progressBarHS.setProgress(25);
                textWithProgressHS.setText("25%");
                textHSLevel.setText(R.string.adv_pike);
                break;
            case 3:
                progressBarHS.setProgress(50);
                textWithProgressHS.setText("50%");
                textHSLevel.setText(R.string.wall_pu);
                break;
            case 4:
                progressBarHS.setProgress(75);
                textWithProgressHS.setText("75%");
                textHSLevel.setText(R.string.hs_pu);
                break;
            case 5:
                progressBarHS.setProgress(100);
                textWithProgressHS.setText("100%");
                textHSLevel.setText(R.string.element_nauczony);
                break;
        }
    }
}
