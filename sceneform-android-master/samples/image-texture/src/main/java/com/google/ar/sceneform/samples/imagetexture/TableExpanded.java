package com.google.ar.sceneform.samples.imagetexture;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class TableExpanded extends AppCompatActivity {

    private ImageView arIV, infoIV ,cartIV;
    public ImageView pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_furniture_expanded);

        cartIV = findViewById(R.id.IVcart);
        arIV = findViewById(R.id.IVar);
        infoIV = findViewById(R.id.IVinfo);
        pic = findViewById(R.id.furniturePicIV);
        pic.setImageResource(R.drawable.table);

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
                String furniture = "table";
                startActivity(new Intent(TableExpanded.this, TableMainActivity.class).putExtra("table", furniture));
            }
        });
        infoIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFurnitureDetails();
            }
        });
    }

    private void showFurnitureDetails() {
        AlertDialog.Builder builder =new AlertDialog.Builder(TableExpanded.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(TableExpanded.this).inflate(
                R.layout.dialog_fur_details,(ConstraintLayout)findViewById(R.id.dialog_details)
        );
        builder.setView(view);
        ((TextView) view.findViewById(R.id.TVtitle_fur_details)).setText("TABLE DETAILS");
        ((TextView) view.findViewById(R.id.TVfur_details)).setText(
                "\nTABLE\n" +
                        "\nPRICE\n" +
                        "RM29.99\n" +
                        "\n" +
                        "\nName :  Round Table\n" +
                        "\nMaterial : Solid Wood\n" +
                        "\nBrand : MIVA\n" +
                        "\nVariant : Brown\n" +
                        "\nSize : 50cmx50cmx60cm\n" +
                        "\nWeight : 750g\n");
        ((Button) view.findViewById(R.id.BTNok)).setText("OK");

        final  AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.BTNok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        if (alertDialog.getWindow()!=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }
}