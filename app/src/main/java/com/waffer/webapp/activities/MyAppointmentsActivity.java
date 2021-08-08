package com.waffer.webapp.activities;

import com.waffer.webapp.Model.Appointment;
import com.waffer.webapp.Model.Login.User;
import com.waffer.webapp.Retrofit.RetrofitUtil;
import com.waffer.webapp.Utils.AppUtils;
import com.waffer.webapp.Utils.DatabaseUtil;
import com.waffer.webapp.adapters.MyAppointmentsRecyclerViewAdapter;

import java.util.List;

import static com.waffer.webapp.Retrofit.RetrofitUtil.getUserAppointments;


public class MyAppointmentsActivity extends CommonProviderInfoActivity {

    @Override
    public void onAppointmentsLoad(List<Appointment> items) {
        if(items != null && items.size() >0) {
            getRecyclerView().setAdapter(new MyAppointmentsRecyclerViewAdapter(items,this));
        }else{
            showNoData();
        }
    }

    @Override
    protected void setAdapter() {
        User user = DatabaseUtil.getInstance().getUser();
        RetrofitUtil.createProviderAPI().getUserAppointments(user.getData().getID()).
                enqueue(getUserAppointments(MyAppointmentsActivity.this));
    }

    @Override
    public void onAppointmentInteraction(Appointment item, int pos) {
        AppUtils.showDialog(this,item.toString(),null);
    }
}
