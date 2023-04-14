package com.example.ubiupp.ui.buscar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.content.DialogInterface;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.result.ActivityResultLauncher;

import com.example.ubiupp.AulaData;
import com.example.ubiupp.BuildingData;
import com.example.ubiupp.CaptureAct;
import com.example.ubiupp.DataBaseAccess;
import com.example.ubiupp.MasActivity;
import com.example.ubiupp.R;
import com.example.ubiupp.databinding.FragmentBuscarBinding;
import com.journeyapps.barcodescanner.ScanOptions;
import com.journeyapps.barcodescanner.ScanContract;

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
    private ImageButton ib_camera;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BuscarViewModel buscarViewModel =
                new ViewModelProvider(this).get(BuscarViewModel.class);

        binding = FragmentBuscarBinding.inflate(inflater, container, false);
        View root = inflater.inflate(R.layout.fragment_buscar, container,false);
        ib_camera = root.findViewById(R.id.ib_camera);
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
        ib_camera.setOnClickListener(view -> {
            scanCode();
        });
        db.close();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void scanCode(){
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volumen arriba para activar flash");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLaucher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> barLaucher = registerForActivityResult(new ScanContract(), result->
    {
        if(result.getContents() !=null)
        {

            db.open();
            BuildingData buildingDataList = db.getBuilding(Integer.parseInt(result.getContents()));
            db.close();

            Intent intent = new Intent(getContext(), MasActivity.class);
            intent.putExtra("id",buildingDataList.getBuildingId());
            intent.putExtra("nombre",buildingDataList.getBuildingName());
            intent.putExtra("imagen",buildingDataList.getBuildingImage());
            intent.putExtra("descripcion", buildingDataList.getBuildingDescription());
            intent.putExtra("ubicacion", buildingDataList.getBuildingLocation());
            getContext().startActivity(intent);
        }
    });
}