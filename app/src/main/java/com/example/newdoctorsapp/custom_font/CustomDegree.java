package com.example.newdoctorsapp.custom_font;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.models.getQualiModel.GetQualiData;

import java.util.ArrayList;

public class CustomDegree  extends LinearLayout {
    public Spinner spin_degree;
  public   EditText edt_orgnasation;
    ArrayList<GetQualiData> getQualiData;
    ArrayAdapter<String> adapter;
    ArrayList<String> degree_name=new ArrayList<>();
    DegreeInterface degreeInterface;
    public CustomDegree(Context context, ArrayList<GetQualiData> getQualiData ) {
        super(context);
        degreeInterface= (DegreeInterface) context;
        this.getQualiData=getQualiData;
        myView();
    }
    public View myView() {
        View v; // Creating an instance for View Object
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.custom_degree_org, this);
        spin_degree=v.findViewById(R.id.spin_degree);
        edt_orgnasation=v.findViewById(R.id.edt_orgnasation);
        degree_name.add("----Select Degree---- ");
        for(int i=0;i<getQualiData.size();i++){
            degree_name.add(getQualiData.get(i).getAbbreviation());
        }
        adapter=new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,degree_name);
        spin_degree.setAdapter(adapter);
        spin_degree.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    degreeInterface.send_degree_id("-1");
                    return;
                }
                degreeInterface.send_degree_id(getQualiData.get(position-1).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return v;
    }
    public interface DegreeInterface{
        public void send_degree_id(String degree_id);
    }
}
