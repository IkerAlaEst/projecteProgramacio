package com.projecte.entitats;

import java.io.Serializable;
import java.util.ArrayList;

public class Director implements Serializable{
    private String nom;
    private ArrayList<Pelicula> obres;

    public Director(String nom, ArrayList<Pelicula> obres) {
        this.nom = nom;
        this.obres = obres;
    }
}