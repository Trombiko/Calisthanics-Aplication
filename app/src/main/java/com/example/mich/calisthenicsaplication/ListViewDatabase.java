package com.example.mich.calisthenicsaplication;

import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class ListViewDatabase extends Fragment
{
    DatabaseHelper mDatabaseHelper;

    int itemID = -1;
    String name, item, treningText = "TU DOCELOWO BĘDZIE WYKONANY TRENING + DATA WYKONYWANIA DANEGO POZIOMU";

    private ListView mlistView;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.listview_fragment, container, false);

        mlistView = rootView.findViewById(R.id.listView);
        mDatabaseHelper = new DatabaseHelper(getContext());

        int position1 = mlistView.getFirstVisiblePosition();

        populateListView();


        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
            getActivity().setTitle(R.string.historia_treningow);
    }


    private void populateListView()
    {

        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();


        while(data.moveToNext())
        {
            listData.add(data.getString(1)); // wyswietlanie koluny 1 w listview
            listData.add(data.getString(2)); // przypisujemy kolumne druga z bazy do wyswietlania w dialog window
            treningText = data.getString(2);

        }
        ListAdapter adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1, listData);

        mlistView.setAdapter(adapter);


        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                if(position %2 == 0) {
                    name = parent.getItemAtPosition(position).toString();
                    Cursor data = mDatabaseHelper.getItemID(name);

                    while (data.moveToNext()) {
                        itemID = data.getInt(0);
                    }

                    if (itemID > -1) {
                        DialogQuit();
                    }
                }
            }
        });
    }



    public void DialogQuit()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        builder.setTitle("Czy jesteś pewien?");
        builder.setMessage("Czynności tej nie da się cofnąć!");



        builder.setPositiveButton("   WYJDŹ   ", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("  USUŃ TRENING   ", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                mDatabaseHelper.deleteName(itemID, name);
                Toast.makeText(getActivity(), "Pomyślnie usunięto rekord", Toast.LENGTH_SHORT).show();
                dialog.dismiss();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    getFragmentManager().beginTransaction().detach(ListViewDatabase.this).commitNow();
                    getFragmentManager().beginTransaction().attach(ListViewDatabase.this).commitNow();
                } else {
                    getFragmentManager().beginTransaction().detach(ListViewDatabase.this).attach(ListViewDatabase.this).commit();
                }

            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}




