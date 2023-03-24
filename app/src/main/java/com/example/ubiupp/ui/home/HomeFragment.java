package com.example.ubiupp.ui.home;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ubiupp.BuildingAdapter;
import com.example.ubiupp.BuildingData;
import com.example.ubiupp.DataBaseAccess;
import com.example.ubiupp.R;
import com.example.ubiupp.VerMas;
import com.example.ubiupp.databinding.FragmentHomeBinding;

import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private DataBaseAccess db = null;
    private FragmentActivity fragmentActivity;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = inflater.inflate(R.layout.fragment_home, container,false);
        db = DataBaseAccess.getInstance(container.getContext());
        RecyclerView recyclerView = root.findViewById(R.id.rv_buildings);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        db.open();
        List<BuildingData> buildingDataList = db.getBuildings();

        BuildingData[] buildingData = new BuildingData[buildingDataList.size()];


        for (int i = 0; i < buildingDataList.size(); i++) {
            buildingData[i] = new BuildingData(buildingDataList.get(i).getBuildingName(),buildingDataList.get(i).getBuildingId(),buildingDataList.get(i).getBuildingImage(),buildingDataList.get(i).getBuildingDescription(),buildingDataList.get(i).getBuildingLocation());
        }
        db.close();
        fragmentActivity = getActivity();
        BuildingAdapter buildingAdapter = new BuildingAdapter(buildingData,getActivity());
        recyclerView.setAdapter(buildingAdapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    public void vermas(String id,String nombre) {
        VerMas verMas = new VerMas();
        verMas.newInstance(id,nombre);
        if(fragmentActivity!=null) {
            FragmentTransaction fragmentTransaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.nav_host_fragment_activity_main, verMas);
            fragmentTransaction.commit();
        }

    }
}