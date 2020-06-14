package com.example.chamikanandasiri.electricitybillcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class SecondActivity extends AppCompatActivity {

    TextView tv_label_consumed, tv_label_service, tv_label_total, tv_Result_consumed, tv_Result_service, tv_Result_total;
    Button button_back;

    //call the layout to screen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String Value_Calculated = getIntent().getStringExtra("calculatedValue");
        String Service_Calculated = getIntent().getStringExtra("serviceValue");
        assert Value_Calculated != null;
        float valueCal = Float.parseFloat(Value_Calculated);
        assert Service_Calculated != null;
        float serviceCal = Float.parseFloat(Service_Calculated);
        float total = valueCal + serviceCal;

        tv_label_consumed = findViewById(R.id.textView1);
        tv_label_service = findViewById(R.id.textView3);
        tv_label_total = findViewById(R.id.textView);
        tv_Result_consumed = findViewById(R.id.textView2);
        tv_Result_service = findViewById(R.id.textView4);
        tv_Result_total = findViewById(R.id.textView5);
        button_back = findViewById(R.id._back);

        String s = Float.toString(total);

        tv_Result_consumed.setText(Value_Calculated);
        tv_Result_service.setText(Service_Calculated);
        tv_Result_total.setText(s);


        button_back.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent back = new Intent(SecondActivity.this, MainActivity.class);
                        startActivity(back);
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
        Toast.makeText(SecondActivity.this, "Press BACK agian to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                twice = false;
            }
        }, 2000);
        twice = true;
    }
}
