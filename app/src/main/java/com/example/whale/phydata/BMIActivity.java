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
import static com.example.whale.phydata.R.id.default_activity_button;

public class BMIActivity extends AppCompatActivity {
    float bmi = 0f;

    EditText etHeight2,etWeight2;
    Button btnCalcBMI,btnReturn2,btnSendMail2;
    TextView tvBMIResult;

    boolean isCalc = false,isNormal = false;

    Range range;

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
                    isNormal = false;
                    float height = Float.parseFloat(etHeight2.getText().toString())/100f;
                    float weight = Float.parseFloat(etWeight2.getText().toString());

                    float bmi = weight / height / height;
                    String bmiRange;

                    if(bmi<16f){
                        bmiRange = "Severely Underweight(~16)";
                        tvBMIResult.setTextColor(Color.MAGENTA);
                        range = Range.SevUnd;
                    } else if(bmi>=16f && bmi<18.5f){
                        bmiRange = "Underweight(16~18.5)";
                        tvBMIResult.setTextColor(Color.BLUE);
                        range = Range.UndWgh;
                    } else if(bmi>=18.5f && bmi<25f){
                        bmiRange = "Normal(18.5~25)";
                        tvBMIResult.setTextColor(Color.GREEN);
                        range = Range.Normal;
                        isNormal = true;
                    } else if(bmi>=25f && bmi<30f){
                        bmiRange = "Overweight(25~30)";
                        tvBMIResult.setTextColor(Color.YELLOW);
                        range = Range.OvrWgh;
                    } else if(bmi>=30f && bmi<35f){
                        bmiRange = "Moderately Obese(30~35)";
                        tvBMIResult.setTextColor(Color.rgb(255, 127, 0));
                        range = Range.ModObs;
                    } else{
                        bmiRange = "Severely Obese(35~)";
                        tvBMIResult.setTextColor(Color.RED);
                        range = Range.SevObs;
                    }

                    tvBMIResult.setText("Your BMI is: "+String.format("%.1f",bmi)+"\n"+
                    "Your BMI is in "+bmiRange);
                    BMIActivity.this.bmi = bmi;
                    isCalc = true;
                }
                catch (Exception e){
                    isCalc = false;
                    Toast.makeText(BMIActivity.this,"Please check your input again.",Toast.LENGTH_SHORT).show();
                    Log.e(e.toString(),e.toString());
                }
            }
        });

        btnSendMail2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                try {

                    if(!isCalc) throw new Exception("Not calculated yet");
                    if(isNormal) throw new Exception("Normal BMI, no need to send mail");
                    String rangemessage = "undefined";
                    switch(BMIActivity.this.range){
                        case SevUnd:
                            rangemessage = "severely underweight";
                            break;
                        case UndWgh:
                            rangemessage = "underweight";
                            break;
                        case OvrWgh:
                            rangemessage = "overweight";
                            break;
                        case ModObs:
                            rangemessage = "moderately obese";
                            break;
                        case SevObs:
                            rangemessage = "severely obese";
                            break;
                        default:
                            break;
                    }

                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("message/rfc822");
                    i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"john.smith@abc.com.tw"});
                    i.putExtra(Intent.EXTRA_SUBJECT, "Reminding Letter");
                    i.putExtra(Intent.EXTRA_TEXT   , "It is a goodwill letter to remind you that your body mass index "+
                                    String.format("%.1f",BMIActivity.this.bmi)+" belongs to "+ rangemessage + "range as stated in below table\n"+
                    "Severely Underweight(~16)\nUnderweight(16~18.5)\nNormal(18.5~25)\nOverweight(25~30)\nModerately Obese(30~35)\nSeverely Obese(35~)");
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(BMIActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Toast.makeText(BMIActivity.this, e.getMessage(),Toast.LENGTH_SHORT).show();
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

enum Range{
    SevUnd,UndWgh,Normal,OvrWgh,ModObs,SevObs;
}