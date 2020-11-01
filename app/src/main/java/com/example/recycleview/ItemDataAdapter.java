package com.example.recycleview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ItemDataAdapter extends RecyclerView.Adapter<ItemDataAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<Product> products;

    ItemDataAdapter(Context context, List<Product> products){
        this.context = context;
        this.products = products;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.recycle_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.imageView.setImageResource(product.getImage());
        holder.nameView.setText(product.getName());
        holder.descriptionView.setText(product.getDescription());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductActivity.class);

                intent.putExtra("imageId", product.getImage());
                intent.putExtra("name", product.getName());
                intent.putExtra("description", product.getDescription());
                intent.putExtra("price", product.getPrice());

                context.startActivity(intent);
            }
        });

        holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Cart.addProduct(product);

                Toast toast = new Toast(context);
                toast.setText("Added");
                toast.show();

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void addProduct(Product product){
        products.add(product);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView nameView;
        final TextView descriptionView;
        final LinearLayout layout;
        ViewHolder(View view){
            super(view);
            imageView = view.findViewById(R.id.image);
            nameView = view.findViewById(R.id.name);
            descriptionView = view.findViewById(R.id.description);
            layout = view.findViewById(R.id.mainLayout);
        }
    }
}
