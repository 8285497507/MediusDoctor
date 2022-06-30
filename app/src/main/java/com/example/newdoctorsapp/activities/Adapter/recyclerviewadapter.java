package com.example.newdoctorsapp.activities.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.activity.Activity_Hospital_Practice;
import com.example.newdoctorsapp.models.HospitalList.Data;
import com.example.newdoctorsapp.models.HospitalList.HospitalList;

import java.util.ArrayList;
import java.util.List;

public class recyclerviewadapter extends RecyclerView.Adapter<recyclerviewadapter.MyViewHolder> implements Filterable {
    private Context context;
    private List<Data> data;
    private List<Data> filterdata ;
    private chekboxe chekboxe;

    public recyclerviewadapter(Context activity_hospital_practice, List<Data> data, recyclerviewadapter.chekboxe chekboxe) {
        this.context = activity_hospital_practice;
        this.chekboxe = chekboxe;
        this.data = data;
        this.filterdata = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new MyViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.practice_cardview, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.checkedbox.setChecked(data.get(position).getChecked());
        holder.hospitalname.setText(data.get(position).getName());
        holder.Hospitaladderss.setText(data.get(position).getAddress().getAddressLine_1());
        holder.checkedbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                data.get(position).setChecked(isChecked);
                chekboxe.OnCheckedChangeListener(data.get(position));

            }
        });
    }

    @Override
    public int getItemCount() {
        return filterdata.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView hospitalname, Hospitaladderss;
        CheckBox checkedbox;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            hospitalname = itemView.findViewById(R.id.Hospitalname);
            Hospitaladderss = itemView.findViewById(R.id.hospitaladderss);
            checkedbox = itemView.findViewById(R.id.checkedbox);
        }
    }

    public interface chekboxe {
        void OnCheckedChangeListener(Data data);
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filterdata = data;

                } else {
                    List<Data> filteredList = new ArrayList<>();
                    for (Data row : data) {

                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            Log.d("sdAS","***"+row.getName()+"7777"+charString.toLowerCase());
                            Log.d("sdASbb","***"+"7777"+charString.toLowerCase());
                            filteredList.add(row);
                        }
                    }

                    filterdata = filteredList;

                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filterdata;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                data = (ArrayList<Data>) filterResults.values;

                // refresh the list with filtered data
                notifyDataSetChanged();
            }
        };
    }
}
