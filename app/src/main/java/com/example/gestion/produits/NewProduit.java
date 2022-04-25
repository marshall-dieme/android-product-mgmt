package com.example.gestion.produits;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gestion.produits.db.Database;

public class NewProduit extends AppCompatActivity {

    Database db;
    EditText name;
    EditText price;
    EditText quantity;
    EditText famille;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_produit);
        db = new Database(this);
        findIds();
        insertData();
    }

    public void findIds() {
        name = findViewById(R.id.product_name);
        price = findViewById(R.id.product_price);
        quantity = findViewById(R.id.product_quantity);
        famille = findViewById(R.id.product_family);
        save = (Button) findViewById(R.id.save_produit);
    }

    public void insertData() {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = db.insertData(name.getText().toString(), Double.valueOf(price.getText().toString()), Integer.valueOf(quantity.getText().toString()), famille.getText().toString());

                if (result) {
                    Toast.makeText(NewProduit.this, "Produit ajouté", Toast.LENGTH_SHORT).show();

                    name.setText("");
                    price.setText("");
                    quantity.setText("");
                    famille.setText("");
                }
            }
        });
    }
}