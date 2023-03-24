package com.example.ubiupp.ui.home;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ubiupp.BuildingAdapter;
import com.example.ubiupp.BuildingData;
import com.example.ubiupp.R;
import com.example.ubiupp.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = inflater.inflate(R.layout.fragment_home, container,false);

        RecyclerView recyclerView = root.findViewById(R.id.rv_buildings);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        BuildingData[] buildingData = new BuildingData[]{
                new BuildingData("Docencia I","1",R.drawable.docencia1),
                new BuildingData("CMI", "2",R.drawable.cmi),
                new BuildingData("LT2","3", R.drawable.lt2),
                new BuildingData("Docencia II","4", R.drawable.tdb),
                new BuildingData("Rectoría", "5", R.drawable.tdb),
                new BuildingData("Biblioteca", "6", R.drawable.tdb)
        };

        BuildingAdapter buildingAdapter = new BuildingAdapter(buildingData,getContext());
        recyclerView.setAdapter(buildingAdapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}