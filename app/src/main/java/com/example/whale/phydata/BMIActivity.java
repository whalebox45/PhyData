package com.example.whale.phydata;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.whale.phydata.R.id.btnSendMail2;

public class BMIActivity extends AppCompatActivity {
    EditText etHeight2,etWeight2;
    Button btnCalcBMI,btnReturn2,btnSendMail2;
public class BMIActivity extends AppCompatActivity {
    EditText etHeight2,etWeight2;
    Button btnCalcBMI,btnReturn2;
    TextView tvBMIResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        etHeight2 = (EditText) findViewById(R.id.etHeight2);
        etWeight2 = (EditText) findViewById(R.id.etWeight2);

        btnCalcBMI = (Button) findViewById(R.id.btnCalcBMI);
        btnSendMail2 = (Button) findViewById(R.id.btnSendMail2);
        btnReturn2 = (Button) findViewById(R.id.btnReturn2);
        tvBMIResult = (TextView) findViewById(R.id.tvBMIResult);

        btnCalcBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    float height = Float.parseFloat(etHeight2.getText().toString())/100f;
                    float weight = Float.parseFloat(etWeight2.getText().toString());

                    float bmi = weight / height / height;
                    String bmiRange;

                    if(bmi<16f){
                        bmiRange = "Severely Underweight(~16)";
                        tvBMIResult.setTextColor(Color.MAGENTA);
                    } else if(bmi>=16f && bmi<18.5f){
                        bmiRange = "Underweight(16~18.5)";
                        tvBMIResult.setTextColor(Color.BLUE);
                    } else if(bmi>=18.5f && bmi<25f){
                        bmiRange = "Normal(18.5~25)";
                        tvBMIResult.setTextColor(Color.GREEN);
                    } else if(bmi>=25f && bmi<30f){
                        bmiRange = "Overweight(25~30)";
                        tvBMIResult.setTextColor(Color.YELLOW);
                    } else if(bmi>=30f && bmi<35f){
                        bmiRange = "Moderately Obese(30~35)";
                        tvBMIResult.setTextColor(Color.rgb(255, 127, 0));
                    } else{
                        bmiRange = "Severely Obese(35~)";
                        tvBMIResult.setTextColor(Color.RED);
                    }

                    tvBMIResult.setText("Your BMI is: "+String.format("%.1f",bmi)+"\n"+
                    "Your BMI is in "+bmiRange);
                }
                catch (Exception e){
                    Toast.makeText(BMIActivity.this,"Please check your input again.",Toast.LENGTH_SHORT).show();
                    Log.e(e.toString(),e.toString());
                }
            }
        });

        btnSendMail2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"recipient@example.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
                i.putExtra(Intent.EXTRA_TEXT   , "body of email");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(BMIActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnReturn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
