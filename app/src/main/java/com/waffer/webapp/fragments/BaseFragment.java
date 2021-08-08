package com.waffer.webapp.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.waffer.webapp.DataManager.CategoryDataManager;
import com.waffer.webapp.Interface.OnDataLoadListener;
import com.waffer.webapp.Interface.OnListInteractionListener;
import com.waffer.webapp.Model.Appointment;
import com.waffer.webapp.Model.Countries;
import com.waffer.webapp.Model.JobItem;
import com.waffer.webapp.Model.Provider.BusinessHours;
import com.waffer.webapp.Model.Provider.PrivacySettings;
import com.waffer.webapp.Model.Provider.ProfileServices;
import com.waffer.webapp.Model.Provider.ProviderModel;
import com.waffer.webapp.Model.Provider.ProviderReviewListData;
import com.waffer.webapp.Model.categories.Category;
import com.waffer.webapp.Model.packages.PackageItem;
import com.waffer.webapp.Utils.AppUtils;
import com.waffer.webapp.Utils.Constants;
import com.waffer.webapp.activities.ProviderDetailActivity;
import com.waffer.webapp.activities.ProviderListActivity;

import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.waffer.webapp.fragments.HomeFragment.REQUEST_UPDATE_FAVORITE;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment implements OnListInteractionListener,OnDataLoadListener{

    private ProgressDialog progressDialog;
    protected ProviderModel provider;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        if(getActivity() != null) {
            getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onProviderListInteraction(ProviderModel item) {
        Intent detailActiivtyIntent = new Intent(getActivity(), ProviderDetailActivity.class);
        detailActiivtyIntent.putExtra(Constants.DATA, item);
        provider = item;
        startActivityForResult(detailActiivtyIntent,REQUEST_UPDATE_FAVORITE);
    }

    @Override
    public void onCategoryListInteraction(Category item) {
        try {
            Intent newIntent = new Intent(getActivity(), ProviderListActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(Constants.CATEGORY, item.getTitle());
            bundle.putInt(Constants.CATEGORY_ID, item.getId());
            bundle.putString(Constants.TITLE, item.getTitle());
            newIntent.putExtra(Constants.DATA, bundle);
            newIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(newIntent);
        }catch (Exception e){
            e.printStackTrace();
            new CategoryDataManager().loadDataFromServer(this,true);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_UPDATE_FAVORITE && resultCode == RESULT_OK
                && data != null &&  provider != null) {
            provider.setIsfav(((ProviderModel) data.getSerializableExtra(Constants.DATA)).isfav());
            notifyChange();
        }
    }

    protected void notifyChange(){

    }

    @Override
    public void onAppointmentInteraction(Appointment item, int pos) {

    }

    @Override
    public void onProviderFavorite(ProviderModel item) {

    }

    @Override
    public void onJobItemSelection(JobItem item) {

    }

    @Override
    public void onUserMessageSelection(String path, int post) {

    }

    @Override
    public void onPackageSelection(PackageItem item) {

    }

    @Override
    public void removeItem(int position) {

    }

    @Override
    public void onCategoriesLoad(List<Category> items) {
    }

    @Override
    public void onProviderLoad(List<ProviderModel> items) {
    }

    @Override
    public void onCountriesLoad(List<Countries> items) {

    }

    @Override
    public void onAppointmentsLoad(List<Appointment> items) {

    }

    @Override
    public void onServiceLoad(List<ProfileServices> items) {

    }

    @Override
    public void onReviewsLoad(List<ProviderReviewListData> items) {

    }

    @Override
    public void onUpdateFavorites(ProviderModel item) {

    }

    @Override
    public void onSuccess(String msg) {
        hideProgressDialog();
        AppUtils.showDialog(getActivity(),msg,null);
    }

    @Override
    public void onError(Constants.Errors errorCode, String error) {
        hideProgressDialog();
        AppUtils.showDialog(getActivity(),error,null);

    }

    @Override
    public void onBusinessHoursLoaded(BusinessHours item) {

    }

    protected  void setAdapter(){

    }

    protected void showProgressDialog(String msg) {
        try {
            if (progressDialog == null ) {
                progressDialog = new ProgressDialog(getActivity());
                progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            }
            if(!progressDialog.isShowing()) {
                progressDialog.setMessage(msg);
                progressDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void hideProgressDialog() {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPrivacyLoaded(PrivacySettings item) {

    }

    @Override
    public void onJobsLoaded(List<JobItem> items) {

    }

    @Override
    public void onProfileLoaded(ProviderModel items) {

    }

    @Override
    public void onPackagesLoad(List<PackageItem> items) {

    }

    protected void openAcitivty(Bundle bundle, Class<?> cls) {
        Intent intent = new Intent(getActivity(), cls);
        intent.putExtra(Constants.DATA, bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }




}
