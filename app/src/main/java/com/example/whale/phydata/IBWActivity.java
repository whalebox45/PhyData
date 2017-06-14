package com.example.whale.phydata;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class IBWActivity extends AppCompatActivity {

    // For Girls: Please don't blame about this plzzzzzzzzzz
    private boolean isMale = false;

    RadioGroup ragrpGender;
    RadioButton radioMale,radioFemale;
    EditText etHeight1,etWeight1;

    Button btnCalcIBW,btnSendMail1,btnReturn1;

    TextView tvIBWResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ibw);

        ragrpGender = (RadioGroup) findViewById(R.id.ragrpGender);
        radioMale = (RadioButton) findViewById(R.id.radioMale);
        radioFemale = (RadioButton) findViewById(R.id.radioFemale);

        etHeight1 = (EditText) findViewById(R.id.etHeight1);
        etWeight1 = (EditText) findViewById(R.id.etWeight1);

        btnCalcIBW = (Button) findViewById(R.id.btnCalcIBW);
        tvIBWResult = (TextView) findViewById(R.id.tvIBWResult);

        btnSendMail1 = (Button) findViewById(R.id.btnSendMail1);
        btnReturn1 = (Button) findViewById(R.id.btnReturn1);

        ragrpGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                Log.d("chk","id"+checkedId);
                switch (checkedId){
                    case R.id.radioFemale:
                        isMale = false;
                        break;
                    case R.id.radioMale:
                        isMale = true;
                        break;
                    default:
                        break;
                }
            }
        });

        btnCalcIBW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    float height = Float.parseFloat(etHeight1.getText().toString());
                    float weight = Float.parseFloat(etWeight1.getText().toString());

                    float idealWeight = 0f;
                    if(IBWActivity.this.isMale){
                        idealWeight = (height - 170f) * 0.6f + 62f;
                    } else {
                        idealWeight = (height - 158f) * 0.5f + 52f;
                    }



                    if (weight <= idealWeight * 0.9f || weight >= idealWeight * 1.1f) {
                        tvIBWResult.setTextColor(Color.RED);
                    } else {
                        tvIBWResult.setTextColor(Color.BLUE);
                    }

                        tvIBWResult.setText("Your BW is " + weight + "kg\n" +
                                "Normal Range of Your BW\n" + String.format("%.1f",idealWeight* 0.9f)+ "kg ~ " + String.format("%.1f",idealWeight*1.1f) + "kg");
                }
                catch(Exception e){
                    Toast.makeText(IBWActivity.this,"Please check your input again.",Toast.LENGTH_SHORT).show();
                    Log.e(e.toString(),e.toString());
                }
            }
        });

        btnSendMail1.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(IBWActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnReturn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
