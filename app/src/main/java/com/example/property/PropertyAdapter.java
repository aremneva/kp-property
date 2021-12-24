package com.example.property;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PropertyAdapter extends
        RecyclerView.Adapter<PropertyAdapter.ViewHolder> {
    private List<Property> mProperty;
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View propView = inflater.inflate(R.layout.fragment_property_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(propView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Property property = mProperty.get(position);

        Picasso.with(holder.itemView.getContext())
                .load(property.getImage())
                .into(holder.img);
   // holder.img.setImageResource(property.getImage()); старое
    }

    public PropertyAdapter(List<Property> properties) {
        mProperty=properties;
    }

    @Override
    public int getItemCount() {
        return mProperty.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img_property);

        }
    }
}
