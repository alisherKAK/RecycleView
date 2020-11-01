package com.example.recycleview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Product> products = new ArrayList<>();
    private NotificationManager notificationManager;
    private ItemDataAdapter adapter;

    private final String CHANNEL_ID = "CHANNEL_1";
    private int NOTIFY_ID = 101;

    private int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        
        setData();

        RecyclerView recyclerView = findViewById(R.id.recycleItems);
        adapter = new ItemDataAdapter(this, products);
        recyclerView.setAdapter(adapter);
    }

    private void setData(){
        products.add(new Product(R.drawable.sushi, "Sushi", "Japanese food", 800.00));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.cart){
            Snackbar snackbar = Snackbar.make(findViewById(R.id.recycleItems), "To pay: " + Cart.getCost(), Snackbar.LENGTH_LONG);

            snackbar.setAction("Pay", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        NotificationChannel channel = new NotificationChannel(
                                CHANNEL_ID, "FirstChannel", NotificationManager.IMPORTANCE_HIGH);

                        notificationManager.createNotificationChannel(channel);
                    }

                    String text = "Cost: " + Cart.getCost() + "\n";

                    DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                    text += "Date: " + format.format(Calendar.getInstance().getTime());

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID);
                    builder
                            .setSmallIcon(R.drawable.ic_cart)
                            .setContentTitle("Bill")
                            .setContentText(text)
                            .setAutoCancel(true);

                    notificationManager.notify(NOTIFY_ID, builder.build());
                    NOTIFY_ID++;
                }
            });
            snackbar.show();
        }
        else if(item.getItemId() == R.id.create_product){
            Intent intent = new Intent(MainActivity.this, CreateProductActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            int imageId = data.getIntExtra("new_image", R.drawable.sushi);
            String name = data.getStringExtra("new_name");
            String description = data.getStringExtra("new_description");
            double price = data.getDoubleExtra("new_price", 0);

            adapter.addProduct(new Product(imageId, name, description, price));
        }
    }
}