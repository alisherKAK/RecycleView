package com.example.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Product> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        setData();

        RecyclerView recyclerView = findViewById(R.id.recycleItems);
        recyclerView.setAdapter(new ItemDataAdapter(this, products));
    }

    private void setData(){
        products.add(new Product(R.drawable.sushi, "Sushi", "Japanese food", 800.00));
    }
}