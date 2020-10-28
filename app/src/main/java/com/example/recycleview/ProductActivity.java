package com.example.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Intent intent = getIntent();

        ImageView image = findViewById(R.id.productImage);
        image.setImageResource(intent.getIntExtra("imageId", 0));

        TextView nameView = findViewById(R.id.productName);
        nameView.setText(intent.getStringExtra("name"));

        TextView descriptionView = findViewById(R.id.productDescription);
        descriptionView.setText(intent.getStringExtra("description"));

        TextView priceView = findViewById(R.id.productPrice);
        priceView.setText("Price: " + intent.getDoubleExtra("price", 0) + " KZT");

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}