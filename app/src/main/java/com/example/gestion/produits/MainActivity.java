package com.example.gestion.produits;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gestion.produits.db.Database;
import com.example.gestion.produits.model.Produit;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button button;

    List<Produit> produits;
    ArrayAdapter adapter;

    Database db;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new Database(this);

        button = (Button) findViewById(R.id.new_product);
        listView = findViewById(R.id.produit_list);
        produits = new ArrayList<>();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewProduit();
            }
        });

        viewData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Produit produit = (Produit) listView.getItemAtPosition(i);
                viewDetails(produit);
            }
        });
    }

    public void openNewProduit() {
        Intent intent = new Intent(this, NewProduit.class);
        startActivity(intent);
    }

    public void viewData() {
        Cursor cursor = db.viewData();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Aucun enregistrement", Toast.LENGTH_LONG).show();
        } else {
            while (cursor.moveToNext()) {
                Produit produit = new Produit();
                produit.setId(cursor.getInt(0));
                produit.setNom(cursor.getString(1));
                produit.setPrix(cursor.getDouble(2));
                produit.setQuantite(cursor.getInt(3));
                produit.setFamille(cursor.getString(4));

                produits.add(produit);
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, produits);
            listView.setAdapter(adapter);
        }
    }

    public void viewDetails(Produit produit) {
        Intent intent = new Intent(this, DetailProduit.class);
        intent.putExtra("produitName", produit.getNom());
        intent.putExtra("produitPrice", String.valueOf(produit.getPrix()));
        intent.putExtra("produitFamille", produit.getFamille());
        intent.putExtra("produitQuantity", String.valueOf(produit.getQuantite()));
        intent.putExtra("produitId", String.valueOf(produit.getId()));

        startActivity(intent);
    }

}