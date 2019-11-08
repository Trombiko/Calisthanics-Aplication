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

        //lvlFrontAchieved();
        //lvlMuscleUpAchieved();
        //lvlPlancheAchieved();
        //lvlBackLeverAchieved();
        //lvlHSPushUpsAchieved();
    }


    public void lvlFrontAchieved()
    {
        SharedPreferences prefFront = getActivity().getSharedPreferences("NAME_FRONT" , 0);
        SharedPreferences.Editor edtFront = prefFront.edit();

    }


    public void lvlMuscleUpAchieved()
    {
        SharedPreferences prefMuscleUp = getActivity().getSharedPreferences("NAME_MUSCLE_UP" , 0);
        SharedPreferences.Editor edtMuscleUp = prefMuscleUp.edit();

        if(maxMuscleUp > 3)
        {
            edtMuscleUp.putInt("keyMU", 4);//trening ostatni
        }else if(maxMuscleUp > 0 || maxPull > 10)
        {
            edtMuscleUp.putInt("keyMU", 3);// trening przedostatni
        }else if(maxMuscleUp == 0 && maxPull > 5)
        {
            edtMuscleUp.putInt("keyMU", 2);// trening drugi
        }else
        {
            edtMuscleUp.putInt("keyMU", 1);//trening pierwszy
        }
        edtMuscleUp.apply();
    }
}
