package com.example.recycleview;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static List<Product> products = new ArrayList<>();

    public static void addProduct(Product product){
        products.add(product);
    }

    public static double getCost(){
        double cost = 0;
        for(Product product : products){
            cost += product.getPrice();
        }

        return cost;
    }
}
