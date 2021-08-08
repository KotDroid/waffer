package com.waffer.webapp.activities;

import com.waffer.webapp.Model.Provider.Experience;
import com.waffer.webapp.Utils.Constants;
import com.waffer.webapp.adapters.ProviderExperienceAdapter;

import java.util.List;

public class ProviderExperienceActivity extends CommonProviderInfoActivity {

    @Override
    protected void setAdapter() {
        List<Experience> services = (List<Experience>) (getIntent().getBundleExtra(Constants.DATA)).getSerializable(Constants.DATA);
        if (services != null && !services.isEmpty()) {
            getRecyclerView().setAdapter(new ProviderExperienceAdapter
                    (services, null));
        }else{
            showNoData();
        }
    }
}
