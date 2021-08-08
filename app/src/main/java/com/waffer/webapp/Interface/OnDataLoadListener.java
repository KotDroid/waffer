package com.waffer.webapp.Interface;

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
import com.waffer.webapp.Utils.Constants;

import java.util.List;

/**
 * Created by Tregix on 12/14/2017.
 */

public interface OnDataLoadListener {

    void onCategoriesLoad(List<Category> items);

    void onProviderLoad(List<ProviderModel> items);

    void onCountriesLoad(List<Countries> items);

    void onAppointmentsLoad(List<Appointment> items);

    void onServiceLoad(List<ProfileServices> items);

    void onReviewsLoad(List<ProviderReviewListData> items);

    void onUpdateFavorites(ProviderModel item);

    void onPrivacyLoaded(PrivacySettings item);

    void onBusinessHoursLoaded(BusinessHours item);

    void onJobsLoaded(List<JobItem> items);

    void onProfileLoaded(ProviderModel items);

    void onPackagesLoad(List<PackageItem> items);

    void onSuccess(String msg);

    void onError(Constants.Errors errorCode, String error);
}
