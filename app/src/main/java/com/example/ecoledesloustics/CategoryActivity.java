package com.example.ecoledesloustics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener {

    Button btComputers, btMaths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        btMaths =findViewById(R.id.button4);
        btComputers = findViewById(R.id.button5);

        btMaths.setOnClickListener(this);
        btComputers.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.button4: // Math



                Intent intentMath = new Intent(CategoryActivity.this,QuizActivity.class);
                intentMath.putExtra("Category", CategoryConstants.Maths);
                startActivity(intentMath);

                break;
            case R.id.button5: //Comp


                Intent intentComputers = new Intent(CategoryActivity.this,QuizActivity.class);
                intentComputers.putExtra("Category", CategoryConstants.COMPUTERS);
                startActivity(intentComputers);

                break;

        }
    }




}