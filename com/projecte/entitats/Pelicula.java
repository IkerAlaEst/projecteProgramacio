package com.projecte.entitats;

import java.io.Serializable;
import java.util.ArrayList;

public class Pelicula implements Serializable, ListItem {
    private int id;
    private String nom;
    private ArrayList<Actor> actors;
    private Director director;
    private int anyPublicació;

    public Pelicula(String nom, ArrayList<Actor> actors, Director director, int anyPublicació, int id) {
        this.id = id;
        this.nom = nom;
        this.actors = actors;
        this.director = director;
        this.anyPublicació = anyPublicació;
    }

    public Pelicula(String nom, int id) {
        this.id = id;
        this.nom = nom;
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
    public int getAnyPublicació() {
        return anyPublicació;
    }

    public void mostrarDetalles() {
        System.out.println("Pelicula " + nom + ":");
        System.out.println("- Any de publicació: " + anyPublicació);
        System.out.println("- Director: " + director.getNom());
        System.out.println("- Actors: \n" + nomsActors());
    }

    private String nomsActors() {
        String cadena = "";
        for (Actor a : actors) {
            cadena += ("· " + a.getNom() + "\n");
        }
        return cadena;
    }

    public int getId() {
        return id;
    }
}