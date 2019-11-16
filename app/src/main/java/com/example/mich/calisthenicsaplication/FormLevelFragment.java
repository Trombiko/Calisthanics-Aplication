package com.example.mich.calisthenicsaplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FormLevelFragment extends Fragment implements View.OnClickListener
{
    EditText editPullUp, editPushUp, editDips, editBarDips, editMuscleUp;
    Button btnFinish;
    int maxPull = 0, maxPush = 0, maxDips = 0, maxBarDips = 0, maxMuscleUp = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.form_fragment, container, false);

        editPullUp = rootView.findViewById(R.id.editPullUp);
        editPushUp = rootView.findViewById(R.id.editPushUp);
        editDips = rootView.findViewById(R.id.editDips);
        editBarDips = rootView.findViewById(R.id.editBarDips);
        editMuscleUp = rootView.findViewById(R.id.editMuscleUp);

        btnFinish = rootView.findViewById(R.id.btnFinish); btnFinish.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onStart()
    {
        super.onStart();
    }



    @Override
    public void onClick(View view)
    {
        String pullup = editPullUp.getText().toString();
        String pushup = editPushUp.getText().toString();
        String dips = editDips.getText().toString();
        String bardips = editBarDips.getText().toString();
        String muscleup = editMuscleUp.getText().toString();

        if(pullup.matches("") || pushup.matches("") || dips.matches("") || bardips.matches("") || muscleup.matches(""))
        {
            Toast.makeText(getActivity(), "Uzupelnij wszystkie pola!", Toast.LENGTH_SHORT).show();
        }else {
            maxPull = Integer.parseInt(pullup);
            maxPush = Integer.parseInt(pushup);
            maxDips = Integer.parseInt(dips);
            maxBarDips = Integer.parseInt(bardips);
            maxMuscleUp = Integer.parseInt(muscleup);
            Toast.makeText(getActivity(), "Pomyślnie zapisano twoje dane", Toast.LENGTH_SHORT).show();
        }

        lvlFrontAchieved();
        lvlMuscleUpAchieved();
        lvlPlancheAchieved();
        lvlBackLeverAchieved();
        lvlHSPushUpsAchieved();
    }


    public void lvlFrontAchieved()
    {
        SharedPreferences prefFront = getActivity().getSharedPreferences("NAME_FRONT" , 0);
        SharedPreferences.Editor edtFront = prefFront.edit();

        if(maxPull > 10 && maxDips > 15 && maxBarDips > 10)
        {
            edtFront.putInt("keyFront", 3);
        }else if(maxPull > 8 && maxDips > 10)
        {
            edtFront.putInt("keyFront", 2);
        }else
        {
            edtFront.putInt("keyFront", 1);
        }
        edtFront.apply();
    }


    public void lvlMuscleUpAchieved()
    {
        SharedPreferences prefMuscleUp = getActivity().getSharedPreferences("NAME_MUSCLE_UP" , 0);
        SharedPreferences.Editor edtMuscleUp = prefMuscleUp.edit();

        if(maxMuscleUp > 0)
        {
            edtMuscleUp.putInt("keyMU", 4);//trening ostatni
        }else if(maxDips > 15 || maxPull > 11)
        {
            edtMuscleUp.putInt("keyMU", 3);// trening przedostatni
        }else if(maxDips > 10 && maxPull > 8)
        {
            edtMuscleUp.putInt("keyMU", 2);// trening drugi
        }else
        {
            edtMuscleUp.putInt("keyMU", 1);//trening pierwszy
        }
        edtMuscleUp.apply();
    }

    public void lvlPlancheAchieved()
    {
        SharedPreferences prefPlanche = getActivity().getSharedPreferences("NAME_PLANCHE", 0);
        SharedPreferences.Editor edtPlanche = prefPlanche.edit();

        if(maxDips > 10 && maxBarDips > 10)
        {
            edtPlanche.putInt("keyPlanche", 2);
        }else
        {
            edtPlanche.putInt("keyPlanche", 1);
        }
        edtPlanche.apply();
    }

    public void lvlBackLeverAchieved()
    {
        SharedPreferences prefBack = getActivity().getSharedPreferences("NAME_BACK", 0);
        SharedPreferences.Editor edtBack = prefBack.edit();

        if(maxPull > 10 && maxDips > 12 && maxBarDips > 10)
        {
            edtBack.putInt("keyBack", 3);
        }else if(maxPull > 5 && maxDips > 7 && maxBarDips > 7)
        {
            edtBack.putInt("keyBack", 2);
        }else
        {
            edtBack.putInt("keyBack", 1);
        }
        edtBack.apply();
    }

    public void lvlHSPushUpsAchieved()
    {
        SharedPreferences prefHandStand = getActivity().getSharedPreferences("NAME_HS", 0);
        SharedPreferences.Editor edtHandStand = prefHandStand.edit();

        if(maxPush > 20 && maxDips > 15 && maxBarDips > 15)
        {
            edtHandStand.putInt("keyHS", 3);
        }else if(maxPush > 15 && maxDips > 10 && maxBarDips > 10)
        {
            edtHandStand.putInt("keyHS", 2);
        }else
        {
            edtHandStand.putInt("keyHS", 1);
        }
        edtHandStand.apply();
    }
}
