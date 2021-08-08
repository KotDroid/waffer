package com.waffer.webapp.activities;

import com.waffer.webapp.Model.Provider.ProfileServices;
import com.waffer.webapp.Utils.Constants;
import com.waffer.webapp.adapters.ProviderServicesRecyclerViewAdapter;

import java.util.List;

public class ProviderServicesActivity extends CommonProviderInfoActivity {

    @Override
    protected void setAdapter() {
        List<ProfileServices> services = (List<ProfileServices>) (getIntent().getBundleExtra(Constants.DATA)).getSerializable(Constants.DATA);
        if (services != null && !services.isEmpty()) {
            getRecyclerView().setAdapter(new ProviderServicesRecyclerViewAdapter
                    (services, null));
        } else {
            showNoData();
        }
    }
}
