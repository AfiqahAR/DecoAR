package com.google.ar.sceneform.samples.imagetexture;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentHistory extends Fragment {


    private Button BuyMoreBTN;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history,container,false);

        BuyMoreBTN = view.findViewById(R.id.button);

        BuyMoreBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // startActivity(new Intent(FragmentBrowse.this, FurnitureList.class));
                //chairCV.getContext().startActivity(new Intent(chairCV.getContext(), FragmentBrowse.class));
                Intent intent = new Intent(getActivity(), ChairExpanded.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
