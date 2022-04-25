package com.example.gestion.produits;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gestion.produits.db.Database;

public class DetailProduit extends AppCompatActivity {

    EditText nomProduit;
    EditText prixProduit;
    EditText quantiteProduit;
    EditText familleProduit;

    Intent intent;

    Button update;
    Button delete;

    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produit);

        nomProduit = findViewById(R.id.edit_nom);
        prixProduit = findViewById(R.id.edit_prix);
        quantiteProduit = findViewById(R.id.edit_quantite);
        familleProduit = findViewById(R.id.edit_famille);

        update = findViewById(R.id.btn_edit);
        delete = findViewById(R.id.btn_delete);

        db = new Database(this);

        intent = getIntent();

        printDetails();

        updateData();

        deleteData();

    }

    public void printDetails(){
        nomProduit.setText(intent.getStringExtra("produitName"));
        prixProduit.setText(intent.getStringExtra("produitPrice"));
        quantiteProduit.setText(intent.getStringExtra("produitQuantity"));
        familleProduit.setText(intent.getStringExtra("produitFamille"));
    }

    public void updateData(){
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = db.updateData(Long.valueOf(intent.getStringExtra("produitId")), nomProduit.getText().toString(), Double.valueOf(prixProduit.getText().toString()),
                        Integer.valueOf(quantiteProduit.getText().toString()), familleProduit.getText().toString());
                if (result)
                    Toast.makeText(DetailProduit.this, "Modifications enregistrée", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void deleteData() {
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = db.deleteData(intent.getStringExtra("produitId"));
                System.out.println(result);
                if (result) {
                    openHome();
                }

            }
        });
    }

    public void openHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}