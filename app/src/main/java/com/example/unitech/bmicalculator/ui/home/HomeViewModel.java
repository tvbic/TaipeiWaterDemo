package com.example.unitech.bmicalculator.ui.home;

import com.example.unitech.bmicalculator.SingleLiveEvent;

import java.math.BigDecimal;
import java.math.RoundingMode;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel
{

    public MutableLiveData<String> Height = new MutableLiveData<>();
    public MutableLiveData<String> Weight = new SingleLiveEvent<>();
    public MutableLiveData<String> Result = new MutableLiveData<>();
    public SingleLiveEvent<String> ToastMsg = new SingleLiveEvent<>();
    public SingleLiveEvent<String> DialogMsg = new SingleLiveEvent<>();

    public HomeViewModel()
    {
        Height.setValue("");
        Weight.setValue("");
        Result.setValue("");
    }

    public void calculateBMI()
    {
        if (!isDataReady()) return;

        BigDecimal value = calculateResult();

        Result.setValue(value.toString());

        DialogMsg.setValue("計算結果: " + value.toString());
    }

    private BigDecimal calculateResult()
    {
        //體重（公斤）除以身高（公尺）的平方
        BigDecimal weight = new BigDecimal(Weight.getValue());

        BigDecimal height = new BigDecimal(Height.getValue());

        BigDecimal p2 = height.divide(new BigDecimal(100)).pow(2);

        return weight.divide(p2, 1, RoundingMode.HALF_UP);
    }


    private boolean isDataReady()
    {
        if (Height.getValue().isEmpty())
        {
            ToastMsg.setValue("請輸入身高");
            return false;
        }

        if (Weight.getValue().isEmpty())
        {
            ToastMsg.setValue("請輸入體重");
            return false;
        }

        return true;
    }


    public void clearData()
    {
        Height.setValue("");
        Weight.setValue("");
        Result.setValue("");
    }
}