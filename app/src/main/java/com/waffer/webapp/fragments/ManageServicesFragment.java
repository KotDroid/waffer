package com.waffer.webapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.waffer.webapp.Model.ManageServicesRequestParam;
import com.waffer.webapp.Model.Provider.ProfileServices;
import com.waffer.webapp.R;
import com.waffer.webapp.Retrofit.RetrofitUtil;
import com.waffer.webapp.Utils.Constants;
import com.waffer.webapp.Utils.DatabaseUtil;
import com.waffer.webapp.activities.NavigationDrawerActivity;
import com.waffer.webapp.adapters.ManageServicesAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.waffer.webapp.Retrofit.RetrofitUtil.getProviderServices;
import static com.waffer.webapp.Retrofit.RetrofitUtil.sendRequest;

/**
 * Created by Tregix on 2/21/2018.
 */

public class ManageServicesFragment extends BaseFragment {

    private ShimmerRecyclerView recyclerView;
    private TextView addService;
    private ManageServicesAdapter manageServicesAdapter;
    private Button submit;
    private int uid;
    private List<ProfileServices> data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.ui_manage_services, container, false);

        ((NavigationDrawerActivity) getActivity()).getSupportActionBar().setTitle("Manage Services");

        recyclerView = view.findViewById(R.id.list);
        addService = view.findViewById(R.id.manage_service_add_item);
        submit = view.findViewById(R.id.manage_service_submit);

        if(DatabaseUtil.getInstance().getUser() != null)
        uid = DatabaseUtil.getInstance().getUser().getData().getID();

        addService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manageServicesAdapter.addService(new ProfileServices());
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<ProfileServices> services = manageServicesAdapter.getList();
                ManageServicesRequestParam param = new ManageServicesRequestParam
                        (uid,services);

                showProgressDialog(getString(R.string.msg_update_services));
                RetrofitUtil.createProviderAPI().updateUserServices(param).enqueue
                        (sendRequest(ManageServicesFragment.this));
            }
        });

        RetrofitUtil.createProviderAPI().getUserServices(uid)
                .enqueue(getProviderServices(this));

        return view;
    }

    @Override
    public void onError(Constants.Errors errorCode, String error) {
        super.onError(errorCode, error);
        onServiceLoad(new ArrayList<ProfileServices>());
    }

    @Override
    public void onServiceLoad(List<ProfileServices> items) {
        data = items;
        manageServicesAdapter = new ManageServicesAdapter(items,this);
        recyclerView.setAdapter(manageServicesAdapter);
    }

    @Override
    public void removeItem(int position) {
        super.removeItem(position);
        if(data != null && position < data.size()) {
            data.remove(position);
            manageServicesAdapter = new ManageServicesAdapter(data, this);
            recyclerView.setAdapter(manageServicesAdapter);
        }
    }
}
