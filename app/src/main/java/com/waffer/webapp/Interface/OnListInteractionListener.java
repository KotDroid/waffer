package com.waffer.webapp.Interface;

import com.waffer.webapp.Model.Appointment;
import com.waffer.webapp.Model.JobItem;
import com.waffer.webapp.Model.Provider.ProviderModel;
import com.waffer.webapp.Model.categories.Category;
import com.waffer.webapp.Model.packages.PackageItem;

/**
 * Created by Tregix on 12/13/2017.
 */

public interface OnListInteractionListener  {

     void onProviderListInteraction(ProviderModel item) ;

     void onCategoryListInteraction(Category item) ;

     void onAppointmentInteraction(Appointment item, int pos) ;

     void onProviderFavorite(ProviderModel item);

     void onJobItemSelection(JobItem item);

     void onUserMessageSelection(String path, int post);

     void onPackageSelection(PackageItem item);

     void removeItem(int position);
}
