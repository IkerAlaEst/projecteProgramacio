package com.projecte.entitats;

import java.io.Serializable;
import java.util.ArrayList;

public class Actor implements Serializable{
    private String nom;
    private ArrayList<Pelicula> peliculesActuatEn;

    public Actor(String nom, ArrayList<Pelicula> peliculesActuatEn) {
        this.nom = nom;
        this.peliculesActuatEn = peliculesActuatEn;
    }
}