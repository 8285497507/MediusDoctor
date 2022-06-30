package com.example.newdoctorsapp.customSpinner;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomeSpinnerAdopter extends ArrayAdapter<SpinnerModelclass> {

    public CustomeSpinnerAdopter(@NonNull Context context, ArrayList<SpinnerModelclass> arrayLis) {

        super(context, 0,arrayLis);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        return super.getView(position, convertView, parent);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return super.getFilter();
    }

    @Override
    public int getCount() {
        return super.getCount();
    }
}
