package com.waffer.webapp.DataManager;

import com.waffer.webapp.Interface.DataManager;
import com.waffer.webapp.Interface.OnDataLoadListener;
import com.waffer.webapp.Retrofit.RetrofitUtil;
import com.waffer.webapp.Utils.DatabaseUtil;

import static com.waffer.webapp.Retrofit.RetrofitUtil.getCategries;

/**
 * Created by Tregix on 12/25/2017.
 */

public class CategoryDataManager implements DataManager {

    @Override
    public void loadDataFromServer(OnDataLoadListener listener, boolean isForced) {
        if(isForced) {
            RetrofitUtil.createProviderAPI().loadCategories().enqueue(getCategries(listener));
        }else{
            loadDataFromDataBase(listener);
        }
    }

    @Override
    public void loadDataFromDataBase(OnDataLoadListener listener) {
        listener.onCategoriesLoad(DatabaseUtil.getInstance().getCategoriesList());
    }

    @Override
    public void saveDataToDataBase(Object data) {
       // DatabaseUtil.getInstance().delteCategoriesList();
       // DatabaseUtil.getInstance().storeCategoriesList((List<Category>)(Object)data);
    }

    @Override
    public void deleteDataToDataBase() {
        DatabaseUtil.getInstance().delteCategoriesList();
    }
}
