package com.example.decoar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class FragmentBrowse extends Fragment implements View.OnClickListener{

    private CardView chairCV, bedroomCV,storageCV, tableCV,entertainmentCV,otherCV;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_browse,container,false);

        chairCV = view.findViewById(R.id.CVchair);

        chairCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // startActivity(new Intent(FragmentBrowse.this, FurnitureList.class));
                //chairCV.getContext().startActivity(new Intent(chairCV.getContext(), FragmentBrowse.class));
                Intent intent = new Intent(getActivity(),ChairList.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onClick(View view) {
        //getActivity().findViewById((R.id.CVchair)).setOnClickListener(this);

    }
}
