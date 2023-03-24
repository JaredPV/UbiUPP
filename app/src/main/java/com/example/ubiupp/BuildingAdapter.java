package com.example.ubiupp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        holder.tv_nombre.setText(buildingDataList.getBuildingName());
        holder.buildingImage.setImageResource(buildingDataList.getBuildingImage());

        holder.tv_vermas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, buildingDataList.getBuildingId(), Toast.LENGTH_SHORT).show();
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
