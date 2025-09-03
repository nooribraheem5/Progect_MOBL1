package com.example.myapplication;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DataFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar c =Calendar.getInstance();
        int year =c.get(Calendar.YEAR);
        int month =c.get(Calendar.MONTH);
        int day =c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity() , this ,year ,month,day );

    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        // This method is called when the user clicks the "OK" button in the DatePickerDialog.

        ActivityAddItem orderActivity =(ActivityAddItem) getActivity();
        // ActivityAddItem.addItemBinding.actAddItemEdtDate.setOnClickListener(month+"/"+dayOfMonth+"/"+year);
//        ActivityAddItem.addItemBinding.actAddItemEdtDate.setText(month+"/"+dayOfMonth+"/"+year);
//        orderActivity.processDatePickerResult(year,month,dayOfMonth);

    }
}