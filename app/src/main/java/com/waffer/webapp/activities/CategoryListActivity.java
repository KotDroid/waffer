package com.waffer.webapp.activities;

import android.content.Intent;
import android.os.Bundle;

import com.waffer.webapp.DataManager.CategoryDataManager;
import com.waffer.webapp.Model.categories.Category;
import com.waffer.webapp.Utils.Constants;
import com.waffer.webapp.Utils.DatabaseUtil;
import com.waffer.webapp.adapters.CategoryRecyclerViewAdapter;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * interface.
 */
public class CategoryListActivity extends CommonProviderInfoActivity {

    @Override
    protected void setAdapter() {
        List<Category> categoriesList = DatabaseUtil.getInstance().getCategoriesList();

        if (categoriesList != null && !categoriesList.isEmpty()) {
            onCategoriesLoad(categoriesList);
        }else{
            new CategoryDataManager().loadDataFromServer(this,true);
        }
    }

    @Override
    public void onCategoryListInteraction(Category item) {
        Intent newIntent = new Intent(this, ProviderListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.CATEGORY, item.getTitle());
        bundle.putInt(Constants.CATEGORY_ID, item.getId());
        bundle.putString(Constants.TITLE,item.getTitle());
        newIntent.putExtra(Constants.DATA, bundle);
        newIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(newIntent);
    }

    @Override
    public void onCategoriesLoad(List<Category> items) {
        getRecyclerView().setAdapter(new CategoryRecyclerViewAdapter(items, this));
    }

}
