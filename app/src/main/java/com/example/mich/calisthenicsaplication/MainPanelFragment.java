package com.example.mich.calisthenicsaplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainPanelFragment extends Fragment implements View.OnClickListener
{
    RelativeLayout relativeLay;
    ImageView lifeStyleLogo;
    ImageView btnInfo1, btnInfo2, btnInfo3, btnInfo4, btnInfo5;
    ImageView btnTraining1, btnTraining2, btnTraining3, btnTraining4, btnTraining5;

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

        
//        relativeLay = rootView.findViewById(R.id.relativeLay);
//        lifeStyleLogo = rootView.findViewById(R.id.lifeStyleLogo);
//        logoStart();


        return rootView;
    }

//    public void logoStart()
//    {
//        relativeLay.setVisibility(View.GONE);
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            public void run() {lifeStyleLogo.setVisibility(View.GONE); relativeLay.setVisibility(View.VISIBLE);}
//        }, 5000);   //5 seconds
//    }


    @Override
    public void onStart()
    {
        super.onStart();

        getActivity().setTitle("Menu Główne");
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
}
