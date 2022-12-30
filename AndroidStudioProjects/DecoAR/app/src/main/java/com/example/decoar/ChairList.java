package com.example.decoar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class ChairList extends AppCompatActivity {

    private ImageView arIV, infoIV ,cartIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chair_list);

        cartIV = findViewById(R.id.IVcart);
        arIV = findViewById(R.id.IVar);
        infoIV = findViewById(R.id.IVinfo);

        cartIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(this,"Added to cart!", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),"Added to cart!", Toast.LENGTH_SHORT).show();
            }
        });

        arIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChairList.this, ArView.class));

            }
        });




    }
}