package com.waffer.webapp.activities;

import com.waffer.webapp.Model.Provider.Award;
import com.waffer.webapp.Utils.Constants;
import com.waffer.webapp.adapters.ProviderAwardsAdapter;

import java.util.List;

public class ProviderAwardsActivity extends CommonProviderInfoActivity {

    @Override
    protected void setAdapter() {
        List<Award> services = (List<Award>) (getIntent().getBundleExtra(Constants.DATA)).getSerializable(Constants.DATA);
        if (services != null && !services.isEmpty()) {
            getRecyclerView().setAdapter(new ProviderAwardsAdapter
                    (services, null));
        }else{
            showNoData();
        }
    }
}
