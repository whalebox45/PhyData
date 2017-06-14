package com.example.whale.phydata;

<<<<<<< HEAD
import android.content.Intent;
=======
>>>>>>> 5274e65dc98895c1d2ad3e63fd8f2186f053df6a
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
    EditText etSBP,etDBP;
<<<<<<< HEAD
    Button btnCalcMAP,btnReturn3,btnSendMail3;
=======
    Button btnCalcMAP,btnReturn3;
>>>>>>> 5274e65dc98895c1d2ad3e63fd8f2186f053df6a
    TextView tvMAPResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        etSBP = (EditText) findViewById(R.id.etSBP);
        etDBP = (EditText) findViewById(R.id.etDBP);

        btnCalcMAP = (Button) findViewById(R.id.btnCalcMAP);
        tvMAPResult = (TextView) findViewById(R.id.tvMAPResult);
<<<<<<< HEAD
        btnSendMail3 = (Button) findViewById(R.id.btnSendMail3);
=======
>>>>>>> 5274e65dc98895c1d2ad3e63fd8f2186f053df6a
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
                    } else {
                        tvMAPResult.setTextColor(Color.BLUE);
                    }

                    tvMAPResult.setText("Your MAP is "+ String.format("%.1f",map)+"mmHg\n"+
                    "Normal Range of MAP\n"+"80 mmHg ~ 100mmHg");
                }
                catch (Exception e){
                    Toast.makeText(MAPActivity.this,"Please check your input again.",Toast.LENGTH_SHORT).show();
                    Log.e(e.toString(),e.toString());
                }
            }
        });
<<<<<<< HEAD

        btnSendMail3.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(MAPActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
=======
>>>>>>> 5274e65dc98895c1d2ad3e63fd8f2186f053df6a
        btnReturn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
