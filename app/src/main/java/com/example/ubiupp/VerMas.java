package com.example.ubiupp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VerMas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VerMas extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ID = "id";
    private static final String ARG_NOMBRE = "nombre";

    // TODO: Rename and change types of parameters
    private String id;
    private String nombre;

    public VerMas() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VerMas.
     */
    // TODO: Rename and change types and number of parameters
    public static VerMas newInstance(String id, String nombre) {
        VerMas fragment = new VerMas();
        Bundle args = new Bundle();
        args.putString(ARG_ID, id);
        args.putString(ARG_NOMBRE, nombre);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getString(ARG_ID);
            nombre = getArguments().getString(ARG_NOMBRE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String data = getArguments().getString("id");
        TextView tv_nombre_edificio = getActivity().findViewById(R.id.tv_nombre_edificio);

        tv_nombre_edificio.setText(nombre);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ver_mas, container, false);
    }
}