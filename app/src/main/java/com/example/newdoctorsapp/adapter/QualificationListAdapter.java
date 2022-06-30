package com.example.newdoctorsapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.activity.AddQualificationDetailActivity;
import com.example.newdoctorsapp.fragments.AddQulifactionFragment;
import com.example.newdoctorsapp.interfaces.DegreeIdCallback;
import com.example.newdoctorsapp.models.getQualiModel.GetQualiData;

import java.util.ArrayList;

public class QualificationListAdapter  extends RecyclerView.Adapter<QualificationListAdapter.MyViewHolder> {
    private Context context;
    ArrayList<GetQualiData> getQualiData = new ArrayList<>();
    AddQualificationDetailActivity addQualificationDetailActivity;
    AddQulifactionFragment addQulifactionFragment;
    DegreeIdCallback degreeIdCallback;


    public QualificationListAdapter(Context context,  ArrayList<GetQualiData> getQualiData,AddQualificationDetailActivity addQualificationDetailActivity,DegreeIdCallback degreeIdCallback) {
        this.context = context;
        this. getQualiData = getQualiData;
        this. addQualificationDetailActivity = addQualificationDetailActivity;
        this. degreeIdCallback = degreeIdCallback;
    }

    public QualificationListAdapter(Context context,  ArrayList<GetQualiData> getQualiData, AddQulifactionFragment addQulifactionFragment,DegreeIdCallback degreeIdCallback) {
        this.context = context;
        this. getQualiData = getQualiData;
        this. addQulifactionFragment = addQulifactionFragment;
        this. degreeIdCallback = degreeIdCallback;
    }

    @NonNull
    @Override
    public QualificationListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new QualificationListAdapter.MyViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.adapter_hospital_list, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull QualificationListAdapter.MyViewHolder holder, int position) {
        holder.hospitalname.setText(getQualiData.get(position).getName());
        Log.d("asdass","***"+getQualiData.get(position).getName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                degreeIdCallback.degreeidcallback(getQualiData.get(position).getId(),
                        getQualiData.get(position).getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d("asdass","***"+getQualiData.size());
        return getQualiData.size();


    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView hospitalname, Hospitaladderss;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            hospitalname = itemView.findViewById(R.id.hospitalname);

        }
    }

}

