package com.projecte.entitats;

import java.io.Serializable;
import java.util.ArrayList;

public class Pelicula implements Serializable{
    private String nom;
    private ArrayList<Actor> actors;
    private Director director;

    public Pelicula(String nom, ArrayList<Actor> actors, Director director) {
        this.nom = nom;
        this.actors = actors;
        this.director = director;
    }

    public String getNom() {
        return nom;
    }
    public ArrayList<Actor> getActors() {
        return actors;
    }
    public Director getDirector() {
        return director;
    }
}