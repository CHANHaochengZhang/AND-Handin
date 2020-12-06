package com.example.footprint.UI;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.footprint.Model.City;
import com.example.footprint.Model.PhotoResponse;
import com.example.footprint.R;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder> {

    private List<City> cities = new ArrayList<>();
    private ArrayList<String> urls = new ArrayList<>();
    private Context mContet;
    RequestOptions options;

    public MainAdapter(Context mContext) {
        this.mContet = mContext;
        options = new RequestOptions()
                .centerCrop();

        urls.add("https://images.unsplash.com/photo-1578907780270-33f7b8b415b1?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MXwxODg0MDB8MHwxfHNlYXJjaHwxfHx8ZW58MHx8fA&ixlib=rb-1.2.1&q=80&w=400");

        urls.add("https://images.unsplash.com/photo-1510514837604-dbe585413c5e?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MXwxODg0MDB8MHwxfHNlYXJjaHwxfHx8ZW58MHx8fA&ixlib=rb-1.2.1&q=80&w=400");

        urls.add("https://images.unsplash.com/photo-1530959106156-50f49c30932d?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MXwxODg0MDB8MHwxfHNlYXJjaHwxfHx8ZW58MHx8fA&ixlib=rb-1.2.1&q=80&w=400");

        urls.add("https://images.unsplash.com/photo-1477959858617-67f85cf4f1df?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max");
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cityimage_item, parent, false);
        return new MainHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainHolder holder, int position) {
        City current = cities.get(position);
        holder.cityText.setText(current.getCityName());

       Glide.with(mContet).load(urls.get(position)).apply(options).into(holder.cityImg);

    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public void setCityNames(List<City> cities) {
        this.cities = cities;
        notifyDataSetChanged();
    }

    class MainHolder extends RecyclerView.ViewHolder {
        private TextView cityText;
        private ImageView cityImg;

        public MainHolder(@NonNull View itemView) {
            super(itemView);
            cityText = itemView.findViewById(R.id.text_view_city);
            cityImg = itemView.findViewById(R.id.image_view);
        }
    }
}
