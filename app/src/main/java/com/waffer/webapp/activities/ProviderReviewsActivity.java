package com.waffer.webapp.activities;

import com.waffer.webapp.Model.Provider.ProviderReviewListData;
import com.waffer.webapp.Model.ReviewProvider;
import com.waffer.webapp.Retrofit.RetrofitUtil;
import com.waffer.webapp.Utils.Constants;
import com.waffer.webapp.adapters.ProviderReviewAdapter;

import java.util.List;

import static com.waffer.webapp.Retrofit.RetrofitUtil.getProviderReviews;

public class ProviderReviewsActivity extends CommonProviderInfoActivity {

    @Override
    protected void setAdapter() {
        if(getIntent().getBundleExtra(Constants.DATA) != null){
            ReviewProvider provider = new ReviewProvider();
            provider.setProviderId(getIntent().getBundleExtra(Constants.DATA).getInt(Constants.ID));
            RetrofitUtil.createProviderAPI().getProviderReviews(provider).enqueue(getProviderReviews(this));
        }
    }

    @Override
    public void onReviewsLoad(List<ProviderReviewListData> items) {
        if (items != null && !items.isEmpty()) {
            getRecyclerView().setAdapter(new ProviderReviewAdapter
                    (items));
        } else {
            showNoData();
        }
    }
}
