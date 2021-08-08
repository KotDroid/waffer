package com.waffer.webapp.activities;

import com.waffer.webapp.Model.Provider.Qualification;
import com.waffer.webapp.Utils.Constants;
import com.waffer.webapp.adapters.ProviderQualificationAdapter;

import java.util.List;

public class ProviderQualificationActivity extends CommonProviderInfoActivity {

    @Override
    protected void setAdapter() {
        List<Qualification> services = (List<Qualification>) (getIntent().getBundleExtra(Constants.DATA)).getSerializable(Constants.DATA);
        if (services != null && !services.isEmpty()) {
            getRecyclerView().setAdapter(new ProviderQualificationAdapter
                    (services, null));
        }else{
            showNoData();
        }
    }
}
