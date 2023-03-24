package com.example.ubiupp.ui.buscar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ubiupp.AulaData;
import com.example.ubiupp.BuildingData;
import com.example.ubiupp.DataBaseAccess;
import com.example.ubiupp.R;
import com.example.ubiupp.databinding.FragmentBuscarBinding;

import java.util.ArrayList;
import java.util.List;

public class BuscarFragment extends Fragment {

    private FragmentBuscarBinding binding;
    private SearchView sv_buscador;
    private ListView lv_aulas;
    private List<AulaData> aulaDataList;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter aulaDataAdapter;
    private DataBaseAccess db=null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BuscarViewModel buscarViewModel =
                new ViewModelProvider(this).get(BuscarViewModel.class);

        binding = FragmentBuscarBinding.inflate(inflater, container, false);
        View root = inflater.inflate(R.layout.fragment_buscar, container,false);
        sv_buscador = root.findViewById(R.id.sv_buscar);
        lv_aulas = root.findViewById(R.id.lv_aulas);
        db = DataBaseAccess.getInstance(container.getContext());
        db.open();
        aulaDataList = db.buscarAula();
        for (int i = 0; i < aulaDataList.size(); i++) {
            arrayList.add(aulaDataList.get(i).getAula());
        }
        aulaDataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arrayList);
        lv_aulas.setAdapter(aulaDataAdapter);
        sv_buscador.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                aulaDataAdapter.getFilter().filter(s);
                return false;
            }
        });
        db.close();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}