package com.example.property;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
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
    Context context =holder.itemView.getContext();
        Picasso.with(holder.itemView.getContext())
                .load(property.getImage())
                .into(holder.img);
        holder.price.setText(property.getPrice()+"$");
   // holder.img.setImageResource(property.getImage()); старое

    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context,page_property.class);
            intent.putExtra("price",holder.price.getText());
            intent.putExtra("img",holder.img.getDrawable().toString());
            intent.putExtra("obj", (Serializable) mProperty.get(position));
            holder.itemView.getContext().startActivity(intent);
        }
    });
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
        public TextView price;

        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img_property);
            price = (TextView) itemView.findViewById(R.id.txt_propertyPrice);

        }
    }
}
