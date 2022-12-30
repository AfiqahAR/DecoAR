package com.google.ar.sceneform.samples.imagetexture;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class FragmentBrowse extends Fragment implements View.OnClickListener{

    private CardView chairCV, bedroomCV,storageCV, tableCV,entertainmentCV,otherCV;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_browse,container,false);

        chairCV = view.findViewById(R.id.CVchair);
        bedroomCV = view.findViewById(R.id.CVbedroom);
        tableCV = view.findViewById(R.id.CVtable);
        storageCV = view.findViewById(R.id.CVstorage);
        entertainmentCV = view.findViewById(R.id.CVentertainment);
        otherCV = view.findViewById(R.id.CVother);

        //Toast.makeText(getActivity(),"display", Toast.LENGTH_SHORT).show();}

        chairCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChairExpanded.class);
                startActivity(intent);
            }
        });
        bedroomCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BedExpanded.class);
                startActivity(intent);
            }
        });
        tableCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TableExpanded.class);
                startActivity(intent);
            }
        });
        storageCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), StorageExpanded.class);
                startActivity(intent);
            }
        });
        entertainmentCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EntertainmentExpanded.class);
                startActivity(intent);
            }
        });
        otherCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"OTHERS", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getActivity(), ChairExpanded.class);
//                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onClick(View view) {
        //getActivity().findViewById((R.id.CVchair)).setOnClickListener(this);

    }
}
