package com.example.ecoledesloustics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LevelsActivity extends AppCompatActivity implements View.OnClickListener {

    Button btLevel1,btLevel2,btLevel3;

    String categoryValue = "";

    int CL1,CL2,CL3;

    TextView txtLevel1,txtLevel2,txtLevel3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        btLevel1 = findViewById(R.id.btLevel1);
        btLevel2 = findViewById(R.id.btLevel2);
        btLevel3 = findViewById(R.id.btLevel3);

        //btLevel1.setOnClickListener(this);
        //btLevel2.setOnClickListener(this);
        //btLevel3.setOnClickListener(this);

        txtLevel1 = findViewById(R.id.txtLevel1);
        txtLevel2 = findViewById(R.id.txtLevel2);
        txtLevel3 = findViewById(R.id.txtLevel3);

        lockandUnlockLevels();


        Intent intentCategory = getIntent();
        categoryValue = intentCategory.getStringExtra("Category");



    }

    private void lockandUnlockLevels() {

        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName() + Constant.MY_LEVEL_PREFFILE, Context.MODE_PRIVATE);

        CL1 = sharedPreferences.getInt(Constant.KEY_CMP_LEVEL_1,0);
        CL2 = sharedPreferences.getInt(Constant.KEY_CMP_LEVEL_2,0);
        CL3 = sharedPreferences.getInt(Constant.KEY_CMP_LEVEL_3,0);

        if (CL1 == 1){
            btLevel1.setClickable(true);
            btLevel1.setBackground(ContextCompat.getDrawable(this,R.drawable.button_next_background));
            btLevel1.setOnClickListener(this);

        }

        if (CL1 == 1){
            btLevel2.setClickable(true);
            btLevel2.setBackground(ContextCompat.getDrawable(this,R.drawable.button_next_background));
            btLevel2.setOnClickListener(this);

        }else if(CL2 == 0){

            btLevel2.setClickable(false);
            btLevel2.setBackground(ContextCompat.getDrawable(this,R.drawable.level_lock));

        }

        if (CL1 == 1){
            btLevel3.setClickable(true);
            btLevel3.setBackground(ContextCompat.getDrawable(this,R.drawable.button_next_background));
            btLevel3.setOnClickListener(this);

        }else if(CL3 == 0){

            btLevel3.setClickable(false);
            btLevel3.setBackground(ContextCompat.getDrawable(this,R.drawable.level_lock));

        }



    }

    public void LoadData(View view) {

        txtLevel1.setText(String.valueOf(CL1));
        txtLevel2.setText(String.valueOf(CL2));
        txtLevel3.setText(String.valueOf(CL3));
    }

    @Override
    public void onClick(View v) {

        if(categoryValue.equals("Computers")){


            switch ((v.getId())){

                case R.id.btLevel1:

                    Intent intentCompLevel1 = new Intent(LevelsActivity.this,QuizActivity.class);
                    intentCompLevel1.putExtra("Category",Questions.CATEGORY_COMPUTERS);
                    intentCompLevel1.putExtra("Level",Questions.LEVEL1);
                    startActivity(intentCompLevel1);

                    break;
                case R.id.btLevel2:

                    Intent intentCompLevel2 = new Intent(LevelsActivity.this,QuizActivity.class);
                    intentCompLevel2.putExtra("Category",Questions.CATEGORY_COMPUTERS);
                    intentCompLevel2.putExtra("Level",Questions.LEVEL2);
                    startActivity(intentCompLevel2);

                    break;
                case R.id.btLevel3:

                    Intent intentCompLevel3 = new Intent(LevelsActivity.this,QuizActivity.class);
                    intentCompLevel3.putExtra("Category",Questions.CATEGORY_COMPUTERS);
                    intentCompLevel3.putExtra("Level",Questions.LEVEL3);
                    startActivity(intentCompLevel3);

                    break;

            }

        }


    }
}