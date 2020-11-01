package com.example.recycleview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class CreateProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);

        Spinner spinner = findViewById(R.id.spinner);
        SpinnerAdapter adapter = new SpinnerAdapter(getIds());
        spinner.setAdapter(adapter);

        EditText name = findViewById(R.id.editTextName);
        EditText description = findViewById(R.id.editTextDescription);
        EditText price = findViewById(R.id.editTextPrice);

        Button createButton = findViewById(R.id.createButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                int position = spinner.getSelectedItemPosition();

                intent.putExtra("new_image", (int) adapter.getItem(position));
                intent.putExtra("new_name", name.getText().toString());
                intent.putExtra("new_description", description.getText().toString());
                intent.putExtra("new_price", Double.parseDouble(price.getText().toString()));

                setResult(Activity.RESULT_OK, intent);
                onBackPressed();
            }
        });
    }

    private ArrayList<Integer> getIds(){
        ArrayList<Integer> ids = new ArrayList<>();

        ids.add(R.drawable.sushi);

        return ids;
    }
}