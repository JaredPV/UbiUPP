package com.example.ubiupp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ubiupp.ui.home.HomeFragment;

public class BuildingAdapter extends RecyclerView.Adapter<BuildingAdapter.ViewHolder> {

    BuildingData[] buildingData;
    Context context;



    public BuildingAdapter(BuildingData[] buildingData, Context context) {
        this.buildingData = buildingData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.building_item_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BuildingAdapter.ViewHolder holder, int position) {
        final BuildingData buildingDataList = buildingData[position];
        System.out.print(buildingDataList);
        holder.tv_nombre.setText(buildingDataList.getBuildingName());

        holder.buildingImage.setImageResource(context.getResources().getIdentifier(buildingDataList.getBuildingImage(), "drawable", context.getPackageName()));
                holder.tv_vermas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MasActivity.class);
                intent.putExtra("id",buildingDataList.getBuildingId());
                intent.putExtra("nombre",buildingDataList.getBuildingName());
                intent.putExtra("imagen",buildingDataList.getBuildingImage());
                intent.putExtra("descripcion", buildingDataList.getBuildingDescription());
                intent.putExtra("ubicacion", buildingDataList.getBuildingLocation());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return buildingData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView buildingImage;
        TextView tv_nombre;
        TextView tv_vermas;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            buildingImage = itemView.findViewById(R.id.iv_building);
            tv_nombre = itemView.findViewById(R.id.tv_nombre);
            tv_vermas = itemView.findViewById(R.id.tv_vermas);

        }
    }
}
