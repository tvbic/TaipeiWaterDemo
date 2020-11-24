package com.example.unitech.bmicalculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;

import androidx.appcompat.app.AppCompatActivity;

public class BmiActivity extends AppCompatActivity
{

    private EditText txtHeight;
    private EditText txtWeight;
    private TextView lblResult;
    private Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        initUI();
        initListener();
    }

    private void initListener()
    {
        btnCalculate.setOnClickListener(s -> calculateBMI());
    }

    private void calculateBMI()
    {
        if (!isDataReady()) return;

        BigDecimal result = calculateResult();

        lblResult.setText(result.toString());
    }

    private BigDecimal calculateResult()
    {
        //體重（公斤）除以身高（公尺）的平方
        BigDecimal weight = new BigDecimal(txtWeight.getText().toString());
        BigDecimal height = new BigDecimal(txtHeight.getText().toString());
        BigDecimal result = weight.divide(height.divide(new BigDecimal(100)).pow(2), 1, RoundingMode.HALF_UP);
        return result;
    }

    private boolean isDataReady()
    {
        if (txtHeight.getText().toString().isEmpty())
        {
            warnMessage("請輸入身高");
            return false;
        }

        if (txtWeight.getText().toString().isEmpty())
        {
            warnMessage("請輸入體重");
            return false;
        }

        return true;
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