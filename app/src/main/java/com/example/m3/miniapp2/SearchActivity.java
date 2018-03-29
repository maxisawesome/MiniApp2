package com.example.m3.miniapp2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by m3 on 3/18/18.
 */

public class SearchActivity extends AppCompatActivity {

    Context mContext;
    Spinner dietSpinner;
    Spinner prepSpinner;
    Spinner servingSpinner;
    Button searchButton;

    String[] servingSizes = new String[]{"", "less than 4", "4-6", "7-9", "more than 10"};
    String[] prepTimes = new String[]{"", "30 minutes or less", "less than 1 hour", "more than 1 hour"};
    String[] dietRestrictions = new String[]{"", "Vegetarian", "Balanced", "Low-Sodium", "Low-Carb", "Medium-Carb", "High-Carb", "Low-Fat"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        mContext = this;
        dietSpinner = findViewById(R.id.dietSpinner);
        prepSpinner = findViewById(R.id.prepSpinner);
        servingSpinner = findViewById(R.id.servingSpinner);
        searchButton = findViewById(R.id.search_button);

        ArrayAdapter<String> sSpinnerAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, servingSizes);
        servingSpinner.setAdapter(sSpinnerAdapter);

        ArrayAdapter<String> pSpinnerAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, prepTimes);
        prepSpinner.setAdapter(pSpinnerAdapter);

        ArrayAdapter<String> dSpinnerAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, dietRestrictions);
        dietSpinner.setAdapter(dSpinnerAdapter);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String servingSelection = servingSpinner.getSelectedItem().toString();
                String prepSelection = prepSpinner.getSelectedItem().toString();
                String dietSelection = dietSpinner.getSelectedItem().toString();

                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("servingSelection",  servingSelection);
                intent.putExtra("prepSelection", prepSelection);
                intent.putExtra("dietSelection", dietSelection);
                startActivity(intent);
            }
        });
    }
}
