package com.aoslec.contactproject.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.aoslec.contactproject.Adapter.GroupAdapter;
import com.aoslec.contactproject.Bean.People;
import com.aoslec.contactproject.NetworkTask.NetworkTask;
import com.aoslec.contactproject.R;
import com.aoslec.contactproject.Utill.Share;

import java.util.ArrayList;


public class GroupFragment extends Fragment {

    ArrayList<People> people;
    GroupAdapter adapter;
    ListView listView;
    TextView tvEmpty;

    Share share = new Share();
    private String uEmail,url,urlAddr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group, container, false);

        uEmail = share.sEmail;
        url = share.sUrl;

        listView = view.findViewById(R.id.group_list);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        urlAddr = url +"group.jsp?email=" + uEmail;
        Log.v("ggg","group " + urlAddr );
        connectGroupData();

    }

    private void connectGroupData() {
        try {
            Log.v("ggg","group data");
            NetworkTask networkTask = new NetworkTask(getActivity(), urlAddr,"group");
            Object obj = networkTask.execute().get();
            people = (ArrayList<People>) obj;

            Log.v("ggg","group people");

            adapter = new GroupAdapter(getContext(),R.layout.group_list, people);
            listView.setAdapter(adapter);

            Log.v("ggg","group listView");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}//==