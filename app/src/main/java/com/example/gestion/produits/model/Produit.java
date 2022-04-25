package com.example.gestion.produits.model;

public class Produit {
    private long id;
    private String nom;
    private double prix;
    private int quantite;
    private String famille;

    public Produit() {
    }

    public Produit(long id, String nom, double prix, int quantite, String famille) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
        this.famille = famille;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getFamille() {
        return famille;
    }

    public void setFamille(String famille) {
        this.famille = famille;
    }

    @Override
    public String toString() {
        return nom;
    }
}
