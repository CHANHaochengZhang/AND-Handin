package com.example.footprint.UI;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footprint.Model.City;
import com.example.footprint.R;

import java.util.ArrayList;
import java.util.List;

public class AddAdapter extends RecyclerView.Adapter<AddAdapter.AddHolder> {

    private List<City> cities = new ArrayList<>();

    @NonNull
    @Override
    public AddHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_item, parent, false);
        return new AddHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AddHolder holder, int position) {
        City current = cities.get(position);
        holder.cityText.setText(current.getCityName());
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public void setCityNames(List<City>  cities){
        this.cities = cities;
        notifyDataSetChanged();
    }

    class AddHolder extends RecyclerView.ViewHolder {
        private TextView cityText;

        public AddHolder(@NonNull View itemView) {
            super(itemView);
            cityText = itemView.findViewById(R.id.text_city);
        }
    }

    public City getCityAt(int position){
        return cities.get(position);
    }
}
