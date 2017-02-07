package com.bmianalyzer.bmianalyzer;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class BMIInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiinfo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bmi_info, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.itemBMIAnalyzer) {
            Intent intent = new Intent(BMIInfoActivity.this, ScrollingActivity.class);
            startActivity(intent);
            finish();
            return true;
        } else if (id == R.id.itemAboutUs) {
            final AlertDialog.Builder aDBuilder = new AlertDialog.Builder(BMIInfoActivity.this);
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
        } else if (id == R.id.itemWeightAnalyzer) {
            Intent intent = new Intent(BMIInfoActivity.this, WeightAnalyzerActivity.class);
            startActivity(intent);
            finish();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
