package com.example.newdoctorsapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.models.Apointmentlist.From;
import com.example.newdoctorsapp.models.Apointmentlist.Till;
import com.example.newdoctorsapp.models.Apointmentlist.Timing;
import com.example.newdoctorsapp.models.Apointmentlist.WorkingHour;

import java.util.ArrayList;
import java.util.List;

public class ParentAdopter extends RecyclerView.Adapter<ParentAdopter.MyViewHolder> {
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private final Addsession addsession;

    private List<WorkingHour> data;
   private List<Timing> timings;
    private Context context;
    private List<String> days=new ArrayList<>();
    String daysname[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    public ParentAdopter(Context context, List<WorkingHour> data, Addsession addsession) {
        this.context = context;
        this.data = data;
        this.addsession = addsession;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.parentview, parent, false)
        );


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.day.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
      //  String upperString = data.get(position).getDay().substring(0, 1).toUpperCase() + data.get(position).getDay().substring(1).toLowerCase();

         // holder.day.setText(upperString);
      //  holder.day.setText(daysname[position]);

        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.chrildrecycle.getContext());
       // layoutManager.setInitialPrefetchItemCount(data.get(position).getTimings().size());
//        ScheduleDayListAdopter childItemAdapter = new ScheduleDayListAdopter(data.get(position).getTimings(), new ScheduleDayListAdopter.Updateschdule() {
//            @Override
//            public void Updateschdule(List<Timing> data, int position) {
//                addsession.onclick(holder.day.getText().toString(), data,position);
//
//            }
//
//        });
        holder.chrildrecycle.setLayoutManager(layoutManager);
       // holder.chrildrecycle.setAdapter(childItemAdapter);
        holder.chrildrecycle.setRecycledViewPool(viewPool);

        holder.AddSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addsession.onAddlessioner(daysname[position]);
            }
        });


    }

    private void adddase(int position) {
    }



    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView day, AddSession;
        RecyclerView chrildrecycle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            day = itemView.findViewById(R.id.day);
            AddSession = itemView.findViewById(R.id.addsection);
            chrildrecycle = itemView.findViewById(R.id.monday_recycler_view);
        }
    }

    public interface Addsession {

        void onAddlessioner(String s);

        void onclick(String s, List<Timing> data, int position);
    }
}
