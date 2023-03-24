package com.example.ubiupp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MasActivity extends AppCompatActivity {
    private TextView tv_nombre, tv_descripcion, tv_lista;
    private ImageView iv_back, iv_edificio;
    private DataBaseAccess db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mas);
        db = DataBaseAccess.getInstance(this);

        tv_nombre = findViewById(R.id.tv_nombre_edificio);
        tv_descripcion = findViewById(R.id.tv_descripcion_edificio);
        tv_lista = findViewById(R.id.tv_aulas);
        iv_back = findViewById(R.id.iv_back);
        iv_edificio = findViewById(R.id.iv_vermas);
        Intent intent = getIntent();
        tv_nombre.setText(intent.getStringExtra("nombre"));
        tv_descripcion.setText(intent.getStringExtra("descripcion"));
        iv_edificio.setImageResource(getResources().getIdentifier(intent.getStringExtra("imagen"),"drawable",getPackageName()));
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MasActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        db.open();

        List<AulaData> aulaDataList = db.getAula(Integer.parseInt(intent.getStringExtra("id")));

        db.close();
        tv_lista.setText(aulaDataList.get(0).getAula());
        for (int i = 1; i <aulaDataList.size() ; i++) {
            tv_lista.setText(tv_lista.getText()+"\n\n"+aulaDataList.get(i).getAula());

        }
    }
}