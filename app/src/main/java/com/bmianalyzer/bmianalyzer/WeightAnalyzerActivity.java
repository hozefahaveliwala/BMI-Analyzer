package com.bmianalyzer.bmianalyzer;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class WeightAnalyzerActivity extends AppCompatActivity {
    RadioGroup rg;
    EditText feet;
    EditText inches;
    TextView setResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_analyzer);

        rg = (RadioGroup) findViewById(R.id.radioGroup_bmiRange);
        feet = (EditText) findViewById(R.id.editText_feet);
        inches = (EditText) findViewById(R.id.editText_inches);
        setResult = (TextView) findViewById(R.id.textView_resut);


        findViewById(R.id.button_calculate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int range_id = rg.getCheckedRadioButtonId();
                int height_feet, height_inches;
                try {
                    height_feet = Integer.parseInt(feet.getText().toString());
                    height_inches = Integer.parseInt(inches.getText().toString());
                    if (height_inches >= 12) {
                        Toast.makeText(WeightAnalyzerActivity.this, "Enter height correctly", Toast.LENGTH_SHORT).show();
                    } else {
                        height_inches = (height_feet * 12) + height_inches;

                        if (range_id == -1) {
                            Toast.makeText(WeightAnalyzerActivity.this, "Choose BMI Index", Toast.LENGTH_SHORT).show();
                        } else {
                            if (range_id == R.id.radioButton_range1) {
                                setResult.setText("Your weight should be less than " + calculateWeight(height_inches, 18.5));
                            } else if (range_id == R.id.radioButton_range2) {
                                setResult.setText("Your weight should be in between " + calculateWeight(height_inches, 18.5) + " to " + calculateWeight(height_inches, 24.9));
                            } else if (range_id == R.id.radioButton_range3) {
                                setResult.setText("Your weight should be in between" + calculateWeight(height_inches, 25.0) + " to " + calculateWeight(height_inches, 29.9));
                            } else if (range_id == R.id.radioButton_range4) {
                                setResult.setText("Your weight should be greater than " + calculateWeight(height_inches, 29.9));
                            }
                            Toast.makeText(WeightAnalyzerActivity.this, "Weight Calculated", Toast.LENGTH_SHORT).show();
                        }

                    }

                } catch (NumberFormatException e) {
                    Toast.makeText(WeightAnalyzerActivity.this, "Enter Height", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weight_analyzer, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.itemBMIAnalyzer) {
            Intent intent = new Intent(WeightAnalyzerActivity.this, ScrollingActivity.class);
            startActivity(intent);
            finish();
            return true;
        } else if (id == R.id.itemAboutUs) {
            final AlertDialog.Builder aDBuilder = new AlertDialog.Builder(WeightAnalyzerActivity.this);
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
            Intent intent = new Intent(WeightAnalyzerActivity.this, BMIInfoActivity.class);
            startActivity(intent);
            finish();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public double calculateWeight(int height, double bmi) {
        double result;
        result = (bmi * height * height) / 703.0;
        result = Math.round(result * 10);
        result = result / 10;
        return result;
    }
}
