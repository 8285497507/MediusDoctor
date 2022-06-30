package com.example.newdoctorsapp.activity;

import static com.example.newdoctorsapp.MediusApp.getContext;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.newdoctorsapp.R;
import com.example.newdoctorsapp.RXCalling.BaseActivityJava;
import com.example.newdoctorsapp.models.Submitfeedback.SubmitFeedbackResponse;
import com.example.newdoctorsapp.models.Submitfeedback.sumitfeedbackmodel;
import com.example.newdoctorsapp.utility.Utils;
import com.example.newdoctorsapp.workspace.RetrofitHelper;
import com.example.newdoctorsapp.workspace.getworkingworkihgoursmodel;

import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactUsAcitivity extends BaseActivityJava implements Callback<SubmitFeedbackResponse>,View.OnClickListener {

    EditText edttextmsg;
    Button save;

    @Override
    public Observable getModel() {
        return serviceModel;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);
        initToolbar(this);
        edttextmsg =  findViewById(R.id.edttextmsg);
        save =  findViewById(R.id.save);
        toolbar.setTitle("Contact Us");
        RetrofitHelper.getInstance().init(this);



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addfeedback();
            }
        });
    }

    public  void addfeedback(){
        if (edttextmsg.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Please add your message", Toast.LENGTH_LONG).show();
            return;

        }else {
            sumitfeedbackmodel sumitfeedbackmodel = new sumitfeedbackmodel();
            sumitfeedbackmodel.setFeedback(edttextmsg.getText().toString());
            sumitfeedbackmodel.setUserId(getuserdata().get_id());
            if (Utils.haveInternet(getContext())) {
                mycustomdialog.show();
                RetrofitHelper.getInstance().CallAddFeedback(this,sumitfeedbackmodel);
            }

        }

    }




    @Override
    public void update(Observable observable, Object o) {

    }

    @Override
    public void onResponse(Call<SubmitFeedbackResponse> call, Response<SubmitFeedbackResponse> response) {
        if (response.isSuccessful()){
            mycustomdialog.hide();
            if (response.body().getStatus().equals(200)){
                Toast.makeText(getApplicationContext(), "Thanks! for your feedback", Toast.LENGTH_LONG).show();
                edttextmsg.setText("");
            }

        }else {

        }

    }

    @Override
    public void onFailure(Call<SubmitFeedbackResponse> call, Throwable t) {
        mycustomdialog.hide();
    }

    @Override
    public void onClick(View view) {

    }
}
