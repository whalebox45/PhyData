package com.example.whale.phydata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnIBW,btnBMI,btnMAP,btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIBW = (Button) findViewById(R.id.btnIBW);
        btnBMI = (Button) findViewById(R.id.btnBMI);
        btnMAP = (Button) findViewById(R.id.btnMAP);
        btnExit = (Button) findViewById(R.id.btnExit);



        btnIBW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,IBWActivity.class);
                startActivity(intent);
            }
        });

        btnBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, BMIActivity.class);
                    startActivity(intent);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        btnMAP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,MAPActivity.class);
                startActivity(intent);
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
