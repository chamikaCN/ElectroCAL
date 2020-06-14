package com.example.chamikanandasiri.electricitybillcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //declaring variables
    private Button btn_calculate;
    private EditText no_days, no_units;
    int days, units;
    float value, service;

    //setting the layout and variables before starting app
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getValues();
    }


    //defining method 'getValues'
    private void getValues() {
        btn_calculate = findViewById(R.id.button1);
        no_days = findViewById(R.id.Txt1);
        no_units = findViewById(R.id.Txt2);

        //set onClickListening
        btn_calculate.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        btn_calculate.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                        try {
                            days = Integer.parseInt(no_days.getText().toString());
                            units = Integer.parseInt(no_units.getText().toString());

                            value = 0;
                            service = 0;

                            if (units <= 2 * days) {
                                if (units <= days) {
                                    value += (float) (units * 2.50);
                                    service = 30f;
                                } else {
                                    value += (float) ((units - days) * 4.85);
                                    value += (float) (days * 2.50);
                                    service = 60f;
                                }
                            } else {
                                if (units <= 3 * days) {
                                    value += (float) ((2 * days) * 7.85);
                                    value += (float) ((units - (2 * days)) * 10.00);
                                    service = 90f;
                                } else if (units <= 4 * days){
                                    value += (float) ((2 * days) * 7.85);
                                    value += (float) ((days) * 10.00);
                                    value += (float) ((units - (3 * days)) * 27.75);
                                    service = 480f;
                                } else if (units <= 6 * days){
                                    value += (float) ((2 * days) * 7.85);
                                    value += (float) ((days) * 10.00);
                                    value += (float) ((days) * 27.75);
                                    value += (float) ((units - (4 * days)) * 32.00);
                                    service = 480f;
                                } else {
                                    value += (float) ((2 * days) * 7.85);
                                    value += (float) ((days) * 10.00);
                                    value += (float) ((days) * 27.75);
                                    value += (float) ((2 * days) * 32.00);
                                    value += (float) ((units - (6 * days)) * 45.00);
                                    service = 540f;
                                }
                            }
                            String val = Float.toString(value);
                            String ser = Float.toString(service);
                            Toast.makeText(MainActivity.this, val, Toast.LENGTH_LONG).show();
                            Intent passing = new Intent(MainActivity.this, SecondActivity.class);
                            passing.putExtra("calculatedValue", val);
                            passing.putExtra("serviceValue", ser);
                            startActivity(passing);
                        } catch (Exception e) {
                            btn_calculate.setBackgroundColor(getResources().getColor(R.color.colorAttention));
                            Toast.makeText(MainActivity.this, "You have to enter information first", Toast.LENGTH_LONG).show();
                        }
                    }


                }
        );

    }

    boolean twice;

    @Override
    public void onBackPressed() {

        if (twice) {
            Intent exit = new Intent(Intent.ACTION_MAIN);
            exit.addCategory(Intent.CATEGORY_HOME);
            exit.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(exit);
            finish();
            System.exit(0);
        }
        //super.onBackPressed();
        Toast.makeText(MainActivity.this, "Press BACK agian to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                twice = false;
            }
        }, 2000);
        twice = true;
    }

}
