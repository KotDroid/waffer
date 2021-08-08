package com.waffer.webapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.waffer.webapp.Model.JobItem;
import com.waffer.webapp.R;
import com.waffer.webapp.Retrofit.RetrofitUtil;
import com.waffer.webapp.Utils.Constants;
import com.waffer.webapp.activities.JobDetailActivity;
import com.waffer.webapp.activities.NavigationDrawerActivity;
import com.waffer.webapp.adapters.JobsListingAdapter;

import java.util.List;

import static com.waffer.webapp.Retrofit.RetrofitUtil.getAllJobs;

/**
 * Created by Tregix on 2/21/2018.
 */

public class JobsListingFragment extends BaseFragment {

    private ShimmerRecyclerView recyclerView;
    private JobsListingAdapter jobsListingAdapter;
    private TextView noData;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_provider_list, container, false);

        ((NavigationDrawerActivity) getActivity()).getSupportActionBar().setTitle("Jobs");

        recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        noData = view.findViewById(R.id.list_no_data);


        RetrofitUtil.createProviderAPI().getAllJobs()
                    .enqueue(getAllJobs(this));

        return view;
    }

    @Override
    public void onJobsLoaded(List<JobItem> items) {
        if(items != null && items.size() > 0) {
            jobsListingAdapter = new JobsListingAdapter(items, this);
            recyclerView.setAdapter(jobsListingAdapter);
        }else{
            recyclerView.setAdapter(null);
            noData.setVisibility(View.VISIBLE);
        }
    }

    public void onJobItemSelection(JobItem item){
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.DATA,item);

        openAcitivty(bundle, JobDetailActivity.class);
    }

}
