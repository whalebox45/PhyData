package com.example.whale.phydata;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MAPActivity extends AppCompatActivity {
    boolean isCalc = false,isOk = true;
    EditText etSBP,etDBP;
    Button btnCalcMAP,btnReturn3,btnSendMail3;
    TextView tvMAPResult;
    float mapvar = 0f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        etSBP = (EditText) findViewById(R.id.etSBP);
        etDBP = (EditText) findViewById(R.id.etDBP);

        btnCalcMAP = (Button) findViewById(R.id.btnCalcMAP);
        tvMAPResult = (TextView) findViewById(R.id.tvMAPResult);
        btnSendMail3 = (Button) findViewById(R.id.btnSendMail3);
        btnReturn3 = (Button) findViewById(R.id.btnReturn3);
        btnCalcMAP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    float sbp = Float.parseFloat(etSBP.getText().toString());
                    float dbp = Float.parseFloat(etDBP.getText().toString());

                    if (dbp>sbp) throw new Exception();

                    float map = dbp + (sbp - dbp)/3;

                    if (map<80f || map>100f){
                        tvMAPResult.setTextColor(Color.RED);
                        isOk = false;
                    } else {
                        isOk = true;
                        tvMAPResult.setTextColor(Color.BLUE);
                    }

                    tvMAPResult.setText("Your MAP is "+ String.format("%.1f",map)+"mmHg\n"+
                    "Normal Range of MAP\n"+"80 mmHg ~ 100mmHg");
                    MAPActivity.this.mapvar = map;
                    isCalc = true;
                }
                catch (Exception e){
                    isCalc = false;
                    Toast.makeText(MAPActivity.this,"Please check your input again.",Toast.LENGTH_SHORT).show();
                    Log.e(e.toString(),e.toString());
                }
            }
        });
        btnSendMail3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                try {
                    if(!isCalc) throw new Exception("Not calculated yet");
                    if(isOk) throw new Exception("Normal MAP");
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"john.smith@abc.com.tw"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Reminding Letter");
                i.putExtra(Intent.EXTRA_TEXT   , "It is a goodwill letter to remind you that your Mean Arterial\n" +
                        "Pressure (" + String.format("%.1f",mapvar) + " mmHg) is out of normal range (80mmHg < MAP <100mmHg)");

                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MAPActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Toast.makeText(MAPActivity.this,e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnReturn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
