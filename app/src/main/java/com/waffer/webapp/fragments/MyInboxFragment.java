package com.waffer.webapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.waffer.webapp.R;
import com.waffer.webapp.Utils.AppUtils;
import com.waffer.webapp.Utils.Constants;
import com.waffer.webapp.Utils.DatabaseUtil;
import com.waffer.webapp.activities.NavigationDrawerActivity;
import com.waffer.webapp.adapters.MyInboxAdapter;
import com.waffer.webapp.chat.ChatActivity;

import java.util.ArrayList;
import java.util.List;

import static com.waffer.webapp.chat.ChatActivity.THREAD_CHILD;
import static com.waffer.webapp.chat.ChatActivity.USERS_CHILD;

/**
 * Created by Tregix on 2/21/2018.
 */

public class MyInboxFragment extends BaseFragment {

    private ShimmerRecyclerView recyclerView;
    private TextView noData;
    List<String> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_provider_list, container, false);

        ((NavigationDrawerActivity) getActivity()).getSupportActionBar().setTitle("Inbox");

        recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        noData = view.findViewById(R.id.list_no_data);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://waffer-7ecf5-default-rtdb.firebaseio.com/").getReference()
                .child(USERS_CHILD).child(DatabaseUtil.getInstance().getUserID()+"").child(THREAD_CHILD);
        
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    list.add(ds.getKey());
                }

                if(list != null && list.size() > 0) {
                    recyclerView.setAdapter(new MyInboxAdapter(list, MyInboxFragment.this));
                }else {
                    recyclerView.setAdapter(null);
                    noData.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                recyclerView.setAdapter(null);
                noData.setVisibility(View.VISIBLE);
            }
        });

        return view;
    }


    @Override
    public void onUserMessageSelection(String path, int pos) {
        super.onUserMessageSelection(path, pos);
        String id = DatabaseUtil.getInstance().getUserID() +"";
        Bundle bundle = new Bundle();
        if(path.contains(id)) {
            String providerID = AppUtils.getProviderId(path, id);
            if(!providerID.isEmpty()) {
                bundle.putInt(Constants.ID, Integer.parseInt(providerID));
            }else{
                bundle.putInt(Constants.ID, Integer.parseInt(id));
            }
        }

        openAcitivty(bundle, ChatActivity.class);
    }


}
