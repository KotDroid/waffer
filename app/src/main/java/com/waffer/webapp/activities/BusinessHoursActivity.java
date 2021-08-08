package com.waffer.webapp.activities;

import com.waffer.webapp.Model.Provider.BusinessHours;
import com.waffer.webapp.Utils.Constants;
import com.waffer.webapp.adapters.ProviderBusinessHoursAdapter;

public class BusinessHoursActivity extends CommonProviderInfoActivity {
    @Override
    protected void setAdapter() {
        BusinessHours services = (BusinessHours)(getIntent().getBundleExtra(Constants.DATA)).getSerializable(Constants.DATA);
        if(services != null) {
            getRecyclerView().setAdapter(new ProviderBusinessHoursAdapter
                    (services, null));
        }else{
            showNoData();
        }
    }
}
