package com.bmianalyzer.bmianalyzer;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ScrollingActivity extends AppCompatActivity {
    EditText editText_weight, editText_H_feet, editText_H_inches;
    TextView txt_BMI, txt_BMI_comment;
    double weight, height_Feet, height_inches, BMI;
    final double UNDERWEIGHT = 18.5, NORMAL1 = 18.5, NORMAL2 = 24.9, OVERWEIGHT1 = 25, OVERWEIGHT2 = 29.9, OBESITY = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editText_weight = (EditText) findViewById(R.id.editTextWeight);
        editText_H_feet = (EditText) findViewById(R.id.editTextHeightFeet);
        editText_H_inches = (EditText) findViewById(R.id.editTextHeightInches);
        txt_BMI = (TextView) findViewById(R.id.textViewBMI);
        txt_BMI_comment = (TextView) findViewById(R.id.textViewBMIComment);

        findViewById(R.id.btn_calculateBMI).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpannableString ss;
                if ((editText_weight.length() == 0) || (editText_H_feet.length() == 0) || (editText_H_inches.length() == 0) || (Integer.valueOf(editText_H_inches.getText().toString()) >= 12)) {
                    Toast.makeText(ScrollingActivity.this, "Invalid Input", Toast.LENGTH_SHORT).show();
                } else {
                    BMI = BMICalculate(Double.valueOf(editText_weight.getText().toString()), Double.valueOf(editText_H_feet.getText().toString()), Double.valueOf(editText_H_inches.getText().toString()));
                    //txt_BMI.setText(getResources().getString(R.string.txt_yourBMI) + " " + BMI + "");

                    /*BMI Comment Calculation*/
                    if (BMI < UNDERWEIGHT) {
                         ss = new SpannableString(getResources().getString(R.string.txt_yourBMI) + " " + BMI + "");
                        ss.setSpan(new ForegroundColorSpan(Color.RED), 0, ss.length(), 0);
                        txt_BMI.setText(ss);

                        ss = new SpannableString(getResources().getString(R.string.BMI_comment) + " " + getResources().getString(R.string.BMI_underweight));
                        ss.setSpan(new ForegroundColorSpan(Color.RED), 0, ss.length(), 0);
                        txt_BMI_comment.setText(ss);
                    } else if (BMI >= UNDERWEIGHT && BMI <= NORMAL2) {
                        txt_BMI.setText(getResources().getString(R.string.txt_yourBMI) + " " + BMI + "");
                        txt_BMI_comment.setText(getResources().getString(R.string.BMI_comment) + " " + getResources().getString(R.string.BMI_normal));
                    } else if (BMI >= OVERWEIGHT1 && BMI <= OVERWEIGHT2) {
                        txt_BMI.setText(getResources().getString(R.string.txt_yourBMI) + " " + BMI + "");
                        txt_BMI_comment.setText(getResources().getString(R.string.BMI_comment) + " " + getResources().getString(R.string.BMI_overweight));
                    } else if (BMI >= OBESITY) {
                        ss = new SpannableString(getResources().getString(R.string.txt_yourBMI) + " " + BMI + "");
                        ss.setSpan(new ForegroundColorSpan(Color.RED), 0, ss.length(), 0);
                        txt_BMI.setText(ss);

                        ss = new SpannableString(getResources().getString(R.string.BMI_comment) + " " + getResources().getString(R.string.BMI_obesity));
                        ss.setSpan(new ForegroundColorSpan(Color.RED), 0, ss.length(), 0);
                        txt_BMI_comment.setText(ss);

                    }
                    Toast.makeText(ScrollingActivity.this, "BMI Calculated", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.itemWeightAnalyzer) {
            Intent intent = new Intent(ScrollingActivity.this, WeightAnalyzerActivity.class);
            startActivity(intent);
            finish();
            return true;
        } else if (id == R.id.itemAboutUs) {
            final AlertDialog.Builder aDBuilder = new AlertDialog.Builder(ScrollingActivity.this);
            aDBuilder.setTitle(getResources().getString(R.string.BMI_about_us))
                    .setMessage(getResources().getString(R.string.BMI_about_us_info))
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

            AlertDialog aD = aDBuilder.create();
            aD.show();
            return true;
        } else if (id == R.id.itemBMIInfo) {

            Intent intent = new Intent(ScrollingActivity.this, BMIInfoActivity.class);
            startActivity(intent);
            finish();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private double BMICalculate(double w, double h_Feet, double h_inches) {
        double BMI;
    /*BMI Caluculatoion*/
        h_inches = h_inches + (h_Feet * 12.0);
        BMI = (w / (h_inches * h_inches)) * 703;
    /*BMI Round Off*/
        BMI = Math.round(BMI * 10.0);
        BMI = BMI / 10.0;
        return BMI;
    }
}
