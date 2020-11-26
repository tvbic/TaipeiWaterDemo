package com.example.unitech.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private EditText txtHeight;
    private EditText txtWeight;
    private TextView lblResult;
    private Button btnCalculate;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        initListener();
    }

    private void initListener()
    {
        btnCalculate.setOnClickListener(s-> calculateBMI());
    }

    private void calculateBMI()
    {
        if(txtHeight.getText().toString().isEmpty())
        {
            warnMessage("請輸入身高");
            return;
        }

        if(txtWeight.getText().toString().isEmpty())
        {
            warnMessage("請輸入體重");
            return;
        }
    }

    private void warnMessage(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void initUI()
    {
        txtHeight = findViewById(R.id.txtHeight);
        txtWeight = findViewById(R.id.txtWeight);
        lblResult = findViewById(R.id.lblResult);
        btnCalculate = findViewById(R.id.btnCalculate);
    }
}